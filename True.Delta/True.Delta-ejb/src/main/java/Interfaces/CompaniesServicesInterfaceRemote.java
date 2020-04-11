package Interfaces;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Remote;
import Entities.Company;
import Entities.Security;
import Entities.Stock;
@Remote

public interface CompaniesServicesInterfaceRemote {
	public String AddCompany(Company C);
	public void DeleteCompany(String sym);
	public Company DisplayCompany(String sym);
	public List<Company> DisplayCompanies();
	public Company EditCompany(Company C,String sym);
	public void CompaniesInfoFinder();
	public Boolean ifExists(Company C);
	public List<Company> SearchByMarketCap(BigInteger M,String operator);
	public List<Company> SearchByInput(String SearchField, String operator,Object o);
	public List<Company> GetTopByInput(String Input,int TopN);
	public List<Company> ReplaceMissingValues(List<Company> L);
	

}
