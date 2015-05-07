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

import Author.Author;
import dbconnectionlib.Dbconnection;
/**
 * Servlet implementation class EditorAccessToAllArticle
 */
@WebServlet("/EditorAccessToAllArticle")
public class EditorAccessToAllArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		ArrayList<String> al = new ArrayList();
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
			ps=con.prepareStatement("select articleName from Article");
			rs = ps.executeQuery();
			if (rs.next()) {
	        	al.add(rs.getString("articleName"));
	        	System.out.println("_____"+al.iterator());
			}
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
//			String name=null;
//		  name = "Hello "+request.getParameter("user");
//		  if(request.getParameter("user").toString().equals("")){
//		   name="Hello User";
//		  }
		  try {
			Thread.sleep(5);
			response.setContentType("text/plain");  
			  response.setCharacterEncoding("UTF-8"); 
			  response.getWriter().write(al.get(0));
			  System.out.println("+++++++"+al.iterator());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
