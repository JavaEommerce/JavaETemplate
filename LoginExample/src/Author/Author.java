package Author;

import java.io.Serializable;

public class Author implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -229943365280689964L;
	private String authorName = null;
	private String email = null;
	private int submitState;
	private int id;
	
	public Author(String reviewerName,int selectedNum,int id){
		this.authorName=reviewerName;
		this.email=email;
		this.submitState=selectedNum;
		this.id=id;
	}
	
	
	
	public String getAuthorName() {
		return this.authorName;
	}



	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}



	public String getEmail() {
		return this.email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getSubmitState() {
		return this.submitState;
	}



	public void setSubmitState(int submitState) {
		this.submitState = submitState;
	}



	public int getId() {
		return this.id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "id: "+this.id+" State:"
		+this.submitState+" Author Name:"+this.authorName+" email:"+this.email;
		return result;
	}
	
}
