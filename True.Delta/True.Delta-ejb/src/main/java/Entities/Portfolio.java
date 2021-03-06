package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;
import Enumerations.*;

@Entity
@Table(name="PORTFOLIO")

public class Portfolio implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="Id_Portfolio")
	private int IdPortfolio;
	
	@Enumerated(EnumType.STRING)
	private Portfolio_Type TypePortfolio;
	
	@Column (name="Returns")
	private double Returns;
	
	@Column (name="Volatility")
	private float Volatility;
	
	@Column (name="Price")
	private double Price;

	
	@Column (name="Sharpe_Ratio")
	private float Ratio;

	
	@OneToOne(cascade = {CascadeType.MERGE},mappedBy="Portfolio")
	private Contract Contract;
	@OneToMany(cascade = {CascadeType.MERGE},mappedBy="P")
	Set<Flux> F;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},mappedBy="portfolio")
	private User User;

	
	public Portfolio() {
		super();
	}
	
	
	


	public Portfolio(int idPortfolio, Portfolio_Type typePortfolio, double returns, float volatility, double price,
			float ratio,Contract contract,User user) {

		super();
		IdPortfolio = idPortfolio;
		TypePortfolio = typePortfolio;
		Returns = returns;
		Volatility = volatility;
		Contract = contract;
		
		User = user;

		Price = price;
		Ratio = ratio;

	}





	@Override
	public String toString() {
		return "Portfolio [IdPortfolio=" + IdPortfolio + ", TypePortfolio=" + TypePortfolio + ", Returns=" + Returns

				+ ", Volatility=" + Volatility + ", Price=" + Price + ", Ratio=" + Ratio + "]";

	}





	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	
	

	public float getRatio() {
		return Ratio;
	}
	
	public void setRatio(float ratio) {
		Ratio = ratio;
	}




	public Contract getContract() {
		return Contract;
	}

	public void setContract(Contract contract) {
		Contract = contract;
	}

	

	public int getIdPortfolio() {
		return IdPortfolio;
	}
	
	public void setIdPortfolio(int idPortfolio) {
		IdPortfolio = idPortfolio;
	}

	public Portfolio_Type getTypePortfolio() {
		return TypePortfolio;
	}

	public void setTypePortfolio(Portfolio_Type typePortfolio) {
		TypePortfolio = typePortfolio;
	}


	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public double getReturns() {
		return Returns;
	}

	public void setReturns(double returns) {
		Returns = returns;
	}

	public float getVolatility() {
		return Volatility;
	}

	public void setVolatility(float volatility) {
		Volatility = volatility;
	}



	public Set<Flux> getF() {
		return F;
	}



	public void setF(Set<Flux> f) {
		F = f;
	}

	

}
