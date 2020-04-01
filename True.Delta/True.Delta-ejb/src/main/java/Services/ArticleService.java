package Services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Article;
import Entities.Company;
import Interfaces.ArticleServiceLocal;
import Interfaces.ArticleServiceRemote;

@Stateless
public class ArticleService implements ArticleServiceLocal, ArticleServiceRemote {
	@PersistenceContext(unitName="primary")
	EntityManager em;
	
	@Override
	public int addArticle(Article a) {
		System.out.println("Adding Article: " + a.getTitle());
		em.persist(a);
		System.out.println("Article ***** " + a.getTitle() + " **** added successfully ");
		return a.getId();
	}

	@Override
	public void removeArticle(int id) {
		System.out.println("Removing Article: Processing...");
		em.remove(em.find(Article.class, id));
		System.out.println("Article removed successfully ");
		
	}

	@Override
	public List<Article> findAllArticles() {
		System.out.println("Searching for all Articles: Processing");
		List<Article> articles= em.createQuery("from Article", Article.class).getResultList();
		System.out.println("Out of findAllUsers: ");
		return articles;
	}

	@Override
	public List<Article> findArticleByFirm(Company c) {
		List<Article> articles = em.createQuery("select a from Article s where SOCIETE=" + c.getName(),Article.class).getResultList();
		return articles;
	}

	@Override
	public List<Article> findArticleByScore(int score) {
		List<Article> articles = em.createQuery("select a from Article s where ARTICLE_SCORE>" + score,Article.class).getResultList();
		return articles;
	}

	@Override
	public List<Article> findArticleByPrediction(int prediction) {
		List<Article> articles = em.createQuery("select a from Article s where ARTICLE_SCORE>" + prediction,Article.class).getResultList();
		return articles;
	}

	
	
}
