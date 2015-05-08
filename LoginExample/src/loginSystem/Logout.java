package loginSystem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.SessionCookieConfig;
import Author.Author;

/**
 * Servlet implementation class Logout
 */
@WebServlet(name="loginSystem.Logout", urlPatterns={"/Logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("log out success");
		HttpSession ses = request.getSession();
		ses.removeAttribute("User");
		ses.removeAttribute("Author");
		ses.removeAttribute("Editor");
	    ses.invalidate();
	    response.sendRedirect("http://localhost:8080/JavaEE/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		User currentUser = (User)session.getAttribute("User"); 
//		Author currentAuthor = (Author)session.getAttribute("Author");
		System.out.println("log out success");
		HttpSession ses = request.getSession();
		ses.removeAttribute("User");
		ses.removeAttribute("Author");
	    ses.invalidate();
	    response.sendRedirect("http://localhost:8080/JavaEE/index.jsp");
	}

}