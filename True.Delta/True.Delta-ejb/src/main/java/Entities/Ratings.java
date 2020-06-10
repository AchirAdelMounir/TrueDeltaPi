package Entities;
import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import Enumerations.*;
import javax.persistence.InheritanceType;

@Entity

@Table(name="Ratings")
public class Ratings implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	
	@Column(name = "NomAM")
	private String nomAM;
	
	@Column(name = "id_Visitor")
	private int IdVisitor;
	
	@Column(name = "Rating")
	private float Rating;
	
	@Column(name = "Cmntr")
	private String Cmntr;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}


	public String getNomAM() {
		return nomAM;
	}

	public void setNomAM(String nomAM) {
		this.nomAM = nomAM;
	}

	public int getIdVisitor() {
		return IdVisitor;
	}

	public void setIdVisitor(int idVisitor) {
		IdVisitor = idVisitor;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
	}

	public Ratings() {
		super();
	}

	public String getCmntr() {
		return Cmntr;
	}

	public void setCmntr(String cmntr) {
		Cmntr = cmntr;
	}
	


	

}
