package Entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "watchlist")
public class Watchlist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -558553967080513790L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private User u;

	@ManyToMany( fetch = FetchType.EAGER, cascade = {  CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Company> Companies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public List<Company> getCompanies() {
		return Companies;
	}

	public void setCompanies(List<Company> companies) {
		Companies = companies;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Watchlist [id=" + id + ", u=" + u + ", Companies=" + Companies + "]";
	}

	public Watchlist() {
		super();
		// TODO Auto-generated constructor stub
	}

}
