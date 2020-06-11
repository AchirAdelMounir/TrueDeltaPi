package Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import Entities.AssetManager;
import Entities.Ratings;
import Entities.Visitor;


@Remote
public interface RatingServiceInterface {

	
	
	 int addRating(Ratings rating) ;

	List<Ratings> getall();

	    public  List<String> Commentaire( ) ;

	
	

}
