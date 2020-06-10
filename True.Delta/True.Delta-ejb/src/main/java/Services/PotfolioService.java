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
import Entities.Portfolio;
import Entities.Ratings;
import Entities.Visitor;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.PortfolioServiceInterface;
import Interfaces.RatingServiceInterface;
import Interfaces.VisitorServiceInterface;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Stateless
@LocalBean
public  class  PotfolioService implements PortfolioServiceInterface {

	
	@PersistenceContext(unitName= "primary")
	EntityManager em;
	

	@Override
	public int addPortfolio(Portfolio potfolio) {
		em.persist(potfolio);
				return potfolio.getIdPortfolio();
	}

	@Override
	public List<Portfolio> getall() {

		TypedQuery<Portfolio> query = em.createQuery("SELECT r FROM Portfolio r  ", Portfolio.class);
		 return query.getResultList();

		
		
		
	}

	@Override
	public List<Portfolio> getPortfolioByAM(int id) {

		TypedQuery<Portfolio> query = em.createQuery("SELECT r FROM Portfolio r  WHERE r.assetmanager.Id=:hamma ", Portfolio.class)
				
				 .setParameter("hamma", id);
		
				
				
		 return query.getResultList();

		
		
		
	}

}

    
    
    
