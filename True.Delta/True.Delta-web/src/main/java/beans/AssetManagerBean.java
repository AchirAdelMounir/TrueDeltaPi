package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import Entities.AssetManager;

import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import Interfaces.AssetManagerServiceInterface;

@ManagedBean(name = "AssetManagerBean")
@SessionScoped
public class AssetManagerBean {

@EJB 	
AssetManagerServiceInterface es ;
private int Id;
private String description;
private String experience;
private double score ;
private double rating;
private String risk;
private List<AssetManager> assetManagers = new ArrayList<>();

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}
public double getRating() {
	return rating;
}
public void setRating(double rating) {
	this.rating = rating;
}
public String getRisk() {
	return risk;
}
public void setRisk(String risk) {
	this.risk = risk;
}

public List<AssetManager> getAssetManagers() {
	this.assetManagers= es.getall();
	return assetManagers;
}
public void setAssetManagers(List<AssetManager> assetManagers) {
	this.assetManagers = assetManagers;
}

public String add() {
	AssetManager Vh = new AssetManager();
	
	Vh.setDescription(this.getDescription());
	Vh.setExperience(this.getExperience());
	Vh.setScore(this.getScore());
	Vh.setRating(this.getRating());
	Vh.setRisk(this.getRisk());
	
	es.addAssetManager(Vh);
	
	Vh.setDescription("");
	Vh.setExperience("");
	Vh.setScore(0);
	Vh.setRating(0);
	Vh.setRisk("");
	
	return "/pages/list?faces-redirect=true";
	
}
public void delete( int id) {

	es.deleteAssetManager(id);;
	
}
public String updateAssetManager(AssetManager Vh) {
	
	this.setDescription(Vh.getDescription());
	this.setExperience(Vh.getExperience());
	this.setScore(Vh.getScore());
	this.setRating(Vh.getRating());
	this.setRisk(Vh.getRisk());
	
	
	
	
	return "/pages/update?faces-redirect=true";
	
	
	}
public String update() {
	AssetManager Vh = new AssetManager();
	Vh.setDescription(this.getDescription());
	Vh.setExperience(this.getExperience());
	Vh.setScore(this.getScore());
	Vh.setRating(this.getRating());
	Vh.setRisk(this.getRisk());
	
	
	es.updateAssetManager(Vh);
	
	Vh.setDescription("");
	Vh.setExperience("");
	Vh.setScore(0);
	Vh.setRating(0);
	Vh.setRisk("");

	
	
	
	
	return "/pages/list?faces-redirect=true";
	
}
public List<AssetManager> getAll()
{
	return es.getall();
}
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}


}
