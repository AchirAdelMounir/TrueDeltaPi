package Services;

import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;


import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import Entities.Company;
import Entities.Security;
import Entities.Stock;
import Entities.User;
import Interfaces.CompaniesServicesInterfaceLocal;
import Interfaces.CompaniesServicesInterfaceRemote;

@Stateless


public class CompaniesServices implements CompaniesServicesInterfaceRemote, CompaniesServicesInterfaceLocal {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public String AddCompany(Company C) {
		if (ifExists(C) == false) {
			em.persist(C);
			System.out.println("Company:" + C.getSymbol());
			return C.getSymbol();
		} else
			return null;

	}

	@Override
	public void DeleteCompany(String sym) {
		Company C = new Company();
		C = em.find(Company.class, sym);
		if (ifExists(C) == true) {

			em.remove(C);
		}

	}

	@Override
	public Company DisplayCompany(String sym) {
		Company C = new Company();
		C = em.find(Company.class, sym);
		if (ifExists(C) == true) {
			return C;
		} else
			return null;

	}

	@Override
	public List<Company> DisplayCompanies() {
		TypedQuery<Company> query = em.createQuery("Select C from Company C", Company.class);
		return query.getResultList();

	}

	@Override
	public Company EditCompany(String sym,Company C) {
		if (ifExists(C)) {
			Company OldC = em.find(Company.class, sym);

			/*OldC.setMarket(C.getMarket());
			OldC.setName(C.getName());
			OldC.setSector(C.getSector());
			OldC.setBITDA(C.getBITDA());
			OldC.setDividendYield(C.getDividendYield());
			OldC.setMarket_Cap_E(C.getMarket_Cap_E());
			
			OldC.setPrice(C.getPrice());
			OldC.setR_Earnings_Share(C.getR_Earnings_Share());
			OldC.setR_Price_Book(C.getR_Price_Book());
			OldC.setR_Price_Earnings(C.getR_Price_Earnings());
			OldC.setR_Price_Sales(C.getR_Price_Sales());
			OldC.setYear_Week_High(C.getYear_Week_High());
			OldC.setYear_Week_Low(C.getYear_Week_Low());
			OldC.setSEC_Filings("http://www.sec.gov/cgi-bin/browse-edgar?action=getcompany&CIK=" + OldC.getSymbol());*/

			//OldC.setSecurities(C.getSecurities());
em.merge(OldC);
			return OldC;

		} else
			return null;
	}

	@Override
	public void CompaniesInfoFinder() {
		List<Company>L=new ArrayList<>();

		String url = "https://datahub.io/core/s-and-p-500-companies-financials/r/constituents-financials.csv";
		try {
			URL yhoofin = new URL(url);
			URLConnection data = yhoofin.openConnection();
			data.addRequestProperty("User-Agent", "Mozilla");
			Scanner input = new Scanner(data.getInputStream()).useDelimiter(",");
			if (input.hasNext()) {
				input.nextLine();
			}
			while (input.hasNextLine()) {

				String line = input.nextLine().replaceAll("(?m)$", ",").replaceAll(",,", ",0,")// pour enlever les
																								// valeur abbérrantes
						.replace("Allergan, Plc", "Allergan. Plc")// pour enelever les virgules parce que ce sont les
																	// délémiteurs
						.replace("American International Group, Inc.", "American International Group. Inc.")
						.replace("Analog Devices, Inc.", "Analog Devices Inc.")
						.replace("AvalonBay Communities, Inc.", "AvalonBay Communities Inc.")
						.replace("Baker Hughes, a GE Company", "Baker Hughes a GE Company")
						.replace("CA, Inc.", "CA Inc.")
						.replace("Essex Property Trust, Inc.", "Essex Property Trust Inc.")
						.replace("Coty, Inc", "Coty Inc").replace("Facebook, Inc.", "Facebook Inc.")
						.replace("Nasdaq, Inc.", "Nasdaq Inc.").replace("S&P Global, Inc.", "S&P Global Inc.")
						.replace("Tapestry, Inc.", "Tapestry Inc.")
						.replace("United Rentals, Inc.", "United Rentals Inc.")
						.replace("Universal Health Services, Inc.", "Universal Health Services Inc.")
						.replace("540167833.423084", "540167833");
				Scanner newinput = new Scanner(line).useDelimiter(",");
				// System.out.println(newinput.nextLine());
				Company C = new Company();
				C.setSymbol(newinput.next());

				C.setName(newinput.next());

				C.setSector(newinput.next());

				C.setPrice(newinput.nextDouble());

				C.setR_Price_Earnings(newinput.nextDouble());

				C.setDividendYield(newinput.nextDouble());

				C.setR_Earnings_Share(newinput.nextDouble());

				C.setYear_Week_Low(newinput.nextDouble());

				C.setYear_Week_High(newinput.nextDouble());

				C.setMarket_Cap_E(newinput.nextBigInteger());

				C.setBITDA(newinput.nextBigInteger());

				C.setR_Price_Sales(newinput.nextDouble());

				C.setR_Price_Book(newinput.nextDouble());
				C.setSEC_Filings(newinput.next());
				C.setMarket("Nasdaq");

				
				L.add(C);

				System.out.println(C);

			}
			ReplaceMissingValues(L);
			for(Company c:L)
			{
				// Verified already with ifExists method !!
				AddCompany(c);
			}

		} catch (Exception e) {
			System.err.println(e);

		}

	}

