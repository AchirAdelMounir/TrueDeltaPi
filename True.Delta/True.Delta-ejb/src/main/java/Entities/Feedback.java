package Entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="FEEDBACK")
public class Feedback implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="FEEDBACK_ID") 
	int id;
	//Foreign key (id Customer)
	@Column(name="FEEDBACK_RATING") 
	double rating; 
	@Column(name="FEEDBACK_DATE") 
	Date date ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
