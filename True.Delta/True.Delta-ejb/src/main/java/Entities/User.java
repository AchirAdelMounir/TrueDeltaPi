package Entities;
import java.io.Serializable;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



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
	
	
	
	@Embedded
	private Visitor visitor;
	
	@Embedded
	private AssetManager asset_manager;
	//@Column(name = "USER_TYPE")
	//private UserType Type;
	
	@Enumerated(EnumType.STRING) 
    Enumerations.UserType UserType;
	
	
	
	
	@OneToMany(mappedBy="User")
	private Set<Feedback> Feedbacks;
	@OneToMany(mappedBy="User")
	private Set<Complain> Complains;
	@OneToMany(mappedBy="User")
	private Set<Complain> Articles;
	@OneToMany(mappedBy="User")
	private Set<Contract> Contratcs;
	@OneToOne 
	private Portfolio portfolio;

	
	
	


	public AssetManager getAsset_manager() {
		return asset_manager;
	}

	public void setAsset_manager(AssetManager asset_manager) {
		this.asset_manager = asset_manager;
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

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Set<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(Set<Contract> contratcs) {
		Contratcs = contratcs;
	}

	public Enumerations.UserType getUserType() {
		return UserType;
	}

	public void setUserType(Enumerations.UserType userType) {
		UserType = userType;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String nom, String prenom, String adresseMail, String password, String login, Visitor visitor,
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
	}

	public User(String nom, String prenom, String adresseMail, String password, String login, 
			 Enumerations.UserType userType) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresseMail = adresseMail;
		this.password = password;
		this.login = login;
		
		UserType = userType;
	}







	
	

	
	

}
