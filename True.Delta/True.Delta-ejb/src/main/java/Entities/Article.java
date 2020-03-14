package Entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="T_UTILISATEUR")
public class Article implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_ID")
	private int id;
	
	@Column(name = "ARTICLE_TEXT")
	private String article;
	
	@Column(name = "SOCIETE")
	private String firm;
	
	@Column(name = "ARTICLE_SCORE")
	private double score;
	
	@Column(name = "ARTICLE_PREDICTION")
	private String prediction;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User User;
	
	
	

	public Article() {
	}

	public Article(int id, String article, String firm, double score, String prediction) {
		super();
		this.id = id;
		this.article = article;
		this.firm = firm;
		this.score = score;
		this.prediction = prediction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getPrediction() {
		return prediction;
	}

	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}
	
	
	
	
	

}
