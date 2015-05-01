package reviewer;

import java.io.Serializable;

public class Reviewer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -229943365280689964L;
	private String reviewerName = null;
	
	public Reviewer(String reviewerName){
		this.reviewerName=reviewerName;
	}
	
	public String getReviewerName() {
		return this.reviewerName;
	}
	
	public void setReviewerName(String newName) {
		this.reviewerName=newName;
	}
}
