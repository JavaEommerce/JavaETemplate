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

			//��html���л�ȡ�ʼ���Ϣ
			String tto=request.getParameter("to");
			String ttitle=request.getParameter("title");
			String tcontent=request.getParameter("content");
			
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
			InternetAddress to=new InternetAddress(tto);
			message.setRecipient(Message.RecipientType.TO,to);//�����ռ���,���������������ΪTO
			message.setSubject(ttitle);//��������
			message.setText(tcontent);//�����ż�����
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
		
		
		
		
		
	}

}
