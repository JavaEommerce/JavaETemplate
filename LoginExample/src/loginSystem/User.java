package loginSystem;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName=null;
	private int id;
	private int role;
	
	public User(String userName,int ID,int role){
		this.userName=userName;
		this.id=ID;
		this.role=role;
	}
	
	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setId(int newId) {
		this.id=newId;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setRole(int newRole) {
		this.role=newRole;
	}
	
	public int getRole() {
		return this.role;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "User: "+this.userName+"Role: "+this.role;
		return result;
	}
}	
