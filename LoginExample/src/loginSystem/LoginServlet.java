package loginSystem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMessage+"</font>");
            rd.include(request, response);
			
			
			
			
//			PrintWriter out = response.getWriter();
//			response.setContentType("text/html");
//			out.println("<font size = '6' color = red>"+errorMessage +"</font>");
//			out.println("<html><body>");
//			//out.println("<p>back to login page in 3s</p>");
//			out.println("<p><a href=\"http://localhost:8080/JavaEE/login.jsp\">back</a></p>");
//			out.println("</body></html>");
			
//			try {
//				response.wait(3000);
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//response.sendRedirect("http://localhost:8080/JavaEE/login.jsp");
			
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<font size = '6' color = red>"+"login successfully" +"</font>");

		}
		
	}

}
