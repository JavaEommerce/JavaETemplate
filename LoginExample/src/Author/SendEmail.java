package Author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class SendEmail
 */
@WebServlet("/SendEmail")
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
		
		
		try{

			//从html表单中获取邮件信息
			String tto=request.getParameter("to");
			String ttitle=request.getParameter("title");
			String tcontent=request.getParameter("content");
			
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
			InternetAddress to=new InternetAddress(tto);
			message.setRecipient(Message.RecipientType.TO,to);//设置收件人,并设置其接收类型为TO
			message.setSubject(ttitle);//设置主题
			message.setText(tcontent);//设置信件内容
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
		
		
		
		
		
	}

}
