package Services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import Entities.Reclamations;
import Interfaces.ReclamationInterfaceLocal;
import Interfaces.ReclamationInterfaceRemote;
@Stateless
@LocalBean
public class ReclamationService implements ReclamationInterfaceLocal,ReclamationInterfaceRemote {
	
	
	@PersistenceContext(unitName = "primary")
	EntityManager em;
	
	@Override
	public int addRec(Reclamations reclamation) {
		System.out.println("In addRec : ");
		em.persist(reclamation);
		System.out.println("Out of addRec" + reclamation.getId());
		return reclamation.getId();
	}

	@Override
	public void removeRec(int idrec) {
		System.out.println("In removeRec : ");
		em.remove(em.find(Reclamations.class, idrec));
		System.out.println("Out of removeRec : ");
	}

	@Override
	public void updateRec(Reclamations reclamation) {
		System.out.println("In updateRec : ");
		Reclamations NewRec = em.find(Reclamations.class, reclamation.getId());
		NewRec.setCategories(reclamation.getCategories());
		System.out.println("Out of updateRec : ");
		
	}

	@Override
	public Reclamations findRecById(int idrec) {
		System.out.println("In findRecById : ");
		Reclamations reclamation = em.find(Reclamations.class, idrec);
		System.out.println("Out of findRecById : ");
		return reclamation;
	}

	@Override
	public List<Reclamations> findAllRecs() {
		System.out.println("In findAllRecs : ");
		List<Reclamations> Recs = em.createQuery("select r from Reclamations r ",Reclamations.class).getResultList();
		System.out.println("Out of findAllRecs : ");
		return Recs;
	}

}
