package reviewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class ReviewerLogin
 */
@WebServlet("/ReviewerLogin")
public class ReviewerLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> chosenArticles = new ArrayList<String>();
	private ArrayList<ReviewingArticle> reviewingArticles = new ArrayList<ReviewingArticle>();
	//private ArticleBag articleBag = new ArticleBag();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewerLogin() {
        super();
        // TODO check if the client is login and if the client is a valid reviewer
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO identify user id and create instance of info displayed
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
		if (reviewer==null) {
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>please login</font>");
	        rd.include(request, response);
	        
		} else {
			
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
			
			
			
			/*create article instances************************************************************/
			try {
				boolean c = getChosenArticles(request, response, reviewer, con);
				boolean r = getReviewingArticles(request, response, reviewer, con);
				if (c&&r) {
					System.out.println("initialize successfully");
					session.setAttribute("ChosenArticles", chosenArticles);
					session.setAttribute("reviewingArticles", reviewingArticles);
					
				}
				else {
					System.out.println("initialize failure");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerCentre.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=green>hello "+reviewer.getReviewerName()+"</font>");
	        rd.include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
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
				System.out.println("db connection successfullllllll");
			}// end of connecting db--------------------------------
			
			
			// recerive posting info. from PendingArticles.jsp*************************************************/
			String formName = request.getParameter("forms");
			if (formName!=null&&formName.equalsIgnoreCase("valid")) {
				handlePendingForm(request, response, session, reviewer, con, formName);
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

	private void handlePendingForm(HttpServletRequest request, HttpServletResponse response,HttpSession session,Reviewer reviewer,Connection con,String formName) throws ServletException, IOException {
		
		System.out.println("updating chosen articles----------------------------");
		System.out.println("source: "+formName);
		
		// update selected articles
		String[] selectedTitles=request.getParameterValues("pendingArticles");
		if (selectedTitles!=null) {
			if (selectedTitles.length<=3-reviewer.getSelectedNum()) {
				for (String title : selectedTitles) {
					try {
						boolean a = updateChosenArticle(request, response, title, reviewer, con);
						reviewer.addSelectedNum1();
						boolean b = updateReviewer(request, response, reviewer, con);
						if (a&&b) {
							System.out.println("selected article: "+title);
						}
						else {
							System.out.println("error");
						}
						// redirect to centre
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerCentre.jsp");
				        PrintWriter out= response.getWriter();
				        out.println("<font color=red>selection successful</font>");
				        rd.include(request, response);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.toString();
					}
				}
				session.setAttribute("Reviewer", reviewer);
			}
			else {
				System.out.println("You have chosen enough articles");
				// didnt choose any article, back to 
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerCentre.jsp");
		        PrintWriter out= response.getWriter();
		        out.println("<font color=red>You have chosen enough articles</font>");
		        rd.include(request, response);
			}
			
		}
		else {
			System.out.println("Didn't choose any article");
			// didnt choose any article, back to 
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/pendingArticles.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>Didn't choose any article</font>");
	        rd.include(request, response);
		}
		
		
	}
	
	
	private boolean updateChosenArticle(HttpServletRequest request, HttpServletResponse response,String title,Reviewer reviewer,Connection con) throws ServletException, SQLException, IOException {
			
			boolean result = false;
			PreparedStatement psLookup = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
	        try {
	        	psLookup = con.prepareStatement("select articlename,reviewername from ArticleReview where articlename=? ");
	        	psLookup.setString(1, title);
	        	rs = psLookup.executeQuery();
	        	int count = 0;
	        	boolean self = true;
	        	String name="";
	            while (rs.next()) {
					count++;
					name = rs.getString("reviewername");
					if (name.equals(reviewer.getReviewerName())) {
						self=false;
					}
				}
	            if (self&&(count<=5)) {
	            	ps = con.prepareStatement("insert into ArticleReview(reviewername,articlename,reviewstatus) values (?,?,?) ");
		        	ps.setString(1, reviewer.getReviewerName());
		        	ps.setString(2, title);
		        	ps.setString(3, "selected");
		        	ps.execute();
		        	result=true;
				}
	            else {
	            	System.out.println(name+" is already taken by someone, please change another one");
					// didnt choose any article, back to 
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/pendingArticles.jsp");
			        PrintWriter out= response.getWriter();
			        out.println("<font color=red>"+name+" is already taken by someone, please change another one</font>");
			        rd.include(request, response);
				}
	        	
	          
	          return result;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ServletException("DB Connection problem.");
	        }
	}
		
	private boolean updateReviewer(HttpServletRequest request, HttpServletResponse response,Reviewer reviewer,Connection con) throws ServletException, SQLException, IOException {
		
		boolean result = false;
		PreparedStatement ps = null;
		
        try {
        	
        	ps = con.prepareStatement("update Reviewer set selectednum=? where reviewername=? ");
        	ps.setInt(1, reviewer.getSelectedNum());
        	ps.setString(2, reviewer.getReviewerName());
        	ps.execute();
        	result=true;
			
          return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }
	}
	
	private boolean getChosenArticles(HttpServletRequest request, HttpServletResponse response,Reviewer reviewer,Connection con) throws ServletException, SQLException, IOException {
		
		boolean result = false;
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement("select articlename from ArticleReview where reviewername=? and reviewstatus=? ");
		ps.setString(1, reviewer.getReviewerName());
		ps.setString(2, "selected");
		rs = ps.executeQuery();
		if (rs!=null) {
			while (rs.next()) {
				String articleName = rs.getString("articlename");
				chosenArticles.add(articleName);
			}
			result=true;
		}
		return result;
	}
	
	private boolean getReviewingArticles(HttpServletRequest request, HttpServletResponse response,Reviewer reviewer,Connection con) throws ServletException, SQLException, IOException {
		
		boolean result = false;
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement("select Article.url,ArticleReview.articlename,ArticleReview.reviewstatus "
				+ "from ArticleReview,Article where reviewername=? and not reviewstatus=? "
				+ "and Article.articlename=ArticleReview.articlename");
		ps.setString(1, reviewer.getReviewerName());
		ps.setString(2, "selected");
		rs = ps.executeQuery();
		if (rs!=null) {
			String articleName=null;
			String url = null;
			String reviewStatus = null;
			while (rs.next()) {
				articleName = rs.getString("articlename");
				url = rs.getString("url");
				reviewStatus = rs.getString("reviewstatus");
				ReviewingArticle ra = new ReviewingArticle(articleName, reviewStatus, url);
				reviewingArticles.add(ra);
				System.out.println(ra.toString()+"--");
			}
			result=true;
		}
		return result;
	}
}
