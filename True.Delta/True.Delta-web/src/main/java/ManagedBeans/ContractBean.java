package ManagedBeans;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import Entities.Contract;
import Entities.User;
import Enumerations.ContractType;
import Enumerations.Etat_Contract;
import Enumerations.FinancialAsset;
import Enumerations.LevelOfRisk;
import Services.ContractService;
import Services.UserService;




@ManagedBean (name="contractBean")
@SessionScoped

public class ContractBean implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String destination="C:\\Users\\djebb\\OneDrive\\Bureau\\Upload\\";

	
	 private UploadedFile file;
	private Integer contractIdToBeUpdated;

	private Double amount; 

	private Double commission;
	//private Date dateCreation ;

	private Date startDate;

	private Date endDate;
	private String description;

	private ContractType contractType;

    private FinancialAsset financialAsset;

    private Etat_Contract etatContract;

    private  LevelOfRisk levelIR;
	
    private Boolean loggedIn;
	Contract contract;
	private int score ;
	private int scoreC;

	private Double GainClient;

	private Double GainAsset;

	private Double RisqueClient;

	private Double RisqueAsset;

	private Double gain;

	private Double risque;
	private Boolean isApproved;
	
	private int IdProposition;
	private User IdUser;
	private int user;
	private int IdPortfolio;
	private int pn;
	private Integer IDUser;
	
	List <Contract> contracts ;
	
	private List<Contract> Co;
	private List<Contract> Top;
	private List<Contract> LastS;
	private String input;
	
	
	
	
	
	  public Integer getIDUser() {
		return IDUser;
	}

	public void setIDUser(Integer iDUser) {
		IDUser = iDUser;
	}

	public UploadedFile getFile() {
	        return file;
	    }
	 
	    public void setFile(UploadedFile file) {
	        this.file = file;
	    }


	public Double getGainClient() {
		return GainClient;
	}



	public void setGainClient(Double gainClient) {
		GainClient = gainClient;
	}



	public Double getGainAsset() {
		return GainAsset;
	}



	public void setGainAsset(Double gainAsset) {
		GainAsset = gainAsset;
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



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public Boolean getLoggedIn() {
		return loggedIn;
	}



	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}



	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	

	
	public int getPn() {
		
		return pn;
	}


	public void setPn(int  pn) {
		this.pn = pn;
	}



	Long millis = System.currentTimeMillis();
    Date dateCreation = new Date(millis);

    
    
	
private Date currentday = new Date();
	
	

	public Double getGain() {
	return gain;
}


public void setGain(Double gain) {
	this.gain = gain;
}


public Double getRisque() {
	return risque;
}


public void setRisque(Double risque) {
	this.risque = risque;
}


public Boolean getIsApproved() {
	return isApproved;
}


public void setIsApproved(Boolean isApproved) {
	this.isApproved = isApproved;
}





public LevelOfRisk getLevelIR() {
	return levelIR;
}



public void setLevelIR(LevelOfRisk levelIR) {
	this.levelIR = levelIR;
}



public int getIdProposition() {
	return IdProposition;
}


public void setIdProposition(int idProposition) {
	IdProposition = idProposition;
}




public User getIdUser() {
	return IdUser;
}



public void setIdUser(User idUser) {
	IdUser = idUser;
}



public int getIdPortfolio() {
	return IdPortfolio;
}


public void setIdPortfolio(int idPortfolio) {
	IdPortfolio = idPortfolio;
}





	public Double getCommission() {
	return commission;
}


public void setCommission(Double commission) {
	this.commission = commission;
}


public void setContract(Contract contract) {
	this.contract = contract;
}


	public Date getCurrentday() {
		return currentday;
	}


	public void setCurrentday(Date currentday) {
		
		this.currentday = currentday;
	}
	
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public FinancialAsset getFinancialAsset() {
		return financialAsset;
	}

	public void setFinancialAsset(FinancialAsset financialAsset) {
		this.financialAsset = financialAsset;
	}
	
	

	public Etat_Contract getEtatContract() {
		return etatContract;
	}



	public void setEtatContract(Etat_Contract etatContract) {
		this.etatContract = etatContract;
	}



	public Contract getContract() {
		return contract;
	}
	
	public Integer getContractIdToBeUpdated() {
		return contractIdToBeUpdated;
	}
	public void setContractIdToBeUpdated(Integer contractIdToBeUpdated) {
		this.contractIdToBeUpdated = contractIdToBeUpdated;
	}


