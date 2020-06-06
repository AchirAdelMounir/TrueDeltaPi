package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Entities.Administrator;
import Entities.AssetManager;
import Entities.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import Interfaces.AdminServiceInterface;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.VisitorServiceInterface;

@ManagedBean(name = "AdminBean")
@SessionScoped
public class AdminBean {

@EJB 	
AdminServiceInterface es ;
private int Id;
private String adresseMail;
private String nom ;
private String password ;


public int getId() {
	return Id;
}


public void setId(int id) {
	Id = id;
}


public String getAdresseMail() {
	return adresseMail;
}


public void setAdresseMail(String mail) {
	this.adresseMail = mail;
}


public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String add() {
	Administrator Vh = new Administrator();
	Vh.setNom(this.getNom());
	Vh.setAdresseMail(this.getAdresseMail());
	Vh.setPassword(this.getPassword());
   
 
 	es.addAdmin(Vh);
	
	Vh.setNom("");
	Vh.setPassword("");
	Vh.setAdresseMail("");
	
	
	

	return "/Template/ListAM?faces-redirect=true";
	
}


public AdminBean() {
	super();
	// TODO Auto-generated constructor stub
}



}
