package Entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import Enumerations.LevelRating;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import Enumerations.LevelRating;
import Enumerations.Who;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
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
	private int id;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User user;
	//Foreign key (id Customer)
	//@Column(name="FEEDBACK_RATING") 
	@Enumerated(EnumType.STRING) 
	private Who who;
	@Enumerated(EnumType.STRING) 
	private LevelRating rating;
	public Who getWho() {
		return who;
	}
	public void setWho(Who who) {
		this.who = who;
	}
	@Column(name="FEEDBACK_NOTICE") 
	String notice;
	@Column(name="FEEDBACK_DATE") 
	Date date ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Enumerations.LevelRating getRating() {
		return rating;
	}
	public void setRating(Enumerations.LevelRating rating) {
		this.rating = rating;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User User) {
		this.user = User;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(int id, User user, Who who, LevelRating rating, String notice, Date date) {
		super();
		this.id = id;
		this.user = user;
		this.who = who;
		this.rating = rating;
		this.notice = notice;
		this.date = date;
	}
	
	
	
	
	
	
}
