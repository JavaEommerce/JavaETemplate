package roseindia.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Login Servlet", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	public String connectWithSQL(String un,String pw)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Driver found");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found" + e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String url = "jdbc:mysql://stusql.dcs.shef.ac.uk";
//		String user = "team153";
//		String password = "80473623";
		String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
	
		Connection con = null;
		try {
			con = DriverManager.getConnection(DB);
			Statement myStmt = (Statement) con.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from test");
			System.out.println("Connected");
			while(myRs.next())
			{
				//System.out.println(myRs.getString("username"));
//				if(un.equals(myRs.getString("username"))&&pw.equals(myRs.getString("password")))
//				{
//					return un;
//				}
				System.out.println(myRs.getString("username"));
				if(un.equals(myRs.getString("username")))
				{
					return un;
				}
				
			}
			
			return myRs.getString("username")+"not found";
			
		} catch (SQLException e) {
			System.out.println("Connection failed");
			String errorLog = "Failed";
			return errorLog;
		}
		//return true;
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("servlet method in servelt");
		String username ="user";
		String password = "root";
		
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		String msg = "";
		String feedback = connectWithSQL(un,pw);
		//String feedback = "Login successed";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//out.print(feedback);
		//response.sendRedirect("http://localhost:8080/LoginExample/signup.jsp");
		
		out.println("<font size = '6' color = red>"+feedback +"</font>");
		
	}

}
