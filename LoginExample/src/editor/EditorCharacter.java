package editor;

import java.io.Serializable;

public class EditorCharacter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -229943365280689964L;
	private String editorName = null;
	private int selectedNum;
	private int id;
	
	public EditorCharacter(String editorname,int selectedNum,int id){
		this.editorName=editorname;
		this.selectedNum=selectedNum;
		this.id=id;
	}
	
	public String getEditorName() {
		return this.editorName;
	}
	
	public void setEditorName(String newName) {
		this.editorName=newName;
	}
	
	public void setSelectedNum(int newNum) {
		this.selectedNum=newNum;
	}
	
	public int getSelectedNum() {
		return this.selectedNum;
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
		+this.selectedNum+" Editor Name:"+this.editorName;
		return result;
	}
	
}
