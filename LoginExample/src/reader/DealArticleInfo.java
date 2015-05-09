package reader;

import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reader.Article;
import dbconnectionlib.Dbconnection;


/**
 * Servlet implementation class ShowArticle
 */
@WebServlet("/DealArticleInfo")
public class DealArticleInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealArticleInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String articlename = request.getParameter("articlename");
		System.out.println("I get it in get !!!!"+articlename);
		
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
				System.out.println("successfullllllll");
			}
			
			PreparedStatement pss = null;
			ResultSet rs =null;
			
			try {

					pss=con.prepareStatement("select * from Article where articlename= \""+articlename+"\"");
					rs = pss.executeQuery();
				
				if (rs!=null) {
					Article temp=null;
					while(rs.next()){
						 temp= new Article(rs.getString("articlename"),rs.getString("keywords"), rs.getString("abstract"), 
								rs.getString("url"), rs.getString("domain"), rs.getString("uploaddate"), rs.getString("ispublish"),
								rs.getString("affiliations"), rs.getString("currentreviewnum"));
						
						
					}
		
					
					HttpSession session = request.getSession();
					session.setAttribute("selectedarticle",temp);
					response.sendRedirect("ShowArticles.jsp");
				
				} else {
					response.sendRedirect("index.jsp");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					pss.close();
					con.close();
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
