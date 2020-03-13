package Services;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.Stock;
import Interfaces.StocksServicesInterface;

@Stateful
public class StocksServices implements StocksServicesInterface {
	@PersistenceContext(unitName= "imputation-ejb")
	EntityManager em;

	@Override
	public int AddStock(Stock S) {
		em.persist(S);
		System.out.println("Bond:"+ S.getId());
		return S.getId();
	}

	@Override
	public void DeleteStock(int IdStock) {
		Stock S=new Stock();
		S=em.find(Stock.class, IdStock);
		em.remove(S);
		
	}

	@Override
	public void DisplayStock(int IdStock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisplayStocks() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditStock(int IdStock) {
		// TODO Auto-generated method stub
		
	}

}
