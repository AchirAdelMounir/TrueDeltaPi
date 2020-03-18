package Interfaces;

import javax.ejb.Local;

import Entities.Contract;

@Local
public interface ContractServiceLocal {
	
	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContract(int IdContract);
	void AffecterAMAContrat (int IdAM , int IdCpntract );
	public void ReadContract(int IdContract);

}
