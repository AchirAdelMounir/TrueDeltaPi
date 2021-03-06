package Interfaces;
import javax.ejb.Local;
import javax.persistence.Query;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;

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
	public double getStockPriceInstantly(String Sym);
	public double VolatilityCalculator(String Sym,String Period1, String Period2);
	public List<Security> SearchByInput(String SearchField, String operator, Object o);
	public List<Security> GetTopByInput(String Input,int TopN);
	public List<Security> GetLastByInput(String Input,int TopN);
	//public void DisplayStockPrices();
	public List<Security> SecuritiesFinder(int Number,String operator,double value);
	public double StandardDev(String Sym, String Period1, String Period2);
	public double CoefOfDeviation(String Sym, String Period1, String Period2);
	public String StockExcelFinder(String Sym,String frequency, String Period1, String Period2);
	
	public List<Security> DisplayStock();
	public void AddStock(Company c,Stock s);
	public int getIdSecurities(Stock s);
	BarSeries Creating_TimeSeries(String Sym, String frequency, String Period1, String Period2);
	Strategy Calculate_Indicator(BarSeries series);
	void Trading_Strategy(BarSeries series, Strategy strategy);
	public List<String> trader(String Sym, String frequency, String Period1, String Period2,double stop_loss_earns,double stop_loss_drops,double stop_loss);
	public List<Indicators> Indicators_Calculator(String Sym, String frequency, String Period1, String Period2);
}
