package reader;
import java.io.Serializable;
public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String articlename;
	private String keywords;
	private String abstractinfo;
	private String url;
	private String domain;
	private String uploadString;
	private String ispublish;
	private String affiliations;
	private String currentreviewnum;
	
	
	public Article(String articlename,String keywords,String abstractinfo,String url,String domain,String uploadString,String ispublish,String affiliations,String currentreviewnum){
		this.articlename=articlename;
		this.keywords=keywords;
		this.abstractinfo=abstractinfo;
		this.url=url;
		this.domain=domain;
		this.uploadString=uploadString;
		this.ispublish=ispublish;
		this.affiliations=affiliations;
		this.currentreviewnum=currentreviewnum;
	}


	public String getArticlename() {
		return articlename;
	}


	public void setArticlename(String articlename) {
		this.articlename = articlename;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public String getAbstractinfo() {
		return abstractinfo;
	}


	public void setAbstractinfo(String abstractinfo) {
		this.abstractinfo = abstractinfo;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getUploadString() {
		return uploadString;
	}


	public void setUploadString(String uploadString) {
		this.uploadString = uploadString;
	}


	public String getIspublish() {
		return ispublish;
	}


	public void setIspublish(String ispublish) {
		this.ispublish = ispublish;
	}


	public String getAffiliations() {
		return affiliations;
	}


	public void setAffiliations(String affiliations) {
		this.affiliations = affiliations;
	}


	public String getCurrentreviewnum() {
		return currentreviewnum;
	}


	public void setCurrentreviewnum(String currentreviewnum) {
		this.currentreviewnum = currentreviewnum;
	}
	
	
	
	
	
}


