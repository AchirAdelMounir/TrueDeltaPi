
package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Enumerations.ContractType;


@Entity
@Table(name="Contract")

public class Contract implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column()
	int IDContract;
	@Column()
	int Amount;
	@Column()
	Date CreationDate;
	@Column()
	Date StartDate;
	@Column()
	Date EndDate;
	
	@Enumerated(EnumType.STRING)
	ContractType ContartType;
	
	@Enumerated(EnumType.STRING) 
    Enumerations.FinancialAsset FinancialAsset;
	@OneToOne
	private Portfolio Portfolio;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User User;
	
	public int getIDContract() {
		return IDContract;
	}
	public void setIDContract(int iDContract) {
		IDContract = iDContract;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public ContractType getContartType() {
		return ContartType;
	}
	public void setContartType(ContractType contartType) {
		ContartType = contartType;
	}
	public Enumerations.FinancialAsset getFinancialAsset() {
		return FinancialAsset;
	}
	public void setFinancialAsset(Enumerations.FinancialAsset financialAsset) {
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, int amount, Date creationDate, Date startDate, Date endDate,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Portfolio getPortfolio() {
		return Portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		Portfolio = portfolio;
	}
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	public Contract(int amount, Date creationDate, Date startDate, Date endDate, ContractType contartType,
			Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	
	
	
	
	

}
