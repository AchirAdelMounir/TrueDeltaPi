package Interfaces;
import java.util.List;

import javax.ejb.Remote;
import Entities.Company;
@Remote

public interface CompaniesServicesInterface {
	public String AddCompany(Company C);
	public void DeleteCompany(int IdCompany);
	public Company DisplayCompany(int IdCompany);
	public List<Company> DisplayCompanies();
	public void EditCompany(Company C);

}
