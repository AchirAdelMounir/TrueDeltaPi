package ManagedBeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import Entities.Bond;
import Entities.Company;
import Entities.Flux;
import Entities.Security;
import Entities.Stock;
import Services.SecuritesServices;

@ManagedBean(name = "SecurityBean")
@SessionScoped
public class SecuritiyBean {
	@EJB
	SecuritesServices s;

	private int Id;
	private String Type;
	private double Price;
	private String SymStock;
	@Embedded
	private Stock S;
	@Embedded
	private Bond B;
	private Company Company;
	private Set<Flux> F;
	private List<Stock> Ls;
	private Stock Yesterdaystock;
	double PriceDifference;
	double PriceDifferencePercentage;

	public void AddSecurity(Security S) {
		s.AddSecurity(S);
	}

	public List<Security> DisplaySecurities() {
		return s.DisplaySecurities();
	}

	public List<Stock> DisplayStockHistory(String Sym, String frequency, String Period1, String Period2) {
		Ls = s.StocksDownloader(Sym, frequency, Period1, Period2);
		return Ls;
	}

	public double VolatilityCalculated(String Sym, String Period1, String Period2) {
		return s.VolatilityCalculator(Sym, Period1, Period2);
	}

	public double CoefOfDeviation(String Sym, String Period1, String Period2) {
		return s.CoefOfDeviation(Sym, Period1, Period2);
	}

	public double StandardDev(String Sym, String Period1, String Period2) {
		return s.StandardDev(Sym, Period1, Period2);
	}

	public List<Security> LastByInput(String Input, int TopN) {
		return s.GetLastByInput(Input, TopN);
	}

	public List<Security> TopByInput(String Input, int TopN) {
		return s.GetTopByInput(Input, TopN);

	}

	public List<Security> FindSecurity(String SearchField, String operator, Object o) {
		return s.SearchByInput(SearchField, operator, o);
	}

	public void GetPriceInstantanly() {
		

		System.out.println(SymStock);
		Price = s.getStockPriceInstantly(SymStock);
		
		final Calendar cal1 = Calendar.getInstance();
		
	}
	public List<Stock> DisplayInfosStocks()
	{
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		List<Stock> ns = new ArrayList<Stock>();
		Ls = s.StocksDownloader(SymStock, "1d", yes, tod);
		cal1.add(Calendar.DATE, 0);

	

		return Ls;
	}

	public String Redirect() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		SymStock = params.get("Symbol");
		System.out.println(SymStock);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


		String tod = dateFormat.format(cal1.getTime());
		List<Stock> ns = new ArrayList<Stock>();


		cal1.add(Calendar.DATE, 0);
		double price = s.getStockPriceInstantly(SymStock);

	
		List<Stock>Ls1 = s.StocksDownloader(SymStock, "1d", tod, tod);
		Yesterdaystock=Ls1.get(1);
		PriceDifference=price-Yesterdaystock.getAdj_Close();
		PriceDifferencePercentage=(PriceDifference/price)*100;
		return ("StockDisplay?faces-redirect=true");
	}

	private int number = 100;

	public int getNumber() {
		return number;
	}

	public void increment() {
		System.out.println("Incrementing....");
		number++;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public Stock getS() {
		return S;
	}

	public void setS(Stock s) {
		S = s;
	}

	public Bond getB() {
		return B;
	}

	public void setB(Bond b) {
		B = b;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	public Set<Flux> getF() {
		return F;
	}

	public void setF(Set<Flux> f) {
		F = f;
	}

	public String getSymStock() {
		return SymStock;
	}

	public void setSymStock(String symStock) {
		SymStock = symStock;
	}

	public List<Stock> getLs() {
		return Ls;
	}

	public void setLs(List<Stock> ls) {
		Ls = ls;
	}

	public Stock getYesterdaystock() {
		return Yesterdaystock;
	}

	public void setYesterdaystock(Stock yesterdaystock) {
		Yesterdaystock = yesterdaystock;
	}

	public double getPriceDifference() {
		return PriceDifference;
	}

	public void setPriceDifference(double priceDifference) {
		PriceDifference = priceDifference;
	}

	public double getPriceDifferencePercentage() {
		return PriceDifferencePercentage;
	}

	public void setPriceDifferencePercentage(double priceDifferencePercentage) {
		PriceDifferencePercentage = priceDifferencePercentage;
	}

}
