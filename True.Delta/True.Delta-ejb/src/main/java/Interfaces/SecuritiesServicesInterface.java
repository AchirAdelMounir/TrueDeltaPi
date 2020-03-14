package Interfaces;
import javax.ejb.Remote;

import Entities.Bond;
import Entities.Security;
@Remote

public interface SecuritiesServicesInterface {
	public int AddSecurity(Security S);
	public void DeleteSecurity(int IdSecurity);
	public void DisplaySecurity(int IdSecurity);
	public void DisplaySecurities();
	public void EditSecurity(int IdSecurity);

}
