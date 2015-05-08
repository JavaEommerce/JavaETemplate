package editor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditorArticleDetail
 */
@WebServlet("/EditorArticleDetail")
public class EditorArticleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorArticleDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get post information");
		String gi =request.getParameter("appointname");
		System.out.print("article name" +gi);
		String articleName = removeLastChar(gi);
		//appointUserAsEditor(usernameN);
		String articleInfo = articleDetail(articleName);
		HttpSession ses = request.getSession();
		ses.setAttribute("articleDetail", articleInfo);
		response.sendRedirect("ArticleDetail.jsp");
	}
	
	private String articleDetail(String articlename){
		String artiN = articlename;
		return artiN;
	}

}
