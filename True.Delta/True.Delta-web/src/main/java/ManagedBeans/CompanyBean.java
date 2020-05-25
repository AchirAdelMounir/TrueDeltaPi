package ManagedBeans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

import Entities.*;
import Services.*;

@ManagedBean(name = "CompanyBean")
@SessionScoped

public class CompanyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	CompaniesServices c;
	@ManagedProperty(value = "#{commonUtils}")
	private CommonUtils util;

	private Company C;
	@NotNull
	@Size(min = 1, max = 5)
	private String Symbol;

	@NotNull
	@Size(min = 2, max = 8)
	private String Market;

	@NotNull
	@Size(min = 2, max = 15)
	private String Name;
	@NotNull
	@Size(min = 2, max = 15)
	private String Sector;

	private double Price;

	private double R_Price_Earnings;

	private double DividendYield;

	private double R_Earnings_Share;

	private double Year_Week_Low;

	private double Year_Week_High;
	@NotNull
	private BigInteger Market_Cap_E;

	private BigInteger BITDA;

	private double R_Price_Sales;

	private double R_Price_Book;

	private String SEC_Filings;

	private Set<Security> Securities;
	private List<Company> Co;
	private List<Company> Top;
	private List<Company> LastS;
	private String input;

	public void Initialize() {
		c.CompaniesInfoFinder();
	}

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	public void AddCompany() {
		C = new Company();

		C.setSymbol(Symbol);
		C.setMarket(Market);
		C.setName(Name);
		C.setSector(Sector);
		C.setSEC_Filings(SEC_Filings);
		C.setMarket_Cap_E(Market_Cap_E);

		c.AddCompany(C);
		clear();

		util.redirectWithGet();
	}

	public void DeleteCompany(String sym) {
		c.DeleteCompany(sym);
	}

	public void Update(Company C, String sym) {
		c.EditCompany(sym, C);
	}

	public List<Company> DisplayCompanies() {

		Co = c.DisplayCompanies();
		clear();

		return Co;
	}

	public String DisplayCompany(String sym) {
		BigInteger i = new BigInteger("1000");

		C = c.DisplayCompany(sym);
		C.setBITDA(C.getBITDA().divide(i));
		C.setMarket_Cap_E(C.getMarket_Cap_E().divide(i));
		return ("ShowDetails?faces-redirect=true");

	}

	public String DisplayTopLastNCompaniesFirst(String Input, int TopN) {
		Top = new ArrayList<Company>();
		LastS = new ArrayList<Company>();
		LastS = c.GetLastByInput(Input, TopN);
		Top = c.GetTopByInput(Input, TopN);
		return ("TopLastCompanies?faces-redirect=true");

		
	}
	public void DisplayTopLastNCompanies() {
		System.out.println(input);
		Top = new ArrayList<Company>();
		LastS = new ArrayList<Company>();
		LastS = c.GetLastByInput(input, 4);
		Top = c.GetTopByInput(input,4 );

		
	}

	public List<Company> FindBy(String SearchField, String operator, Object o) {
		return c.SearchByInput(SearchField, operator, o);
	}

	

	public void clear() {
		Symbol = "";
		Market = "";
		Name = "";
		SEC_Filings = "";
		Market_Cap_E = null;
		Sector = "";

	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public String getMarket() {
		return Market;
	}

	public void setMarket(String market) {
		Market = market;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSector() {
		return Sector;
	}

	public void setSector(String sector) {
		Sector = sector;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public double getR_Price_Earnings() {
		return R_Price_Earnings;
	}

	public void setR_Price_Earnings(double r_Price_Earnings) {
		R_Price_Earnings = r_Price_Earnings;
	}

	public double getDividendYield() {
		return DividendYield;
	}

	public void setDividendYield(double dividendYield) {
		DividendYield = dividendYield;
	}

	public double getR_Earnings_Share() {
		return R_Earnings_Share;
	}

	public void setR_Earnings_Share(double r_Earnings_Share) {
		R_Earnings_Share = r_Earnings_Share;
	}

	public double getYear_Week_Low() {
		return Year_Week_Low;
	}

	public void setYear_Week_Low(double year_Week_Low) {
		Year_Week_Low = year_Week_Low;
	}

	public double getYear_Week_High() {
		return Year_Week_High;
	}

	public void setYear_Week_High(double year_Week_High) {
		Year_Week_High = year_Week_High;
	}

	public BigInteger getMarket_Cap_E() {
		return Market_Cap_E;
	}

	public void setMarket_Cap_E(BigInteger market_Cap_E) {
		Market_Cap_E = market_Cap_E;
	}

	public BigInteger getBITDA() {
		return BITDA;
	}

	public void setBITDA(BigInteger bITDA) {
		BITDA = bITDA;
	}

	public double getR_Price_Sales() {
		return R_Price_Sales;
	}

	public void setR_Price_Sales(double r_Price_Sales) {
		R_Price_Sales = r_Price_Sales;
	}

	public double getR_Price_Book() {
		return R_Price_Book;
	}

	public void setR_Price_Book(double r_Price_Book) {
		R_Price_Book = r_Price_Book;
	}

	public String getSEC_Filings() {
		return SEC_Filings;
	}

	public void setSEC_Filings(String sEC_Filings) {
		SEC_Filings = sEC_Filings;
	}

	public Set<Security> getSecurities() {
		return Securities;
	}

	public void setSecurities(Set<Security> securities) {
		Securities = securities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Company getC() {
		return C;
	}

	public void setC(Company c) {
		C = c;
	}

	public List<Company> getTop() {
		return Top;
	}

	public void setTop(List<Company> top) {
		Top = top;
	}

	public List<Company> getLastS() {
		return LastS;
	}

	public void setLastS(List<Company> lastS) {
		LastS = lastS;
	}


	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
