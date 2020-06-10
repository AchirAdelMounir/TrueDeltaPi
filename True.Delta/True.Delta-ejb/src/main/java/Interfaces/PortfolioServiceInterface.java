package Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.TypedQuery;

import Entities.AssetManager;
import Entities.Portfolio;
import Entities.Ratings;
import Entities.Visitor;


@Remote
public interface PortfolioServiceInterface {

	
	
	 int addPortfolio(Portfolio potfolio) ;

	List<Portfolio> getall();


	List<Portfolio> getPortfolioByAM(int id);

	
	

}
