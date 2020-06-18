package Entities;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


import Enumerations.*;


@Entity
@Table(name="USER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "USER_NOM")
	private String nom;
	
	@Column(name = "USER_PRENOM")
	private String prenom;
	
	@Column(name = "USER_DATE_OF_BIRTH")
	private Date date;
	
	
	@Column(name = "USER_ADRESS_MAIL")
	private String adresseMail;
	
	@Column(name = "USER_PASSWORD")
	private String password;
	
	@Column(name = "USER_LOGIN")
	private String login;
	
	@Enumerated(EnumType.STRING) 
	@Column(name = "USER_TYPE")
	private UserType Type;
	
	@Column(name = "USER_IS_VALID")
	private boolean isValid;
	@Column(name = "USER_PIC")
	private String image;
	
	
	
	

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "USER_TOKEN")
	private String code;

	@Embedded
	private Administrator admin;
	
	
	@Embedded
	private Customer customer;
	
	@Embedded
	private AssetManager asset_manager;
	
	
	
	
	
	
	@OneToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER,mappedBy="user")
	private Set<Feedback> feedbacks;
	@OneToOne
	private Watchlist w;
	@OneToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER,mappedBy="user")
	private Set<Complain> Complains;
	//@OneToMany(mappedBy="user")
	//private Set<Complain> Articles;
	@OneToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER,mappedBy="user")
	private Set<Contract> Contratcs;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}) 
	private Portfolio portfolio;

	public AssetManager getAsset_manager() {
		return asset_manager;
	}

	public void setAsset_manager(AssetManager asset_manager) {
		this.asset_manager = asset_manager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserType getType() {
		return Type;
	}

	public void setType(UserType type) {
		Type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<Complain> getComplains() {
		return Complains;
	}

	public void setComplains(Set<Complain> complains) {
		Complains = complains;
	}

	public Set<Contract> getContratcs() {
		return Contratcs;
	}

	public void setContratcs(Set<Contract> contratcs) {
		Contratcs = contratcs;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String nom, String prenom, Date date, String adresseMail, String password, String login,
			UserType type, boolean isValid, String image, String code, Administrator admin, Customer customer,
			AssetManager asset_manager, Set<Feedback> feedbacks, Set<Complain> complains, Set<Contract> contratcs,
			Portfolio portfolio) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.adresseMail = adresseMail;
		this.password = password;
		this.login = login;
		Type = type;
		this.isValid = isValid;
		this.image = image;
		this.code = code;
		this.admin = admin;
		this.customer = customer;
		this.asset_manager = asset_manager;
		this.feedbacks = feedbacks;
		Complains = complains;
		Contratcs = contratcs;
		this.portfolio = portfolio;
	}

	

	
	
	
	

	

}
