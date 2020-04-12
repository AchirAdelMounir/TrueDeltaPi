package Services;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Entities.User;
import Interfaces.UserIServices;



@Stateless
@Local

public class UserServices implements UserIServices {
	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	/*@Override
	public int AddUser(User u) {
		// TODO Auto-generated method stub
		em.persist(u);
		System.out.println("User:"+ u.getId());
		return u.getId();
		}*/
}