@EJB
	ContractService contractService;
    UserService userService;

   int id= 1;
   int idBq=1;
	
	public String addContractFree()  {
	 etatContract = Etat_Contract.In_class; 
	 isApproved =false ;
	 levelIR = LevelOfRisk.Low;
	 contractType = ContractType.Free;
	 gain =0.0; risque =0.0;
	 score = contractService.ScoreVisitor(id);
	 scoreC = contractService.ScoreContract(id);
	 GainClient= contractService.CalculGainClient(id);
	 RisqueClient = contractService.RisqueClient(id);
	 GainAsset=contractService.CalculGainAsset(id);
	 RisqueAsset = contractService.RisqueAsset(id);

		contract= (new Contract ( amount, dateCreation , startDate, endDate, gain , description, isApproved , risque,  commission, GainAsset ,  GainClient , RisqueClient , RisqueClient , scoreC , score , etatContract , levelIR, contractType , financialAsset , IdUser ));
		contractService.AddContract(contract);
		//contractService.SmsSender("Contrat crer attendez un message de l'administrateur ");
		return  ("UpdateContractFree?faces-redirect=true");	
		}
	
	public List<Contract> getContracts() {
		
		contracts = contractService.ListContract();
		return contracts;
	}
	
	public void removeContract(int id) {
		contractService.DeleteContractById(id);
	}

	public void displayContract(Contract contract) 
	{
		this.setAmount(contract.getAmount());
		this.setCommission(contract.getComission());
		this.setDateCreation(contract.getCreationDate());
		this.setDescription(contract.getDescription());
		this.setEndDate(contract.getEndDate());
		this.setStartDate(contract.getStartDate());
		this.setEtatContract(contract.getEtatContract());
		this.setContractType(contract.getContartType());
		this.setFinancialAsset(contract.getFinancialAsset());
	    this.setContractIdToBeUpdated(contract.getIDContract());
	    this.setGain(contract.getGain());
	    this.setRisque(contract.getRisque());
	    this.setScore(contract.getScore());
	    this.setIsApproved(contract.getIsApproved());;
	}
	
	public void UpdateContract() 
	{// employeService.updateEmploye(new Employe(employeIdToBeUpdated, nom, prenom, email, password, isActif, role));
		contractService.UpdateContract(new Contract (contractIdToBeUpdated , amount, dateCreation , startDate, endDate, gain , description, isApproved , risque,  commission, GainAsset ,  GainClient , RisqueClient , RisqueClient , scoreC , score , etatContract , levelIR, contractType , financialAsset  ));	
	} 
	
	
	public void UpdateContractAM()  {
	
	Etat_Contract etat = Etat_Contract.Accept;
	// employeService.updateEmploye(new Employe(employeIdToBeUpdated, nom, prenom, email, password, isActif, role));
	
			contractService.UpdateContract(new Contract (contractIdToBeUpdated , amount, dateCreation , startDate, endDate, gain , description, isApproved , risque,  commission, GainAsset ,  GainClient , RisqueClient , RisqueClient , scoreC , score , etatContract , levelIR, contractType , financialAsset  ));	
		
			if (isApproved == true) {
				//contractService.SmsSender("Votre contrat est approuver par un asset manager , pouvez le signez");
		}
	} 
	
	public String UpdateContractAdmin()  {
		
	Etat_Contract etat = Etat_Contract.Accept;
	// employeService.updateEmploye(new Employe(employeIdToBeUpdated, nom, prenom, email, password, isActif, role));
	if (contractService.VerificationBanqueUser(id, idBq)) {
			contractService.UpdateContract(new Contract (contractIdToBeUpdated , amount, dateCreation , startDate, endDate, gain , description, isApproved , risque,  commission, GainAsset ,  GainClient , RisqueClient , RisqueClient , scoreC , score , etatContract , levelIR, contractType , financialAsset  ));	
	}
	else {
		return  ("VerfierDonnees?faces-redirect=true");
	}
			if (etatContract== Etat_Contract.Accept) {
				//contractService.SmsSender("Votre contrat est accepter par un Administrateur , Attendez un message de l'ae=sset amanger ");
		}
			
			if (etatContract== Etat_Contract.Refuse) {
				//contractService.SmsSender("Votre contrat est refuser par un Administrateur  ");
		}
			return  ("ContractAdminShow?faces-redirect=true"); 

	} 
	
	
	public String addContractWithCondition() {
		 etatContract = Etat_Contract.In_class; 
		 isApproved =false ;
		 levelIR = LevelOfRisk.Low;
		 score = contractService.ScoreVisitor(id);
		 scoreC = contractService.ScoreContract(id);
		 contractType = ContractType.With_Condition;
		 GainClient= contractService.CalculGainClient(id);
		 RisqueClient = contractService.RisqueClient(id);
		 GainAsset=contractService.CalculGainAsset(id);
		 RisqueAsset = contractService.RisqueAsset(id);
			contract= (new Contract ( amount, dateCreation , startDate, endDate, gain , description, isApproved , risque,  commission, GainAsset ,  GainClient , RisqueClient , RisqueClient , scoreC , score , etatContract , levelIR, contractType , financialAsset ));
			contractService.AddContract(contract);
			//contractService.SmsSender("Contrat crer attendez un message de l'administrateur ");
			return  ("UpdateContractWithCondition?faces-redirect=true");
			
			}
	
	public String ContractFree() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return ("AddContractFree?faces-redirect=true"); 
	}
		
		public String ContractWithCondition() {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return ("AddContractWithCondition?faces-redirect=true"); 
		
	}
	
