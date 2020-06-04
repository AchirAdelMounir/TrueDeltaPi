package Entities;



import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@DiscriminatorValue(value="AssetManager")
@PrimaryKeyJoinColumn(name="Id")
public class AssetManager extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="AM_DESCRIPTION")
	String description;
	
	@Column(name="AM_EXPERIENCE")
	String experience;
	
	@Column(name="AM_SCORE")
	double score ;
	
	@Column(name="AM_RATING") 
	double rating;
	
	@Column(name="AM_RISK")
	String risk;
	
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
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
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
	
	
	
	

}
