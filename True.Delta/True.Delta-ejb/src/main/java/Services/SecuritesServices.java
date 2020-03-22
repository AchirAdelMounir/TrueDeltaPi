package Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Bond;
import Entities.Security;
import Entities.Stock;
import Interfaces.SecuritiesServicesInterfaceLocal;
import Interfaces.SecuritiesServicesInterfaceRemote;


@Stateful

public class SecuritesServices implements SecuritiesServicesInterfaceRemote,SecuritiesServicesInterfaceLocal {
	@PersistenceContext(unitName= "primary")
	EntityManager em;

	@Override
	public int AddSecurity(Security S) {
		em.persist(S);
		System.out.println("Bond:"+ S.getId());
		return S.getId();
		
	}

	@Override
	public void DeleteSecurity(int IdSecurity) {
		Security S=new Security();
		S=em.find(Security.class, IdSecurity);
		em.remove(S);
		
	}

	@Override
	public Security DisplaySecurity(int IdSecurity) {
		Security S=new Security();
		S=em.find(Security.class, IdSecurity);
		return S;
		
		
	}

	@Override
	public List<Security> DisplaySecurities() {
		TypedQuery<Security> query= em.createQuery("Select * from Security",Security.class);
		return query.getResultList();
		
	}

	@Override
	public void EditSecurity(int IdSecurity, Object o) {
		if(o.getClass()==Bond.class)
		{
			Security old=em.find(Security.class, IdSecurity);
			Bond n=(Bond)o;
			old.setB(n);
			
		}
		if(o.getClass()==Stock.class)
		{
			Security old=em.find(Security.class, IdSecurity);
			Stock n=(Stock)o;
			old.setS(n);
			
		}
		
	}

	@Override
	public List<Security> SearchByMaturityDate(Date d) {
		return(em.createQuery("select s from Security s where MaturityDate <="+d,Security.class).getResultList());
	}

	
	@Override
	public List<Bond> DisplayBonds() {
		List<Security>LS=em.createQuery("select s from Security s where Yield!=null",Security.class).getResultList();
	List<Bond> Lb=new ArrayList<>();
	for(Security s : LS)
	{
		Bond B=new Bond();
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
		List<Security>LS=em.createQuery("select s from Security s where Yield!=null",Security.class).getResultList();
		List<Stock> Ls=new ArrayList<>();
		for(Security s : LS)
		{
			Stock n=new Stock();
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

}
