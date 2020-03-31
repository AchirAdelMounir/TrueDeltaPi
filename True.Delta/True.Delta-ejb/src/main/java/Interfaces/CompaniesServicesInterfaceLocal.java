package Interfaces;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import Entities.Company;
import Entities.Security;
@Local

public interface CompaniesServicesInterfaceLocal {
	public String AddCompany(Company C);
	public void DeleteCompany(String sym);
	public Company DisplayCompany(String sym);
	public List<Company> DisplayCompanies();
	public Company EditCompany(Company C,String sym);
	public void CompaniesInfoFinder();
	public Boolean ifExists(Company C);
	public List<Company> SearchByMarketCap(BigInteger M,String operator);
	public List<Company> SearchByInput(String SearchField, String operator,Object o);
	

}
