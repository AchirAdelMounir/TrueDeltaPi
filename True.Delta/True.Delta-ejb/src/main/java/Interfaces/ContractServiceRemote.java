
package Interfaces;

import javax.ejb.Remote;

import Entities.Contract;

@Remote
public interface ContractServiceRemote {
	
	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContract(int IdContract);
	void AffecterAMAContrat (int IdAM , int IdCpntract );
	public void ReadContract(int IdContract);

}

