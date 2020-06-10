package Entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@DiscriminatorValue(value="Visitor")
@PrimaryKeyJoinColumn(name="Id")
public class Visitor extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "Visitor_FamilyName")
	private String nom;
	
	@Column(name = "Visitor_Name")
	private String prenom;
	
	@Column(name = "Age")
	private int age;
	
	@Column(name = "Residency_Status")
	private String Residence; // propriétaire ou locataire
	
	@Column(name = "Professional_Status")
	private String Profession;
	
	@Column(name = "Type_of_contract")
	private String typeofcontract; // type du contrat de travail
	
	@Column(name = "Resources")
	private String Resource;
	
	@Column(name = "Refund_in_progress")
	private double Refund; // les remboursements en cours (crédits..)
	
	@Column(name = "Credit_Balance")
	private double Credit; // Solde en banque
	
	@Column(name = "Risk")
	private int Risk; // le risque à prendre
	
	@Column(name = "Diversified")
	private boolean Diversity; // divérsifié ou pas 
	


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getResidence() {
		return Residence;
	}


	public void setResidence(String residence) {
		Residence = residence;
	}


	public String getProfession() {
		return Profession;
	}


	public void setProfession(String profession) {
		Profession = profession;
	}


	public String getTypeofcontract() {
		return typeofcontract;
	}


	public void setTypeofcontract(String typeofcontract) {
		this.typeofcontract = typeofcontract;
	}


	public String getResource() {
		return Resource;
	}


	public void setResource(String resource) {
		Resource = resource;
	}


	public double getRefund() {
		return Refund;
	}


	public void setRefund(double refund) {
		Refund = refund;
	}


	public double getCredit() {
		return Credit;
	}


	public void setCredit(double credit) {
		Credit = credit;
	}


	public int getRisk() {
		return Risk;
	}


	public void setRisk(int risk) {
		Risk = risk;
	}


	public boolean isDiversity() {
		return Diversity;
	}


	public void setDiversity(boolean diversity) {
		Diversity = diversity;
	}


	
	
	
	
}
