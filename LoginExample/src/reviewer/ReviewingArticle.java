package reviewer;

import java.io.Serializable;

public class ReviewingArticle implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleName;
	private String reviewStatus;
	private String url;
	
	public ReviewingArticle(String articleName, String reviewStatus, String url){
		this.articleName=articleName;
		this.reviewStatus=reviewStatus;
		this.url=url;
	}
	
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus=reviewStatus;
	}
	
	public String getReviewStatus() {
		return this.reviewStatus;
	}
	
	
	public void setArticleName(String articleName) {
		this.articleName=articleName;
	}
	
	public String getArticleName() {
		return this.articleName;
	}
	
	public void setURL(String url) {
		this.url=url;
	}
	
	public String getURL() {
		return this.url;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "Article name: "+this.articleName+"Reviewer status: "+reviewStatus;
		return result;
	}
}
