package Entities;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table( name= "COMPANIES")
public class Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -558553967080513790L;
	@Id
	@Column(name="SYMBOL")
	private String Symbol;
	@Column(name="MARKET")
	private String Market;
	@Column(name="NAME")
	private String Name;
	@Column(name="SECTOR")
	private String Sector;

	@OneToMany(mappedBy="Company",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
private Set<Security> Securities;

	@Column(name="PRICE")
	private double Price;
	@Column(name="R_Price_Earnings")
	private double R_Price_Earnings;	
	@Column(name="YIELD")
	private double DividendYield;	
	@Column(name="R_Earnings_Share")
	private double R_Earnings_Share;	
	@Column(name="52_Week_Low")
	private double Year_Week_Low;	
	@Column(name="52_Week_High")
	private double Year_Week_High;
	@Column(name="Market_Cap_E")
	private BigInteger Market_Cap_E;
	@Column(name="BITDA")
	private BigInteger BITDA;	
	@Column(name="R_Price_Sales")
	private double R_Price_Sales;	
	@Column(name="R_Price_Book")
	private double R_Price_Book;	
	@Column(name="SEC_Filings")
	private String SEC_Filings;
	
	public Company() {
		super();
	}
	public Company(String symbol2, String market2, String name2, String sector2, String sEC_Filings2) {
		// TODO Auto-generated constructor stub
	}
	public Company(String name2, String market2, String symbol2, String sector2, String sEC_Filings2,
			BigInteger market_Cap_E2) {
		// TODO Auto-generated constructor stub
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
	

	public Set<Security> getSecurities() {
		return Securities;
	}

	public void setSecurities(Set<Security> securities) {
		Securities = securities;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Company [Symbol=" + Symbol + ", Market=" + Market + ", Name=" + Name + ", Sector=" + Sector + ", Price="
				+ Price + ", R_Price_Earnings=" + R_Price_Earnings + ", DividendYield=" + DividendYield
				+ ", R_Earnings_Share=" + R_Earnings_Share + ", Year_Week_Low=" + Year_Week_Low + ", Year_Week_High="
				+ Year_Week_High + ", Market_Cap_E=" + Market_Cap_E + ", BITDA=" + BITDA + ", R_Price_Sales="
				+ R_Price_Sales + ", R_Price_Book=" + R_Price_Book + ", SEC_Filings=" + SEC_Filings + "]";
	}

	
	
}
