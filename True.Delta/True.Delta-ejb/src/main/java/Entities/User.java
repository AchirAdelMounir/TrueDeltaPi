package Entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Enumerations.UserType;


@Entity
@Table(name="USER")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private int id;
	
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
	private AssetManager asset_manager;
	@Column(name = "USER_TYPE")
	private UserType Type;
	
	
	


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
	
	
	
	
	
	

}
