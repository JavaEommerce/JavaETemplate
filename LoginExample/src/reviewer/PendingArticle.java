package reviewer;

import java.io.Serializable;

public class PendingArticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleName;
	private String abstractContent;
	
	public PendingArticle(String articleName, String abstractContent){
		this.articleName=articleName;
		this.abstractContent=abstractContent;
	}
	
	public void setArticleName(String newName) {
		this.articleName=newName;
	}
	
	public String getArticleName() {
		return this.articleName;
	}
	
	public void setAbstractContent(String newAbstract) {
		this.abstractContent=newAbstract;
	}
	
	public String getAbstractContent() {
		return this.abstractContent;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "Title: "+articleName+";\n Abstract: "+abstractContent;
		return result;
	}
}
