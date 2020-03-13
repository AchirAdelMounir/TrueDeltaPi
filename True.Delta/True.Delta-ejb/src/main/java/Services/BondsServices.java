package Services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Bond;
import Interfaces.BondsServicesInterface;


@Stateful

public class BondsServices implements BondsServicesInterface {
	@PersistenceContext(unitName= "imputation-ejb")
	EntityManager em;

	@Override
	public int AddBonds(Bond B) {
		em.persist(B);
		System.out.println("Bond:"+ B.getId());
		return B.getId();
		
	}

	@Override
	public void DeleteBonds(int IdB) {
		Bond B=new Bond();
		B=em.find(Bond.class, IdB);
		em.remove(B);
		
	}

	@Override
	public void DisplayBond(int IdB) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisplayBonds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditBond(int IdB) {
		// TODO Auto-generated method stub
		
	}

}
