package loginSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class Signup
 */
@WebServlet(name = "loginSystem.Signup", urlPatterns = { "/signup" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<font size = '6' color = red>"+"Hellor world" +"</font>");
		
		String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordVerify = request.getParameter("passwordVerify");

        
        String errorMsg = null;
        if(userName == null || userName.equals("")){
            errorMsg = "User name can't be null or empty.";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty.";
        }
        if (!password.equals(passwordVerify)) {
			errorMsg= "Password is not matched with the first";
		}
       
         
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }
        else{
         
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
			PreparedStatement psLookup = null;
			ResultSet rs =null;
			
	        try {
	            
	        	psLookup = con.prepareStatement("select userName from User where userName=? limit 1");
	        	psLookup.setString(1, userName);
	        	rs = psLookup.executeQuery();
	        	if (rs!=null&&rs.next()) {
	        		System.out.println(userName);
	        		//forward to login page to login
		            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
		            PrintWriter out= response.getWriter();
		            out.println("<font color=green>This userName is taken, please change another one.</font>");
		            rd.include(request, response);
		            
				} else {
					ps = con.prepareStatement("insert into User(id, userName, password) values (?,?,?)");
					ps.setString(1, null);
		            ps.setString(2, userName);
		            ps.setString(3, password);
		            ps.execute();
		            System.out.println("register succeed");
		          //forward to index page
		            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
		            PrintWriter out= response.getWriter();
		            out.println("<font color=green>Registration successful</font>");
		            out.println("<p>Back Home: <a href=\"http://localhost:8080/JavaEE/index.jsp\">click me</a></p>");
		            rd.include(request, response);
					System.out.println("what?");
				}
	          
	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ServletException("DB Connection problem.");
	        }finally{
	            try {
	               
	            	if (psLookup!=null) {
						psLookup.close();
					}
	            	if (ps!=null) {
		            	ps.close();
	               }
	               
	            } catch (SQLException e) {
	            	System.out.println("sql exception");
	            }
	        }
	   }
		
	}

}
