package editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		PrintWriter out= response.getWriter();
//        out.println("<font color=black>"+"This is retire page"+"</font>");
//        out.println("<h2>do you want to retire</h2>");
		
		Map<String,Object> map = new HashMap<String,Object>();
		boolean isValid = false;
		String username = request.getParameter("username");
        if(username !=null&& username.trim().length()!=0)
        {
        	isValid = true;
        	map.put("username", username);
        }
        map.put("isValid", isValid);
        write(response,map);
        System.out.println("map....."+username);
	}
	
	private void write(HttpServletResponse response,Map<String,Object> map) throws IOException
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
	

}
