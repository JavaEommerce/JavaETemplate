package editor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorRejectReview
 */
@WebServlet("/EditorRejectReview")
public class EditorRejectReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorRejectReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articlename = request.getParameter("reviewername");
		String errorMessage=null;
		
		if(articlename.equals("")||articlename==null){
			errorMessage="Search key words are empty";
		}
		else{
			Dbconnection db=null;
			try {
				db = new Dbconnection();
			} catch (ClassNotFoundException e1) {
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
			;
			
			try {
				pss=con.prepareStatement("update AuthorReviewer set reviseaccepted = '0' where reviewername= ? ");
				pss.setString(1, articlename);
				pss.executeUpdate();
				System.out.println("reject successful*****************");
	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					pss.close();
					con.close();
					response.sendRedirect("EditorAllReviewList.jsp");
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
		// TODO Auto-generated method stub
	}

}
