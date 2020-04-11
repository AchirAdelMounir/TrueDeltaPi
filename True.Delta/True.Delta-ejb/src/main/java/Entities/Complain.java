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

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Enumerations.StatusTypeOfComplain;

import java.util.Date;

@Entity
@Table(name="COMPLAIN") 
public class Complain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final String EnumType = null;
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY ) 
	@Column(name="COMPLAIN_ID") 
	private int id; 
	//Foreign key (id Customer)
	/*@Column(name="CUSTOMER_ID") 
	private int idCustomer;*/

	@Column(name="COMPLAIN_SUBJECT")
	private String subject;
	
	@Column(name="COMPLAIN_DESCRIPTION")
	private String description;
	
	/*@Column(name="COMPLAIN_STATUS")
	private String status;*/
	@Enumerated(EnumType.STRING) 
    Enumerations.StatusTypeOfComplain status;
	
	@Column(name="COMPLAIN_DATE")
	private Date date ;
	

	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User user;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}*/
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
	public StatusTypeOfComplain getStatus() {
		return status;
	}
	public void setStatus(StatusTypeOfComplain status) {
		this.status = status;
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
	public void setUser(User user) {
		this.user = user;
	}
	/*public Complain () {
		this.User=new User();
	}*/
	
	

}
