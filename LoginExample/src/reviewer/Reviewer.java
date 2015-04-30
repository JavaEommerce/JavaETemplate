package reviewer;

import java.io.Serializable;

public class Reviewer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -229943365280689964L;
	private String test = null;
	
	public Reviewer(){
		
	}
	
	public String getTest() {
		return this.test;
	}
	
	public void setTest(String t) {
		test=t;
	}
}
