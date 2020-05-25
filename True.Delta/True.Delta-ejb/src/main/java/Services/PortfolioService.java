package Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Entities.Portfolio;
import Entities.Security;
import Entities.Bond;
import Entities.Stock;
import Entities.User;
import Interfaces.PortfolioServiceLocal;
import Interfaces.PortfolioServiceRemote;
import Enumerations.*;


@Stateless

@LocalBean
public class PortfolioService implements PortfolioServiceLocal, PortfolioServiceRemote {



	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	
	SecuritesServices ss = new SecuritesServices();
	String Period1= "2019-01-02";
	String Period2 = "2019-01-04";

	@Override
	public int AddPortfolio(Portfolio P) {
		em.persist(P);
		System.out.println("IdPortfolio :"+ P.getIdPortfolio());
		return P.getIdPortfolio();
	}

	@Override
	public void DeletePortfolio(int IdPortfolio) {
		Portfolio p = new Portfolio();
		p=em.find(Portfolio.class, IdPortfolio);
		em.remove(p);
	}

	@Override
	public Portfolio DisplayPortfolio(int IdPortfolio) {
		Portfolio p = new Portfolio();
		p=em.find(Portfolio.class, IdPortfolio);
		return p;
	}

	@Override
	public List<Portfolio> DisplayPortfolios() {
		Query query=em.createQuery("select p from Portfolio p");
		return query.getResultList();
	}

	@Override
	public void EditPortfolio(Portfolio p) {
		em.merge(p);
		//System.out.println(stocks());

	}

	@Override
	public void AffectedPortfolio(int idPortfolio,int idUser) {
		User u = new User();
		Portfolio p = new Portfolio();
		u=em.find(User.class, idUser);
		p=em.find(Portfolio.class, idPortfolio);
		u.setPortfolio(p);
		p.setUser(u);
		em.merge(u);
		em.merge(p);


	}

	@Override
	public float getRisk(int idUser) {
		User u = new User();
		u=em.find(User.class, idUser);
		return u.getCustomer().getRisk();
	}

	UserService us = new UserService();

	public List<Stock> stocks(){
		
		//return symbols;
		List<Stock> allStock = new ArrayList<Stock>();

	
			List<Security> a = ss.DisplayStock();

			for (Security security : a) {
				System.out.println(" stock is  "+security.getS());

			}
				
		
			
			return allStock;
		}

		
	
		public List<Security> security(){
		
			List<Security> a = ss.DisplayStock();
		
			return a;
		}
		

	

	@Override
	public List<Stock> Type1Portfolio(float money,List<Stock> ls) {

		List<Stock> chosenStock = new ArrayList<Stock>();
		
		boolean verif=true;
		while(verif)
		{
			
			for (Stock stock : ls) {
				
				if(stock.getClose()<=money) {
				if(stock.getVolume()>0)
				{
					money-=stock.getClose();
					chosenStock.add(stock);
					stock.setVolume(stock.getVolume()-1);
					
				}
				}
				else
				{
					verif=false;
				}
				
			}
			

		}
	
		
		
		
		return chosenStock;
		
	}
	
	@Override
	public List<Stock> Type2ReturnsPortfolio(float returns, List<Stock> ls) {
List<Stock> chosenStock = new ArrayList<Stock>();



	Set<Stock> set = new LinkedHashSet<>();
	set.addAll(ls);
	
		

		return chosenStock;
	}
	
	
	@Override
	public List<Stock> Type2VolPortfolio(float vol,List<Stock> ls) {
		
		List<Stock> chosenStock = new ArrayList<Stock>();
		
		
		
		
			
			for (Stock stock : ls) {
				
					for (int i = 0; i < stock.getVolume(); i++) {
						
						chosenStock.add(stock);
					}
				}
			
			
			
			return chosenStock;
				
		}
			
		

		
		
		
		
	


	@Override
	public void volatilityBasedPortfolio(float maxVol,Portfolio p) {
		List<Stock> chosenStocks = new ArrayList<Stock>();
		double vol =0;
		double mean = 0;
		double sum=stocks().get(0).getClose();
		chosenStocks.add(stocks().get(0));	
		int i=1;

		List<Stock> sortedStockList = stocks().stream().sorted(Comparator.comparing(Stock::getClose)).collect(Collectors.toList());

		while ( chosenStocks.stream().mapToDouble(e->e.getClose()).average().getAsDouble() <maxVol && i<stocks().size()) {	
			chosenStocks.add(stocks().get(i));			
			sum += chosenStocks.get(i).getClose();
			i++;
		}
		
		
		double Mean=chosenStocks.stream().mapToDouble(e->e.getClose()).average().getAsDouble();

		
		p.setReturns(Math.round(Mean));
		p.setVolatility(Math.round((float) (maxVol-5 + 5*Math.random())));
		p.setPrice(Math.round(sum));
		p.setRatio((float)(Mean/(float) (maxVol-5 + 5*Math.random())));

		AddPortfolio(p);


	}

	public double getVol(List<Stock> chosenStocks) {
		double Mean=chosenStocks.stream().mapToDouble(e->e.getClose()).average().getAsDouble();
		System.out.println("Mean"+Mean);
		double PeriodDeviation=0;
		

		for(Stock i1 : chosenStocks)
		{

			double Racine = (i1.getClose()-Mean);
			PeriodDeviation+=Math.pow((i1.getClose()-Mean),2);


		}
		float vol = (float) (PeriodDeviation/(chosenStocks.stream().count()-1));
		return vol;
	}


	@Override
	public Portfolio OptimalPortfolio() {
		// TODO Auto-generated method stub
		List<Portfolio> ls = DisplayPortfolios();
		Portfolio porfolio =  Collections.max(ls, Comparator.comparing(p -> p.getRatio()));
        
        return porfolio;

		
	}
	
	
	public double volatilityCalculator(List<Stock> Ls) {

		long ObsNumber=Ls.stream().count();
		System.out.println("ObsNumber"+ObsNumber);
		double Mean=Ls.stream().mapToDouble(e->e.getAdj_Close()).average().getAsDouble();
		System.out.println("Mean"+Mean);
		double PeriodDeviation=0;
		double Var=0;
		double Racine=0;

		for(Stock i : Ls)
		{

			Racine=(i.getAdj_Close()-Mean);
			PeriodDeviation+=Math.pow((i.getAdj_Close()-Mean),2);


		}
		Var=(PeriodDeviation/(ObsNumber-1));

		return Var;
	}

	

	

}


