package Interfaces;

import java.io.IOException;
import java.text.ParseException;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import Entities.Contract;
import Entities.User;
import Enumerations.ContractType;
import Enumerations.Etat_Contract;


@Remote
public interface ContractServiceRemote {
	public int AddUser (User user);
	
	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContractByID(int IdContract , Double Amount);
	void AffecterAMAContrat(int IdAM, int IdCpntract);
	public Contract ReadContractById( int contractId );
	public Contract ReadContractByEtat( String EtatContract );
	public  List<Contract> ListContract();
	public void   VerificationBanque(int idBanque , int idUser);
	public int ScoreVisitor  (int IdVisitor);
//	User VerificationBanque(int IdUser);
	public int ScoreContract(int IdUser);
	public int AffecterContratAClient (Contract contract);
	public int addContract1(Contract contract, int id_user);
	public int EstimatedScore(Contract contrat,int id);
	public double CalculGainClient(Contract con);
	public double CalculGainAsset(Contract con);
	public List<String> matchingContract(Double Amount) throws IOException;
	Object count();
	public void  DeleteUserFromContract(int idUser, int idContract);
	public List ListContractByType( ContractType type);
	public List<Contract> getContractPostedByClient(User c);
	 public int isAproved(Contract c);
	 public void updateContractDescription(String desc, int ContractId);
	   public int deleteContract1(int ContractId);
	 public void affecterPropositionContract(int PropId,int ContractId);
	 public List<Contract> getContractAproved();
	 public int Extractyear(int id);
	 public double Somme(int year);
	 public void UpdateContract(Contract contract);
	 void AffectedPortfolio(int idPortfolio, int idUser);
	 void AffectedContract(int idPortfolio, int idUser);
	 public void SelectUser (User user);
	 double CalculGainAsset(int Idcon);

		double CalculGainClient(int Idcon);


	List<Integer> findPackProductsdid();

	double RisqueClient(int IDContract);
	double RisqueAsset(int IDContract);
	List<Contract> getAllContractByIdUser(int idUser);

	List<Contract> getContractByDate(String date) throws ParseException;

	List<Contract> getAllContractByType(ContractType ContartType);

	Long getNbContractByIdUser(int idUser);
	public Long NbrContractByDate(String date) throws ParseException;

	public Long getNbContractByContractType(Enumerations.ContractType ContractType);

	double getAvgOfContractType(ContractType ContractType);

	Long NbUser();

	Contract findById(int id);

	void UpdateContract1(Contract c);

	List<Contract> getAllContract();

	int DeleteContractByIdUser(int idUser);
	int DeleteContractByDate(String date);


	
	
 
}
