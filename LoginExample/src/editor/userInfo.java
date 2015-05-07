package editor;

import java.io.Serializable;

public class userInfo {
	private static final long serialVersionUID = 1L;
	private String username;
	private String userrole;
	
	public userInfo(String username,String userrole){
		this.username = username;
		this.userrole = userrole;
	}
	
	public void setUserName(String username) {
		this.username=username;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public void setUserRole(String userrole) {
		this.userrole=userrole;
	}
	
	public String getUserRole() {
		return this.userrole;
	}
	
}



