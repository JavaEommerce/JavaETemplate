package reviewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class DownloadArticle
 */
@WebServlet("/DownloadArticle")
public class DownloadArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String articleName=null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadArticle() {
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
		System.out.println("Servlet DownloadArticle==================================");
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
			
			
			
			articleName = request.getParameter("selected");
			System.out.println(articleName);

			if (articleName!=null) {
				if (request.getParameter("confirm")!=null) {
					// download selected article and change article review status to downloaded with article name
					System.out.println("confirm");
				} else {
					System.out.println("cancel");
					// remove record in ArticleReview and Reviewer.selectedNum
				}
				
			}
			articleName=request.getParameter("downloaded");
			if (articleName!=null) {
				System.out.println(articleName);
				// redirect to download
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

}
