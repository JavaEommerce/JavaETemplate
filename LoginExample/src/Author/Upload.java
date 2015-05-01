package Author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

/**
 * Servlet implementation class Upload
 */

@WebServlet("/Upload")
public class Upload extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
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
		File file ;
		   int maxFileSize = 5000 * 1024;
		   int maxMemSize = 5000 * 1024;
		   String filePath ="E:\\COM6514\\Data\\";
		   
		   String contentType = request.getContentType();
		   if ((contentType.indexOf("multipart/form-data") >= 0)) {

		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      
		      factory.setSizeThreshold(maxMemSize);
		      
		      factory.setRepository(new File("E:\\COM6514"));   //c:\\temp
		      
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      
		       upload.setSizeMax( maxFileSize );
		      try{ 
		      String authorname=request.getParameter("author");
		      List fileItems = upload.parseRequest(request);
		      Iterator i = fileItems.iterator();

		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            if ( !fi.isFormField () )	
		            {
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            if( fileName.lastIndexOf("\\") >= 0 ){
		            file = new File( filePath , 
		            fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		            file = new File( filePath ,
		            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            
		            
		            String name = "";
		            String extention = "";
		                   if(fileName.length()>0 && fileName!=null){  
		            int j = fileName.lastIndexOf(".");
		            if(j>-1 && j<fileName.length()){
		            name = fileName.substring(0, j); 
		            extention = fileName.substring(j+1); 
		            }
		            } 
		            
					
		            
		                   System.out.println(111111);
		            fi.write( file ) ;

		            
		            
		            
		           
		            }
		         }
		         
		      }catch(Exception ex) {
		         System.out.println(ex);
		      }
		   }else{
		      
		   }
	}

}
