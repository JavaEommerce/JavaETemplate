package editor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import editor.EditorAllArticlesInfo;
import dbconnectionlib.Dbconnection;
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
		EditorAllArticlesInfo al = getArticleDetail(articleName);
		HttpSession session = request.getSession();
		session.setAttribute("allArticles", al);
		response.sendRedirect("ArticleDetail.jsp");
	}
	
	private EditorAllArticlesInfo getArticleDetail(String articlename){
		EditorAllArticlesInfo art = null;
		Dbconnection db=null;
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
		try{
			ps=con.prepareStatement("select * from Article where articlename = ?");
			ps.setString(1, articlename);
			rs = ps.executeQuery();
	        art= new EditorAllArticlesInfo(rs.getString("articleName"), rs.getString("abstract"), rs.getString("currentreviewnum"), null, rs.getString("ispublish"));
	        System.out.println(rs.getString("articleName") + " + "+rs.getString("abstract")+" + "+rs.getString("currentreviewnum")+" + "+rs.getString("ispublish"));
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
            try {
                
            	if (ps!=null) {
					ps.close();
				}	
               
            } catch (SQLException e) {
            	System.out.println("sql exception");
            }
	     }
		return art;
	}

}
