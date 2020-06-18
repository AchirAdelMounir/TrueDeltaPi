package Services;

//import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//import javax.print.DocFlavor.STRING;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
//import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
//import java.util.Set;
//import java.util.stream.Collectors;
import java.util.List;

import Entities.Feedback;
//import Entities.User;
import Enumerations.LevelRating;
import Enumerations.StatusTypeOfComplain;
import Enumerations.Who;
//import Enumerations.LevelRating;
import Interfaces.FeedbackIServices;
//import Entities.Complain;
@Stateless
@LocalBean
//@EJB
public class FeedbackServices implements FeedbackIServices{
	@Inject
	ComplainsServices service;
	@Inject
	UserService userv;
	@PersistenceContext(unitName= "primary")
	EntityManager em;

	@Override
	public int AddFeedback(Feedback f) {
		// TODO Auto-generated method stub
		LocalDateTime localDateTime = LocalDateTime.now();
		f.setDate(Date.from( localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		f.setAdmin_vu(false);
		em.persist(f);
		
		
		
		System.out.println("Feedback:"+ f.getId());
		return f.getId();
		}
	
	 @Override
	 public void DeleteFeddbackById(int idFeeddback) {
		 Feedback feedback=new Feedback();
		 feedback=em.find(Feedback.class,idFeeddback);
		 em.remove(feedback);
		 }
	 @Override
	 public int DeleteFeedbackByIdUser(int idUser) {
		Query query = em.createQuery("delete  from Feedback as f  where f.user.id=:idUser").setParameter("idUser", idUser);
		int result=query.executeUpdate();
		return result;
		 }
	 @Override
	 public int DeleteFeedbackByDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datec = dateFormat.parse(date);
			Query query = em.createQuery("delete from Feedback as f where DATEDIFF (DATE_FORMAT(f.date,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0").setParameter("datec", datec);
			int result=query.executeUpdate();
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
		 }
	 
	 @Override
	 public void UpdateFeedback(Feedback f) {
		 em.merge(f);
	 }
	
	
	@Override
	public Feedback GetFeedbackById(int idFeedback) {
		Feedback feedback = new Feedback();
		feedback =em.find(Feedback.class, idFeedback);
	 return feedback;
	  }
	@Override
	public List<Feedback> getAllFeedback() {
			List<Feedback> feedbacks = em.createQuery("Select f from Feedback f ", Feedback.class).getResultList();
			System.out.println(feedbacks);
			return feedbacks;
		}
	
	@Override
	 public List<Feedback> getAllFeedbackByIdUser(int idUser) {
			/*User userManagedEntity = em.find(User.class, idUser);
			List<Integer> feedbacksids = new ArrayList<>();
			for(Feedback f : userManagedEntity.getFeedback()){
				feedbacksids.add(f.getId());
			}
			
			return feedbacksids;*/
		 TypedQuery<Feedback> query= em.createQuery("Select f from Feedback f where f.user.id=:idUser",Feedback.class);
			query.setParameter("idUser",idUser);
			return query.getResultList();
		}
	@Override
	public List<Feedback> getFeedbackByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			Date datec = dateFormat.parse(date);
			TypedQuery<Feedback> query = em.createQuery("select f from Feedback as f where DATEDIFF (DATE_FORMAT(f.date,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Feedback.class);
			query.setParameter("datec", datec);
			return query.getResultList();
		
		
		

	}
	@Override
	 public List<Feedback> getAllFeedbackByRating(Enumerations.LevelRating rating) {
		 TypedQuery<Feedback> query= em.createQuery("Select f from Feedback f where f.rating=:rating ",Feedback.class);
			query.setParameter("rating",rating);
			return query.getResultList();
		}
	@Override
	 public List<Feedback> getAllFeedbackByWho(Who who) {
		 TypedQuery<Feedback> query= em.createQuery("select f from Feedback f where f.who=:who ",Feedback.class);
			query.setParameter("who",who);
			return query.getResultList();
		}
	@Override
	public Long getNbFeedback() {
		TypedQuery<Long> query = em.createQuery("select COUNT(*) from Feedback ", Long.class);
		return query.getSingleResult();

	}
	@Override
	public Long getNbFeedbackByIdUser(int idUser) {
		TypedQuery<Long> query = em.createQuery("select COUNT (f) from Feedback f where f.user.id=:idUser", Long.class);
		query.setParameter("idUser", idUser);
		return query.getSingleResult();

	}
	@Override
	public Long getNbFeedbackByWho(Who who) {
		TypedQuery<Long> query = em.createQuery("select COUNT (f) from Feedback f where f.who=:who", Long.class);
		query.setParameter("who", who);
		return query.getSingleResult();

	}
	@Override
	public Long getNbFeedbackByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date datec = dateFormat.parse(date);
		TypedQuery<Long> query = em.createQuery("select COUNT (f) from Feedback f where DATEDIFF (DATE_FORMAT(f.date,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Long.class);
		query.setParameter("datec", datec);
		return query.getSingleResult();

	}
	@Override
	public Long getNbFeedbackByRating(Enumerations.LevelRating rating,Who who) {
		TypedQuery<Long> query = em.createQuery("select COUNT (f) from Feedback f where f.rating=:rating and f.who=:who", Long.class);
		query.setParameter("rating", rating);
		query.setParameter("who", who);
		return query.getSingleResult();

	}
	@Override 
	public Long getNbFeedbackByVuAdmin() {
		TypedQuery<Long> query = em.createQuery("select COUNT (f) from Feedback f where f.admin_vu=false ", Long.class);
		return query.getSingleResult();
	}
	@Override
	public double getAvgOfRating(Enumerations.LevelRating rating,Who who) {
		double Avg;
		//,n,b,m,g,vg,p,s
		/*n=getNbFeedbackByRating(LevelRating.Null)*0;
		b=getNbFeedbackByRating(LevelRating.Bad)*1;
		m=getNbFeedbackByRating(LevelRating.Middling)*2;
		g=getNbFeedbackByRating(LevelRating.Good)*3;
		vg=getNbFeedbackByRating(LevelRating.Very_good)*4;
		p=getNbFeedbackByRating(LevelRating.Perfect)*5;
		s=n+b+m+g+vg+p;*/
		Avg=((double)getNbFeedbackByRating(rating,who)/(double)getNbFeedbackByWho(who))*100;
		return Avg;
	}
	@Override
	public double nbOfStarsById(int idFeedback) {
		if(GetFeedbackById(idFeedback).getRating()==LevelRating.Null) {
			return 0;
		}
		else if(GetFeedbackById(idFeedback).getRating()==LevelRating.Bad) {
			return 1.0;
		}
		else if(GetFeedbackById(idFeedback).getRating()==LevelRating.Middling) {
			return 2.0;
		}
		else if(GetFeedbackById(idFeedback).getRating()==LevelRating.Good) {
			return 3.0;
		}
		else if(GetFeedbackById(idFeedback).getRating()==LevelRating.Very_good) {
			return 4;
		}
		else return 5.0;
	}
	@Override
	public double noteGlobal(Who who) {
		double s=0;
		List<Feedback> l= getAllFeedbackByWho(who);
		for(int i=0; i<l.size(); i++) {
			s+=(double)nbOfStarsById(l.get(i).getId());
		}
		 return s/(double)getNbFeedbackByWho(who);
		
	}
	@Override
	public Long getNbUser() {
		TypedQuery<Long> query = em.createQuery("select COUNT(*) from User ", Long.class);
		return query.getSingleResult();

	}
	@Override
	public Long getNbActivityByUser(int idUser) {
		long s;
		s=getNbFeedbackByIdUser(idUser)+service.getNbComplainByCustomer(idUser);
		
		return s;
	}
	@Override
	public Map<Integer, Long>  best3ActiveUser(){
		List<Integer> listOfInd =new ArrayList<>();
		List<Long> listOfActivity =new ArrayList<>();
		Map<Integer, Long> h = new HashMap<>();
		//Map<Integer, Long> f = new HashMap<>();
		
		listOfInd = em.createQuery("Select u.id from User u ", Integer.class).getResultList();
		for(int i=0; i<listOfInd.size(); i++) {
			listOfActivity.add(getNbActivityByUser((listOfInd.get(i))));
		}
		List<Long> sortedlist = new ArrayList<>(listOfActivity); 
		Collections.sort(sortedlist);
		for(int i=0; i<3; i++) {
			long max= sortedlist.get(sortedlist.size() - 1);
			int ind=listOfInd.get(listOfActivity.indexOf(max));
			h.put(ind, max);
			sortedlist.remove(sortedlist.size() - 1);
			listOfActivity.set(listOfActivity.indexOf(max), (long)-1);
		}
		return h;
		/*ArrayList<Integer> keys = new ArrayList<Integer>();
        keys.addAll(h.keySet());
        Collections.sort(keys, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
            	Long val1 = h.get(lhs);
            	Long val2 = h.get(rhs);
                if (val1 == null) {
                    return (val2 != null) ? 1 : 0;
                } else if ((val1 != null) && (val2 != null)) {
                    return val2.compareTo(val1);
                }
                else {
                    return 0;
                }
            }
        });

        for (Integer key : keys) {
            Long c = h.get(key);
            if (c != null) {
                f.put(key,c);
            } 
        }
		return f;*/
		
		
	}
	@Override
	public List <String> affichageOfBest3() {
		List<Integer> listOfInd =new ArrayList<>();
		List<Long> listOfActivity =new ArrayList<>();
		List <String> finale =new ArrayList<>();
		String s="";
		listOfInd = em.createQuery("Select u.id from User u ", Integer.class).getResultList();
		for(int i=0; i<listOfInd.size(); i++) {
			listOfActivity.add(getNbActivityByUser((listOfInd.get(i))));
		}
		List<Long> sortedlist = new ArrayList<>(listOfActivity); 
		Collections.sort(sortedlist);
		for(int i=0; i<3; i++) {
			long max= sortedlist.get(sortedlist.size() - 1);
			int ind=listOfInd.get(listOfActivity.indexOf(max));
			s="TOP "+(i+1)+userv.DisplayUser(ind).getPrenom()+"  "+userv.DisplayUser(ind).getNom()+"  "+"with "+" "+max+" "+"activity"+"\n";
			finale.add(s);
			sortedlist.remove(sortedlist.size() - 1);
			listOfActivity.set(listOfActivity.indexOf(max), (long)-1);
			System.out.println(s);
		}
		return finale;
		
		
	}
	@Override
	public Boolean validerFeedback(String Str) {
		List <String> dic =new ArrayList<>();
		
		try {
		      File myObj = new File("D://pi/My_Dictionary.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		        dic.add(data);
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		//split the notice
		List <String> not =new ArrayList<>();
	      
	      for (String s: Str.split(" ")) {
	         System.out.println(s);
	         not.add(s);
	      }
	      //test
	      for(int i=0; i<not.size();i++) {
	    	  for(int j=0;j<dic.size();j++) {
	    		  if (not.get(i).contains(dic.get(j)))  return false;
	    		  
	    	  }
	      }
	      return true;
	    /*while((i<not.size())) {
	    	while(j<dic.size() &&  (val==true)) {
	    		if (not.get(i).equalsIgnoreCase(dic.get(j))==true) {
	    			val=false;
	    			System.out.println(val);
	    		} 
	    		else
	    			j=j+1;
	    			
	    	}
	    	i=i+1;
	    	
	    }*/
		
		//return val;
		
		
	}
	public String wordDanger(String Str) {
		
List <String> dic =new ArrayList<>();
		
		try {
		      File myObj = new File("D://pi/My_Dictionary.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		        dic.add(data);
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		//split the notice
		List <String> not =new ArrayList<>();
	      
	      for (String s: Str.split(" ")) {
	         System.out.println(s);
	         not.add(s);
	      }
	      //test
	      for(int i=0; i<not.size();i++) {
	    	  for(int j=0;j<dic.size();j++) {
	    		  if (not.get(i).contains(dic.get(j)))  return dic.get(j);
	    		  
	    	  }
	      }
	      return "";
	}
	
	/*@Override
	public String affichageOfBest3(Map<Integer,Long> h) {
		List<Integer> ind =(List<Integer>) h.keySet();
		List<Long> valeur =(List<Long>) h.values();
		for (int i = 0; i < valeur.size() - 1; i++)  
        {
             int index = i;  
             for (int j = i + 1; j < valeur.size(); j++)
             {
                  if (valeur.get(j) < valeur.get(index)){ 
                       index = j;
                  }
             }

             long min = valeur.get(index);
             int indmin=ind.get(index);
             valeur.set(index, valeur.get(i)); 
             ind.set(index, ind.get(i));
             valeur.set(i, min);
             ind.set(i, indmin);
        }
		String s;
		s="User N 1 : ID"+ind.get(0)+"with"+valeur.get(0)+"activity"+"\n"+ "User N 2 : ID"+ind.get(1)+"with"+valeur.get(1)+"activity"+"\n"+ "User N 3 : ID"+ind.get(2)+"with"+valeur.get(2)+"activity";
				//System.out.println("User N 1 : ID"+ind.get(0)+"with"+valeur.get(0)+"activity"+"\n"+ "User N 2 : ID"+ind.get(1)+"with"+valeur.get(1)+"activity"+"\n"+ "User N 3 : ID"+ind.get(2)+"with"+valeur.get(2)+"activity");		
		return s;
		
	}*/
	/*Originale
	@Override
	public Map<Integer,Long>  best3ActiveUser(){
		List<Integer> l =new ArrayList<>();
		List<Long> k =new ArrayList<>();
		Map<Integer, Long> h = new HashMap<>();
		for(int i=0; i<getNbUser(); i++) {
			l.add(i+1);
		} 
		for(int i=0; i<getNbUser(); i++) {
			
			k.add(getNbActivityByUser(i+1));
		} 
		//create a new list to avoid modification 
        // in the original list 
        List<Long> sortedlist = new ArrayList<>(k); 
  
        // sort list in natural order 
        Collections.sort(sortedlist); 
  
        // last element in the sorted list would be maximum 
         
		for(int i=0; i<3; i++) {
			long max= sortedlist.get(sortedlist.size() - 1);
			int ind= k.indexOf(max);
			sortedlist.remove(max);
			h.put(ind, max);
	
			
			
			
		}
		
		
		return h;
		
		
		
		
	}*/ //fin Originale
	/*@Override
	public  Map<Integer,Long> sortByValue( Map<Integer,Long> h) {
        return h.entrySet()
                .stream()
                .sorted((Map.Entry.<Integer,Long>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
	@Override
	public Map<String,Long>  best3ActiveUser(){
		List<Integer> listOfId =new ArrayList<>();
		List<Long> k =new ArrayList<>();
		Map<String, Long> h = new HashMap<>();
		 listOfId = em.createQuery("Select u.id from User u ", Integer.class).getResultList();		
		 
		for(int i=0; i<getNbUser(); i++) {
			
			
			k.add(getNbActivityByUser((listOfId.get(i))));
			h.put(Integer.toString(listOfId.get(i)), k.get(i));
		} 
		//Map<Integer, Long> soutedMap=sortByValue(h);
		/*Map<String, Long> sortedMap=new HashMap<>();
		sortedMap=h.entrySet()
                .stream()
                .sorted((Map.Entry.<String,Long>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    
		
		
		return  sortedMap;
		return h;
		
		
		
		
	}*/
	@Override
	public Feedback findById(int id) {
        return em.find(Feedback.class, id);
    }
	 
	/* @Override
	 public Feedback getFeedbackById_JPQL(int idFeedback) {
			TypedQuery<Feedback> query = em
					.createQuery("select f from Feedback f WHERE e.id=:idFeedback", Feedback.class);
			query.setParameter("id", idFeedback);
			Feedback feedback = null;
			try {
				feedback = query.getSingleResult();
			} catch (Exception e) {
				System.out.println("Erreur : " + e);
			}
			return feedback;
		}*/
	 
	 
	 
	 
	 
	 
	 
	 
	

}
