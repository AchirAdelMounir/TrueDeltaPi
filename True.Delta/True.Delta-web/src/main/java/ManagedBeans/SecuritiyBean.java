package ManagedBeans;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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

@ManagedBean
@ApplicationScoped
public class SecuritiyBean {
	@EJB
	SecuritesServices s;

	private int Id;
	private String Type;
	private double Price;
	@Embedded
	private Stock S;
	@Embedded
	private Bond B;
	private Company Company;
	private Set<Flux> F;
	
	public void AddSecurity(Security S)
	{	
	s.AddSecurity(S);	
	}
	public List<Security> DisplaySecurities()
	{
		return s.DisplaySecurities();
	}
	public List<Stock> DisplayStockHistory(String Sym, String frequency, String Period1, String Period2)
	{
		return s.StocksDownloader(Sym, frequency, Period1, Period2);
	}
	public double VolatilityCalculated(String Sym, String Period1, String Period2)
	{
		return s.VolatilityCalculator(Sym, Period1, Period2);
	}
	public double CoefOfDeviation(String Sym, String Period1, String Period2)
	{
		return s.CoefOfDeviation(Sym, Period1, Period2);
	}
	public double StandardDev (String Sym, String Period1, String Period2)
	{
		return s.StandardDev(Sym, Period1, Period2);
	}
	public List<Security> LastByInput(String Input, int TopN)
	{
		return s.GetLastByInput(Input, TopN);
	}
	public List<Security> TopByInput(String Input, int TopN)
	{
		return s.GetTopByInput(Input, TopN);
		
	}
	public List<Security> FindSecurity(String SearchField, String operator, Object o)
	{
		return s.SearchByInput(SearchField, operator, o);
	}
	public List<Security> DisplayBonds()
	{
		return s.DisplayBonds();
	}
	public List<Security> DisplayStocks()
	{
		return s.DisplayStocks();
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

}
