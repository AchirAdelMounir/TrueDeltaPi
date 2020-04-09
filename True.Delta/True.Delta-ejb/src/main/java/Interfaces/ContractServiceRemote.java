package Interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import Entities.Contract;
import Entities.User;
import Entities.Visitor;


@Remote
public interface ContractServiceRemote {
	public int AddUser (User user);
	
	public int AddContract(Contract contract);
	public void DeleteContractById(int IdContract);
	public void EditContractByID(int IdContract , int Amount);
	void AffecterAMAContrat(int IdAM, int IdCpntract);
	public Contract ReadContractById( int contractId );
	public  List ListContract( );
	public Boolean  VerificationBanque();
	public int ScoreVisitor  (int IdVisitor);
	public int ScoreContract(int IdUser);

	
	
 
}
