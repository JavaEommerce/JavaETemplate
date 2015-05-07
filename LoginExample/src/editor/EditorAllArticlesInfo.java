package editor;

import java.io.Serializable;



public class EditorAllArticlesInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String articleName;
	private String abstractContent;
	private String articleReviewed;
	private String articlePublisher;
	private String articleuploadDate;
	private String articleIsPublished;
	
	public EditorAllArticlesInfo(String articleName,String abstractContent,String articleReviewed, String articleuploadDate,String articleIsPublished){
		this.articleIsPublished = articleIsPublished;
		this.abstractContent= abstractContent;
		this.articleName = articleName;
		this.articleReviewed=articleReviewed;
		this.articleuploadDate = articleuploadDate;
	}
	
	public void setArticleName(String newName) {
		this.articleName=newName;
	}
	
	public String getArticleName() {
		return this.articleName;
	}
	
	public String getArticleAbstract(){
		return this.abstractContent;
	}
	
	public void setArticleAbstract(String AbstractContent){
		this.abstractContent = AbstractContent;
	}
	
	public void setArticleProfileUrl(String reviewd){
		this.articleReviewed = reviewd;
		
	}
	public String getArticleProfileUrl(){
		return this.articleReviewed;
	}
	public void setArticlePublisher(String publisher){
		this.articlePublisher = publisher;
	}
	public String getArticleuploadDate(){
		return this.articleuploadDate;
	}
	public void setArticleuploadDate(String date){
		this.articleuploadDate = date;
	}
	
	
}
