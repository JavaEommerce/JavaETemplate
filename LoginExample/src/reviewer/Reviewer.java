package reviewer;

import java.io.Serializable;

public class Reviewer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -229943365280689964L;
	private String reviewerName = null;
	private int selectedNum;
	
	public Reviewer(String reviewerName,int selectedNum){
		this.reviewerName=reviewerName;
		this.selectedNum=selectedNum;
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
	
	
}
