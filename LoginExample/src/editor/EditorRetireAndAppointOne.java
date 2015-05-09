package editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginSystem.User;

import com.google.gson.Gson;

import editor.userInfo;
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
		ArrayList<userInfo> ui = new ArrayList<userInfo>();
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
			ps=con.prepareStatement("select * from User");
			rs = ps.executeQuery();
			while (rs.next()) {
	        	//al.add(rs.getString("username"));
				userInfo ui2= new userInfo(rs.getString("username"), rs.getString("role"));
				//artiCle2.setArticleName(rs.getString("articleName"));
	        	//artiCle2.setArticleAbstract(rs.getString("abstract"));
	        	ui.add(ui2);
	        	System.out.println(rs.getString("username")+" " + rs.getString("role"));
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
		  HttpSession session = request.getSession();
		  session.setAttribute("userInfo", ui);
		  String LOGIN_PAGE = "EditUser.jsp";
		  response.sendRedirect(LOGIN_PAGE);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}
	
	private void write(HttpServletResponse response,Map<String,Object> map) throws IOException
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	

}
