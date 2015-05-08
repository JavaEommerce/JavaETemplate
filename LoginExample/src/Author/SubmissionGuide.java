package Author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubmissionGuide
 */
@WebServlet("/SubmissionGuide")
public class SubmissionGuide extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmissionGuide() {
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
		
		String filename = "memon.pdf";
//		String filepath = "E:\\export\\tomtemp\\clubcard.pdf";
		String filepath = "/export/tomtemp/memon.pdf";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition","attachment; filename=\""+filename+"\"");
		java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath);
		java.io.OutputStream os=response.getOutputStream();
		int i=0;
		byte[] b = new byte[1024];
		while((i=fileInputStream.read(b))>0){
		    os.write(b, 0, i);
		}
		fileInputStream.close();
		os.close();
		
		
		
	}

}
