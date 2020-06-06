package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
import Entities.Ratings;
import Entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.RatingServiceInterface;
import Interfaces.VisitorServiceInterface;

@ManagedBean(name = "RatingBean")
@SessionScoped
public class RatingBean {

@EJB 	
RatingServiceInterface es ;

private int Id;
private float Rating;
private String cmntr ;
@EJB 	
AssetManagerServiceInterface am ;


public String getCmntr() {
	return cmntr;
}


public void setCmntr(String cmntr) {
	this.cmntr = cmntr;
}



private String nomAM ;


@ManagedProperty(value = "#{loginBean}")
private LoginBean login;






public LoginBean getLogin() {
	return login;
}


public void setLogin(LoginBean login) {
	this.login = login;
}


public int getId() {
	return Id;
}


public void setId(int id) {
	Id = id;
}



public float getRating() {
	return (float) Rating;
}


public void setRating(float rating) {
	Rating = rating;
}





public String getNomAM() {
	return nomAM;
}


public void setNomAM(String nomAM) {
	this.nomAM = nomAM;
}


public String add() {
	Ratings Vh = new Ratings();
	
	Vh.setIdVisitor(login.getUser().getId());
    Vh.setCmntr(this.getCmntr());
	
	Vh.setNomAM(this.getNomAM());
	
	
	Vh.setRating(this.getRating());
 
	

 
 	es.addRating(Vh);

	Vh.setNomAM("");

	Vh.setRating(0);
	
	
	

	return "/Template/ListAM?faces-redirect=true";
	
}



public RatingBean() {
	super();
	// TODO Auto-generated constructor stub
}



}
