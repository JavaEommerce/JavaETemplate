package loginSystem;

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
 * Servlet implementation class Login
 */
@WebServlet(name = "loginSystem.LoginServlet", urlPatterns = { "/LoginServlet" })
//@WebServlet(name = "loginSystem.LoginServlet", urlPatterns = { "/login" })

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO verify the identity of client
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String errorMessage = null;
		// verifying 
		if (userName.equals("")||userName==null) {
			errorMessage="userName is empty";
		}
		if (password.equals("")||password==null) {
			errorMessage="password is empty";
		}
		if (errorMessage!=null) {
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMessage+"</font>");
            rd.include(request, response);
			
		}
		else {
			
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
				
				ps=con.prepareStatement("select userName from User where userName=? and password=? limit 1");
				ps.setString(1, userName);
				ps.setString(2, password);
				rs = ps.executeQuery();
				
				if (rs!=null&&rs.next()) {
					
					User u = new User(rs.getString("userName"));
					log(u.toString());
					HttpSession session = request.getSession();
					session.setAttribute("User", u);
					response.sendRedirect("http://localhost:8080/JavaEE/index.jsp");
					
					
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	                PrintWriter out= response.getWriter();
	                out.println("<font color=red>User name and password didn't match,please try again. </font>");
	                out.println("<p>Don't have an account? Register here:<a href=\"http://localhost:8080/JavaEE/signup.jsp\">click me</a></p>");
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
			
			//PrintWriter out = response.getWriter();
			//out.println("<font size = '6' color = red>"+"login successfully" +"</font>");

		}
		
	}

}
