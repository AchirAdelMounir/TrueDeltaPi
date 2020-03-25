package Entities;

import java.io.Serializable;

import Enumerations.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;




@Embeddable

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "Age")
	private int age;
	
	@Enumerated(EnumType.STRING)
	private Residency_Status_Type Residency_Status; // propriétaire ou locataire
	
	@Enumerated(EnumType.STRING)
	private Professional_Status_Type Profession;
	
	@Enumerated(EnumType.STRING)
	private Type_of_contract_type typeofcontract; // type du contrat de travail
	
	@Column(name = "Resources")
	private double Resource;
	
	@Column(name = "Salary")
	private double Salary;
	
	@Column(name = "Refund_in_progress")
	private double Refund; // les remboursements en cours (crédits,pension alimentaire,autre charge)
	
	@Column(name = "Credit_Balance")
	private double Credit; // Solde en banque
	
	@Column(name = "Risk")
	private float Risk; // le risque à prendre
	
	@Enumerated(EnumType.STRING)
	private Bank_type bank; 
	
	@Column(name = "Score")
	private int score; 
	
	@Enumerated(EnumType.STRING)
	private Diversified_Type Diversity; // divérsifié ou pas 
	
	@Enumerated(EnumType.STRING)
	private Active_account_Type active; // 1 s'il est actif, 0 s'il ne l'est pas
	
	


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	


	public double getSalary() {
		return Salary;
	}


	public void setSalary(double salary) {
		Salary = salary;
	}


	public Residency_Status_Type getResidency_Status() {
		return Residency_Status;
	}


	public void setResidency_Status(Residency_Status_Type residency_Status) {
		Residency_Status = residency_Status;
	}



	public double getResource() {
		return Resource;
	}


	public void setResource(double resource) {
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


	public float getRisk() {
		return Risk;
	}


	public void setRisk(float risk) {
		Risk = risk;
	}


	public Professional_Status_Type getProfession() {
		return Profession;
	}


	public void setProfession(Professional_Status_Type profession) {
		Profession = profession;
	}


	public Type_of_contract_type getTypeofcontract() {
		return typeofcontract;
	}


	public void setTypeofcontract(Type_of_contract_type typeofcontract) {
		this.typeofcontract = typeofcontract;
	}


	public Diversified_Type getDiversity() {
		return Diversity;
	}


	public void setDiversity(Diversified_Type diversity) {
		Diversity = diversity;
	}


	public Active_account_Type getActive() {
		return active;
	}


	public void setActive(Active_account_Type active) {
		this.active = active;
	}


	

	public Bank_type getBank() {
		return bank;
	}


	public void setBank(Bank_type bank) {
		this.bank = bank;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	
	

	
	
	
}
