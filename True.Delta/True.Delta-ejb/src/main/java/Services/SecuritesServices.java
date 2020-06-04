package Services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Bond;
import Entities.Security;
import Interfaces.SecuritiesServicesInterface;


@Stateful

public class SecuritesServices implements SecuritiesServicesInterface {
	@PersistenceContext(unitName= "primary")
	EntityManager em;

	@Override
	public int AddSecurity(Security S) {
		em.persist(S);
		System.out.println("Bond:"+ S.getId());
		return S.getId();
		
	}

	@Override
	public void DeleteSecurity(int IdSecurity) {
		Security S=new Security();
		S=em.find(Security.class, IdSecurity);
		em.remove(S);
		
	}

	@Override
	public void DisplaySecurity(int IdSecurity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisplaySecurities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditSecurity(int IdSecurity) {
		// TODO Auto-generated method stub
		
	}

}
