package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Article;
import Entities.Company;

@Remote
public interface ArticleServiceRemote {
	
	public int addArticle(Article a);
	public void removeArticle(int IdSecurity);
	public List<Article> findAllArticles();
	public List<Article> findArticleByFirm(Company c);
	public List<Article> findArticleByScore(int score);
	public List<Article> findArticleByPrediction(int prediction);

}
