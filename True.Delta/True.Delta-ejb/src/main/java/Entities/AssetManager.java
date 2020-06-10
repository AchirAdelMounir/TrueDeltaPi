package Entities;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@DiscriminatorValue(value="AssetManager")
@PrimaryKeyJoinColumn(name="Id")
public class AssetManager extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Column(name="AM_EXPERIENCE")
	String experience;
	
	@Column(name="AM_SCORE")
	double score ;
	
	@Column(name="AM_RATING") 
	double ratingAM;
	
	@Column(name="AM_RISK")
	String risk;
	
	@Column(name="AM_CLASSIFICATION")
	String classification;
	
	@Column(name="AM_CONFIRMATION")
	boolean confirmation ;
	
	@Column(name="AM_Banned")
	boolean ban ;
	
	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}

	@Column(name="AM_DATEINSCRI")

	@Temporal(TemporalType.DATE)
	private Date DateInscri;
	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@OneToMany(mappedBy="assetmanager", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<Portfolio> portfolios = new ArrayList<>();

	
	
	public List<Portfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public boolean getConfirmation() {
		return confirmation;
	}
	
	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	

	
	public String getExperience() {
		return experience;
	}
	
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public double getRatingAM() {
		return ratingAM;
	}
	
	public void setRatingAM(double rating) {
		this.ratingAM = rating;
	}
	
	public String getRisk() {
		return risk;
	}
	
	public void setRisk(String risk) {
		this.risk = risk;
		
	}

	public Date getDateInscri() {
		return DateInscri;
	}

	public void setDateInscri(Date dateInscri) {
		DateInscri = dateInscri;
	}
	
	
	
	

}
