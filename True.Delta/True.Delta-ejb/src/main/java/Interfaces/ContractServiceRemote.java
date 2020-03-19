package Interfaces;

import javax.ejb.Remote;

import Entities.Contract;
import Entities.User;


@Remote
public interface ContractServiceRemote {
	
	public int AddContract(Contract contract);
	public int AddUser(User user);
	public void DeleteContractById(int IdContract);
	public void EditContract(int IdContract);
	void AffecterAAContrat (int IdAM , int IdCpntract );
	public void ReadListeContract(int IdContract);
	void AffecterAMAContrat(int IdAM, int IdCpntract);
	
	
 
}
