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

import reader.Article;
import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorSetArticlePublished
 */
@WebServlet("/EditorSetArticlePublished")
public class EditorSetArticlePublished extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorSetArticlePublished() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articlename = request.getParameter("articlename");
		String errorMessage=null;
		
		if(articlename.equals("")||articlename==null){
			errorMessage="Search key words are empty";
		}
		else{
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
			
			PreparedStatement pss = null;
			PreparedStatement ps2 = null;
			
			try {
				pss=con.prepareStatement("update Article set ispublish = '1' where articlename= ? ");
				ps2=con.prepareStatement("insert into JournalArticle(articlename,jounalID) values(?,?)");
				ps2.setString(1,articlename);
				ps2.setInt(2,1);
				pss.setString(1, articlename);
				pss.executeUpdate();
				ps2.executeUpdate();
				System.out.println("updated successful*****************");
	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					
					
					
					pss.close();
					con.close();
					response.sendRedirect("EditorTest.jsp");
					System.out.println("db closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String articlename = request.getParameter("articlename");
//		String errorMessage=null;
//		
//		if(articlename.equals("")||articlename==null){
//			errorMessage="Search key words are empty";
//		}
//		else{
//			Dbconnection db=null;
//			try {
//				db = new Dbconnection();
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			Connection con = db.getConnection();
//			
//			if (con==null) {
//				System.out.println("it's closed!");
//			}
//			else{
//				System.out.println("successful");
//			}
//			
//			PreparedStatement pss = null;
//			PreparedStatement ps2 = null;
//			
//			try {
//					pss=con.prepareStatement("update Article set ispublish = '1' where articlename= ? ");
//					ps2=con.prepareStatement("insert into JournalArticle(articlename,jounalID) values(?,?)");
//					ps2.setString(1,articlename);
//					ps2.setInt(2, 1);
//					pss.setString(1, articlename);
//					pss.executeUpdate();
//					ps2.executeUpdate();
//					System.out.println("updated successful*****************");
//	
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally{
//				try {
//					
//					
//					
//					pss.close();
//					con.close();
//					response.sendRedirect("EditorTest.jsp");
//					System.out.println("db closed");
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		}
	}

}
