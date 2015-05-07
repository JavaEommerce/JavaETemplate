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
	
	public void addSelectedNum1() {
		this.selectedNum=this.selectedNum+1;
	}
	
	public void setId(int newId) {
		this.id=newId;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "id: "+this.id+" Number of article selected:"
		+this.selectedNum+" Reviewer Name:"+this.reviewerName;
		return result;
	}
	
}
