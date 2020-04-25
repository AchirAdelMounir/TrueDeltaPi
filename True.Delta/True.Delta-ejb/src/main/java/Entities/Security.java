package Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SECURITIES")

public class Security implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -558553967080513790L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int Id;
	@Column(name = "TYPE")
	private String Type;
	@Column(name = "Price")
	private double Price;

	@Embedded
	private Stock S;
	@Embedded
	private Bond B;
	@Transient
	double Volatility;

	// Relations
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "COMPANY_SYMBOL", referencedColumnName = "SYMBOL")
	private Company Company;
	@OneToMany(cascade = {CascadeType.MERGE},mappedBy="S")
	private Set<Flux> F;

	// Constructeurs 
	public Security()
	{
		
	}
	public Security(Stock s) {
		super();
		S = s;
	}

	public Security(Bond b) {
		super();
		B = b;
	}

	// Getters Setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Stock getS() {
		return S;
	}

	public void setS(Stock s) {
		S = s;
	}

	public Bond getB() {
		return B;
	}

	public void setB(Bond b) {
		B = b;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	
	public double getVolatility() {
		return Volatility;
	}
	public void setVolatility(double volatility) {
		Volatility = volatility;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "Security [Id=" + Id + ", Type=" + Type + ", Price=" + Price + ", S=" + S + ", B=" + B + ", Volatility="
				+ Volatility + ", Company=" + Company + ", Portfolio=";
	}
	public Set<Flux> getF() {
		return F;
	}
	public void setF(Set<Flux> f) {
		F = f;
	}

}
