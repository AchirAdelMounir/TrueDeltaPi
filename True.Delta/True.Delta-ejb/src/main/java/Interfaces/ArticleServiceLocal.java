package Interfaces;

import java.util.List;

import Entities.Article;
import Entities.Company;

public interface ArticleServiceLocal {
	
	public int addArticle(Article a);
	public void removeArticle(int IdSecurity);
	public List<Article> findAllArticles();
	public List<Article> findArticleByFirm(Company c);
	public List<Article> findArticleByScore(int score);
	public List<Article> findArticleByPrediction(int prediction);

}
