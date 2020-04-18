package Interfaces;
import javax.ejb.Local;

import Entities.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
@Local

public interface SecuritiesServicesInterfaceLocal {
	public int AddSecurity(Security S);
	public void DeleteSecurity(int IdSecurity);
	public Security DisplaySecurity(int IdSecurity);
	public Boolean ifExists(Security S);
	public List<Security> DisplaySecurities();
	public void EditSecurity(int IdSecurity,Object o);
	public List<Security> SearchByMaturityDate(Date d);
	public List<Bond> DisplayBonds();
	public List<Stock> DisplayStocks();
	public List<Stock> StocksDownloader(String Sym,String frequency, String Period1, String Period2);
	public BigDecimal getStockPriceInstantly(String Sym);
	public double VolatilityCalculator(String Sym,String Period1, String Period2);
	public List<Company> SearchByInput(String SearchField, String operator, Object o);
	public List<Security> GetTopByInput(String Input,int TopN);
	public List<Security> GetLastByInput(String Input,int TopN);
	//public void DisplayStockPrices();
	public List<Security> SecuritiesFinder(int Number,String operator,double value);
	public double StandardDev(String Sym, String Period1, String Period2);
	public double CoefOfDeviation(String Sym, String Period1, String Period2);
	
	

}
