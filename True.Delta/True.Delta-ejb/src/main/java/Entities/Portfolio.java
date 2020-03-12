package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

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
	@Column (name="Id_Contract")
	private int IdContract;
	@Column (name="Id_Costumer")
	private int IdCostumer;
	@Column (name="Id_Broker")
	private int IdBroker;
	@Column (name="Id_Stock")
	private int IdStock;
	@Column (name="Id_Bond")
	private int IdBond;
	@Column (name="Type_of_Portfolio")
	private int TypePortfolio;
	
	

	
	public Portfolio() {
		super();
	}
	
	
	
	public Portfolio(int idPortfolio, int idContract, int idCostumer, int idBroker, int idStock, int idBond,
			int typePortfolio) {
		super();
		IdPortfolio = idPortfolio;
		IdContract = idContract;
		IdCostumer = idCostumer;
		IdBroker = idBroker;
		IdStock = idStock;
		IdBond = idBond;
		TypePortfolio = typePortfolio;
	}
	
	
	public int getIdPortfolio() {
		return IdPortfolio;
	}
	public void setIdPortfolio(int idPortfolio) {
		IdPortfolio = idPortfolio;
	}
	public int getIdContract() {
		return IdContract;
	}
	public void setIdContract(int idContract) {
		IdContract = idContract;
	}
	public int getIdCostumer() {
		return IdCostumer;
	}
	public void setIdCostumer(int idCostumer) {
		IdCostumer = idCostumer;
	}
	public int getIdBroker() {
		return IdBroker;
	}
	public void setIdBroker(int idBroker) {
		IdBroker = idBroker;
	}
	public int getIdStock() {
		return IdStock;
	}
	public void setIdStock(int idStock) {
		IdStock = idStock;
	}
	public int getIdBond() {
		return IdBond;
	}
	public void setIdBond(int idBond) {
		IdBond = idBond;
	}
	public int getTypePortfolio() {
		return TypePortfolio;
	}
	public void setTypePortfolio(int typePortfolio) {
		TypePortfolio = typePortfolio;
	}
	
	
	

}
