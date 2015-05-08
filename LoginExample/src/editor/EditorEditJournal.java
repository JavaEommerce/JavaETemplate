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

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorEditJournal
 */
@WebServlet("/EditorEditJournal")
public class EditorEditJournal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorEditJournal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Journal information received");
//		String title = request.getParameter("title");
//		System.out.println(title);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Journal information received");
		String title = request.getParameter("title");
		String aims =request.getParameter("aims");
		String goals=request.getParameter("goals");
		String profile = request.getParameter("profileUrl");
		String id=request.getParameter("id");
		String newID = removeLastChar(id);
		System.out.println("changed journal detail " + newID);
		int foo = Integer.parseInt(newID);
		updateJournal(title,aims,goals,profile,foo);
		String LOGIN_PAGE = "EditorTest.jsp";
		response.sendRedirect(LOGIN_PAGE);
		
	}
	private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
	public void updateJournal(String title,String aims,String goals,String profile,int id){
		
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
			psLookup = con.prepareStatement("update Journal set journalname = ? , journalAims = ?, journalGoals=?, journalProfileUrl=?  where journalID= ?");
        	psLookup.setString(1,title);
        	psLookup.setString(2, aims);
        	psLookup.setString(3, goals);
        	psLookup.setString(4,profile);
        	psLookup.setInt(5, id);
        	psLookup.executeUpdate();
        	
			System.out.println(title + aims + goals + profile);
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
