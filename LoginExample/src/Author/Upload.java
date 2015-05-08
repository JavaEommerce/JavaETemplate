package Author;


import java.io.File;  
import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  

import javax.servlet.http.HttpSession;

import Author.Author;
import dbconnectionlib.Dbconnection;

import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  


/**
 * Servlet implementation class Upload
 */

@WebServlet("/Upload")
public class Upload extends HttpServlet {
	
	private String uploadPath = "Testtt\\"; // �ϴ��ļ���Ŀ¼    
	private String tempPath = "Testttt\\"; // ��ʱ�ļ�Ŀ¼    
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

		
		String mainAuthorname="";
        String mainEmail="";
        String mainAffiliation="";
        String otherAuthorname="";
        String otherEmail="";
        String otherAffiliation="";
        String abst="";
        String title="";
        String keywords="";
		
		HttpSession session = request.getSession();
		Author currentAuthor = (Author)session.getAttribute("Author");
		System.out.println(currentAuthor);

//		if(currentAuthor.getSubmitState()!=2){
		
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
                   filePath = serverPath + uploadPath + fileName;  
                   
//************************************************************************
           		//not author
           		if(currentAuthor==null){
           			System.out.println("==null");
           			
           			
           			if(!mainAuthorname.equals("")){
//                    	System.out.println(mainAuthorname);
                          if(mainEmail.indexOf("@") != -1){
                        	  
                        	  String password="";
                    		  for(int w=0;w<=7;w++){
                    			  password += (int)(Math.random()*10);
                    		  }
           			
//database ***************************************
                        	  Dbconnection db=null;
                          	  db = new Dbconnection();
                          	  Connection con = db.getConnection();
                    		  
                          	  PreparedStatement pch = null;		//check whether reviewer exist
                          	  ResultSet rs =null;
                          	  
                          	PreparedStatement pi = null;		//new article
                          	PreparedStatement paa = null;		//new AuthorArticle
                          	
                          	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    		  
                    if(con!=null) {
                        	try{  
                        	
                        		  System.out.println("enter database");

              					
              					pch = con.prepareStatement("select username from User where username=?");
              					pch.setString(1, mainEmail);
              		        	rs = pch.executeQuery();
              		        	
              		        	
              		        	if (rs!=null&&rs.next()) {
              		        		//have account
              		        		System.out.println(mainEmail);

              			            PrintWriter out= response.getWriter();
              			            out.println("<font color=green>You have account,please login first.</font>"); 
              			            
              			            rs.close();   //????
              			            pch.close();
              			            con.close();
              			            break;
              		        	}else{
              		        		//don't have account
              		        	insertUser(response, request, mainEmail, password,con);
                				insertReviewer(response, request, mainEmail, password, con);
                				insertAuthor(response,request,mainEmail,password,mainAuthorname,mainEmail,con);
                          		
                          		pi = con.prepareStatement("insert into Article(articlename, keywords, abstract, url, domain, uploaddate, ispublish, affiliations) values (?,?,?,?,?,?,?,?)");
                				pi.setString(1, title);
                	            pi.setString(2, keywords);
                	            pi.setString(3, abst);
                	            pi.setString(4, filePath);
                				pi.setString(5, "computer");
                				pi.setDate(6, currentDate);
                				pi.setBoolean(7, false);
                				pi.setString(8, otherAffiliation);
                	            pi.execute();
                          		
                	            paa = con.prepareStatement("insert into AuthorArticle(authorname, articlename) values (?,?)");
                	            paa.setString(1, mainAuthorname);
                	            paa.setString(2, title);
                	            paa.execute();
                          		
                          	  
                          	    pi.close();
                          	    paa.close();
                          	    con.close();
              		        		
              		        		
              		        		
                            	  System.out.println("Send email");
//**********************************************************************************************                   			                	  
                            	//email                    	  
                            	                    	  
                            	                    	  System.out.println("Send mail");
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
                            	
                            	                    			String content = "username: "+ mainEmail + "\n"+"password: "+password;
                            	                    			
                            	                    			//�����ʼ�
                            	                    			InternetAddress from=new InternetAddress("javaeteam3@gmail.com");
                            	                    			message.setFrom(from);//���÷�����
                            	                    			InternetAddress to=new InternetAddress(mainEmail);
                            	                    			message.setRecipient(Message.RecipientType.TO,to);//�����ռ���,���������������ΪTO
                            	                    			message.setSubject("Reviewer account");//��������
                            	                    			message.setText(content);//�����ż�����
                            	                    			message.setSentDate(new Date());//���÷���ʱ��
                            	
                            	                    			//�����ʼ�
                            	                    			message.saveChanges();//�洢�ʼ���Ϣ
                            	                    			Transport transport=s.getTransport("smtp");
                            	                    			transport.connect("smtp.gmail.com","javaeteam3@gmail.com","weizhao888");//��smtp��ʽ��¼����
                            	                    			transport.sendMessage(message,message.getAllRecipients());//�����ʼ�,���еڶ�������������
                            	                    			//����õ��ռ��˵�ַ
                            	                    			transport.close();
                            	
                            	                    			
                            	                    			
                            	
                            	                    			
                            	                    			
                            	             			
                            	                    		}catch(MessagingException e){
                            	                    			System.out.println("emailerror");
                            	                    			System.out.println(e.toString());
                            	                    		}                            	  
              		        		
                            	  File file = new File(filePath);  
                                  item.write(file);    
                                  PrintWriter out= response.getWriter();
            			          out.println("<font color=green>Up load successful! Please check your email to find your accoutn!</font>"); 
                                  break;
              		        		
              		        	}
            		        }catch(SQLException e){                                      
                                    System.out.println("SQLException;"+e.getMessage());   
                                }  
              			            
                    		  
                          }                         
           			}else{System.out.println("û��������");	break;}       			
           		}else{System.out.println("û��������");break;	}    
           	}
