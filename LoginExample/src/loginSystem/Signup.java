package loginSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signup
 */
@WebServlet(name = "signup", urlPatterns = { "/signup" })
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
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
        
        String errorMsg = null;
        if(userName == null || userName.equals("")){
            errorMsg = "User name can't be null or empty.";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty.";
        }
       
         
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }
        else{
         
	        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
	        PreparedStatement ps = null;
	        try {
	            
	        	ps = con.prepareStatement("insert into Users(name, password) values (?,?)");
	            ps.setString(1, userName);
	            ps.setString(2, password);
	            ps.execute();
	            
	            //forward to login page to login
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	            PrintWriter out= response.getWriter();
	            out.println("<font color=green>Registration successful, please login below.</font>");
	            rd.include(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ServletException("DB Connection problem.");
	        }finally{
	            try {
	                ps.close();
	            } catch (SQLException e) {
	            	System.out.println("sql exception");
	            }
	        }
	   }
		
	}

}
