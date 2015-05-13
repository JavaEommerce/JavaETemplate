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
 * Servlet implementation class Revision
 */
@WebServlet("/Revision")
public class Revision extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Revision() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
		String revision = request.getParameter("revision");
		String articleName = request.getParameter("artcileNN");
		System.out.println(revision);
		if (reviewer!=null&&!revision.equalsIgnoreCase("")) {
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
			
			
			if (revision.equalsIgnoreCase("Accept")) {
				// accept : set AuthorReviewer reviseaccepted 1 & reviseinfo="" & revisetime +1
				System.out.println(revision);
				try {
					accpetR(articleName, reviewer, con);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// redirect to reviewer centre
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
		        PrintWriter out= response.getWriter();
		        out.println("<font color=red>Operation successful </font>");
		        rd.forward(request, response);
				
			}
			else if (revision.equalsIgnoreCase("Reject")) {
				// reject : set AuthorReviewer reviseaccepted 0 & reviseinfo="" & revisetime +1
				System.out.println(revision);
				try {
					RejectR(articleName, reviewer, con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// redirect to reviewer centre
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
		        PrintWriter out= response.getWriter();
		        out.println("<font color=red>Operation successful </font>");
		        rd.forward(request, response);
			}
			else {
				System.out.println("error");
				// redirect to reviewer centre
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
		        PrintWriter out= response.getWriter();
		        out.println("<font color=red>error. </font>");
		        rd.forward(request, response);
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

	
	private void accpetR(String articleName, Reviewer reviewer,Connection con) throws  ServletException,SQLException {
		
		PreparedStatement ps = null;
        try {
        	String authorName = getAuthorName(con, articleName);
        	int reviseTime = getReviseTime(con, authorName, reviewer);
        	ps = con.prepareStatement("update AuthorReviewer set reviseinfo=?, reviseaccepted=1 "
        			+ ", revisetime=? where authorname=? and reviewername=? ");
        	ps.setString(1, "");
        	ps.setInt(2, reviseTime+1);
        	ps.setString(3, authorName);
        	ps.setString(4, reviewer.getReviewerName());
	    	ps.execute();
	    	System.out.println("8888888888888888888");
		
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }
	}
	
	private void RejectR(String articleName, Reviewer reviewer,Connection con) throws  ServletException,SQLException {
			
		PreparedStatement ps = null;
        try {
        	String authorName = getAuthorName(con, articleName);
        	int reviseTime = getReviseTime(con, authorName, reviewer);
        	ps = con.prepareStatement("update AuthorReviewer set reviseinfo=?, reviseaccepted=0 "
        			+ ", revisetime=? where authorname=? and reviewername=? ");
        	ps.setString(1, "");
        	ps.setInt(2, reviseTime+1);
        	ps.setString(3, authorName);
        	ps.setString(4, reviewer.getReviewerName());
	    	ps.execute();
		
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
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
	
	private int getReviseTime(Connection con, String authorName, Reviewer reviewer) throws SQLException {
		
		int reviseTime = 0;
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement("select revisetime from AuthorReviewer where reviewername=? and "
				+ "authorname=? ");
		ps.setString(1, reviewer.getReviewerName());
		ps.setString(2, authorName);
		rs = ps.executeQuery();
		if (rs!=null&&rs.next()) {
			reviseTime = rs.getInt("revisetime");
		}
		return reviseTime;
	}
	
}
