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

import dbconnectionlib.Dbconnection;

import editor.EditorArticleReviewInfo;
/**
 * Servlet implementation class EditorGetAllReview
 */
@WebServlet("/EditorGetAllReview")
public class EditorGetAllReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorGetAllReview() {
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
		ArrayList<EditorArticleReviewInfo> jL = new ArrayList<EditorArticleReviewInfo>();
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
			ps=con.prepareStatement("select * from AuthorReviewer");
			rs = ps.executeQuery();
			while (rs.next()) {
				EditorArticleReviewInfo temp= new EditorArticleReviewInfo(rs.getString("reviewername"),rs.getString("authorname"),rs.getString("overalljudgement"), rs.getString("summary"));
	        	jL.add(temp);
	        	System.out.println(rs.getString("reviewername") + " + "+rs.getString("authorname"));
	        	
			}
			HttpSession session = request.getSession();
			session.setAttribute("EditorAuthorReviewer", jL);
			response.sendRedirect("EditorAllReviewList.jsp");
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
	}

}
