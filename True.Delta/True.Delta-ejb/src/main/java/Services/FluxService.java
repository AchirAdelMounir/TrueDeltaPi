package Services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import Entities.Flux;
import Entities.Portfolio;
import Entities.PortfolioSecurityKey;
import Entities.Security;
import Entities.Stock;
import Interfaces.FluxServiceLocal;
import Interfaces.FluxServiceRemote;


@Stateful
public class FluxService implements FluxServiceRemote,FluxServiceLocal{

	
	@PersistenceContext(unitName= "primary")
	EntityManager em;
	



	@Override
	public void AffectedStock(int idPortfolio, int idStock, float poids, int nb) {
		Security s = new Security();
		Portfolio p = new Portfolio();
		s=em.find(Security.class, idStock);
		p=em.find(Portfolio.class, idPortfolio);
		PortfolioSecurityKey FK = new PortfolioSecurityKey();
		FK.setPortfolioI(p.getIdPortfolio());
		FK.setSecurityId(s.getId());
		Flux f = new Flux();
		f.setId(FK);
		f.setP(p);
		f.setS(s);
		f.setPoids(poids);
		f.setVolume(nb);
		em.persist(f);
		
	}}
