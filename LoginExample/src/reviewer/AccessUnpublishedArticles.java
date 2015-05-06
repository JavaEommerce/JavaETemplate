package reviewer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class AccessUnpublishedArticles
 */
@WebServlet("/AccessUnpublishedArticles")
public class AccessUnpublishedArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessUnpublishedArticles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO browse pending articles
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
		int selectedNum = reviewer.getSelectedNum();
		if (selectedNum<=3) {
			// connect db
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
			ArrayList<PendingArticle> pendingArticles = new ArrayList<>();
			PendingArticle oldestOne = null;
			Date oldDate = null;
			
			// create pendingArticle instances
			try {
				ps = con.prepareStatement("select articlename,abstract,uploaddate from Article where ispublish=? ");
				ps.setInt(1, 0);
				rs=ps.executeQuery();
				while (rs.next()) {
					Date date = rs.getDate("uploaddate");
					PendingArticle pa = new PendingArticle(rs.getString("articlename"), rs.getString("abstract"));
					if (date.before(oldDate)) {
						oldDate=date;
						oldestOne=pa;
					}
					pendingArticles.add(pa);
				}
				session.setAttribute("ForceToChoose", oldestOne);
				session.setAttribute("pendingArticles", pendingArticles);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerCentre.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>You have selected enough articles</font>");
	        rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
        out.println("<font color=red>bu dui ba !!!</font>");
	}
	//asd
	
}