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

import loginSystem.User;
import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorRetire
 */
@WebServlet("/EditorRetire")
public class EditorRetire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorRetire() {
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
		HttpSession ses = request.getSession();
		User currentUser = (User)ses.getAttribute("User"); 
		String retireEditorname = currentUser.getUserName();
		System.out.println("are you sure to retire");
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
		
		try {
			ps = con.prepareStatement("update User set role = 1 where username=?");
        	ps.setString(1, retireEditorname);
        	ps.executeUpdate();
			System.out.println("set user "+retireEditorname+"as user");
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				System.out.println("db closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//		  HttpSession session = request.getSession();
//		  session.setAttribute("userInfo", ui);
		  String LOGIN_PAGE = "index.jsp";
		  response.sendRedirect(LOGIN_PAGE);
	}

}
