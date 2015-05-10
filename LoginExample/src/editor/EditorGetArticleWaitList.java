package editor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reader.Article;
import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorGetArticleWaitList
 */
@WebServlet("/EditorGetArticleWaitList")
public class EditorGetArticleWaitList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorGetArticleWaitList() {
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
		Dbconnection db=null;
		ArrayList<Article> waitArticle = new ArrayList<Article>();
		try {
			db = new Dbconnection();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Connection con = db.getConnection();
		if (con==null) {
			System.out.println("it's closed!");
		}
		else{
			System.out.println("successful");
		}
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
				ps=con.prepareStatement("select * from Article where uploaddate= '2015-05-07' and ispublish='0'");
				rs = ps.executeQuery();
				
				if (rs!=null) {
				while(rs.next()){
					Article temp= new Article(rs.getString("articlename"),rs.getString("keywords"), rs.getString("abstract"), 
							rs.getString("url"), rs.getString("domain"), rs.getString("uploaddate"), rs.getString("ispublish"),
							rs.getString("affiliations"), rs.getString("currentreviewnum"));
					waitArticle.add(temp);
				}
				HttpSession session = request.getSession();
				session.setAttribute("waitArticle",waitArticle);
				response.sendRedirect("EditorWaitingArticle.jsp");
				
			} else {
				response.sendRedirect("EditorTest.jsp");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
				System.out.println("db closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
