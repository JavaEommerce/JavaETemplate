package reviewer;

import java.util.HashSet;
import java.util.Set;

public class ArticleBag {
	
	private Set<ReviewingArticle> reviewingArticles = new HashSet<ReviewingArticle>();
	
	public ArticleBag(){
		
	}
	
	public void addArticle(ReviewingArticle r){
		reviewingArticles.add(r);
	}
	
	public Set<ReviewingArticle> getArticles() {
		return this.reviewingArticles;
	}
}
