package reviewer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectPendingArticle
 */
@WebServlet("/SelectPendingArticle")
public class SelectPendingArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectPendingArticle() {
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
		// TODO insert selections in ArticleReview table in db
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
		String[] selectedTitles=request.getParameterValues("pendingArticles");
		
		if (reviewer!=null) {
			if (selectedTitles!=null) {
				
				
				for (String title : selectedTitles) {
					System.out.println(title);
				}
			}
			else {
				// didnt choose any article, back to 
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/pendingArticles.jsp");
		        PrintWriter out= response.getWriter();
		        out.println("<font color=red>Didn't choose any article</font>");
		        rd.include(request, response);
			}
		}
		
		// access denied
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>please login</font>");
	        rd.include(request, response);
		}
		
		
	}

}
