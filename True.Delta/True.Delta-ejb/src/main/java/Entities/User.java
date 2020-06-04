package Entities;
import java.io.Serializable;
import javax.persistence.Inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Enumerations.Civil_Status;
import Enumerations.ContractType;
import Enumerations.Professional_Status_Type;
import Enumerations.Residency_Status_Type;
import Enumerations.TypeDevise;
import Enumerations.User_Type;

import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;

@Entity
@Table(name="USER")

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	
	@Column(name = "USER_NOM")
	private String nom;
	
	@Column(name = "USER_PRENOM")
	private String prenom;
	
	@Column(name = "USER_ADRESS_MAIL")
	private String adresseMail;
	
	@Column(name = "USER_PASSWORD")
	private String password;
	
	@Column(name = "USER_LOGIN")
	private String login;
	
	@Column(name = "ID_BANQUE")
	private int id_banque;
	
	@Column(name = "Age")
	private int age;
	
	@Column(name = "Credit_Balance")
	private double Credit; // Solde en banque

	
	@Column(name="Account_Number")
	private int Account_Number;
	@Column(name = "Refund")
	private double Refund;


	@Enumerated(EnumType.STRING)
	TypeDevise Devise;

	
	@Enumerated(EnumType.STRING)
	Residency_Status_Type  Residence;
	@Enumerated(EnumType.STRING)
	Professional_Status_Type Profession;


	@Enumerated(EnumType.STRING)
	Civil_Status Civil_Status;

	
	
	
	
	//@Embedded
	//private Visitor visitor;
	

	//@Column(name = "USER_TYPE")
	//private UserType Type;
	
	@Enumerated(EnumType.STRING) 
    User_Type UserType;
	
	
	
	
	/*@OneToMany(mappedBy="User")
	private Set<Feedback> Feedbacks;
	@OneToMany(mappedBy="User")
	private Set<Complain> Complains;
	@OneToMany(mappedBy="User")
	private Set<Complain> Articles;*/

	@OneToMany(mappedBy="user")
	private List<Contract> Contratcs;
	@OneToOne 
	private Portfolio portfolio;

	
	
	
	


/*	public List<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(List<Contract> contratcs) {
		Contratcs = contratcs;
	}*/

	/*public AssetManager getAsset_manager() {
		return asset_manager;
	}

	public void setAsset_manager(AssetManager asset_manager) {
		this.asset_manager = asset_manager;
	}*/

	public List<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(List<Contract> contratcs) {
		Contratcs = contratcs;
	}

	public Residency_Status_Type getResidence() {
		return Residence;
	}

	public void setResidence(Residency_Status_Type residence) {
		Residence = residence;
	}

	public Professional_Status_Type getProfession() {
		return Profession;
	}

	public void setProfession(Professional_Status_Type profession) {
		Profession = profession;
	}

	public Civil_Status getCivil_Status() {
		return Civil_Status;
	}

	public void setCivil_Status(Civil_Status civil_Status) {
		Civil_Status = civil_Status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

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

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	/*public UserType getType() {
		return Type;
	}
	public void setType(UserType type) {
		Type = type;
	}*/

	/*public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}*/

	/*public Set<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(Set<Contract> contratcs) {
		Contratcs = contratcs;
	}*/
	
	

	

	/*public List<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(List<Contract> contratcs) {
		Contratcs = contratcs;
	}*/


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public User(int id, String nom, String prenom, String adresseMail, String password, String login, Visitor visitor,
			AssetManager asset_manager, Enumerations.UserType userType, Set<Feedback> feedbacks,
			Set<Complain> complains, Set<Complain> articles, Set<Contract> contratcs, Portfolio portfolio) {
		super();
		Id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
		this.password = password;
		this.login = login;
		this.visitor = visitor;
		this.asset_manager = asset_manager;
		UserType = userType;
		Feedbacks = feedbacks;
		Complains = complains;
		Articles = articles;
		Contratcs = contratcs;
		this.portfolio = portfolio;
	}*/

	public User_Type getUserType() {
		return UserType;
	}

	public void setUserType(User_Type userType) {
		UserType = userType;
	}

	public User(String nom, String prenom, String adresseMail, String password, String login, 
			 User_Type userType) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
		this.password = password;
		this.login = login;
		
		UserType = userType;
	}

	public int getId_banque() {
		return id_banque;
	}

	public void setId_banque(int id_banque) {
		this.id_banque = id_banque;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCredit() {
		return Credit;
	}

	public void setCredit(double credit) {
		Credit = credit;
	}




	public int getAccount_Number() {
		return Account_Number;
	}

	public void setAccount_Number(int account_Number) {
		Account_Number = account_Number;
	}

	public TypeDevise getDevise() {
		return Devise;
	}

	public void setDevise(TypeDevise devise) {
		Devise = devise;
	}

	/*public Set<Feedback> getFeedbacks() {
		return Feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		Feedbacks = feedbacks;
	}*/

	/*public Set<Complain> getComplains() {
		return Complains;
	}

	public void setComplains(Set<Complain> complains) {
		Complains = complains;
	}*/

	/*public Set<Complain> getArticles() {
		return Articles;
	}

	public void setArticles(Set<Complain> articles) {
		Articles = articles;
	}*/

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
	/*public Set<Feedback> getFeedbacks() {
		return Feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		Feedbacks = feedbacks;
	}*/

	/*public Set<Complain> getArticles() {
		return Articles;
	}

	public void setArticles(Set<Complain> articles) {
		Articles = articles;
	}*/

	/*public List<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(List<Contract> contratcs) {
		Contratcs = contratcs;
	}*/



	public double getRefund() {
		return Refund;
	}

	public void setRefund(double refund) {
		Refund = refund;
	}

	public User(String nom, String prenom, String adresseMail, String password, String login, int id_banque, int age,
			double credit, int account_Number, double refund, TypeDevise devise, Residency_Status_Type residence,
			Professional_Status_Type profession, Enumerations.Civil_Status civil_Status, User_Type userType) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
		this.password = password;
		this.login = login;
		this.id_banque = id_banque;
		this.age = age;
		Credit = credit;
		Account_Number = account_Number;
		Refund = refund;
		Devise = devise;
		Residence = residence;
		Profession = profession;
		Civil_Status = civil_Status;
		UserType = userType;
	}

	public User(int id, String nom, String prenom, String adresseMail, String password, String login, int id_banque,
			int age, double credit, int account_Number, double refund, TypeDevise devise,
			Residency_Status_Type residence, Professional_Status_Type profession,
			Enumerations.Civil_Status civil_Status, User_Type userType) {
		super();
		Id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
		this.password = password;
		this.login = login;
		this.id_banque = id_banque;
		this.age = age;
		Credit = credit;
		Account_Number = account_Number;
		Refund = refund;
		Devise = devise;
		Residence = residence;
		Profession = profession;
		Civil_Status = civil_Status;
		UserType = userType;
	}

	



	
	

	
	

}