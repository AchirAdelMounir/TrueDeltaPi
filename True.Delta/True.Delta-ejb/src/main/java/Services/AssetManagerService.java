package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.AssetManager;
import Interfaces.AssetManagerServiceInterface;

@Stateless
@LocalBean
public class AssetManagerService implements AssetManagerServiceInterface {
	
	@PersistenceContext(unitName= "primary")
	EntityManager em;


	@Override
	public void addAssetManager(AssetManager AssetManager) {
	
		em.persist(AssetManager);
		
	}
	

	@Override
	public void updateAssetManager(AssetManager AssetManager) {
		em.merge(AssetManager);
		
	}

	@Override
	public AssetManager getAssetManagerById(int id) {
       return em.find(AssetManager.class, id);
}

	@Override
	public void deleteAssetManager(int id) {
		em.remove(em.find(AssetManager.class, id));
		
	}

	@Override
	public List<AssetManager> getall() {
		TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f", AssetManager.class);
		 return query.getResultList();

}

	
}
