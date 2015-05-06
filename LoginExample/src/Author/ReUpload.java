package Author;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ReUpload
 */
@WebServlet("/ReUpload")
public class ReUpload extends HttpServlet {
	
	private String uploadPath = "E:\\COM6514\\Data\\"; // �ϴ��ļ���Ŀ¼    
    private String tempPath = "E:\\COM6514\\Data\\"; // ��ʱ�ļ�Ŀ¼    
    private String serverPath = null;   
    private String[] fileType = new String[]{".pdf"};  
    private int sizeMax = 5;//ͼƬ�������    
	
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
		String serverPath = getServletContext().getRealPath("/").replace("\\", "/");    
//      System.out.println(serverPath);  
		
		
		
		
      //Servlet��ʼ��ʱִ��,����ϴ��ļ�Ŀ¼���������Զ�����    
      if(!new File(uploadPath).isDirectory()){   
          new File(uploadPath).mkdirs();    
      }    
      if(!new File(tempPath).isDirectory()){  
          new File(tempPath).mkdirs();  
      }   
      DiskFileItemFactory factory = new DiskFileItemFactory();  
      factory.setSizeThreshold(5*1024); //��󻺴�    
      factory.setRepository(new File(tempPath));//��ʱ�ļ�Ŀ¼    
          
      ServletFileUpload upload = new ServletFileUpload(factory);  
      upload.setSizeMax(sizeMax*1024*1024);//�ļ��������   
          
      String filePath = null;    
      try {    
          List<FileItem> items = upload.parseRequest(request);//��ȡ�����ļ��б�   
          //  
          for (int i=0;i<items.size();i++) {  
              //����һ��forѭ������ȡһ�е�����  
              FileItem item = items.get(i);  
              	if(!item.isFormField()){//�ļ���    
                  String fileName = item.getName().toLowerCase();  
                  if(fileName.endsWith(fileType[0])){    
//                      String uuid = UUID.randomUUID().toString();    
                      filePath = uploadPath+fileName;  
//                      System.out.println(filePath);  
                      File file = new File(filePath);  
                      item.write(file);  
                      System.out.println(fileName);  
                   }else {  
                      request.setAttribute("errorMsg", "fail!");  
                      request.getRequestDispatcher("uploaderror.jsp").forward(request,response);   
                  }  
              }else {  
                //���ļ���     
                  String value=item.getString();  
                
                  value = new String(value.getBytes("ISO-8859-1"),"UTF-8");  
//                  System.out.println(value);  
               
              }  
                
          } 
          
    
          
      } catch (Exception e) {  
          e.printStackTrace();    
          request.setAttribute("errorMsg", "fail!");  
          request.getRequestDispatcher("uploaderror.jsp").forward(request,response);   
      }  
      
      
      
	}
		
		
	

}
