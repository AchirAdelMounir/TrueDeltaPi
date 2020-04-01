package Interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.Article;
import Entities.Company;

@Local
public interface ArticleServiceLocal {
	
	public int addArticle(Article a);
	public void removeArticle(int IdSecurity);
	public List<Article> findAllArticles();
	public List<Article> findArticleByFirm(Company c);
	public List<Article> findArticleByScore(int score);
	public List<Article> findArticleByPrediction(int prediction);

}
