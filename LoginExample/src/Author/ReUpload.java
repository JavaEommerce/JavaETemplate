package Author;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class ReUpload
 */
@WebServlet("/ReUpload")
public class ReUpload extends HttpServlet {
	
	private String uploadPath = ""; // 上传文件的目录    
	private String tempPath = ""; // 临时文件目录    
    private String serverPath = null;   
    private String[] fileType = new String[]{".pdf"};  
    private int sizeMax = 5;//图片最大上限 
    
    private int reviewerNum = 0;
    private String reviseInfos1 = "";
    private String reviewerNames1 = "";
    private String reviseInfos2 = "";
    private String reviewerNames2 = "";
    private String reviseInfos3 = "";
    private String reviewerNames3 = "";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReUpload() {
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
//		String serverPath = getServletContext().getRealPath("/").replace("\\", "/");    
		String serverPath = "/export/tomtemp/";
//		String serverPath = "E:\\Testtt\\";
//      System.out.println(serverPath);  	
      //Servlet初始化时执行,如果上传文件目录不存在则自动创建    
		
		
		
      if(!new File(uploadPath).isDirectory()){   
          new File(uploadPath).mkdirs();    
      }    
      if(!new File(tempPath).isDirectory()){  
          new File(tempPath).mkdirs();  
      }   
      
      
      DiskFileItemFactory factory = new DiskFileItemFactory();  
      factory.setSizeThreshold(5*1024); //最大缓存    
      factory.setRepository(new File(tempPath));//临时文件目录    
          
      ServletFileUpload upload = new ServletFileUpload(factory);  
      upload.setSizeMax(sizeMax*1024*1024);//文件最大上限   
          
      String filePath = null;  
      
      	HttpSession session = request.getSession();
		Author currentAuthor = (Author)session.getAttribute("Author");
		String aname = currentAuthor.getAuthorName();
      
      try {    
          List<FileItem> items = upload.parseRequest(request);//获取所有文件列表   
          //  
          for (int i=0;i<items.size();i++) {  
              //里面一个for循环，获取一行的数据  
              FileItem item = items.get(i);  
              	if(!item.isFormField()){//文件名    
                  String fileName = item.getName().toLowerCase();  
                  
                  if(fileName.endsWith(fileType[0])){    
//                      String uuid = UUID.randomUUID().toString();    
                      filePath = serverPath+uploadPath+fileName;  
//                      System.out.println(filePath);  
                      
                      
                      
                      
//data******************************************************************************************                      
                      String articleTitle = "";
                      
                      
                      Dbconnection db=null;
                  	  db = new Dbconnection();
                  	  Connection con = db.getConnection();
            		
                  	
                  	PreparedStatement ps = null;		
                  	PreparedStatement pi = null;
                  	PreparedStatement par = null;
                  	PreparedStatement psa = null;

            		ResultSet rs =null;
            		ResultSet ra =null;
                  	  if(con!=null) {
                  		  try{  
                  	
                  		  System.out.println("enter database");								
        					
        					
        					
                  		ps = con.prepareStatement("select articlename from AuthorArticle where authorname=?");
                  		ps.setString(1, aname);
                  		rs = ps.executeQuery();

                  		while(rs.next()){                			

                  			
                  			psa = con.prepareStatement("select articlename from Article where ispublish=0 and articlename=?");
                      		psa.setString(1, rs.getString("articlename"));
                  			ra=psa.executeQuery();
                  			if(ra.next()){
                      			articleTitle = ra.getString("articlename");	
                  			}
                  			
                  		}
                  		

                  		pi = con.prepareStatement("update Article set url=? where articlename=?");
                  		pi.setString(1, filePath);
                  		pi.setString(2, articleTitle);
                  		pi.executeUpdate();
                  		
                  		for(int now=0;now<reviewerNum;now++){
                  		par = con.prepareStatement("update AuthorReviewer set reviseinfo=? where reviewername=? and authorname=?");
                  		par.setString(1, reviseInfos1);
                  		par.setString(2, reviewerNames1);
                  		par.setString(3, aname);
                  		par.executeUpdate();
                  		par.setString(1, reviseInfos2);
                  		par.setString(2, reviewerNames2);
                  		par.setString(3, aname);
                  		par.executeUpdate();
                  		par.setString(1, reviseInfos3);
                  		par.setString(2, reviewerNames3);
                  		par.setString(3, aname);
                  		par.executeUpdate();
                  		
                  		
                  		
                  		
                  		
                  		}
                  		
                  		rs.close();
                  	    ps.close();
                  	    pi.close();
                  	    ra.close();
                  	    par.close();
                  	    psa.close();
                  	    con.close();

                  		  }catch(SQLException e){  
                                
                                System.out.println("SQLException;"+e.getMessage());   
                                PrintWriter out= response.getWriter();
           			            out.println("<font color=green>Sorry, Something wrong with the database, please contact Administrator</font>"); 
                    		       
                            }  
                  		  
                  	  }
                      
                      
//**********************************************************************************************                     
                      
                  
                      
                      File file = new File(filePath);  
                      item.write(file);   
                      System.out.println(filePath); 

                      
                      PrintWriter out= response.getWriter();
			          out.println("<font color=green>ReUploaded!.</font>");
                      
                      
                   }else {  
                	   PrintWriter out= response.getWriter();
  			            out.println("<font color=green>Please upload a .pdf file</font>"); 
           		       break;
                  }  
              }else {  
                //非文件流     
            	  
         	  
                  String value=item.getString();  
                
                  value = new String(value.getBytes("ISO-8859-1"),"UTF-8");  
//                  System.out.println(value);  
                  
                  
                  String name =item.getFieldName();
                  
                  
                  if("reviewername1".equals(name))  {    
            		  System.out.println("name");
                	  reviewerNames1=item.getString("utf-8");

                  }
                  if("reviewername2".equals(name))  {    
            		  System.out.println("name");
                	  reviewerNames2=item.getString("utf-8");

                  }
                  if("reviewername3".equals(name))  {    
            		  System.out.println("name");
                	  reviewerNames3=item.getString("utf-8");

                  }
                  
                  if("reviseinfo1".equals(name))  {           	  
                	  if(item.getString("utf-8").equals("")){
                		  System.out.println("info");
                		  PrintWriter out= response.getWriter();
    			          out.println("<font color=green>Please wirte revision information to all reviewer</font>"); 
                          break;
                		  
                		  
                	  }else{
                		  reviseInfos1=item.getString("utf-8");
                    	  reviewerNum += 1;
                	  }
                  }
                  if("reviseinfo2".equals(name))  {           	  
                	  if(item.getString("utf-8").equals("")){
                		  System.out.println("info");
                		  PrintWriter out= response.getWriter();
    			          out.println("<font color=green>Please wirte revision information to all reviewer</font>"); 
                          break;
                		  
                		  
                	  }else{
                		  reviseInfos2=item.getString("utf-8");
                    	  reviewerNum += 1;
                	  }
                  }
                  if("reviseinfo3".equals(name))  {           	  
                	  if(item.getString("utf-8").equals("")){
                		  System.out.println("info");
                		  PrintWriter out= response.getWriter();
    			          out.println("<font color=green>Please wirte revision information to all reviewer</font>"); 
                          break;
                		  
                		  
                	  }else{
                		  reviseInfos3=item.getString("utf-8");
                    	  reviewerNum += 1;
                	  }
                  }
                 
                  
                  
              }  
                
          } 
          
    
          
      } catch (Exception e) {  
          e.printStackTrace();    
          request.setAttribute("errorMsg", "fail!");  
          request.getRequestDispatcher("uploaderror.jsp").forward(request,response);   
      }  
      
      
      
	}
		

	

}
