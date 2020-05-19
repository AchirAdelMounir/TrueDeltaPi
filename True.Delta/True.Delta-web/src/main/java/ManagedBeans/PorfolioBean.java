package ManagedBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UpdateModelException;
import javax.faces.context.ExternalContext;

import Entities.Portfolio;
import Entities.Security;
import Entities.Stock;
import Entities.User;
import Enumerations.Portfolio_Type;
import Services.FluxService;
import Services.PortfolioService;
import Services.SecuritesServices;
import Services.UserService;

@ManagedBean(name = "PorfolioBean")
@SessionScoped
public class PorfolioBean {
	@EJB
	UserService us ;
	@EJB
	PortfolioService up ;
	@EJB
	SecuritesServices usec;
	@EJB
	FluxService uf;
	
	public int selecteduser;
	public String test;
	
	public List<User> allUsers;
	
	public String returns;
	
	public String price;
	
	public String volatility;
	private Map<String, Object> sessionMap;

	private ExternalContext externalContext;
	
	
	public String getReturns() {
		return returns;
	}
	public void setReturns(String returns) {
		this.returns = returns;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVolatility() {
		return volatility;
	}
	public void setVolatility(String volatility) {
		this.volatility = volatility;
	}
	public int getSelecteduser() {
		return selecteduser;
	}
	public void setSelecteduser(int selecteduser) {
		this.selecteduser = selecteduser;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String t) {
		this.test = t;
	}
	public List<User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	
	public List<Portfolio> DisplayUnAffectedPortfolio()
	{
		List<Portfolio> lp=up.DisplayPortfolios().stream().filter(e->e.getUser() == null).collect(Collectors.toList());
		return lp;
		
	}
	public List<Portfolio> DisplayValidatePortfolio()
	{
		List<Portfolio> lp=up.DisplayPortfolios().stream().filter(e->e.getPrice() == 0).collect(Collectors.toList());
		return lp;
		
	}
	
	public List<Portfolio> DisplayAffectedPortfolio()
	{
		List<Portfolio> lp=up.DisplayPortfolios().stream().filter(e->e.getUser() != null).collect(Collectors.toList()); 
		return lp;
		
	}
	
	public List<Portfolio> DisplayAllPortfolio()
	{
		List<Portfolio> lp=up.DisplayPortfolios(); 
		
		
		return lp;
		
	}
	
	@PostConstruct
	public void init()
	{
		
		setAllUsers(us.DisplayUsers());
		
	}
	
	public void Deleteportfolio(int id) 
	{
			
		for (User user : us.DisplayUsers().stream().filter(e->e.getPortfolio() != null).collect(Collectors.toList())) {
			
			if(user.getPortfolio().getIdPortfolio() == id)
			{
				user.setPortfolio(null);
				us.EditUser(user);
				up.DeletePortfolio(id);
			}
		}
			
		
		
			}
		
		
			
		
		
		
		
	
	
	public void validatePort(int idport)
	{
		up.AffectedPortfolio(idport, selecteduser);
		
		
	}
	
	public void Type1Portfolio(Portfolio i)
	{
		
		if(i.getTypePortfolio().equals(Portfolio_Type.risk_neutral))
		{
			List<Security> ls =usec.DisplayStock();
			List<Stock> lstock= new ArrayList<Stock>();
			for (Security security : ls) {
				lstock.add(security.getS());
				System.out.println(security.getS());
			}
			List<Stock> chosen=up.Type1Portfolio(Integer.parseInt(price),lstock);
			
			LinkedHashSet<Stock> set = new LinkedHashSet<>();
			set.addAll(chosen);
			
			
			double price=0;
			float returns=0;
			float volatility=0;
			
			for (Stock s : set) {

			    System.out.println(s + " : " + Collections.frequency(chosen, s));
			  for (Security sec : ls) {
				  
				  if(sec.getS().getDATE().equals(s.getDATE())&&sec.getS().getClose()==s.getClose())
				  {
					  
					  Date periode2 = new Date();
					  Calendar c = Calendar.getInstance(); 
					  c.setTime(s.getDATE()); 
					  c.add(Calendar.MONTH, +3);
					  SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
					  String d = format1.format(c.getTime());
					   uf.AffectedStock(i.getIdPortfolio(),sec.getId(),(Collections.frequency(chosen, s)*100)/chosen.size(),Collections.frequency(chosen, s));
					  price += (Collections.frequency(chosen, s))*s.getClose();
					  returns+=(usec.getStockPriceInstantly(sec.getCompany().getSymbol())-s.getClose())/s.getClose()*(Collections.frequency(chosen, s)*100)/chosen.size();
					  System.out.println("returns : "+returns);
					 System.out.println("Sym : "+sec.getCompany().getSymbol()+"date : "+s.getDATE()+"date 2 :"+d);
					  volatility+=usec.VolatilityCalculator(sec.getCompany().getSymbol(), s.getDATE().toString(),d)*(Collections.frequency(chosen, s))/chosen.size();
					  System.out.println(volatility);
					  
				  }
				 
			  }
			    
			}
			Portfolio p =up.DisplayPortfolio(i.getIdPortfolio());
			p.getUser().setComplains(null);
			p.getUser().setContratcs(null);
			p.getUser().setFeedbacks(null);
			p.setVolatility(volatility);
			p.setF(null);
			p.setReturns(returns);
			p.setPrice(price);
			p.setRatio(returns/volatility);
			up.EditPortfolio(p);
			
			
		}
		else if (i.getTypePortfolio().equals(Portfolio_Type.risk_seeking))
		{
			System.out.println("----------------------------Stock Available-----------------------------------");
			List<Security> ls =usec.DisplayStock();
			List<Stock> lstock= new ArrayList<Stock>();
			for (Security s : ls) {
				System.out.println(s.getS());
			}
			
			System.out.println("--------------------------------Selected Stock (volatiliy)----------------------------------------");
			double volatility=0;
			float vol=Float.parseFloat(getVolatility());
			
			for (Security s : ls) {
				Date periode2 = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(s.getS().getDATE()); 
				c.add(Calendar.MONTH, +3);
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
				String d = format1.format(c.getTime());
				volatility=usec.VolatilityCalculator(s.getCompany().getSymbol(), s.getS().getDATE().toString(), d);
				System.out.println("volatility   :"+volatility);

				if(volatility<vol)
				{
					lstock.add(s.getS());
					vol-=volatility;
				}
				
				
			}
			System.out.println("--------------------------------stock final----------------------------------------");
			
			
			for (Stock stock : lstock) {
				System.out.println(stock);
			}
			
			System.out.println("-----------------------------------------------------------------------------");
			
			List<Stock> sfinal=up.Type2VolPortfolio(vol, lstock);
			
			Set<Stock> set = new LinkedHashSet<>();
			set.addAll(sfinal);
			for (Stock stock : set) {
				System.out.println(stock+" : "+Collections.frequency(sfinal, stock));
				
				
			}
			
			double price=0;
			float returns=0;
			float vo=0;
			for (Security sec : ls) {
				for (Stock s : set) {
					if((sec.getS().getDATE().equals(s.getDATE())&&sec.getS().getClose()==s.getClose()))
						{
						Date periode2 = new Date();
						Calendar c = Calendar.getInstance(); 
						c.setTime(sec.getS().getDATE()); 
						c.add(Calendar.MONTH, +3);
						SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
						String d = format1.format(c.getTime());
						  uf.AffectedStock(i.getIdPortfolio(), sec.getId(),(Collections.frequency(sfinal, s)*100)/sfinal.size(),Collections.frequency(sfinal, s));
						  System.out.println(sec.getId()+" "+(Collections.frequency(sfinal, s)*100)/sfinal.size()+" "+Collections.frequency(sfinal, s));
						  price += (Collections.frequency(sfinal, s))*s.getClose();
						  returns+=(usec.getStockPriceInstantly(sec.getCompany().getSymbol())-s.getClose())/s.getClose()*(Collections.frequency(sfinal, s)*100)/sfinal.size();
						  vo+=usec.VolatilityCalculator(sec.getCompany().getSymbol(), sec.getS().getDATE().toString(), d);
						  
						}
					
					
					
				}
			}
			System.out.println("price ; "+price+" returns "+returns+" vol "+vo);
			Portfolio p =up.DisplayPortfolio(i.getIdPortfolio());
			p.setVolatility(vo);
			p.setF(null);
			p.getUser().setComplains(null);
			p.getUser().setContratcs(null);
			p.getUser().setFeedbacks(null);
			p.setReturns(returns);
			p.setPrice(price);
			p.setRatio(returns/vo);
			System.out.println(p.toString());
			up.EditPortfolio(p);
			
		}
		else {
			
			System.out.println("----------------------------Stock Available-----------------------------------");
			List<Security> ls =usec.DisplayStock();
			List<Stock> lstock= new ArrayList<Stock>();
			for (Security s : ls) {
				System.out.println(s.getS());
			}
			
			System.out.println("--------------------------------Selected Stock (volatiliy)----------------------------------------");
		
			double tot=0;
			double retur=Double.parseDouble(getReturns());
			float ret=0;
			
			while(retur+2>=0)
			{
				
				for (Security s : ls) {
					
					tot=(usec.getStockPriceInstantly(s.getCompany().getSymbol())-s.getS().getClose())/s.getS().getClose();
					lstock.add(s.getS());
					retur-=tot;
					ret+=tot;
					
					
					
				}
				
			}
			
			
		
			System.out.println("--------------------------------stock final----------------------------------------");
			
			
			for (Stock stock : lstock) {
				System.out.println(stock);
			}
			System.out.println("--------------------------------stock final----------------------------------------");
			Set<Stock> set = new LinkedHashSet<>();
			set.addAll(lstock);
			for (Stock stock : set) {
				System.out.println(stock+" : "+Collections.frequency(lstock, stock));
				
				
			}
			
			List<Stock> finals=up.Type2ReturnsPortfolio((float) retur,lstock);
			
			
			
			double price=0;
			float returns=0;
			float vo=0;
			for (Security sec : ls) {
				for (Stock s : set) {
					if((sec.getS().getDATE().equals(s.getDATE())&&sec.getS().getClose()==s.getClose()))
						{
						Date periode2 = new Date();
						Calendar c = Calendar.getInstance(); 
						c.setTime(sec.getS().getDATE()); 
						c.add(Calendar.MONTH, +3);
						SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
						String d = format1.format(c.getTime());
						  uf.AffectedStock(i.getIdPortfolio(), sec.getId(),(Collections.frequency(lstock, s)*100)/lstock.size(),Collections.frequency(lstock, s));
						  System.out.println(sec.getId()+" "+(Collections.frequency(lstock, s)*100)/lstock.size()+" "+Collections.frequency(lstock, s));
						  price += (Collections.frequency(lstock, s))*s.getClose();
						  returns=ret;
						  vo+=usec.VolatilityCalculator(sec.getCompany().getSymbol(), sec.getS().getDATE().toString(), d);
						  
						}
					
					
					
				}
			}
			System.out.println("price ; "+price+" returns "+returns+" vol "+vo);
			Portfolio p =up.DisplayPortfolio(i.getIdPortfolio());
			p.setVolatility(vo);
			p.setF(null);
			p.getUser().setComplains(null);
			p.getUser().setContratcs(null);
			p.getUser().setFeedbacks(null);
			p.setReturns(returns);
			p.setPrice(price);
			p.setRatio(returns/vo);
			System.out.println(p.toString());
			up.EditPortfolio(p);
			
			
		}
		
	
	
		
	}
	
	
}
