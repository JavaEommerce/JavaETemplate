package editor;

import java.io.Serializable;



public class EditorArticleReviewInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String reviewername;
	private String authorname;
	private String overalljudgement;
	private String summary;
	
	
	public EditorArticleReviewInfo(String reviewername,String authorname,String overalljudgement, String summary){
		this.reviewername = reviewername;
		this.authorname= authorname;
		this.overalljudgement = overalljudgement;
		this.summary=summary;
	}
		
	public String getreviewername(){
		return this.reviewername;
	}
	
	public String getauthorname() {
		return this.authorname;
	}
	
	public String getoveralljudgement(){
		return this.overalljudgement;
	}
	
	public String getsummary(){
		return this.summary;
	}
	
//	public void setArticleuploadDate(String date){
//		this.articleuploadDate = date;
//	}
	
	
}
