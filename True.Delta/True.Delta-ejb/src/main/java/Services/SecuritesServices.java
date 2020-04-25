package Services;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entities.Bond;
import Entities.Company;
import Entities.Portfolio;
import Entities.Security;
import Entities.Stock;
import Interfaces.SecuritiesServicesInterfaceLocal;
import Interfaces.SecuritiesServicesInterfaceRemote;
import yahoofinance.YahooFinance;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Stateless
@LocalBean

public class SecuritesServices implements SecuritiesServicesInterfaceRemote, SecuritiesServicesInterfaceLocal {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public int AddSecurity(Security S) {
		if (ifExists(S) == false) {
			if(S.getB()==null)
			{
				double d=getStockPriceInstantly(S.getCompany().getSymbol());
				S.setType("Stock");
				S.setPrice(d);
				
			}
			else if (S.getB()!=null)
			{
				S.setType("Bond");
			}
		
			em.persist(S);
			System.out.println("Bond:" + S.getId());
			return S.getId();
		} else
			return 0;

	}
	@Override
	public void AddStock(Company c,Stock s) {

				
			Security st = new Security();
			st.setCompany(c);
			st.setS(s);
			
			System.out.println(c.getSecurities());
			
			em.persist(st);
			
			
	

	}
	
	

	@Override
	public void DeleteSecurity(int IdSecurity) {
		Security S = new Security();
		S = em.find(Security.class, IdSecurity);
		if (ifExists(S) == true) {
			em.remove(S);
		}

	}

	@Override
	public Security DisplaySecurity(int IdSecurity) {
		Security S = new Security();
		if (ifExists(S) == true) {
			S = em.find(Security.class, IdSecurity);
			return S;
		} else
			return null;

	}

	@Override
	public List<Security> DisplaySecurities() {
		TypedQuery<Security> query = em.createQuery("Select * from Security", Security.class);
		return query.getResultList();

	}

	@Override
	public void EditSecurity(int IdSecurity, Object o) {
		if (o.getClass() == Bond.class) {
			Security old = em.find(Security.class, IdSecurity);
			Bond n = (Bond) o;
			old.setB(n);

		}
		if (o.getClass() == Stock.class) {
			Security old = em.find(Security.class, IdSecurity);
			Stock n = (Stock) o;
			old.setS(n);

		}

	}

	@Override
	public List<Security> SearchByMaturityDate(Date d) {
		return (em.createQuery("select s from Security s where MaturityDate <=" + d, Security.class).getResultList());
	}

	@Override
	public List<Bond> DisplayBonds() {
		List<Security> LS = em.createQuery("select s from Security s where Yield!=null", Security.class)
				.getResultList();
		List<Bond> Lb = new ArrayList<>();
		for (Security s : LS) {
			Bond B = new Bond();
			B.setCoupon(s.getB().getCoupon());
			B.setMaturityDate(s.getB().getMaturityDate());
			B.setPrice(s.getB().getPrice());
			B.setYield(s.getB().getYield());
			Lb.add(B);

		}
		return Lb;

	}

	@Override
	public List<Stock> DisplayStocks() {
		List<Security> LS = em.createQuery("select s from Security s where Yield!=null", Security.class)
				.getResultList();
		List<Stock> Ls = new ArrayList<>();
		for (Security s : LS) {
			Stock n = new Stock();
			n.setAdj_Close(s.getS().getAdj_Close());
			n.setClose(s.getS().getClose());
			n.setDATE(s.getS().getDATE());
			n.setHigh(s.getS().getHigh());
			n.setLow(s.getS().getLow());
			n.setOpen(s.getS().getOpen());
			n.setVolume(s.getS().getVolume());
			Ls.add(n);

		}
		return Ls;
	}

	@Override
	public List<Stock> StocksDownloader(String Sym,String frequency, String Period1, String Period2) {		
		/*
		 * Period1 = "2019-05-05"; Period2 = "2020-02-05";
		 */
		List<Stock> Ls = new ArrayList<>();
		Date localDate1 = Date.valueOf(Period1);
		Date localDate2 = Date.valueOf(Period2);
		long p1 = localDate1.getTime() / 1000;

		long p2 = localDate2.getTime() / 1000;
		String url = "https://query1.finance.yahoo.com/v7/finance/download/" + Sym + "?period1=" + p1 + "&period2=" + p2
				+ "&interval="+frequency+"&events=history";
		try {
			URL yhoofin = new URL(url);
			URLConnection data = yhoofin.openConnection();
			
			Scanner input = new Scanner(data.getInputStream()).useDelimiter(",");
		
			if (input.hasNext()) {
				input.nextLine();
			}
			while (input.hasNextLine()) {

				String line = input.nextLine().replaceAll("(?m)$", ",");
				Scanner newinput = new Scanner(line).useDelimiter(",");

			
				Stock S = new Stock();
				S.setDATE(Date.valueOf(newinput.next()));
				S.setOpen(Double.parseDouble(newinput.next()));
				S.setHigh(Double.parseDouble(newinput.next()));
				S.setLow(Double.parseDouble(newinput.next()));
				S.setClose(Double.parseDouble(newinput.next()));
				S.setAdj_Close(Double.parseDouble(newinput.next()));
				S.setVolume(Integer.parseInt(newinput.next()));
				
				Ls.add(S);

			}

		} catch (Exception e) {
			System.err.println(e);

		}
		return Ls;

	}

