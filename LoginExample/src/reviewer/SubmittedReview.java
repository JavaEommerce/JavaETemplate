package reviewer;

import java.io.Serializable;

public class SubmittedReview implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reviewerName = null;
	private String articleName = null;
	private String overallJudgement = null;
	private String level = null;
	private String summary = null;
	private String criticism = null;
	private String smallerrors = null;
	private String reviseInfo = null;
	private int reviseTime = 0;
	private boolean reviseAccepted = false;
	
	public SubmittedReview(String reviewerName,String articleName,
			String overallJudgement,String level,String summary,String criticism,
			String smallerrors,String reviseInfo,int reviseTime,int reviseAccepted){
		
		this.reviewerName = reviewerName;
		this.articleName = articleName;
		this.overallJudgement = overallJudgement;
		this.level = level;
		this.summary = summary;
		this.criticism = criticism;
		this.smallerrors = smallerrors;
		this.reviseInfo = reviseInfo;
		this.reviseTime = reviseTime;
		setReviseAccepted(reviseAccepted);
		
	}
	public void setReviewerName(String articleName) {
		this.reviewerName=articleName;
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
	
	
	
	public void setLevel(String level) {
		this.level=level;
	}
	
	public String getLevel() {
		return this.level;
	}
	
	
	
	public void setOverallJudgement(String overallJudgement) {
		this.overallJudgement=overallJudgement;
	}
	
	public String getOverallJudgement() {
		return this.overallJudgement;
	}
	
	
	
	public void setSummary(String summary) {
		this.summary=summary;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	
	
	public void setCriticism(String criticism) {
		this.criticism=criticism;
	}
	
	public String getCriticism() {
		return this.criticism;
	}
	
	
	
	public void setReviseInfo(String reviseInfo) {
		this.reviseInfo=reviseInfo;
	}
	
	public String getReviseInfo() {
		return this.reviseInfo;
	}
	
	
	
	public void setReviseTime(int reviseTime) {
		this.reviseTime=reviseTime;
	}
	
	public int getReviseTime() {
		return this.reviseTime;
	}
	
	
	
	public void setReviseAccepted(int reviseAccepted) {
		if (reviseAccepted==0) {
			this.reviseAccepted=false;
		} else {
			this.reviseAccepted=true;
		}
	}
	
	public boolean getReviseAccepted() {
		return this.reviseAccepted;
	}
	
	
	
	public void setSmallerrors(String smallerrors) {
		this.smallerrors=smallerrors;
	}
	
	public String getSmallerrors() {
		return this.smallerrors;
	}
	
}
