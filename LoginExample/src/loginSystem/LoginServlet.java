package loginSystem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "loginSystem.LoginServlet", urlPatterns = { "/LoginServlet" })
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			//response.sendRedirect("http://localhost:8080/LoginExample/signup.jsp");
			
			out.println("<font size = '6' color = red>"+errorMessage +"</font>");
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<font size = '6' color = red>"+"login successfully" +"</font>");

		}
		
	}

}
