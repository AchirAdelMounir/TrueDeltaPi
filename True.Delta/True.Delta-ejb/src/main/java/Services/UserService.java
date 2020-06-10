package Services;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.User;
import Interfaces.UserServiceInterface;



@Stateful
@LocalBean
public class UserService implements UserServiceInterface {
	
	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public User getUserByEmailAndPassword(String adresseMail, String password) {
        TypedQuery<User> query =
                em.createQuery("SELECT e FROM User e WHERE e.adresseMail=:adresseMail AND e.password=:password ",
                User.class);
                query.setParameter("adresseMail", adresseMail);
                query.setParameter("password", password);
                User user = null;
                try { user = query.getSingleResult(); }
                catch (Exception e) { System.out.println("Erreur : " + e); }
                return user;
    }

}
