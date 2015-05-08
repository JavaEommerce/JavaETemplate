package reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginSystem.User;
import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		String searchform = request.getParameter("search-form");
		String selectsearchtype = request.getParameter("selectsearchtype");
	
		String errorMessage=null;
		
		System.out.println("Grt research Type:"+selectsearchtype);
		
		if(selectsearchtype.equals("")||selectsearchtype==null){
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
				System.out.println("successfullllllll");
			}
			
			PreparedStatement ps = null;
			ResultSet rs =null;
			
			try {
				
				
				
				if(selectsearchtype.equals("ArticleName")){
					ps=con.prepareStatement("select ArticleName from Article where articlename=?");
					ps.setString(1, searchform);
					rs = ps.executeQuery();
					
					
				}
				
//				if(selectsearchtype.equals("JournalName")){
//					ps=con.prepareStatement("select ArticleName from Journal where articlename=?");
//					ps.setString(1, searchform);
//					rs = ps.executeQuery();
//					
//					
//				}
//				
//				if(selectsearchtype.equals("AuthorName")){
//					ps=con.prepareStatement("select * from Author where articlename=?");
//					ps.setString(1, searchform);
//					rs = ps.executeQuery();
//			
//					
//				}
//				
//				if(selectsearchtype.equals("Domain")){
//					ps=con.prepareStatement("select * from Article where articlename=?");
//					ps.setString(1, searchform);
//					rs = ps.executeQuery();
//			
//					
//				}
				
				
				
				if (rs!=null) {
					System.out.println("Prepare jump");
					HttpSession session = request.getSession();
					session.setAttribute("resulttext",rs);
					response.sendRedirect("SearchResult.jsp");
					System.out.println("After jump");
					
				} else {
					response.sendRedirect("index.jsp");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					ps.close();
					System.out.println("db closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
		
		
		
		}
	}

}