	@Override
	public double getStockPriceInstantly(String Sym) {
		yahoofinance.Stock stock = null;
		try {
			stock = YahooFinance.get(Sym);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			double price = stock.getQuote(true).getPrice().doubleValue();
			return price;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		

	}

	@Override
	public Boolean ifExists(Security S) {
		if (em.find(Company.class, S.getId()) == null)
			return false;
		else
			return true;
	}

	@Override
	public double VolatilityCalculator(String Sym, String Period1, String Period2) {
		System.out.println("HELLO WORLD");
		List<Stock> Ls = StocksDownloader(Sym,"1d", Period1, Period2);
		long ObsNumber = Ls.stream().count();
		System.out.println("ObsNumber" + ObsNumber);
		double Mean = Ls.stream().mapToDouble(e -> e.getAdj_Close()).average().getAsDouble();
		System.out.println("Mean" + Mean);
		double PeriodDeviation = 0;
		double Var = 0;
		double Racine = 0;

		for (Stock i : Ls) {
			System.out.println("PeriodDev" + PeriodDeviation);
			System.out.println("PeriodDev2   h" + (i.getAdj_Close() - Mean));

			Racine = (i.getAdj_Close() - Mean);
			PeriodDeviation += Math.pow((i.getAdj_Close() - Mean), 2);

			System.out.println("PeriodDev" + PeriodDeviation);
		}
		Var = (PeriodDeviation / (ObsNumber - 1));
		System.out.println("Var" + Var);
		System.out.println("Mean" + Mean);
		return Var;
	}

	@Override
	public List<Security> SearchByInput(String SearchField, String operator, Object o) {
		if (o instanceof Integer) {
			int O = (Integer) o;
			
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		} else if (o instanceof Double) {
			Double O = (Double) o;
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		} else if (o instanceof String) {
			String O = (String) o;
			return (em
					.createQuery("select s from Security s where " + SearchField + " " + operator + " " + "'" + O + "'",
							Security.class)
					.getResultList());

		} else if (o instanceof BigInteger) {
			BigInteger O = (BigInteger) o;
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		} else if (o instanceof Date) {
			Date O = (Date) o;
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		}
		return null;
	}

	public List<Security> GetTopByInput(String Input, int TopN) {
		return (em.createQuery("select s from Security s ORDER BY " + Input + " DESC", Security.class)
				.setMaxResults(TopN).getResultList());

	}
	public List<Security> GetLastByInput(String Input,int TopN)
	{
		return (em.createQuery("select s from Security s ORDER BY " + Input + " ASC", Security.class)
				.setMaxResults(TopN).getResultList());
		
	}

	
	public List<Security> SecuritiesFinder(int Number,String operator,double value)
	{if (Number==-1)
	{
		return (em.createQuery("select s from Security s WHERE s.price"+operator+""+value, Security.class)
				.getResultList());
		
	}
	else {
		return (em.createQuery("select s from Security s WHERE s.price"+operator+""+value, Security.class)
				.setMaxResults(Number).getResultList());
	}
		
		
		
	}
	public double CoefOfDeviation(String Sym, String Period1, String Period2)
	{
		List<Stock> Ls = StocksDownloader(Sym,"1d", Period1, Period2);
		double Mean = Ls.stream().mapToDouble(e -> e.getAdj_Close()).average().getAsDouble();
		double StandardDev=Math.sqrt(VolatilityCalculator(Sym, Period1, Period2));
		return ((StandardDev/Mean)*100);
		
	}
	public double StandardDev(String Sym, String Period1, String Period2)
	{
		return Math.sqrt(VolatilityCalculator(Sym, Period1, Period2));
	
		
	}

//	public void TreeClassifier(String[] args) throws Exception {
//	    // load data
//	    ArffLoader loader = new ArffLoader();
//	    loader.setFile(new File(args[0]));
//	    Instances structure = loader.getStructure();
//	    structure.setClassIndex(structure.numAttributes() - 1);
//
//	    // train NaiveBayes
//	    NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
//	    nb.buildClassifier(structure);
//	    Instance current;
//	    while ((current = loader.getNextInstance(structure)) != null)
//	      nb.updateClassifier(current);
//
//	    // output generated model
//	    System.out.println(nb);
//	  }



	@Override
	public List<Security> DisplayStock() {
		
		Query query=em.createQuery("select s from Security s");
		return query.getResultList();
		
	}
	
	@Override
	public int getIdSecurities(Stock s) {
	return 0;
	}
	

	

}