//************************************************************************not author end
           		//author
           		if(currentAuthor!=null){
           			System.out.println("!=");
          			
           		 Dbconnection db=null;
             	  db = new Dbconnection();
             	  Connection con = db.getConnection();
             	  
             	PreparedStatement pch = null;		//check whether reviewer exist
             	ResultSet rs =null;
       		
             	PreparedStatement ps = null;		//update author state
             	PreparedStatement pi = null;		//new article
             	PreparedStatement paa = null;		//new AuthorArticle
             	
             	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
           			
             	
             	if(con!=null) {
                	try{  
             	ps = con.prepareStatement("update Author set submitstate=? where authorname=?");
                ps.setInt(1,2);
           		ps.setString(2, currentAuthor.getAuthorName());
           		ps.executeUpdate();
           		currentAuthor.setSubmitState(2);
           		
           		pi = con.prepareStatement("insert into Article(articlename, keywords, abstract, url, domain, uploaddate, ispublish, affiliations, currentreviewnum) values (?,?,?,?,?,?,?,?,?)");
 				pi.setString(1, title);
 	            pi.setString(2, keywords);
 	            pi.setString(3, abst);
 	            pi.setString(4, filePath);
 				pi.setString(5, "computer");
 				pi.setDate(6, currentDate);
 				pi.setBoolean(7, false);
 				pi.setString(8, otherAffiliation);
 				pi.setInt(9,0);
 	            pi.execute();
           		
 	            paa = con.prepareStatement("insert into AuthorArticle(authorname, articlename) values (?,?)");
 	            paa.setString(1, currentAuthor.getAuthorName());
 	            paa.setString(2, title);
 	            paa.execute();
           		
           	    ps.close();
           	    pi.close();
           	    paa.close();
           	    con.close();
		            
		            
           	  File file = new File(filePath);  
               item.write(file);    
	            
		            System.out.println("�����ϴ��ɹ�");
		            break;		
                	}catch(SQLException e){                                      
                        System.out.println("SQLException;"+e.getMessage());   
    		            System.out.println("�����ϴ�ʧ��");
                    }  
             	}
		            
           		}
