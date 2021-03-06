package Services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import javax.ejb.Local;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entities.*;
import Enumerations.Active_account_Type;
import Enumerations.Professional_Status_Type;
import Enumerations.Residency_Status_Type;
import Enumerations.Type_of_contract_type;
import Interfaces.UserIServices;





@Stateless
@LocalBean
public class UserService implements  UserIServices {


	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddUser(User U) {
		// TODO Auto-generated method stub
		em.persist(U);
		System.out.println("IdUser :"+ U.getId());
		return U.getId();
	}

	@Override
	public void DeleteUser(int Id) {
		User u = new User();
		u=em.find(User.class, Id);
		em.remove(u);
		
	}

	

	@Override
	public int EditUser(User user, int id_user) {
		Query query =  em.createQuery("update User u set nom=:nom , prenom=:prenom , date=:date , adresseMail=:adresseMail , Type=:type   where u.id=:id_user ");
		query.setParameter("nom", user.getNom());
		query.setParameter("prenom", user.getPrenom());
		query.setParameter("date", user.getDate());
		query.setParameter("adresseMail", user.getAdresseMail());
		query.setParameter("type", user.getType());
		query.setParameter("id_user", id_user);
		int m=query.executeUpdate();
	   return m;
	    }

	

	@Override
	public User DisplayUser(int Id) {
		User u = new User();
		System.out.println(Id);
		u=em.find(User.class, Id);
		
		return u;
	}

	@Override
	public List<User> DisplayUsers() {
		Query query=em.createQuery("select u from User u");
		return query.getResultList();
	}

	@Override
	public User authentication(String login, String password) {
	Query query = em.createQuery("select u from User u where u.login=:login AND u.password=:password").setParameter("login",login).setParameter("password", password);
	if(!query.getResultList().isEmpty()) {
		User user = (User) query.getResultList().get(0);
		System.out.println("from ejb, user found, authenticating user with id :"+user.getId());
		return user;
	}
	System.out.println("user not found...");
	return null;}
    
	@Override
	public String happyBirthday(int id_user) {
		User user=DisplayUser(id_user);
		String s="";
		LocalDateTime localDateTime = LocalDateTime.now();
		Date today=Date.from( localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		int age=(today.getYear()+1900)-(user.getCustomer().getDateB().getYear()+1900);
		if((user.getDate().getMonth()==today.getMonth()) && (user.getDate().getDate()==today.getDate()))
			 s ="HAPPY BIRTHDAY "+user.getPrenom()+" "+user.getNom()+"\n"+"HAPPY "+Integer.toString(age)+" "+"YEARS";
		
			return s;
	}
	
	
	
	
	
	@Override
	public int EstimatedScore(User u) {
		int score =0;
		int age;
		Date today = new Date();
		System.out.println(today.getYear()+1900);
		age=(today.getYear()+1900)-(u.getCustomer().getDateB().getYear()+1900);
		if(age>=18&&age<60) {
			score +=50;
		}
		else
		{
			score +=20;
		}
		if(u.getCustomer().getResidency_Status()==Residency_Status_Type.Owner)
		{
			score+=50;
		}
		else 
		{
			score +=20;
		}
		if (u.getCustomer().getSeniority()>=5&&u.getCustomer().getSeniority()<20)
		{
			score +=30;
		}
		if (u.getCustomer().getSeniority()>20)
		{
			score +=50;
		}
		if((u.getCustomer().getProfession()==Professional_Status_Type.Employee&&u.getCustomer().getNetIncome()>=1000&&u.getCustomer().getNetIncome()<3000)||(u.getCustomer().getProfession()==Professional_Status_Type.Entrepreneur&&u.getCustomer().getNetIncome()>=2500&&u.getCustomer().getNetIncome()<6000))

		{
			score +=25;
		}
		else if ((u.getCustomer().getProfession()==Professional_Status_Type.Employee&& u.getCustomer().getNetIncome()>=3000)||(u.getCustomer().getProfession()==Professional_Status_Type.Entrepreneur&&u.getCustomer().getNetIncome()>=6000))
		{
			score +=50;
		}
		
		
		if(u.getCustomer().getTypeofcontract()==Type_of_contract_type.Fixed_term_contract)
		{
			score +=20;
		}
		else if(u.getCustomer().getTypeofcontract()==Type_of_contract_type.permanent_work_contract)
		{
			score += 30;
		}
		else if(u.getCustomer().getTypeofcontract()==Type_of_contract_type.The_Initiation_into_the_World_of_Work_Scheme)
		{
			score += 10;
		}
		
		if(u.getCustomer().getResource()<100000)
		{
			score +=0;
		}
		else if(u.getCustomer().getResource()>=100000&&u.getCustomer().getResource()<500000)
		{
			score +=30;
		}
		else
		{
			score += 50;
		}
		if(u.getCustomer().getRefund()<(0.4*u.getCustomer().getNetIncome()) && u.getCustomer().getRefund()>(0.1*u.getCustomer().getNetIncome()))
		{
			score +=10;
		}	
		else if(u.getCustomer().getRefund()<=(0.1*u.getCustomer().getNetIncome()))
		{
			score +=30;
		}
		if(u.getCustomer().getCredit()<10000)
		{
			score+=10;
		}
		else if(u.getCustomer().getCredit()>10000&&u.getCustomer().getCredit()<100000)
		{
			score+=30;
		}
		else
		{
			score+=50;
		}
		if(u.getCustomer().getRepayment().equals("2020"))
		{
			score+=30;
		}
		

			return score;
		
	}

	@Override
	public void validateProfile(User u) {
		if(u.getCustomer().getScore()>200)
		{
			u.getCustomer().setActive(Active_account_Type.Active);
			em.merge(u);
		}
		
	}

	@Override
	public void EditUser(User u) {
		// TODO Auto-generated method stub
		em.merge(u);
		
	}
	
	



}