	@Override
	public Boolean ifExists(Company C) {
		if (em.find(Company.class, C.getSymbol()) == null)
			return false;
		else
			return true;

	}

	@Override
	public List<Company> SearchByMarketCap(BigInteger M, String operator) {
		return (em.createQuery("select c from Company c where Market_Cap_E " + operator + " " + M, Company.class)
				.getResultList());
	}

	@Override
	public List<Company> SearchByInput(String SearchField, String operator, Object o) {
		if (o instanceof Integer) {
			int O = (Integer) o;
			return (em.createQuery("select c from Company c where " + SearchField + " " + operator + " " + O,
					Company.class).getResultList());

		}
		else if (o instanceof Double) {
			Double O = (Double) o;
			return (em.createQuery("select c from Company c where " + SearchField + " " + operator + " " + O,
					Company.class).getResultList());

		}
		else if (o instanceof String) {
			String O = (String) o;
			return (em.createQuery("select c from Company c where c.Symbol like'"+o+"'"+"or c.Name like'%"+o+"%'"+"or c.Market like'%"+o+"%' or c.Sector like'%"+o+"%'",
					Company.class).getResultList());
			/*CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Company> criteriaQuery = cb.createQuery(Company.class);	
			Root<Company> rootOfQuery = criteriaQuery.from(Company.class);
			criteriaQuery.select(rootOfQuery).where(cb.like(rootOfQuery.get("symbol"), (Expression<String>) o));*/

		}
		else if (o instanceof BigInteger)
		 {
			BigInteger O = (BigInteger) o;
			return (em.createQuery("select c from Company c where " + SearchField + " " + operator + " " + O,
					Company.class).getResultList());

		}
		else if(o instanceof Date)
		 {
			Date O = (Date) o;
			return (em.createQuery("select c from Company c where " + SearchField + " " + operator + " " + O,
					Company.class).getResultList());

		}
		return null;
	}
	
	public List<Company> GetTopByInput(String Input,int TopN)
	{
		return (em.createQuery("select c from Company c ORDER BY "+Input+" DESC",
	          Company.class).setMaxResults(TopN).getResultList());
		
	}
	public List<Company> GetLastByInput(String Input,int TopN)
	{
		return (em.createQuery("select c from Company c ORDER BY "+Input+" ASC",
		          Company.class).setMaxResults(TopN).getResultList());
		
	}
	/*public User GetUserByStock()
	{
		User u=new User();
		em.createQuery("Select u from User u and p from Portfolio p and s from Security s where u=p.user and");
	}*/

	@Override
	public List<Company> ReplaceMissingValues(List<Company> L) {
		
	for(Company i: L)
	{
		if(i.getBITDA()==BigInteger.valueOf(0)) {
			//i.setBITDA(L.stream().flatMapToBigInt(e->e.getBITDA()).average());
		}
		else if(i.getDividendYield()==0)
		{
			i.setDividendYield(L.stream().mapToDouble(e->e.getDividendYield()).average().getAsDouble());
		}
		else if(i.getPrice()==0)
		{
			i.setPrice(L.stream().mapToDouble(e->e.getPrice()).average().getAsDouble());
		}
		else if(i.getR_Earnings_Share()==0)
		{
			i.setR_Earnings_Share(L.stream().mapToDouble(e->e.getR_Earnings_Share()).average().getAsDouble());
		}
		else if(i.getR_Price_Book()==0)
		{
			i.setR_Price_Book(L.stream().mapToDouble(e->e.getR_Price_Book()).average().getAsDouble());
		}
		else if(i.getR_Price_Earnings()==0)
		{
			i.setR_Price_Earnings(L.stream().mapToDouble(e->e.getR_Price_Earnings()).average().getAsDouble());
		}
		else if(i.getR_Price_Sales()==0)
		{
			i.setR_Price_Sales(L.stream().mapToDouble(e->e.getR_Price_Sales()).average().getAsDouble());
		}
		else if(i.getYear_Week_High()==0)
		{
			i.setYear_Week_High(L.stream().mapToDouble(e->e.getYear_Week_High()).average().getAsDouble());
		}
		else if(i.getYear_Week_Low()==0)
		{
			i.setYear_Week_Low(L.stream().mapToDouble(e->e.getYear_Week_Low()).average().getAsDouble());
		}
	}
		
		return L;
	}

	

	
	

}
