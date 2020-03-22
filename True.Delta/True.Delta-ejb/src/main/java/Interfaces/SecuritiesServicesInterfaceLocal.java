package Interfaces;
import javax.ejb.Local;

import Entities.*;
import Entities.Security;

import java.sql.Date;
import java.util.List;
@Local

public interface SecuritiesServicesInterfaceLocal {
	public int AddSecurity(Security S);
	public void DeleteSecurity(int IdSecurity);
	public Security DisplaySecurity(int IdSecurity);
	public List<Security> DisplaySecurities();
	public void EditSecurity(int IdSecurity,Object o);
	public List<Security> SearchByMaturityDate(Date d);
	public List<Bond> DisplayBonds();
	public List<Stock> DisplayStocks();
	
	

}
