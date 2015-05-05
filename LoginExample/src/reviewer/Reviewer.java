package reviewer;

import java.io.Serializable;

public class Reviewer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -229943365280689964L;
	private String reviewerName = null;
	private int selectedNum;
	private int id;
	
	public Reviewer(String reviewerName,int selectedNum,int id){
		this.reviewerName=reviewerName;
		this.selectedNum=selectedNum;
		this.id=id;
	}
	
	public String getReviewerName() {
		return this.reviewerName;
	}
	
	public void setReviewerName(String newName) {
		this.reviewerName=newName;
	}
	
	public void setSelectedNum(int newNum) {
		this.selectedNum=newNum;
	}
	
	public int getSelectedNum() {
		return this.selectedNum;
	}
	
	public void setId(int newId) {
		this.id=newId;
	}
	
	public int getId() {
		return this.id;
	}
	
}
