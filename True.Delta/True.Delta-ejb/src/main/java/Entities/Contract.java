
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Enumerations.ContractType;


@Entity
@Table(name="T_Contrat")

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
	@Column
	int IDUser;
	@Enumerated(EnumType.STRING)
	ContractType ContartType;
	@Enumerated(EnumType.STRING) 
    Enumerations.FinancialAsset FinancialAsset;
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
	public int getIDUser() {
		return IDUser;
	}
	public void setIDUser(int iDUser) {
		IDUser = iDUser;
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
	public Contract(int iDContract, int amount, Date creationDate, Date startDate, Date endDate, int iDUser,
			ContractType contartType, Enumerations.FinancialAsset financialAsset) {
		super();
		IDContract = iDContract;
		Amount = amount;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		IDUser = iDUser;
		ContartType = contartType;
		FinancialAsset = financialAsset;
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
