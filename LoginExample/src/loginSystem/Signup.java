
package loginSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class Signup
 */
@WebServlet(name = "loginSystem.Signup", urlPatterns = { "/signup" })
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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

		
		String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordVerify = request.getParameter("passwordVerify");

        
        String errorMsg = null;
        if(userName == null || userName.equals("")){
            errorMsg = "User name can't be null or empty.";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty.";
        }
        if (!password.equals(passwordVerify)) {
			errorMsg= "Password is not matched with the first";
		}
       
         
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }
        else{
        	
        	Dbconnection db=null;
    		try {
    			db = new Dbconnection();
    		} catch (ClassNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		Connection con = db.getConnection();
    		
    		if (con==null) {
    			System.out.println("it's closed!");
    		}
    		else{
    			System.out.println("successfullllllll");
    		}
        	
    		try {
    			
				insertUser(response, request, userName, password,con);
				insertAuthor(response, request, userName, password, con);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		System.out.println("register succeed");
	          //forward to index page
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	            PrintWriter out= response.getWriter();
	            out.println("<font color=green>Registration successful, please login</font>");
	            out.println("<p>Back Home: <a href=\"http://localhost:8080/JavaEE/index.jsp\">click me</a></p>");
	            rd.include(request, response);
				System.out.println("what?");
        	
    		
        	
	   }
		
	}
	
	private void insertUser(HttpServletResponse response,HttpServletRequest request,String userName,String password,Connection con) throws ServletException, IOException,SQLException {
		
		
		PreparedStatement ps = null;
		PreparedStatement psLookup = null;
		ResultSet rs =null;
		
        try {
            
        	psLookup = con.prepareStatement("select username from User where username=? limit 1");
        	psLookup.setString(1, userName);
        	rs = psLookup.executeQuery();
        	if (rs!=null&&rs.next()) {
        		System.out.println(userName);
        		//forward to login page to login
	            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
	            PrintWriter out= response.getWriter();
	            out.println("<font color=green>This userName is taken, please change another one.</font>");
	            rd.include(request, response);
	            
			} else {
				ps = con.prepareStatement("insert into User(ID, username, password,role) values (?,?,?,?)");
				ps.setString(1, null);
	            ps.setString(2, userName);
	            ps.setString(3, password);
	            ps.setInt(4, 1);
	            ps.execute();
	           
			}
          
          
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
               
            	if (psLookup!=null) {
					psLookup.close();
				}
            	if (ps!=null) {
	            	ps.close();
               }
               
            } catch (SQLException e) {
            	System.out.println("sql exception");
            }
        }
	}
	
	private void insertAuthor(HttpServletResponse response,HttpServletRequest request,String userName,String password,Connection con) throws ServletException,IOException {
		
		PreparedStatement psForAuthor = null;
		PreparedStatement psLookupID = null;
		ResultSet rs =null;
		
        try {
			psLookupID = con.prepareStatement("select ID from User where username=? limit 1");
			psLookupID.setString(1, userName);
	        rs =  psLookupID.executeQuery();
	        if (rs.next()) {
	        	int userID = rs.getInt("ID");
	 	        System.out.println(userID);
			}
	       
	        psForAuthor = con.prepareStatement("insert into Author(authorname, email, submitstate,ID) values (?,?,?,?)");
	        psForAuthor.setString(1, userName);
	        psForAuthor.setString(2,"sample@sheffield.ac.uk");
	        psForAuthor.setInt(3,1);
	        psForAuthor.setInt(4,1);
	        psForAuthor.execute();
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
