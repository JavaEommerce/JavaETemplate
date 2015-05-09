package reviewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class SubmitReview
 */
@WebServlet("/SubmitReview")
public class SubmitReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String articleName = null;
	private String overallJudgement = null;
	private String level = null;
	private String summary = null;
	private String criticism = null;
	private String smallerrors = null;
	private String errorInfo = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO insert info into db AuthorReviewer
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
		articleName = request.getParameter("articleName");
		overallJudgement = request.getParameter("overallJudgement");
		level = request.getParameter("level");
		summary = request.getParameter("summary");
		criticism = request.getParameter("criticism");
		smallerrors = request.getParameter("smallerrors");
		errorInfo = null;
		System.out.println("Review Form data--------------------");
		System.out.println(articleName);
		System.out.println(overallJudgement);
		System.out.println(level);
		System.out.println(summary);
		System.out.println(criticism);
		System.out.println(smallerrors);
		System.out.println("Review Form data end-----------------");
		
		if (articleName==null||articleName.equalsIgnoreCase("--no available article--")) {
			errorInfo="You dont have any article to review";
		} 
		if (overallJudgement==null) {
			errorInfo="Please enter your overall judgement";
		}
		if (level.equalsIgnoreCase("--please select--")) {
			errorInfo="Please enter your relevant expertise level";
		}
		if (summary==null) {
			errorInfo="Please enter your relevant summary";
		}
		if (criticism==null) {
			errorInfo="Please enter your relevant criticism";
		}
		if (smallerrors==null) {
			errorInfo="Please enter your smallerrors";
		}
		if (errorInfo!=null) {
			// redirect to login page
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SubmitReview.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>"+errorInfo+"</font>");
	        rd.include(request, response);
		}
		
		if (reviewer!=null) {
			// connect db------------------------------------------
			Dbconnection db=null;
			try {
				db = new Dbconnection();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Connection con = db.getConnection();
			
			if (con==null) {
				System.out.println("it's closed!");
			}
			else{
				System.out.println("successfullllllll");
			}// end of connecting db--------------------------------

			
			// logic part**********************************************************************/
			try {
				String aurhorName = getAuthorName(con, articleName);
				if (aurhorName!=null) {
					// insert info into AuthorArticle table
					insertIntoAuthorReviewer(aurhorName, reviewer, con);
					// update reviewer's review status
					updateReviewStatus(articleName, reviewer, con);
				}
				else {
					System.out.println("???");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else {
			// redirect to login page
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>please login</font>");
	        rd.include(request, response);
		}
		
	}
	
	private String getAuthorName(Connection con, String articleName) throws SQLException {
			
		String authorName=null;
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement("select authorname from AuthorArticle where articlename=? ");
		ps.setString(1, articleName);
		rs = ps.executeQuery();
		if (rs!=null&&rs.next()) {
			authorName = rs.getString("authorname");
		}
		return authorName;
	}
	
	private void insertIntoAuthorReviewer(String authorName, Reviewer reviewer,Connection con) throws ServletException, SQLException, IOException {
		
		PreparedStatement ps = null;
		
        try {
        	
        	ps = con.prepareStatement("insert into AuthorReviewer(reviewername,authorname,"
        			+ "reviseinfo,reviseaccepted,revisetime,overalljudgement,reviewerlevel,"
        			+ "summary,criticism,smallerrors) values (?,?,?,?,?,?,?,?,?,?) ");
        	ps.setString(1, reviewer.getReviewerName());
        	ps.setString(2, authorName);
        	ps.setString(3, null);
        	ps.setInt(4, 0);
        	ps.setInt(5, 0);
        	ps.setString(6, overallJudgement);
        	ps.setString(7, level);
        	ps.setString(8, summary);
        	ps.setString(9, criticism);
        	ps.setString(10, smallerrors);
        	ps.execute();
		
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }
	}
	
	private void updateReviewStatus(String articleName, Reviewer reviewer,Connection con) throws  ServletException,SQLException {
			
			PreparedStatement ps = null;
	        try {
	        	
	        	ps = con.prepareStatement("update ArticleReview set reviewstatus='review submitted' where articlename=? and reviewername=? ");
	        	ps.setString(1, articleName);
	        	ps.setString(2, reviewer.getReviewerName());
	        	ps.execute();
			
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ServletException("DB Connection problem.");
	        }
		}
	
	}