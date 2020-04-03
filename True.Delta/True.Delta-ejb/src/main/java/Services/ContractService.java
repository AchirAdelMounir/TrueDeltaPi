package Services;



import java.util.List;


import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Contract;
import Entities.User;
import Interfaces.ContractServiceLocal;
import Interfaces.ContractServiceRemote;




@Stateful
public class ContractService implements  ContractServiceRemote , ContractServiceLocal {

	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddContract(Contract contract) {
		
		em.persist(contract);
		System.out.println("Contract:"+ contract.getIDContract());
		
		return contract.getIDContract();
	}
	
	@Override
	public int AddUser (User user) {
		
		em.persist(user);
		System.out.println("Asset Manager:"+ user.getId());
		
		return user.getId();
	}



	@Override
	public void DeleteContractById(int IdContract) {
		Contract contract=new Contract();
		contract=em.find(Contract.class, IdContract);
		em.remove(contract);

	}

	@Override
public void EditContractByID(int IdContract , int Amount) {
		/*Query query = em.createQuery("update Contract c set Amount=:Amount where c.id=:IdContract");
		query.setParameter("Amount", Amount);
		query.setParameter("IDContract", IdContract);
		int modified = query.executeUpdate();
		if(modified == 1){
		System.out.println("successfully updated");
		}else{
		System.out.println("failed to update");
		}*/
		Contract contract = em.find(Contract.class, IdContract);
		contract.setAmount(Amount);

	}

	@Override
	public void AffecterAMAContrat(int IdAM, int IdCpntract) {
	
		 

	}

	@Override
	public Contract ReadContractById ( int contractId ) {
		Contract contracts = em.find(Contract.class, contractId);
			return contracts;
	}

    @Override
    public List ListContract() {
    	
    	 List <Contract>  contracts = em.createQuery("select c from Contract c",Contract.class).getResultList();
 		
 		return contracts ; 

    }
    



}
