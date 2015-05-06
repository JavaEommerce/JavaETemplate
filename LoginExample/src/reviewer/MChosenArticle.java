package reviewer;

import java.io.Serializable;

public class MChosenArticle implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleName;
	private String reviewStatus;
	private String reviewerName;
	
	
	public MChosenArticle(String articleName, String reviewStatus, String reviewerName){
		this.articleName=articleName;
		this.reviewStatus=reviewStatus;
		this.reviewerName=reviewerName;
	}
	
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus=reviewStatus;
	}
	
	public String getReviewStatus() {
		return this.reviewStatus;
	}
	
	
	public void setReviewerName(String reviewerName) {
		this.reviewerName=reviewerName;
	}
	
	public String getReviewerName() {
		return this.reviewerName;
	}
	
	public void setArticleName(String articleName) {
		this.articleName=articleName;
	}
	
	public String getArticleName() {
		return this.articleName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "Article name: "+this.articleName+"Reviewer name: "+this.reviewerName+"Reviewer status: "+reviewStatus;
		return result;
	}
}
