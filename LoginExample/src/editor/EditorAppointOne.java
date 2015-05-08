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

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorAppointOne
 */
@WebServlet(name="editor.EditorAppointOne",urlPatterns = {"/EditorAppointOne"})

public class EditorAppointOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorAppointOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").toString();
		System.out.println("hello user +++ " + username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("get post information");
		String gi =request.getParameter("appointname");
		System.out.print("button value ++++++ " +gi);
		String usernameN = removeLastChar(gi);
		appointUserAsEditor(usernameN);
		String LOGIN_PAGE = "EditorTest.jsp";
		response.sendRedirect(LOGIN_PAGE);
		
	}
	
	private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
	public void appointUserAsEditor(String username){
		String newEditorname = username;
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
			System.out.println("successful connected with mysql");
		}
		PreparedStatement ps = null;
		PreparedStatement psLookup = null;
		ResultSet rs =null;
		try{
//			ps=con.prepareStatement("select * from User");
//			rs = ps.executeQuery();
//			ps=con.prepareStatement("update User set role = 3 where username = " + ); //set user role as editor
//			ps.executeUpdate();
			psLookup = con.prepareStatement("update User set role = 3 where username=?");
        	psLookup.setString(1, newEditorname);
        	psLookup.executeUpdate();
			System.out.println("set user "+newEditorname+"as a new Editor");
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
