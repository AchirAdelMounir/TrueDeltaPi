package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
	public Security DisplaySecurity(int IdSecurity) {
		Security S=new Security();
		S=em.find(Security.class, IdSecurity);
		return S;
		
		
	}

	@Override
	public List<Security> DisplaySecurities() {
		TypedQuery<Security> query= em.createQuery("Select * from Security",Security.class);
		return query.getResultList();
		
	}

	@Override
	public void EditSecurity(int IdSecurity) {
		// TODO Auto-generated method stub
		
	}

}
