package reviewer;

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

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class DownloadArticle
 */
@WebServlet("/DownloadArticle")
public class DownloadArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String articleName=null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadArticle() {
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
		System.out.println("Servlet DownloadArticle==================================");
		HttpSession session = request.getSession();
		Reviewer reviewer = (Reviewer)session.getAttribute("Reviewer");
		if (reviewer!=null) {
			// connect db------------------------------------------
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
				System.out.println("db connection successfullllllll");
			}// end of connecting db--------------------------------
			
			
			
			articleName = request.getParameter("selected");
			System.out.println(articleName);

			if (articleName!=null) {
				if (request.getParameter("confirm")!=null) {
					// download selected article and change article review status to downloaded with article name
					System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTT");
					System.out.println("confirm");
					boolean confirmed;
					try {
						confirmed = confirmSelection(request, response, articleName, con, reviewer);
						if (confirmed) {
							download(request, response, articleName, con);
							System.out.println("downloaded");
							// redirect to reviewerIndex page
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
					        PrintWriter out= response.getWriter();
					        out.println("<font color=red>Operation successful</font>");
					        rd.include(request, response);
						}
						else {
							// redirect to reviewerIndex page
							RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
					        PrintWriter out= response.getWriter();
					        out.println("<font color=red>Confirm Article error, please refresh page</font>");
					        rd.include(request, response);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (Exception e) {
						
						// redirect to reviewerIndex page
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
				        PrintWriter out= response.getWriter();
				        out.println("<font color=red>Download Article error, please refresh page</font>");
				        rd.include(request, response);
					}
					
					
				} else {
					System.out.println("cancel");
					// remove record in ArticleReview and Reviewer.selectedNum
					try {
						cancelSelection(request, response, articleName, con, reviewer);
						session.removeAttribute("ChosenArticles");
						session.removeAttribute("reviewingArticles");
						session.removeAttribute("submittedReviews");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// redirect to reviewerIndex page
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
			        PrintWriter out= response.getWriter();
			        out.println("<font color=red>Cancel successful</font>");
			        rd.include(request, response);
				}
				
			}
			articleName=request.getParameter("downloaded");
			if (articleName!=null) {
				System.out.println(articleName);
				// redirect to download
				try {
					download(request, response, articleName, con);
					// redirect to reviewerIndex page
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
			        PrintWriter out= response.getWriter();
			        out.println("<font color=red>Operation successful</font>");
			        rd.include(request, response);
			        
				} catch (Exception e) {
					// redirect to reviewerIndex page
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
			        PrintWriter out= response.getWriter();
			        out.println("<font color=red>Download Article error, please refresh page</font>");
			        rd.include(request, response);
				}
				
			}
			
			// redirect to reviewerIndex page
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/reviewerIndex.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>Operation successful</font>");
	        rd.include(request, response);
		}
		else {
			// redirect to login page
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	        PrintWriter out= response.getWriter();
	        out.println("<font color=red>please login</font>");
	        rd.include(request, response);
		}
	}
	
	private void download(HttpServletRequest request, HttpServletResponse response, String articleName,Connection con) throws IOException {
		//?
		try {
			String url = getURL(request, response, articleName, con);
			System.out.println(url);
			//String filename = "memon.pdf";
			//String filepath = "/export/tomtemp/memon.pdf";
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition","attachment; filename=\""+url+"\"");
			java.io.FileInputStream fileInputStream=new java.io.FileInputStream(url);
			java.io.OutputStream os=response.getOutputStream();
			int i=0;
			byte[] b = new byte[1024];
			while((i=fileInputStream.read(b))>0){
			    os.write(b, 0, i);
			}
			fileInputStream.close();
			os.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String getURL(HttpServletRequest request, HttpServletResponse response, String articleName,Connection con) throws SQLException {
		
		String url=null;
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement("select url from Article where articlename=? ");
		ps.setString(1, articleName);
		rs = ps.executeQuery();
		if (rs!=null&&rs.next()) {
			url = rs.getString("url");
		}
		return url;
		
	}
	
	private boolean confirmSelection(HttpServletRequest request, HttpServletResponse response, String articleName,Connection con,Reviewer reviewer) throws SQLException {
			
		boolean result = false;
		PreparedStatement ps = con.prepareStatement("update ArticleReview set reviewstatus='downloaded' where reviewername=? and articlename=? ");
		ps.setString(1, reviewer.getReviewerName());
		ps.setString(2, articleName);
		ps.execute();
		result = true;
		return result;
		
			
	}
	
	private void cancelSelection(HttpServletRequest request, HttpServletResponse response, String articleName,Connection con,Reviewer reviewer) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("delete from ArticleReview where reviewername=? and articlename=? ");
		ps.setString(1, reviewer.getReviewerName());
		ps.setString(2, articleName);
		ps.execute();
	
		ResultSet rs = null;
		int selectedNum = 0;
		PreparedStatement ps1 = con.prepareStatement("select selectednum from Reviewer where reviewername=? ");
		ps1.setString(1, reviewer.getReviewerName());
		rs = ps1.executeQuery();
		if (rs!=null&&rs.next()) {
			selectedNum = rs.getInt("selectednum");
			if (selectedNum!=0) {
				PreparedStatement ps2 = con.prepareStatement("update Reviewer set selectednum=? where reviewername=? ");
				ps2.setInt(1, selectedNum-1);
				ps2.setString(2, reviewer.getReviewerName());
				ps2.execute();
			}
			else {
				System.out.println("????");
			}
		}
		else {
			System.out.println("error");
		}
		
		
	}

}
