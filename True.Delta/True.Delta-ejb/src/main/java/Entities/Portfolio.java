package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="PORTFOLIO")
public class Portfolio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="Id_Portfolio")
	private int IdPortfolio;
	@Column (name="Type_of_Portfolio")
	private int TypePortfolio;
	@Column (name="Returns")
	private double Returns;
	@Column (name="Volatility")
	private int Volatility;
	
	@OneToOne(mappedBy="Portfolio")
	private Contract Contract;
	
	@OneToMany(mappedBy="Portfolio")
	private Set<Security> Securities;
	
	
	
	

	@ManyToOne
	private AssetManager assetmanager;

	
	public Portfolio() {
		super();
	}
	
	
	
	



	public int getIdPortfolio() {
		return IdPortfolio;
	}
	
	public void setIdPortfolio(int idPortfolio) {
		IdPortfolio = idPortfolio;
	}

	public int getTypePortfolio() {
		return TypePortfolio;
	}
	
	public AssetManager getAssetmanager() {
		return assetmanager;
	}







	public void setAssetmanager(AssetManager assetmanager) {
		this.assetmanager = assetmanager;
	}







	public void setTypePortfolio(int typePortfolio) {
		TypePortfolio = typePortfolio;
	}



	public double getReturns() {
		return Returns;
	}



	public void setReturns(double returns) {
		Returns = returns;
	}



	public int getVolatility() {
		return Volatility;
	}



	public void setVolatility(int volatility) {
		Volatility = volatility;
	}

	
	
	

}
