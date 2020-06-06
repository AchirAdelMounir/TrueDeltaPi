package Entities;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table( name= "COMPANIES")
public class Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="SYMBOL")
	private String Symbol;
	@Column(name="MARKET")
	private String Market;
	@Column(name="NAME")
	private String Name;
	@Column(name="SECTOR")
	private String Sector;
	@Column(name="INDUSTRY")
	private String Industry;
	@OneToMany(mappedBy="Company")
	private Set<Security> Securities;
	
	
	public Company(String symbol, String market, String name, String sector, String industry) {
		super();
		Symbol = symbol;
		Market = market;
		Name = name;
		Sector = sector;
		Industry = industry;
	}
	
	public Company() {
		super();
	}
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public String getMarket() {
		return Market;
	}
	public void setMarket(String market) {
		Market = market;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSector() {
		return Sector;
	}
	public void setSector(String sector) {
		Sector = sector;
	}
	public String getIndustry() {
		return Industry;
	}
	public void setIndustry(String industry) {
		Industry = industry;
	}
	@Override
	public String toString() {
		return "Company [Symbol=" + Symbol + ", Market=" + Market + ", Name=" + Name + ", Sector=" + Sector
				+ ", Industry=" + Industry + "]";
	}

	public Set<Security> getSecurities() {
		return Securities;
	}

	public void setSecurities(Set<Security> securities) {
		Securities = securities;
	}
	
	
}
