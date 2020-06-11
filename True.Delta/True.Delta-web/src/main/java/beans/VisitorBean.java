package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
import Interfaces.AssetManagerServiceInterface;
import Interfaces.VisitorServiceInterface;

@ManagedBean(name = "VisitorBean")
@SessionScoped
public class VisitorBean {

@EJB 	
VisitorServiceInterface es ;
private int Id;
private String adresseMail;
private String nom ;
private String password ;
private List<Visitor> visitors = new ArrayList<>();


public List<Visitor> getVisitors() {
	visitors=es.getall()
;
	return visitors;
}


public void setVisitors(List<Visitor> visitors) {
	this.visitors = visitors;
}


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
	Visitor Vh = new Visitor();
	Vh.setNom(this.getNom());
	Vh.setAdresseMail(this.getAdresseMail());
	Vh.setPassword(this.getPassword());
   
 
 	es.addVisitor(Vh);
	
	Vh.setNom("");
	Vh.setPassword("");
	Vh.setAdresseMail("");
	
	
	

	return "/Template/ListVisitor?faces-redirect=true";
	
}

public void delete( int id) {

	es.deleteVisitor(id);
	
}



public VisitorBean() 
{
	super();
	// TODO Auto-generated constructor stub
}



}
