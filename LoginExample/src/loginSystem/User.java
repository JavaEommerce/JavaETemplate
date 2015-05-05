package loginSystem;

public class User {
	
	private String userName=null;
	private int id;
	
	public User(String userName,int ID){
		this.userName=userName;
		this.id=ID;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "User: "+this.userName;
		return result;
	}
}	
