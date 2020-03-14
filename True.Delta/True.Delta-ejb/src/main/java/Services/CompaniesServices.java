package Services;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Company;
import Interfaces.CompaniesServicesInterface;

@Stateful

public class CompaniesServices implements CompaniesServicesInterface {
	@PersistenceContext(unitName= "primary")
	EntityManager em;

	@Override
	public String AddCompany(Company C) {
		em.persist(C);
		System.out.println("Company:"+ C.getSymbol());
		return C.getSymbol();
		
	}

	@Override
	public void DeleteCompany(int IdCompany) {
		Company C=new Company();
		C=em.find(Company.class, IdCompany);
		em.remove(C);
		
	}

	@Override
	public Company DisplayCompany(int IdCompany) {
		return em.find(Company.class,IdCompany );
		
	}

	@Override
	public List<Company> DisplayCompanies() {
		TypedQuery<Company> query=em.createQuery("Select * from Company",Company.class);
		return query.getResultList();
		
	}

	@Override
	public void EditCompany(Company C) {
		C=em.find(Company.class,C.getSymbol());
		AddCompany(C);
		
	}

}
