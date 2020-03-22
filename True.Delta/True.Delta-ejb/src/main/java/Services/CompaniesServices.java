package Services;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Company;
import Interfaces.CompaniesServicesInterfaceLocal;
import Interfaces.CompaniesServicesInterfaceRemote;

@Stateful

public class CompaniesServices implements CompaniesServicesInterfaceRemote,CompaniesServicesInterfaceLocal {
	@PersistenceContext(unitName= "primary")
	EntityManager em;

	@Override
	public String AddCompany(Company C) {
		em.persist(C);
		System.out.println("Company:"+ C.getSymbol());
		return C.getSymbol();
		
	}

	@Override
	public void DeleteCompany(String sym) {
		Company C=new Company();
		C=em.find(Company.class,sym);
		em.remove(C);
		
	}

	@Override
	public Company DisplayCompany(String sym) {
		return em.find(Company.class,sym);
		
	}

	@Override
	public List<Company> DisplayCompanies() {
		TypedQuery<Company> query=em.createQuery("Select * from Company",Company.class);
		return query.getResultList();
		
	}

	@Override
	public Company EditCompany(Company C,String sym) {
		Company OldC=em.find(Company.class,sym);
		OldC.setIndustry(C.getIndustry());
		OldC.setMarket(C.getMarket());
		OldC.setName(C.getName());
		OldC.setSector(C.getSector());
		OldC.setSecurities(C.getSecurities());
		OldC.setSymbol(C.getSymbol());
		return OldC;
		
		
	}
	}



	
