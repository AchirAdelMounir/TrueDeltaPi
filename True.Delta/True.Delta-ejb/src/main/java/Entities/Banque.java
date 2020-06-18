package Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Enumerations.TypeDevise;

@Entity
@Table(name="Banque")

public class Banque implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column()
	int  IDClient;
	@Column()
	private String Nom;
	@Column()
	private String Prenom;
	@Column()
	private int Age;
	
	@Column()
    int Amount;
	
	@Column()
	private String Residence; // propriétaire ou locataire
	
	@Column()
	private String Profession;
	
	
	@Column()
	private double Refund; // les remboursements en cours (crédits..)
	
	@Column()
	private double Credit; // Solde en banque

	@Column(name ="Civil_Status")
	private String Civil_Status; 
	

	@Enumerated(EnumType.STRING)
	TypeDevise Devise;
	
	

	public String getCivil_Status() {
		return Civil_Status;
	}


	public void setCivil_Status(String civil_Status) {
		Civil_Status = civil_Status;
	}


	public TypeDevise getDevise() {
		return Devise;
	}


	public void setDevise(TypeDevise devise) {
		Devise = devise;
	}


	public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		this.Nom = nom;
	}


	public String getPrenom() {
		return Prenom;
	}


	public void setPrenom(String prenom) {
		this.Prenom = prenom;
	}


	public int getAge() {
		return Age;
	}


	public void setAge(int age) {
		this.Age = age;
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


	public int getIDClient() {
		return IDClient;
	}


	public void setIDClient(int iDClient) {
		IDClient = iDClient;
	}




	public Banque() {
		super();
	}


	public Banque(int iDClient, String nom, String prenom, int age, String residence, String profession, double refund,
			double credit) {
		super();
		IDClient = iDClient;
		Nom = nom;
		Prenom = prenom;
		Age = age;
		Residence = residence;
		Profession = profession;
		Refund = refund;
		Credit = credit;
	}


	public int getAmount() {
		return Amount;
	}


	public void setAmount(int amount) {
		Amount = amount;
	}


	public Banque(int iDClient, String nom, String prenom, int age, int amount, String residence, String profession,
			double refund, double credit) {
		super();
		IDClient = iDClient;
		Nom = nom;
		Prenom = prenom;
		Age = age;
		Amount = amount;
		Residence = residence;
		Profession = profession;
		Refund = refund;
		Credit = credit;
	}


	@Override
	public String toString() {
		return "Banque [IDClient=" + IDClient + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Age=" + Age + ", Amount="
				+ Amount + ", Residence=" + Residence + ", Profession=" + Profession + ", Refund=" + Refund
				+ ", Credit=" + Credit + "]";
	}
	


	
	
	
	
}

