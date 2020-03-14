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
import java.util.Date;


@Entity
@Table(name="COMPLAIN") 
public class Complain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="COMPLAIN_ID") 
	int id; 
	//Foreign key (id Customer)
	@Column(name="COMPLAIN_SUBJECT")
	String subject;
	@Column(name="COMPLAIN_DESCRIPTION")
	String description;
	@Column(name="COMPLAIN_STATUS")
	String status;
	@Column(name="COMPLAIN_DATE")
	Date date ;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User User;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
