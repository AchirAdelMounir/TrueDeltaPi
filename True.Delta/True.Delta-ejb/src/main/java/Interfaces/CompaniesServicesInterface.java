package Interfaces;
import javax.ejb.Remote;
import Entities.Company;
@Remote

public interface CompaniesServicesInterface {
	public String AddCompany(Company C);
	public void DeleteCompany(int IdCompany);
	public void DisplayCompany(int IdCompany);
	public void DisplayCompanies();
	public void EditCompany(int IdCompany);

}
