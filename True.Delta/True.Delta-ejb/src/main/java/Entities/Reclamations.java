package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Reclamations")
public class Reclamations implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IdRec")	
	private int Id ;
	@Column(name ="Description")
	private String Description;
	@Column(name ="Categories")
	private String Categories;
	@Column(name="Date")
	private Date date;
	@Column(name="Traitement")
	private Boolean traitement;
	public Reclamations(int id, String description, String categories, Date date, Boolean traitement) {
		super();
		Id = id;
		Description = description;
		Categories = categories;
		this.date = date;
		this.traitement = traitement;
	}
	public Reclamations() {
		
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCategories() {
		return Categories;
	}
	public void setCategories(String categories) {
		Categories = categories;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getTraitement() {
		return traitement;
	}
	public void setTraitement(Boolean traitement) {
		this.traitement = traitement;
	}
	@Override
	public String toString() {
		return "Reclamations [Id=" + Id + ", Description=" + Description + ", Categories=" + Categories + ", date="
				+ date + ", traitement=" + traitement + "]";
	}
	
	
}
