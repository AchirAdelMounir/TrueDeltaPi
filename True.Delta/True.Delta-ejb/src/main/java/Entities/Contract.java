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

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import Enumerations.ContractType;
import Enumerations. FinancialAsset;
import Enumerations.LevelOfRisk;


@SuppressWarnings("serial")
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
    Double Amount;
	@Temporal(TemporalType.DATE) 
	Date CreationDate;
	@Column()
	Date StartDate;
	@Column()
	Date EndDate;
	@Column()
	Double Gain;
	@Column()
	private String Description;
	private Boolean isApproved;
	   
	@Column()
	private Double Profit;
	
	@Column()
	private Double Comission;

	@Enumerated(EnumType.STRING)
	LevelOfRisk levelR;
	@Enumerated(EnumType.STRING)
	ContractType ContartType;
	@Enumerated(EnumType.STRING) 
    FinancialAsset FinancialAsset;
	
	@OneToOne
	private Portfolio Portfolio;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User User;
	
	@ManyToOne
	private Proposition proposition ; 
	
	
	public int getIDContract() {
		return IDContract;
	}
	public void setIDContract(int iDContract) {
		IDContract = iDContract;
	}
/*	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}*/
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
	public FinancialAsset getFinancialAsset() {
		return FinancialAsset;
	}
	public void setFinancialAsset(FinancialAsset financialAsset) {
		FinancialAsset = financialAsset;
	}
/*	public Contract(int iDContract, int amount, Date creationDate, Date startDate, Date endDate,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/
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
	@Override
	public String toString() {
		return "Contract [IDContract=" + IDContract + ", Amount=" + Amount + ", CreationDate=" + CreationDate
				+ ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", Gain=" + Gain + ", Description="
				+ Description + ", isApproved=" + isApproved + ", Profit=" + Profit + ", Comission=" + Comission
				+ ", levelR=" + levelR + ", ContartType=" + ContartType + ", FinancialAsset=" + FinancialAsset
				+ ", Portfolio=" + Portfolio + ", User=" + User + ", proposition=" + proposition + "]";
	}
	/*public Contract(int amount, Date creationDate, Date startDate, Date endDate, Entities.User user) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		User = user;
	}*/
	public Double getGain() {
		return Gain;
	}
	public void setGain(Double gain) {
		Gain = gain;
	}
	
	
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}

/*	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			ContractType contartType, FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/
	
	
	/*public Contract(int iDContract, Double amount, Date startDate, Date endDate, Double gain, String description,
			Boolean isApproved, Double profit, Double comission, LevelOfRisk levelR, ContractType contartType,
			FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Profit = profit;
		Comission = comission;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/
	
	
	
	
/*	public Contract(Double amount, Date startDate, Date endDate, Double gain, String description, Double profit,
		Double comission, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
	super();
	Amount = amount;
	StartDate = startDate;
	EndDate = endDate;
	Gain = gain;
	Description = description;
	Profit = profit;
	Comission = comission;
	ContartType = contartType;
	FinancialAsset = financialAsset;
}*/
	public Contract( Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
		String description, Double profit, Double comission, ContractType contartType,
		Enumerations.FinancialAsset financialAsset) {
	super();
	
	Amount = amount;
	CreationDate = creationDate;
	StartDate = startDate;
	EndDate = endDate;
	Gain = gain;
	Description = description;
	Profit = profit;
	Comission = comission;
	ContartType = contartType;
	FinancialAsset = financialAsset;
}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Double getProfit() {
		return Profit;
	}
	public void setProfit(Double profit) {
		Profit = profit;
	}
	public Double getComission() {
		return Comission;
	}
	public void setComission(Double comission) {
		Comission = comission;
	}
	public LevelOfRisk getLevelR() {
		return levelR;
	}
	public void setLevelR(LevelOfRisk levelR) {
		this.levelR = levelR;
	}
	public Proposition getProposition() {
		return proposition;
	}
	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}
	/*public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double profit, Double comission, LevelOfRisk levelR,
			ContractType contartType, Enumerations.FinancialAsset financialAsset, Entities.Portfolio portfolio,
			Entities.User user, Proposition proposition) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Profit = profit;
		Comission = comission;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
		Portfolio = portfolio;
		User = user;
		this.proposition = proposition;
	}*/
	/*public Contract(Double amount, Date creationDate, Date startDate, Date endDate, Double gain, String description,
			Boolean isApproved, Double profit, Double comission, LevelOfRisk levelR, ContractType contartType,
			Enumerations.FinancialAsset financialAsset, Entities.Portfolio portfolio, Entities.User user,
			Proposition proposition) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Profit = profit;
		Comission = comission;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
		Portfolio = portfolio;
		User = user;
		this.proposition = proposition;
	}*/
	/*public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double profit, Double comission, LevelOfRisk levelR,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Profit = profit;
		Comission = comission;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/
	/*public Contract(Double amount, Date startDate, Date endDate, Double gain, String description, Boolean isApproved,
			Double profit, Double comission, LevelOfRisk levelR, ContractType contartType,
			Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Profit = profit;
		Comission = comission;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/

	
	
	
	

	


	
	
	
	
	
	

}