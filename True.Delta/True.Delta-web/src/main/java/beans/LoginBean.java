package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Entities.Administrator;
import Entities.AssetManager;
import Entities.User;
import Entities.Visitor;
import Services.UserService;



@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private String adresseMail;
	private String password;
	private Boolean loggedIn;
	public User user ;
	
	

	

	

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

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@EJB
	UserService ss;

	public String doLogin() {
		String navigateTo = "null";
		user = ss.getUserByEmailAndPassword(adresseMail, password);

		if (user != null  && user instanceof AssetManager) {
			

			
			navigateTo = "/Template/ListAM?faces-redirect=true";
			loggedIn = true;

		} else if (user != null  &&  user instanceof Administrator)
		{
			navigateTo = "/Template/ListAMAdmin?faces-redirect=true";
			loggedIn = true;

			
		}
		
		 else if (user != null  &&  user instanceof Visitor )
			{
				navigateTo = "/Template/ListAMV?faces-redirect=true";
				loggedIn = true;

				
			}
		else
		{
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
		}
		return navigateTo;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Template/login?faces-redirect=true";
	}



	
}