//************************************************************************author end                	  
                	  
                	  
////**************************************************     
//
//                if(!mainAuthorname.equals("")){
////                	System.out.println(mainAuthorname);
//                      if(mainEmail.indexOf("@") != -1){
//                    	  
//                    	  String password="";
//                		  for(int w=0;w<=7;w++){
//                			  password += (int)(Math.random()*10);
//                		  }
////database ***************************************
//                    	  Dbconnection db=null;
//                      	  db = new Dbconnection();
//                      	  Connection con = db.getConnection();
//                      	  
//                      	PreparedStatement pch = null;		//check whether reviewer exist
//                		ResultSet rs =null;
//                		
//                      	PreparedStatement ps = null;		//update author state
//                      	PreparedStatement pi = null;		//new article
//                      	PreparedStatement paa = null;		//new AuthorArticle
//                      	
//                      	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
//                      	  
//                      	  if(con!=null) {
//                      		  try{  
//                      	
//                      		  System.out.println("enter database");
//
//            					String aname = currentAuthor.getAuthorName();
//
//            					
//            					pch = con.prepareStatement("select reviewername from Reviewer where reviewername=?");
//            					pch.setString(1, mainEmail);
//            		        	rs = pch.executeQuery();
//            		        	if (rs!=null&&rs.next()) {
//            		        		System.out.println(mainEmail);
//            		        		//forward to login page to login
//            			            PrintWriter out= response.getWriter();
//            			            out.println("<font color=green>Uploaded!.</font>");
//
//            			            
//            			            ps = con.prepareStatement("update Author set submitstate=? where authorname=?");
//                                    ps.setInt(1,2);
//                              		ps.setString(2, aname);
//                              		ps.executeUpdate();
//                              		currentAuthor.setSubmitState(2);
//                              		
//                              		pi = con.prepareStatement("insert into Article(articlename, keywords, abstract, url, domain, uploaddate, ispublish, affiliations, currentreviewnum) values (?,?,?,?,?,?,?,?,?)");
//                    				pi.setString(1, title);
//                    	            pi.setString(2, keywords);
//                    	            pi.setString(3, abst);
//                    	            pi.setString(4, filePath);
//                    				pi.setString(5, "computer");
//                    				pi.setDate(6, currentDate);
//                    				pi.setBoolean(7, false);
//                    				pi.setString(8, otherAffiliation);
//                    				pi.setInt(9,0);
//                    	            pi.execute();
//                              		
//                    	            paa = con.prepareStatement("insert into AuthorArticle(authorname, articlename) values (?,?)");
//                    	            paa.setString(1, currentAuthor.getAuthorName());
//                    	            paa.setString(2, title);
//                    	            paa.execute();
//                    	            System.out.println("AuthorArticle111");
//                              		
//                              	    ps.close();
//                              	    pi.close();
//                              	    paa.close();
//                              	    con.close();
//            			            
//            			            
//                              	  File file = new File(filePath);  
//                                  item.write(file);    
//        			            
//            			            
//            			            break;
//            					}
//            					
//            					
//            					
//            					insertUser(response, request, mainEmail, password,con);
//            					insertReviewer(response, request, mainEmail, password, con);
//            					
//                      		ps = con.prepareStatement("update Author set submitstate=? where authorname=?");
//                            ps.setInt(1,2);
//                      		ps.setString(2, aname);
//                      		ps.executeUpdate();
//                      		currentAuthor.setSubmitState(2);
//                      		
//                      		pi = con.prepareStatement("insert into Article(articlename, keywords, abstract, url, domain, uploaddate, ispublish, affiliations) values (?,?,?,?,?,?,?,?)");
//            				pi.setString(1, title);
//            	            pi.setString(2, keywords);
//            	            pi.setString(3, abst);
//            	            pi.setString(4, filePath);
//            				pi.setString(5, "computer");
//            				pi.setDate(6, currentDate);
//            				pi.setBoolean(7, false);
//            				pi.setString(8, otherAffiliation);
//            	            pi.execute();
//                      		
//            	            paa = con.prepareStatement("insert into AuthorArticle(authorname, articlename) values (?,?)");
//            	            paa.setString(1, currentAuthor.getAuthorName());
//            	            paa.setString(2, title);
//            	            paa.execute();
//                      	  System.out.println("AuthorAtricle2");
//                      		
//                      	    ps.close();
//                      	    pi.close();
//                      	    paa.close();
//                      	    con.close();
//                      	  	System.out.println(keywords);
//                      		  }catch(SQLException e){  
//                                    
//                                    System.out.println("SQLException;"+e.getMessage());   
//                                }  
//                      		  
//                      	  }
////**********************************************************************************************                   			                	  
////email                    	  
//                    	  
//                    	  System.out.println("Send mail");
//                    	  try{
//                              //send email
//                    		  
//                    		  
//                    		  
//                    			Properties props=new Properties();//Ҳ����Properties props = System.getProperties(); 
//                    			props.put("mail.smtp.host","smtp.gmail.com");//�洢�����ʼ�����������Ϣ
//                    			props.put("mail.smtp.user", "javaeteam3@gmail.com");  
//                    			props.put("mail.smtp.password", "weizhao888");  
//                    			props.put("mail.smtp.auth", "true"); 
//
//                    			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//                    			props.put("mail.smtp.socketFactory.fallback","false");
//                    			props.put("mail.smtp.port","465");
//                    			props.put("mail.smtp.socketFactory.port","465");
//
//                    			Session s=Session.getInstance(props);//���������½�һ���ʼ��Ự
//                    			s.setDebug(true);
//
//                    			MimeMessage message=new MimeMessage(s);//���ʼ��Ự�½�һ����Ϣ����
//
//                    			String content = "username: "+ mainEmail + "\n"+"password: "+password;
//                    			
//                    			//�����ʼ�
//                    			InternetAddress from=new InternetAddress("javaeteam3@gmail.com");
//                    			message.setFrom(from);//���÷�����
//                    			InternetAddress to=new InternetAddress(mainEmail);
//                    			message.setRecipient(Message.RecipientType.TO,to);//�����ռ���,���������������ΪTO
//                    			message.setSubject("Reviewer account");//��������
//                    			message.setText(content);//�����ż�����
//                    			message.setSentDate(new Date());//���÷���ʱ��
//
//                    			//�����ʼ�
//                    			message.saveChanges();//�洢�ʼ���Ϣ
//                    			Transport transport=s.getTransport("smtp");
//                    			transport.connect("smtp.gmail.com","javaeteam3@gmail.com","weizhao888");//��smtp��ʽ��¼����
//                    			transport.sendMessage(message,message.getAllRecipients());//�����ʼ�,���еڶ�������������
//                    			//����õ��ռ��˵�ַ
//                    			transport.close();
//
//                    			
//                    			
//
//                    			
//                    			
//             			
//                    		}catch(MessagingException e){
//                    			System.out.println("emailerror");
//                    			System.out.println(e.toString());
//                    		}
//                      }else{
//                    	  System.out.println("emailelse");
//                    	  request.setAttribute("errorMsg", "fail!");  
//                          request.getRequestDispatcher("SendEmailerror.jsp").forward(request,response);  
//                          break;
//                      } 
//                      
//                }else{
//                	System.out.println("author else");
//                	request.getRequestDispatcher("MainAuthorerror.jsp").forward(request,response);  
//                	break;
//                }
//                
////************************************                      
////upload***************************************                      
//
//                      
//                      File file = new File(filePath);  
//                      item.write(file);    
//                      
////******************************************                      
                      
                      
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
                  if("key1".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key2".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key3".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key4".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key5".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key6".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key7".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key8".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key9".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                	  keywords += ";";
                  }
                  if("key10".equals(name))  {           	  
                	  String key =item.getString("utf-8");
                	  keywords += key;
                  }
                  
                  value = new String(value.getBytes("ISO-8859-1"),"UTF-8");  
