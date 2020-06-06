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
import Entities.Visitor;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.VisitorServiceInterface;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Stateless
@LocalBean
public  class  VisitorService implements VisitorServiceInterface {
	
	@PersistenceContext(unitName= "primary")
	EntityManager em;


	@Override
	public int addVisitor(Visitor visitor) {
	
		em.persist(visitor);
		return (visitor.getId());

		
	}
	
	
	
}
