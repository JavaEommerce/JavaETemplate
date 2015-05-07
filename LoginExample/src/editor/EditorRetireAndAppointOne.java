package editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbconnectionlib.Dbconnection;

/**
 * Servlet implementation class EditorRetireAndAppointOne
 */
@WebServlet("/EditorRetireAndAppointOne")

public class EditorRetireAndAppointOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorRetireAndAppointOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dbconnection db=null;
		String userList = null;
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
			System.out.println("successful");
		}
		
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			ps=con.prepareStatement("select username from User");
			rs = ps.executeQuery();
			if (rs.next()) {
	        	//al.add(rs.getString("username"));
	        	System.out.println(rs.getString("username"));
	        	userList = rs.getString("username");
			}
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				System.out.println("db closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 String name=null;
		  name = "Retire successed, There are all users that U can choose to Appoint. "+userList;
		  response.setContentType("text/plain");  
		  response.setCharacterEncoding("UTF-8"); 
		  response.getWriter().write(name); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		PrintWriter out= response.getWriter();
//        out.println("<font color=black>"+"This is retire page"+"</font>");
//        out.println("<h2>do you want to retire</h2>");
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		boolean isValid = false;
//		String username = request.getParameter("username");
//        if(username !=null&& username.trim().length()!=0)
//        {
//        	isValid = true;
//        	map.put("username", username);
//        }
//        map.put("isValid", isValid);
//        write(response,map);
//        System.out.println("map....."+username);
		int ids;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String val = request.getParameter("id");
        System.out.print(val);
        if(val != null){
            ids = Integer.parseInt(val);
            out.print(ids); //
        }
	}
	
	private void write(HttpServletResponse response,Map<String,Object> map) throws IOException
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	

}
