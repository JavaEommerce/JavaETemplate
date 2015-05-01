package loginSystem;

public class User {
	
	private String userName=null;
	
	
	public User(String userName){
		this.userName=userName;
	}
	
	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "User: "+this.userName;
		return result;
	}
}	
