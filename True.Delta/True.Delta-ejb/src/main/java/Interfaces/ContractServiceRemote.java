package Interfaces;

import java.io.IOException;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import Entities.Contract;
import Entities.User;
import Enumerations.ContractType;

@Remote
public interface ContractServiceRemote {
	public int AddUser (User user);
	
	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContractByID(int IdContract , Double Amount);
	void AffecterAMAContrat(int IdAM, int IdCpntract);
	public Contract ReadContractById( int contractId );
	public  List ListContract( );
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
	 public void deleteContract1(int ContractId);
	 public void affecterPropositionContract(int PropId,int ContractId);
	 public List<Contract> getContractAproved();
	 public int Extractyear(int id);
	 public double Somme(int year);

	List<Integer> findPackProductsdid();

	
	
 
}
