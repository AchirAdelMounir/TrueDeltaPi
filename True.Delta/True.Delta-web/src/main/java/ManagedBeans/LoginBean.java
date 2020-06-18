package ManagedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;

import Entities.User;
import Services.UserService;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userIdToBeUpdated;
    private String nom;
	private String prenom;
	private String adresseMail;
	private String password;
	private String login;
	private User user ;
	private Boolean loggedIn;
	private Enumerations.User_Type UserType;
	public Integer getUserIdToBeUpdated() {
		return userIdToBeUpdated;
	}
	public void setUserIdToBeUpdated(Integer userIdToBeUpdated) {
		this.userIdToBeUpdated = userIdToBeUpdated;
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

	

public Enumerations.User_Type getUserType() {
		return UserType;
	}
	public void setUserType(Enumerations.User_Type userType) {
		UserType = userType;
	}
public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}



@EJB 
UserService userservice;

public static User userConn;

public String doLogin() {

return "home"; } 


public String doLogout() {
FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
return ("Template/login?faces-redirect=true");
}

	
	
	

}
