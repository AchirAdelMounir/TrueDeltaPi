package Services;

import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Company;
import Entities.Security;
import Interfaces.CompaniesServicesInterfaceLocal;
import Interfaces.CompaniesServicesInterfaceRemote;

@Stateful

public class CompaniesServices implements CompaniesServicesInterfaceRemote, CompaniesServicesInterfaceLocal {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public String AddCompany(Company C) {
		if (ifExists(C)) {
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
		if (ifExists(C)) {

			em.remove(C);
		}

	}

	@Override
	public Company DisplayCompany(String sym) {
		Company C = new Company();
		C = em.find(Company.class, sym);
		if (ifExists(C)) {
			return C;
		} else
			return null;

	}

	@Override
	public List<Company> DisplayCompanies() {
		TypedQuery<Company> query = em.createQuery("Select * from Company", Company.class);
		return query.getResultList();

	}

	@Override
	public Company EditCompany(Company C, String sym) {
		if (ifExists(C)) {
			Company OldC = em.find(Company.class, sym);

			OldC.setMarket(C.getMarket());
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
			OldC.setSEC_Filings("http://www.sec.gov/cgi-bin/browse-edgar?action=getcompany&CIK=" + OldC.getSymbol());

			OldC.setSecurities(C.getSecurities());

			return OldC;

		} else
			return null;
	}

	@Override
	public void CompaniesInfoFinder() {

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

				String line = input.nextLine().replaceAll("(?m)$", ",").replaceAll(",,", ",0,")//pour enlever les valeur abbérrantes
						.replace("Allergan, Plc", "Allergan. Plc")//pour enelever les virgules parce que ce sont les délémiteurs
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

				// Verified already with ifExists method !!
				AddCompany(C);

				System.out.println(C);

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
	
	/*public List<Security> SearchByMarketCap(BigInteger M) {
		return (em.createQuery("select c from Company c where MaturityDate <=" + d, Security.class).getResultList());
	}*/

}
