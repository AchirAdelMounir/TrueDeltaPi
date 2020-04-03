package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Contract;
import Entities.User;


@Remote
public interface ContractServiceRemote {
	public int AddUser (User user);
	
	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContractByID(int IdContract , int Amount);
	void AffecterAMAContrat(int IdAM, int IdContract);
	public Contract ReadContractById( int contractId );
	public List<Contract> ListContract( );
	List<String> getAllContratcNamesByAssetManager(int ContractId);
	public float GetAmountByUserID (int UserId);


	
	
 
}
