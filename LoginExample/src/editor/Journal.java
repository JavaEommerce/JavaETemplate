package editor;

import java.io.Serializable;

public class Journal {
	private static final long serialVersionUID = 1L;
	private String journalID;
	private String journalTitle;
	private String journalAims;
	private String journalGoals;
	private String journalStyles;
	private String journalPublishTemplate;
	private String journalProfileUrl;
	
	public Journal(String journalID,String journalTitle,String journalAims,String journalGoals,String journalStyles,String journalPublishTemplate,String journalProfileUrl){
		this.journalID = journalID;
		this.journalTitle = journalTitle;
		this.journalAims = journalAims;
		this.journalGoals = journalGoals;
		this.journalStyles = journalStyles;
		this.journalPublishTemplate = journalPublishTemplate;
		this.journalProfileUrl = journalProfileUrl;
	}
	
	public String getjournalID(){
		return this.journalID;
	}
	public void setjournalTitle(String journalTitle) {
		this.journalTitle=journalTitle;
	}
	
	public String getjournalTitle() {
		return this.journalTitle;
	}
	
	public void setjournalAims(String journalAims) {
		this.journalAims=journalAims;
	}
	
	public String getjournalAims() {
		return this.journalAims;
	}
	public void setjournalGoals(String journalGoals) {
		this.journalGoals=journalGoals;
	}
	
	public String getjournalGoals() {
		return this.journalGoals;
	}
	public void setjournalStyles(String journalStyles) {
		this.journalStyles=journalStyles;
	}
	
	public String getjournalStyles() {
		return this.journalStyles;
	}
	public void setjournalProfileUrl(String journalProfileUrl) {
		this.journalProfileUrl=journalProfileUrl;
	}
	
	public String getjournalProfileUrl() {
		return this.journalProfileUrl;
	}
}



