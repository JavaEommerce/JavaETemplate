package reader;

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
import editor.Journal;

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
		String journalID = request.getParameter("JID");
		//System.out.println(journalID);
		String errorMessage=null;
		ArrayList<Article> articls = new ArrayList<Article>();
		
		if(journalID.equals("")||journalID==null){
			errorMessage="Can not find this Journal.";
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
				ps=con.prepareStatement("select * from Article,Journal,JournalArticle where Article.articlename = JournalArticle.articlename and Journal.journalID= JournalArticle.journalID and Journal.journalID =?");
				ps.setString(1, journalID);
				rs = ps.executeQuery();
				
				if (rs!=null) {
					System.out.println("Prepare jump");
					while(rs.next()){
						Article temp= new Article(rs.getString("articlename"),rs.getString("keywords"), rs.getString("abstract"), 
								rs.getString("url"), rs.getString("domain"), rs.getString("uploaddate"), rs.getString("ispublish"),
								rs.getString("affiliations"), rs.getString("currentreviewnum"));
						articls.add(temp);
						
					
					//System.out.println(rs.getString("articlename"));
					}
					HttpSession session = request.getSession();
					session.setAttribute("articlenamelist",articls);
					response.sendRedirect("SearchResult.jsp");
					
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
		String searchform = request.getParameter("search-form");
		String selectsearchtype = request.getParameter("selectsearchtype");
		String selectfromdate = request.getParameter("fromdate");
		System.out.println(selectfromdate);
		String errorMessage=null;
		ArrayList<Article> articls = new ArrayList<Article>();
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
					ps=con.prepareStatement("select * from Article where articlename like '%"+searchform+"%' and ispublish = 1 "+" and uploaddate > '"+selectfromdate+"'");
					rs = ps.executeQuery();
				}
				
				if(selectsearchtype.equals("JournalName")){
					ps=con.prepareStatement("select * from Article,Journal,JournalArticle where Article.articlename = JournalArticle.articlename and Journal.journalID= JournalArticle.journalID and Journal.journalname =? and ispublish = 1"+" and uploaddate > '"+selectfromdate+"'");
					ps.setString(1, searchform);
					rs = ps.executeQuery();
					
	
				}
				
				if(selectsearchtype.equals("AuthorName")){
					ps=con.prepareStatement("select * from Article,Author,AuthorArticle where Article.articlename = AuthorArticle.articlename and  Author.authorname =? and ispublish = 1"+" and uploaddate > '"+selectfromdate+"'");
					ps.setString(1, searchform);
					rs = ps.executeQuery();
			
					
				}
				
				if(selectsearchtype.equals("Domain")){
					ps=con.prepareStatement("select * from Article where domain=? and ispublish = 1"+" and uploaddate > '"+selectfromdate+"'");
					ps.setString(1, searchform);
					rs = ps.executeQuery();
			
					
				}
				
				if(selectsearchtype.equals("KeyWords")){
					ps=con.prepareStatement("select * from Article where keywords like '%"+searchform+"%'and ispublish = 1"+" and uploaddate > '"+selectfromdate+"'");
					rs = ps.executeQuery();
	
				}
				

				if (rs!=null) {
					System.out.println("Prepare jump");
					while(rs.next()){
						Article temp= new Article(rs.getString("articlename"),rs.getString("keywords"), rs.getString("abstract"), 
								rs.getString("url"), rs.getString("domain"), rs.getString("uploaddate"), rs.getString("ispublish"),
								rs.getString("affiliations"), rs.getString("currentreviewnum"));
						articls.add(temp);
						
					
					//System.out.println(rs.getString("articlename"));
					}
					HttpSession session = request.getSession();
					session.setAttribute("articlenamelist",articls);
					response.sendRedirect("SearchResult.jsp");
					
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
					con.close();
					System.out.println("db closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
		
		
		
		}
	}

}
