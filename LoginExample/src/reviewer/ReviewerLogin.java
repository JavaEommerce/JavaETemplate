package reviewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginSystem.User;
import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class ReviewerLogin
 */
@WebServlet("/ReviewerLogin")
public class ReviewerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
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
		// TODO identify user id
		HttpSession session = request.getSession();
		if (session.getAttribute("User")==null) {
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>please login</font>");
            out.println("<p>Don't have an account? Register here:<a href=\"http://localhost:8080/JavaEE/signup.jsp\">click me</a></p>");
            rd.include(request, response);
            
		} else {
			
			User u = (User)session.getAttribute("User");
			String reviewerName = u.getUserName();
			
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
			}
			
			PreparedStatement ps = null;
			ResultSet rs =null;
			
			try {
				
				ps=con.prepareStatement("select * from Reviewer where username=? limit 1");
				ps.setString(1, reviewerName);
				rs = ps.executeQuery();
				
				if (rs!=null&&rs.next()) {
					
					Reviewer r = new Reviewer(rs.getString("username"),rs.getInt("selectednum"),rs.getInt("ID"));
					log(r.getReviewerName());
					
					session.setAttribute("Reviewer", r);
					response.sendRedirect("http://localhost:8080/JavaEE/reviewerCentre.jsp");
					
					
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
	                PrintWriter out= response.getWriter();
	                out.println("<font color=red>Currently you are not a reviewer. If you want to become the reviewer, see the introduction below. </font>");
	                rd.include(request, response);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					ps.close();
					System.out.println("db closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
}
