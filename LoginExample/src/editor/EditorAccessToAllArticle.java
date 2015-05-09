package editor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Author.Author;
import editor.EditorAllArticlesInfo;
import dbconnectionlib.Dbconnection;
/**
 * Servlet implementation class EditorAccessToAllArticle
 */
@WebServlet("/EditorAccessToAllArticle")
public class EditorAccessToAllArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EditorAllArticlesInfo artiCle;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorAccessToAllArticle() {
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
		
		ArrayList<EditorAllArticlesInfo> al = new ArrayList<EditorAllArticlesInfo>();
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
			System.out.println("successful");
		}
		PreparedStatement ps = null;
		ResultSet rs =null;
		try{
			ps=con.prepareStatement("select * from Article");
			System.out.println("abc");
			rs = ps.executeQuery();
			while (rs.next()) {
	        	//al.add(rs.getString("articleName"));
				
	        	EditorAllArticlesInfo artiCle2= new EditorAllArticlesInfo(rs.getString("articleName"), rs.getString("abstract"), rs.getString("currentreviewnum"), null, rs.getString("ispublish"));
				//artiCle2.setArticleName(rs.getString("articleName"));
	        	//artiCle2.setArticleAbstract(rs.getString("abstract"));
	        	al.add(artiCle2);
	        	System.out.println(rs.getString("articleName") + " + "+rs.getString("abstract")+" + "+rs.getString("currentreviewnum")+" + "+rs.getString("ispublish"));
	        	
			}
			HttpSession session = request.getSession();
			session.setAttribute("allArticles", al);
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
		response.sendRedirect("EditorTest.jsp");
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
