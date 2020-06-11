package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import Entities.AssetManager;
import Entities.Ratings;
import Entities.Visitor;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.RatingServiceInterface;
import Interfaces.VisitorServiceInterface;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Stateless
@LocalBean
public  class  RatingService implements RatingServiceInterface {

	
	@PersistenceContext(unitName= "primary")
	EntityManager em;

    @Override
	public int addRating(Ratings rating) {

		em.persist(rating);
		
		
		
		
		return (rating.getId());
		

		
	}
    
    
    @Override
	public List<Ratings> getall() {
		TypedQuery<Ratings> query = em.createQuery("SELECT r FROM Ratings r  ", Ratings.class);
		 return query.getResultList();

}

    
    
    
    @Override
    public  List<String> Commentaire( )
   	{
          for (Ratings ra : getall() )
    		
    	{
    	
      	 TypedQuery<String> query = em.createQuery("SELECT Cmntr FROM Ratings GROUP BY x ", String.class)
      			 .setParameter("hamma", ra.getNomAM());
      	 
      	 
      	System.out.print(query.getResultList());
      	
      	System.out.print("*********************************");

  	

      	 
    	}	
	
          return null ;
}
    


    
    
}
