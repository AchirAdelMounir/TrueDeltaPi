package Entities;

import java.io.Serializable;


import java.util.Date;
import java.util.List;

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
import Enumerations.Etat_Contract;
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
	private int IDContract;
	@Column()
   private Double Amount;
	@Temporal(TemporalType.DATE) 
	private Date CreationDate;
	@Temporal(TemporalType.DATE)
	private Date StartDate;
	@Temporal(TemporalType.DATE)
	private Date EndDate;
	@Column()
	private Double Gain;
	@Column()
	private String Description;
	@Column()
	private Boolean isApproved;
	@Column()
	private Double Risque;
	@Column()
	private Double Comission;
	@Column()
	private Double GainAset;
	@Column()
	private Double gainClient;
	@Column()
	private Double RisqueClient;
	@Column()
	private Double RisqueAsset;
	@Column(name="ScoreClient")
	private int scoreC;
	@Column
	private int score ;
	@Enumerated(EnumType.STRING)
	private Etat_Contract EtatContract;
	@Enumerated(EnumType.STRING)
	private LevelOfRisk levelR;
	@Enumerated(EnumType.STRING)
	private ContractType ContartType;
	@Enumerated(EnumType.STRING) 
   private  FinancialAsset FinancialAsset;
	
	@OneToOne
	private Portfolio Portfolio;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name="User_Id",referencedColumnName="Id")
	private User user;
	

	
	
	
	
	public Double getGainAset() {
		return GainAset;
	}
	public void setGainAset(Double gainAset) {
		GainAset = gainAset;
	}
	public Double getGainClient() {
		return gainClient;
	}
	public void setGainClient(Double gainClient) {
		this.gainClient = gainClient;
	}
	public Double getRisqueClient() {
		return RisqueClient;
	}
	public void setRisqueClient(Double risqueClient) {
		RisqueClient = risqueClient;
	}
	public Double getRisqueAsset() {
		return RisqueAsset;
	}
	public void setRisqueAsset(Double risqueAsset) {
		RisqueAsset = risqueAsset;
	}
	public int getScoreC() {
		return scoreC;
	}
	public void setScoreC(int scoreC) {
		this.scoreC = scoreC;
	}
	public Etat_Contract getEtatContract() {
		return EtatContract;
	}
	public void setEtatContract(Etat_Contract etatContract) {
		EtatContract = etatContract;
	}
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
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	/*public Contract( Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
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
}*/
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

	public Double getRisque() {
		return Risque;
	}
	public void setRisque(Double risque) {
		Risque = risque;
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
	/*public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			ContractType contartType, Enumerations.FinancialAsset financialAsset ) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/
	/*public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			Double comission, ContractType contartType, FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		Comission = comission;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}*/
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			Double comission, Etat_Contract etatContract, ContractType contartType,
			Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		Comission = comission;
		EtatContract = etatContract;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double risque , Double comission, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque =risque ;
		Comission = comission;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	
	public Contract( Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double risque , Double comission, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();

		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque ;
		Comission = comission;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, String description,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, String description, Double comission, Etat_Contract etatContract,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		Comission = comission;
		EtatContract = etatContract;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract( Double amount, Date creationDate, Date startDate, Date endDate, String description,
			Boolean isApproved, Double comission, Etat_Contract etatContract, LevelOfRisk levelR,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		this.isApproved = isApproved;
		Comission = comission;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;

	}

	public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double risque , Double comission, Etat_Contract etatContract,
			LevelOfRisk levelR,  Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque =risque ;
		Comission = comission;
		EtatContract = etatContract;
		this.levelR = levelR;
	
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			Boolean isApproved, Double comission, Etat_Contract etatContract, LevelOfRisk levelR,
			ContractType contartType, Enumerations.FinancialAsset financialAsset, Entities.User user) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		this.isApproved = isApproved;
		Comission = comission;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
		user = user;
	}
	public Contract(int score) {
		super();
		this.score = score;
	}
	public Contract(int iDContract, int score) {
		super();
		IDContract = iDContract;
		this.score = score;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description, int score,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		this.score = score;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			Boolean isApproved, Double comission, int score, Etat_Contract etatContract, LevelOfRisk levelR,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		this.isApproved = isApproved;
		Comission = comission;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, String description,
			Boolean isApproved, Double comission, int scoreC, int score, Etat_Contract etatContract, LevelOfRisk levelR,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Description = description;
		this.isApproved = isApproved;
		Comission = comission;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double risque, Double comission, int scoreC, int score,
			Etat_Contract etatContract, LevelOfRisk levelR, ContractType contartType,
			Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, Double gain, String description,
			Boolean isApproved, Double risque, Double comission, int scoreC, int score, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double risque, Double comission, Double gainAset, Double gainClient,
			Double risqueClient, Double risqueAsset, int scoreC, int score, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
		GainAset = gainAset;
		this.gainClient = gainClient;
		RisqueClient = risqueClient;
		RisqueAsset = risqueAsset;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, Double gain, String description,
			Boolean isApproved, Double risque, Double comission, Double gainClient,
			Double risqueClient, int scoreC, int score, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
	
		this.gainClient = gainClient;
		RisqueClient = risqueClient;
	
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(int iDContract, Double gainAset) {
		super();
		IDContract = iDContract;
		GainAset = gainAset;
	}
	public Contract( Double amount, Date creationDate, Date startDate, Date endDate, Double gain,
			String description, Boolean isApproved, Double risque, Double comission, Double gainClient, int scoreC,
			int score, Etat_Contract etatContract, LevelOfRisk levelR, ContractType contartType,
			Enumerations.FinancialAsset financialAsset) {
		super();
		
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
		this.gainClient = gainClient;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, Double gain, String description,
			Boolean isApproved, Double risque, Double comission, Double gainAset, Double gainClient,
			Double risqueClient, Double risqueAsset, int scoreC, int score, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
		GainAset = gainAset;
		this.gainClient = gainClient;
		RisqueClient = risqueClient;
		RisqueAsset = risqueAsset;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract(Double amount, Date creationDate, Date startDate, Date endDate, Double gain, String description,
			Boolean isApproved, Double risque, Double comission, Double gainAset, Double gainClient,
			Double risqueClient, Double risqueAsset, int scoreC, int score, Etat_Contract etatContract,
			LevelOfRisk levelR, ContractType contartType, Enumerations.FinancialAsset financialAsset,
			Entities.User user) {
		super();
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		Gain = gain;
		Description = description;
		this.isApproved = isApproved;
		Risque = risque;
		Comission = comission;
		GainAset = gainAset;
		this.gainClient = gainClient;
		RisqueClient = risqueClient;
		RisqueAsset = risqueAsset;
		this.scoreC = scoreC;
		this.score = score;
		EtatContract = etatContract;
		this.levelR = levelR;
		ContartType = contartType;
		FinancialAsset = financialAsset;
		user = user;
	}
	public void setUser(int idClient) {
		// TODO Auto-generated method stub
		
		
	}
	
	
	
	
	
	
	
	
	
	

	


	
	
	
	
	
	

}