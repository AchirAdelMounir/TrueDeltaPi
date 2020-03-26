package Interfaces;

import java.util.List;

import javax.ejb.Local;


import Entities.Contract;
import Entities.User;


@Local
public interface ContractServiceLocal {
	
	public int AddUser (User user);

	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContractByID(int IdContract , int Amount);
	void AffecterAMAContrat(int IdAM, int IdCpntract);
	public Contract ReadContractById( int contractId );

}
