package Author;


import java.io.File;  
import java.io.IOException;  
import java.util.ArrayList;  
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
 * Servlet implementation class Upload
 */

@WebServlet("/Upload")
public class Upload extends HttpServlet {
	
	private String uploadPath = "Testtt\\"; // �ϴ��ļ���Ŀ¼    
	private String tempPath = "Testtt\\"; // ��ʱ�ļ�Ŀ¼    
	private String serverPath = null;   
    private String[] fileType = new String[]{".pdf"};  
    private int sizeMax = 5;//ͼƬ�������    
	
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
//		String serverPath = getServletContext().getRealPath("/").replace("\\", "/");    
		String serverPath = "E:\\";
//      System.out.println(serverPath);  
		
		String mainAuthorname="";
        String mainEmail="";
        String mainAffiliation="";
        String otherAuthorname="";
        String otherEmail="";
        String otherAffiliation="";
        String abst="";
        String title="";
        String keywords="";
		
		
		
      //Servlet��ʼ��ʱִ��,����ϴ��ļ�Ŀ¼���������Զ�����    
        if(!new File(serverPath+uploadPath).isDirectory()){   
            new File(serverPath+uploadPath).mkdirs();    
        }    
        if(!new File(serverPath+tempPath).isDirectory()){  
            new File(serverPath+tempPath).mkdirs();  
        }   
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        factory.setSizeThreshold(5*1024); //��󻺴�    
        factory.setRepository(new File(serverPath+tempPath));//��ʱ�ļ�Ŀ¼    
            
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
//                    String uuid = UUID.randomUUID().toString();    
                    filePath = serverPath+uploadPath+fileName;  
                    
                    System.out.println(filePath);  
//                    System.out.println(serverPath);
                    
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
                  
                  
                  String name =item.getFieldName();
                  if("mainAuthor".equals(name))  {           	  
                	  mainAuthorname=item.getString("utf-8");
                  }
                  if("mainEmail".equals(name))  {           	  
                	  mainEmail=item.getString("utf-8");
                  }
                  if("mainAffiliation".equals(name))  {           	  
                	  mainAffiliation=item.getString("utf-8");
                  }
                  if("otherAuthor".equals(name))  {           	  
                	  otherAuthorname=item.getString("utf-8");
                  }
                  if("otherEmail".equals(name))  {           	  
                	  otherEmail=item.getString("utf-8");
                  }
                  if("otherAffiliation".equals(name))  {           	  
                	  otherAffiliation=item.getString("utf-8");
                  }
                  if("abstract".equals(name))  {           	  
                	  abst=item.getString("utf-8");
                  }
                  if("title".equals(name))  {           	  
                	  title=item.getString("utf-8");
                  }
                  if("key".equals(name))  {           	  
                	  keywords=item.getString("utf-8");
                  }
                  
                  value = new String(value.getBytes("ISO-8859-1"),"UTF-8");  
//                  System.out.println(value);  

                  
                  
              }  
                
          } 
          
          

        	  
            if(mainEmail.indexOf("@") != -1){
        	  try{
                  //send email
        			Properties props=new Properties();//Ҳ����Properties props = System.getProperties(); 
        			props.put("mail.smtp.host","smtp.gmail.com");//�洢�����ʼ�����������Ϣ
        			props.put("mail.smtp.user", "javaeteam3@gmail.com");  
        			props.put("mail.smtp.password", "weizhao888");  
        			props.put("mail.smtp.auth", "true"); 

        			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        			props.put("mail.smtp.socketFactory.fallback","false");
        			props.put("mail.smtp.port","465");
        			props.put("mail.smtp.socketFactory.port","465");




        			Session s=Session.getInstance(props);//���������½�һ���ʼ��Ự
        			s.setDebug(true);

        			MimeMessage message=new MimeMessage(s);//���ʼ��Ự�½�һ����Ϣ����



        			//�����ʼ�
        			InternetAddress from=new InternetAddress("javaeteam3@gmail.com");
        			message.setFrom(from);//���÷�����
        			InternetAddress to=new InternetAddress(mainEmail);
        			message.setRecipient(Message.RecipientType.TO,to);//�����ռ���,���������������ΪTO
        			message.setSubject(title);//��������
        			message.setText(abst);//�����ż�����
        			message.setSentDate(new Date());//���÷���ʱ��

        			//�����ʼ�
        			message.saveChanges();//�洢�ʼ���Ϣ
        			Transport transport=s.getTransport("smtp");
        			transport.connect("smtp.gmail.com","javaeteam3@gmail.com","weizhao888");//��smtp��ʽ��¼����
        			transport.sendMessage(message,message.getAllRecipients());//�����ʼ�,���еڶ�������������
        			//����õ��ռ��˵�ַ
        			transport.close();

        			
        			}catch(MessagingException e){
        			System.out.println(e.toString());
        			}
          }else{
        	  
        	  request.setAttribute("errorMsg", "fail!");  
              request.getRequestDispatcher("SendEmailerror.jsp").forward(request,response); 
        	  
          }
        	  
           
   
          
          
      } catch (Exception e) {  
          e.printStackTrace();    
          request.setAttribute("errorMsg", "fail!");  
          request.getRequestDispatcher("uploaderror.jsp").forward(request,response);   
      }  
      
      
      
	}

}
