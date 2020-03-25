package Services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Entities.Portfolio;
import Entities.User;
import Interfaces.PortfolioServiceLocal;
import Interfaces.PortfolioServiceRemote;

@Stateful
public class PortfolioService implements PortfolioServiceLocal, PortfolioServiceRemote {

	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
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

}