public List<Contract> getContractsFree() {
	contractType= ContractType.Free;
		if (contractType  != null) {
		contracts = contractService.ListContract(); }
		return contracts;
	}

public List<Contract> GetContract() throws ParseException {
	String date = null;
	contracts = contractService.getContractByDate(date); 
	return contracts;
	
}


public List<Contract> GetAllContractByType(){
	ContractType contartType = null;
	List<Contract>  contract=	contractService.getAllContractByType(contartType);
	return contract;
}


public String DisplayTopLastNContractFirst(String Input, int TopN) {
	Top = new ArrayList<Contract>();
	LastS = new ArrayList<Contract>();
	LastS = contractService.GetLastByInput(Input, TopN);
	Top = contractService.GetTopByInput(Input, TopN);
	return ("TopLastCompanies?faces-redirect=true");

	
}

public void DisplayTopLastNContract() {
	System.out.println(input);
	Top = new ArrayList<Contract>();
	LastS = new ArrayList<Contract>();
	LastS = contractService.GetLastByInput(input, 4);
	Top = contractService.GetTopByInput(input,4 );

	
}


public String DisplayContract(String sym) {

	contract = contractService.DisplayContract(sym);
	contract.setContartType(contract.getContartType());
	contract.setAmount(contract.getAmount());
	return ("ShowDetails?faces-redirect=true");

}
	

public void Excel2DatabaseTest () {
	contractService.Excel2DatabaseTest();
	
}

public String VerificationBanqueUser(int idU , int idB ) {
	
	if (contractService.VerificationBanqueUser(id, idBq) == false) {
		return ("VerfierDonnees?faces-redirect=true");

	}
	else {
		return ("ContractAيminShow?faces-redirect=true");	
	}
		
}

public String PDF() throws Exception  {
	
	try {
		
		if (isApproved = true) {
		
		contractService.pdfs();; }
		else {
			 return ("VerifierDonnées?faces-redirect=true");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace(); 

	};
	return ("UpdateContractFree?faces-redirect=true") ; 
}

public String ChoisirContrat() {
	
	return ("ChoisirContract?faces-redirect=true");
}

public String AddContractWithCondition() {
	
	return ("UpdateContractWithCondition?faces-redirect=true");
}

public String AllerChoisirContract() {
	
	return ("Excel2Database?faces-redirect=true");
}

public String AddContractFree() {
	
	return ("AddContractFree?faces-redirect=true");
}

public String UpdateContractWithCondition() {
	
	return ("AddContractWithCondition?faces-redirect=true");
}

public String UpdateContractFree() {
	
	return ("AddContractFree?faces-redirect=true");
}

public String AjouterFichier() {
	
	return ("Excel2Database?faces-redirect=true");
}



}





	
	

