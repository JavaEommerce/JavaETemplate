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
	
	private String uploadPath = "Testtt\\"; // 上传文件的目录    
	private String tempPath = "Testtt\\"; // 临时文件目录    
	private String serverPath = null;   
    private String[] fileType = new String[]{".pdf"};  
    private int sizeMax = 5;//图片最大上限    
	
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
		
		
		
      //Servlet初始化时执行,如果上传文件目录不存在则自动创建    
        if(!new File(serverPath+uploadPath).isDirectory()){   
            new File(serverPath+uploadPath).mkdirs();    
        }    
        if(!new File(serverPath+tempPath).isDirectory()){  
            new File(serverPath+tempPath).mkdirs();  
        }   
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        factory.setSizeThreshold(5*1024); //最大缓存    
        factory.setRepository(new File(serverPath+tempPath));//临时文件目录    
            
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setSizeMax(sizeMax*1024*1024);//文件最大上限   
          
        String filePath = null;    
        try {    
            List<FileItem> items = upload.parseRequest(request);//获取所有文件列表   
            //  
            for (int i=0;i<items.size();i++) {  
                //里面一个for循环，获取一行的数据  
                FileItem item = items.get(i);  
              	if(!item.isFormField()){//文件名    
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
                //非文件流     
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
        			Properties props=new Properties();//也可用Properties props = System.getProperties(); 
        			props.put("mail.smtp.host","smtp.gmail.com");//存储发送邮件服务器的信息
        			props.put("mail.smtp.user", "javaeteam3@gmail.com");  
        			props.put("mail.smtp.password", "weizhao888");  
        			props.put("mail.smtp.auth", "true"); 

        			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        			props.put("mail.smtp.socketFactory.fallback","false");
        			props.put("mail.smtp.port","465");
        			props.put("mail.smtp.socketFactory.port","465");




        			Session s=Session.getInstance(props);//根据属性新建一个邮件会话
        			s.setDebug(true);

        			MimeMessage message=new MimeMessage(s);//由邮件会话新建一个消息对象



        			//设置邮件
        			InternetAddress from=new InternetAddress("javaeteam3@gmail.com");
        			message.setFrom(from);//设置发件人
        			InternetAddress to=new InternetAddress(mainEmail);
        			message.setRecipient(Message.RecipientType.TO,to);//设置收件人,并设置其接收类型为TO
        			message.setSubject(title);//设置主题
        			message.setText(abst);//设置信件内容
        			message.setSentDate(new Date());//设置发信时间

        			//发送邮件
        			message.saveChanges();//存储邮件信息
        			Transport transport=s.getTransport("smtp");
        			transport.connect("smtp.gmail.com","javaeteam3@gmail.com","weizhao888");//以smtp方式登录邮箱
        			transport.sendMessage(message,message.getAllRecipients());//发送邮件,其中第二个参数是所有
        			//已设好的收件人地址
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
