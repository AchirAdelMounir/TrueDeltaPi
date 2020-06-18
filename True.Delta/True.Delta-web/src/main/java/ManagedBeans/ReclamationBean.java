package ManagedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Init;
import javax.ejb.Schedule;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;

import Entities.Reclamations;
import Services.ReclamationService;

@ManagedBean(name = "eymen")
@SessionScoped
public class ReclamationBean implements Serializable {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private List<Reclamations> lr;

	@EJB
	ReclamationService r;

	

	private String Description;

	private String Categories;

	private Date date;

	public List<Reclamations> getLr() {
		return lr;
	}

	public void setLr(List<Reclamations> lr) {
		this.lr = lr;
	}

	public List<Reclamations> displayRec() {
		lr = r.findAllRecs();
		return lr;
	}

	public String DeleteRec(int id) {
		r.removeRec(id);
		return ("DisplayRecs?faces-redirect=true");
	}

	public String DisplayRec(int id) {
		r.findRecById(id);
		return("Detail?faces-redirect=true");
	}

	public void Update() {

	}

	public String addrec() {
		System.out.println("bijor");
		Reclamations R;
		
		R=new Reclamations();
		System.out.println(R);
		R.setCategories(Categories);
		R.setDate(date);
		R.setDescription(Description);
		R.setTraitement(false);
		r.addRec(R);
		System.out.println(R);
		return ("DisplayRecs?faces-redirect=true");
	}



	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCategories() {
		return Categories;
	}

	public void setCategories(String categories) {
		Categories = categories;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
