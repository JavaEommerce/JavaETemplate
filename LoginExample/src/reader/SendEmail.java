package reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

/**
 * Servlet implementation class SendEmail
 */
@WebServlet("/SendEmailforE")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmail() {
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
	}
	
	private void sendEmail(HttpServletRequest request,HttpServletResponse response,String content,String emailaddress,String subject) throws ServletException,IOException {
		
		 try{
            //send email		  
  			Properties props=new Properties();
  			props.put("mail.smtp.host","smtp.gmail.com");
  			props.put("mail.smtp.user", "javaeteam3@gmail.com");  
  			props.put("mail.smtp.password", "weizhao888");  
  			props.put("mail.smtp.auth", "true"); 

  			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
  			props.put("mail.smtp.socketFactory.fallback","false");
  			props.put("mail.smtp.port","465");
  			props.put("mail.smtp.socketFactory.port","465");

  			Session s=Session.getInstance(props);
  			s.setDebug(true);

  			MimeMessage message=new MimeMessage(s);

  			String mainContent = content;
  			
  			
  			InternetAddress from=new InternetAddress("javaeteam3@gmail.com");
  			message.setFrom(from);
  			InternetAddress to=new InternetAddress(emailaddress);
  			message.setRecipient(Message.RecipientType.TO,to);
  			message.setSubject(subject);
  			message.setText(content);
  			message.setSentDate(new Date());

  			//发送邮件
  			message.saveChanges();//存储邮件信息
  			Transport transport=s.getTransport("smtp");
  			transport.connect("smtp.gmail.com","javaeteam3@gmail.com","weizhao888");//以smtp方式登录邮箱
  			transport.sendMessage(message,message.getAllRecipients());//发送邮件,其中第二个参数是所有
  			//已设好的收件人地址
  			transport.close();

			
		
  		}catch(MessagingException e){
  			System.out.println("emailerror");
  			System.out.println(e.toString());
  			PrintWriter out= response.getWriter();
		     out.println("<font color=green>Sorry, Something wrong with the email system, please contact Administrator</font>"); 
  		}                            	 
		
      
		
       
}

}
