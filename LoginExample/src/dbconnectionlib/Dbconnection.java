package dbconnectionlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Dbconnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver found");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found" + e);
		}
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "root";
	
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			Statement myStmt = (Statement) con.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from user2");
			while(myRs.next())
			{
				System.out.println(myRs.getString("username"));
			}
			
			System.out.println("Connected");
		} catch (SQLException e) {
			System.out.println("Connection failed");
		}
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			System.out.println("Driver found");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Driver not found" + e);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team153?user=team153&password=80473623";
//		
//		Connection con = null;
//		try {
//			con = DriverManager.getConnection(DB);
//			Statement myStmt = (Statement) con.createStatement();
//			ResultSet myRs = myStmt.executeQuery("select * from test");
//			while(myRs.next())
//			{
//				System.out.println(myRs.getString("username"));
//			}
//			
//			System.out.println("Connected");
//		} catch (SQLException e) {
//			System.out.println("Connection failed");
//		}
		
	}

}