//                  System.out.println(value);             
              } 
              	
          
              	
          } 
          
                          
      } catch (Exception e) {  
    	  System.out.println("catch");
          e.printStackTrace();    
          request.setAttribute("errorMsg", "fail!");  
          request.getRequestDispatcher("uploaderror.jsp").forward(request,response);   
      }  
      
//	}else{
//		PrintWriter out= response.getWriter();
//        out.println("<font color=green>FFFFFF,do not.</font>");
//	}
      
	}
//end do post
	
	
	private void insertUser(HttpServletResponse response,HttpServletRequest request,String userName,String password,Connection con) throws ServletException, IOException,SQLException {
		
		
		PreparedStatement ps = null;
		
        try {
            
				ps = con.prepareStatement("insert into User(ID, username, password,role) values (?,?,?,?)");
				ps.setString(1, null);
	            ps.setString(2, userName);
	            ps.setString(3, password);
	            ps.setInt(4, 2);
	            ps.execute();
	           
			
          
          
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
            	if (ps!=null) {
	            	ps.close();
               }
               
            } catch (SQLException e) {
            	System.out.println("sql exception");
            }
        }
	}
	
	private void insertReviewer(HttpServletResponse response,HttpServletRequest request,String userName,String password,Connection con) throws ServletException,IOException {
		
		PreparedStatement psForReviewer = null;
		PreparedStatement psLookupID = null;
		ResultSet rs =null;
		int userID=0;
		
        try {
			psLookupID = con.prepareStatement("select ID from User where username=? and role=? limit 1");
			psLookupID.setString(1, userName);
			psLookupID.setInt(2, 2);
			
	        
	        rs =  psLookupID.executeQuery();
	        if (rs.next()) {
	        	userID = rs.getInt("ID");
	 	        System.out.println(userID);
	 	        rs.close();
			}
	       
	        psForReviewer = con.prepareStatement("insert into Reviewer(reviewername, selectednum,ID) values (?,?,?)");
	        psForReviewer.setString(1, userName);
	        psForReviewer.setInt(2,0);
	        psForReviewer.setInt(3,userID);
	        
	        psForReviewer.execute();
			System.out.println("Author created");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            try {
                
            	if (psLookupID!=null) {
					psLookupID.close();
				}
            	if (psForReviewer!=null) {
            		psForReviewer.close();
               }
               
            } catch (SQLException e) {
            	System.out.println("sql exception");
            }
	        
			
		}
        
	}
	
	private void insertAuthor(HttpServletResponse response,HttpServletRequest request,String userName,String password,String authorName, String email,Connection con) throws ServletException,IOException {
		
		PreparedStatement psForAuthor = null;
		PreparedStatement psLookupID = null;
		ResultSet rs =null;
		int userID=0;
		
        try {
			psLookupID = con.prepareStatement("select ID from User where username=? and role=? limit 1");
			psLookupID.setString(1, userName);
			psLookupID.setInt(2, 2);
			
	        
	        rs =  psLookupID.executeQuery();
	        if (rs.next()) {
	        	userID = rs.getInt("ID");
	 	        System.out.println(userID);
	 	        rs.close();
			}
	       
	        psForAuthor = con.prepareStatement("insert into Author(authorname, email, submitstate,ID) values (?,?,?,?)");
	        psForAuthor.setString(1, authorName);
	        psForAuthor.setString(2, email);
	        psForAuthor.setInt(3,2);
	        psForAuthor.setInt(4,userID);
	        
	        psForAuthor.execute();
			System.out.println("Author created");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            try {
                
            	if (psLookupID!=null) {
					psLookupID.close();
				}
            	if (psForAuthor!=null) {
            		psForAuthor.close();
               }
               
            } catch (SQLException e) {
            	System.out.println("sql exception");
            }
	        
			
		}
        
	}
	
	
}
