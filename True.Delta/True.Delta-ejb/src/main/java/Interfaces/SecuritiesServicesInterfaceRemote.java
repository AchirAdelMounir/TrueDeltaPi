package Interfaces;
import javax.ejb.Remote;

import Entities.Bond;
import Entities.Company;
import Entities.Security;
import Entities.Stock;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
@Remote

public interface SecuritiesServicesInterfaceRemote {
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
	public double getStockPriceInstantly(String Sym);
	public double VolatilityCalculator(String Sym,String Period1, String Period2);
	public List<Security> SearchByInput(String SearchField, String operator, Object o);
	public List<Security> GetTopByInput(String Input,int TopN);
	//public void DisplayStockPrices();
	public List<Security> SecuritiesFinder(int Number,String operator,double value);
	public double StandardDev(String Sym, String Period1, String Period2);
	public double CoefOfDeviation(String Sym, String Period1, String Period2);
	public List<Security> GetLastByInput(String Input,int TopN);
	public String StockExcelFinder(String Sym,String frequency, String Period1, String Period2);
	

}
