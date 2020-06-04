package Entities;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import Enumerations.ContractType;
import Enumerations.DisponibiliteAM;
import Enumerations.FinancialAsset;

@Embeddable
public class AssetManager  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="AM_DESCRIPTION")
	String description;
	
	@Column(name="AM_EXPERIENCE")
	String experience;
	
	@Column(name="AM_SCORE")
	double scoreAM ;
	
	@Column(name="AM_RATING") 
	double rating;
	
	@Column(name="AM_RISK")
	String risk;
	
	@Column()
	int ScoreCont;
	
	@Enumerated(EnumType.STRING) 
	private  DisponibiliteAM  Disponibilite ;

	
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getExperience() {
		return experience;
	}
	
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public double getScore() {
		return scoreAM;
	}
	
	public void setScore(double score) {
		this.scoreAM = score;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getRisk() {
		return risk;
	}
	
	public void setRisk(String risk) {
		this.risk = risk;
	}

	public DisponibiliteAM getDisponibilite() {
		return Disponibilite;
	}

	public void setDisponibilite(DisponibiliteAM disponibilite) {
		Disponibilite = disponibilite;
	}

	



	
	
	
	
	

}
