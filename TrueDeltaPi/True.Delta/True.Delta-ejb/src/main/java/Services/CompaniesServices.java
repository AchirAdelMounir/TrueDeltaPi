package Services;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Company;
import Interfaces.CompaniesServicesInterface;

@Stateful

public class CompaniesServices implements CompaniesServicesInterface {
	@PersistenceContext(unitName= "primary")
	EntityManager em;

	@Override
	public String AddCompany(Company C) {
		em.persist(C);
		System.out.println("Bond:"+ C.getSymbol());
		return C.getSymbol();
		
	}

	@Override
	public void DeleteCompany(int IdCompany) {
		Company C=new Company();
		C=em.find(Company.class, IdCompany);
		em.remove(C);
		
	}

	@Override
	public void DisplayCompany(int IdCompany) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisplayCompanies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditCompany(int IdCompany) {
		// TODO Auto-generated method stub
		
	}

}
