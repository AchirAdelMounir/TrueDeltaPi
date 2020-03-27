package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Bond;
import Entities.Company;
import Entities.Security;
import Entities.Stock;
import Interfaces.SecuritiesServicesInterfaceLocal;
import Interfaces.SecuritiesServicesInterfaceRemote;

@Stateful

public class SecuritesServices implements SecuritiesServicesInterfaceRemote, SecuritiesServicesInterfaceLocal {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public int AddSecurity(Security S) {
		if(ifExists(S)==false)
		{
		em.persist(S);
		System.out.println("Bond:" + S.getId());
		return S.getId();}
		else 
			return 0;

	}

	@Override
	public void DeleteSecurity(int IdSecurity) {
		Security S = new Security();
		S = em.find(Security.class, IdSecurity);
		if(ifExists(S)==false)
		{
		em.remove(S);
		}
		

	}

	@Override
	public Security DisplaySecurity(int IdSecurity) {
		Security S = new Security();
		if(ifExists(S)==false)
		{
		S = em.find(Security.class, IdSecurity);
		return S;}
		else return null;

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
	public Stock StocksDownloader(String Sym, String Period1, String Period2) {
		 /*Period1 = "2019-05-05";
		 Period2 = "2020-02-05";
		*/
		Date localDate1 = Date.valueOf(Period1);
		Date localDate2 = Date.valueOf(Period2);
		long p1 = localDate1.getTime() / 1000;
		
		long p2 = localDate2.getTime() / 1000;
		String url = "https://query1.finance.yahoo.com/v7/finance/download/" + Sym
				+ "?period1="+p1+"&period2="+p2+"&interval=1d&events=history";
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

				// System.out.println(line);
				Stock S = new Stock();
				S.setDATE(Date.valueOf(newinput.next()));
				S.setOpen(newinput.nextDouble());
				S.setHigh(newinput.nextDouble());
				S.setLow(newinput.nextDouble());
				S.setClose(newinput.nextDouble());
				S.setAdj_Close(newinput.nextDouble());
				S.setVolume(newinput.nextInt());
				System.out.println(S);

			}

		} catch (Exception e) {
			System.err.println(e);

		}
		return null;

	}

	@Override
	public void getStockPriceInstantly()  {
		/*String Sym="KO";
		URL url= new URL("https://finance.yahoo.com/quote/KO?p=KO");
		URLConnection urlConn=url.openConnection();
		InputStreamReader inStream=new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff=new BufferedReader(inStream);
		String line=buff.readLine();
		while(line!=null)
		{
			System.out.println(line);
			
		}*/
		
		
		
		
		
	}

	@Override
	public Boolean ifExists(Security S) {
		if(em.find(Company.class, S.getId())==null)
			return false;
		else 
			return true;
	}
	

}
