package Services;

import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Contract;
import Interfaces.ContractServiceLocal;
import Interfaces.ContractServiceRemote;

@Stateful
public class ContractService implements ContractServiceLocal , ContractServiceRemote {

	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddContract(Contract contract) {
		em.persist(contract);
		System.out.println("Contract:"+ contract.getIDContract());
		return contract.getIDContract();
	}

	@Override
	public void DeleteContractById(int IdContract) {
		Contract contract=new Contract();
		contract=em.find(Contract.class, IdContract);
		em.remove(contract);

	}

	@Override
	public void EditContract(int IdContract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AffecterAMAContrat(int IdAM, int IdCpntract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReadContract(int IdContract) {
		// TODO Auto-generated method stub

	}

}
