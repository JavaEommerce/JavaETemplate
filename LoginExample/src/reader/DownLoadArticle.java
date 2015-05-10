package reader;

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
 * Servlet implementation class DownLoadArticle
 */
@WebServlet("/DownLoadArticle")
public class DownLoadArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("½øÈëÁË");
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
			System.out.println("successfullllllllqqqqqqqqqq");
			
			PreparedStatement ps = null;
			ResultSet rs =null;
			
			
			try {

				System.out.println(request.getParameter("downloadAname"));
				String articlename = request.getParameter("downloadAname");
//				String filename = filename1+".pdf";
				String filepath = null;
				
				
				
				ps=con.prepareStatement("select url from Article where articlename= '"+articlename+"'");
				System.out.println("select url from Article where articlename= '"+articlename+"'");
				rs = ps.executeQuery();
				
				while(rs.next()){
					filepath=rs.getString("url");
					System.out.println(filepath);
				}
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition","attachment; filename=\""+filepath+"\"");
				
				java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath);
				java.io.OutputStream os=response.getOutputStream();
				int i=0;
				byte[] b = new byte[1024];
				while((i=fileInputStream.read(b))>0){
				    os.write(b, 0, i);
				}
				fileInputStream.close();
				os.close();
				}catch (SQLException e) {
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
		System.out.println("111111111111111111111111");
		

		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}
}

