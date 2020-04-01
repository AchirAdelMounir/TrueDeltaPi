package Services;

import java.util.Date;
import java.util.List;




import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Entities.*;
import Enumerations.Active_account_Type;
import Enumerations.Professional_Status_Type;
import Enumerations.Residency_Status_Type;
import Enumerations.Type_of_contract_type;
import Interfaces.UserServiceLocal;
import Interfaces.UserServiceRemote;
@Stateful
public class UserService implements  UserServiceRemote,UserServiceLocal {

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
	public void EditUser(User u) {
		// TODO Auto-generated method stub
		em.merge(u);
		
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
	public int EstimatedScore(User u) {
		int score =0;
		int age;
		Date today = new Date();
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



}
