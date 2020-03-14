package Interfaces;
import javax.ejb.Remote;
import Entities.Security;
import java.util.List;
@Remote

public interface SecuritiesServicesInterface {
	public int AddSecurity(Security S);
	public void DeleteSecurity(int IdSecurity);
	public Security DisplaySecurity(int IdSecurity);
	public List<Security> DisplaySecurities();
	public void EditSecurity(int IdSecurity);

}
