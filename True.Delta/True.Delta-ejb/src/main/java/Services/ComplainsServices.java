package Services;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.text.ParseException;

import Entities.Complain;
import Interfaces.ComplainIServices;

@Stateless
@Local

public class ComplainsServices implements ComplainIServices {
	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddComplain(Complain c) {
		// TODO Auto-generated method stub
		//System.out.println(c.getUser().getNom());
		//System.getProperty("user.id");
		LocalDateTime localDateTime = LocalDateTime.now();
		c.setDate(Date.from( localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		em.persist(c);
		System.out.println("Complain:"+ c.getId());
		return c.getId();
		}
	
	
	@Override
	 public void DeleteComplainById(int idComplain) {
		Complain complain = new Complain();
		 complain=em.find(Complain.class,idComplain);
		em.remove(complain);
		 }
	
	@Override
	 public int DeleteComplainByIdUser(int idUser) {
		Query query = em.createQuery("delete  from Complain as c  where c.user.id=:idUser").setParameter("idUser", idUser);
		int result=query.executeUpdate();
		return result;
		 }
	
	@Override
	 public int DeleteComplainByStatus(Enumerations.StatusTypeOfComplain status) {
		Query query = em.createQuery("delete  from Complain as c  where c.status=:status").setParameter("status", status);
		int result=query.executeUpdate();
		return result;
		 }
	@Override
	 public int DeleteComplainByDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datec = dateFormat.parse(date);
			Query query = em.createQuery("delete from Complain as c where DATEDIFF (DATE_FORMAT(c.date,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0").setParameter("datec", datec);
			int result=query.executeUpdate();
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
		 }
	
	
	
	@Override
	public void UpdateComplain(Complain c) {

		em.merge(c);

	}
	
	@Override
	public Complain getComplainById(int id) {
		Complain complain = new Complain();
		 complain=em.find(Complain.class,id);
			return complain;
		}
	
	
	@Override
	public List<Complain> getAllComplain() {
			List<Complain> complains = em.createQuery("Select c from Complain c ", Complain.class).getResultList();
			System.out.println(complains);
			return complains;
		}
	
	
	@Override
	public List<Complain> getAllComplainByIdCustomer(int idCustomer){
		TypedQuery<Complain> query= em.createQuery("Select c from Complain c where c.user.id=:idCustomer",Complain.class);
		query.setParameter("idCustomer",idCustomer);
		return query.getResultList();
		
		}
	
	@Override
	public List<Complain> getComplainByStatus(Enumerations.StatusTypeOfComplain status) {
		TypedQuery<Complain> query = em.createQuery("select c from Complain c where c.status=:status", Complain.class);
		query.setParameter("status", status);
		return query.getResultList();

	}
	
	@Override
	public List<Complain> getComplainByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			Date datec = dateFormat.parse(date);
			TypedQuery<Complain> query = em.createQuery("select c from Complain as c where DATEDIFF (DATE_FORMAT(c.date,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Complain.class);
			query.setParameter("datec", datec);
			return query.getResultList();
		
		
		

	}
	
	
	
	
	@Override
	public Long getNbComplain() {
		TypedQuery<Long> query = em.createQuery("select COUNT(*) from Complain ", Long.class);
		return query.getSingleResult();

	}
	
	@Override
	public Long getNbComplainByCustomer(int idCustomer) {
		TypedQuery<Long> query = em.createQuery("select COUNT (c) from Complain c where c.user.id=:idCustomer", Long.class);
		query.setParameter("idCustomer", idCustomer);
		return query.getSingleResult();

	}
	
	@Override
	public Long getNbComplainByStatus(Enumerations.StatusTypeOfComplain status) {
		TypedQuery<Long> query = em.createQuery("select COUNT (c) from Complain c where c.status=:status", Long.class);
		query.setParameter("status", status);
		return query.getSingleResult();

	}
	
	@Override
	public Long getNbComplainByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date datec = dateFormat.parse(date);
		TypedQuery<Long> query = em.createQuery("select COUNT (c) from Complain c where DATEDIFF (DATE_FORMAT(c.date,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Long.class);
		query.setParameter("datec", datec);
		return query.getSingleResult();

	}
	
	
	@Override
	public Complain findById(int id) {
        return em.find(Complain.class, id);
    }
	

}
