package Services;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Contract;
import Entities.User;
import Enumerations.UserType;
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
	public void AffecterAMAContrat(int IdAM, int IdContract) {
	
		
		/*Contract contratManagedEntity =  em.find(Contract.class, IdContract);
		User userManagedEntity = em.find(User.class, IdAM);
		if (userManagedEntity.getUserType()== UserType.AssetManager)  {
		contratManagedEntity.setUser(userManagedEntity);
		}*/
		
	/*	User userManagedEntity = em.find(User.class, IdAM);
		Contract contratManagedEntity =  em.find(Contract.class, IdContract);
		if(userManagedEntity.getContratcs() == null)
		{ List<Contract> contracts = new ArrayList<>();
		contracts.add(contratManagedEntity);
		userManagedEntity.setContratcs(contracts);
		}else{ userManagedEntity.getContratcs().add(contratManagedEntity);
		}*/
		
		Contract contratManagedEntity = em.find(Contract.class, IdContract);
		User userManagedEntity = em.find(User.class, IdAM);
		if (userManagedEntity.getUserType()== UserType.AssetManager)    {
		contratManagedEntity.setUser(userManagedEntity);
		em.merge(contratManagedEntity);
		}

	}

	@Override
	public Contract ReadContractById ( int contractId ) {
		Contract contracts = em.find(Contract.class, contractId);
			return contracts;
	}

<<<<<<< HEAD
  
	@Override
    public List<Contract> ListContract() {
    	//return em.find(Contract.class, contractId);
    	 List<Contract> contrats =  em.createQuery("select c from contract c", Contract.class).getResultList();
    	 return contrats;
=======
    @Override
    public List ListContract() {
    	
    	 List <Contract>  contracts = em.createQuery("select c from Contract c",Contract.class).getResultList();
 		
 		return contracts ; 
>>>>>>> branch 'Eya-Djebbi' of https://github.com/AchirAdelMounir/TrueDeltaPi.git

    }

	@Override
	public List<String> getAllContratcNamesByAssetManager(int ContractId) {
		// TODO Auto-generated method stub
		return null;
	}

	 /* @Override
	public List<String> getAllContratcNamesByAssetManager(int ContractId){
    	
    	Contract ContractManagedEntity = em.find(Contract.class, ContractId);
    	List<String> AMNames = new ArrayList<>();
    	for(User AM : ContractManagedEntity.getUser()){
    	AMNames.add(AM.getNom());
    	}
}*/
	
	@Override
	public float GetAmountByUserID (int UserId) {
		
		TypedQuery<Float> query = em.createQuery("Select c.amount from contract c join c.user u where u.id= UserId " , Float.class);
		query.setParameter("UserId", UserId);
		return query.getSingleResult();
	}


}
