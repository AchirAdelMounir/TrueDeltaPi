package Interfaces;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import Entities.Company;
@Local

public interface CompaniesServicesInterfaceLocal {
	public String AddCompany(Company C);
	public void DeleteCompany(String sym);
	public Company DisplayCompany(String sym);
	public List<Company> DisplayCompanies();
	public Company EditCompany(Company C,String sym);
	public void CompaniesInfoFinder();
	

}
