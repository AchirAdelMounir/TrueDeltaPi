package Services;

import java.util.ArrayList;
import java.util.Date;
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

import Interfaces.AssetManagerServiceInterface;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Stateless
@LocalBean
public  class  AssetManagerService implements AssetManagerServiceInterface {
	
	@PersistenceContext(unitName= "primary")
	EntityManager em;





	
	
	@Override
	public List<String> achat() throws IOException 
	{
		Document doc=Jsoup.connect("http://www.stb.com.tn/fr/site/bourse-change/cours-de-change/").timeout(6000).get();
		List<String> achats = new ArrayList<String>();

		for (Element row :doc.getElementsByClass("t-content"))
		{
			
		
				
			final Elements achat=row.getElementsByClass("achat-change");
	
					for ( Element a : achat)
					{
						achats.add(a.text());	
					}
		
		
		}
		return achats;
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public List<String> devise() throws IOException 
	{
		Document doc=Jsoup.connect("http://www.stb.com.tn/fr/site/bourse-change/cours-de-change/").timeout(6000).get();
		List<String> keys = new ArrayList<String>();

		for (Element row :doc.getElementsByClass("t-content"))
		{
			
		
				
					final Elements elements=row.getElementsByClass("code-change");
	

					for (int i=0 ; i< elements.size() ; i++)
					{
					
                    keys.add(elements.get(i).text()) ;		
			        i++ ;
		
		            }
		
		
		}
keys.add("TND");
keys.add("DZD");
keys.add("KWD");
keys.add("TRY");
		return keys;
		
	}
	
	
	
	@Override
	public Map<String, Float> Change() throws IOException 
	
	{
		Document doc=Jsoup.connect("http://www.stb.com.tn/fr/site/bourse-change/cours-de-change/").timeout(6000).get();

		Map <String ,Float> mp=new HashMap<>() ;
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		for (Element row :doc.getElementsByClass("t-content"))
		{
			
		
				
					final Elements elements=row.getElementsByClass("code-change");
					final Elements achat=row.getElementsByClass("achat-change");
	

					for (int i=0 ; i< elements.size() ; i++)
					{
					
                    keys.add(elements.get(i).text()) ;		
			        i++ ;
		
		            }
					
					for ( Element a : achat)
					{
						values.add(a.text());	
					}
					
					for( int i=1 ; i< values.size() ; i++)
						
					{
					  String k= keys.get(i).toString();
					  String v= values.get(i).toString();

						mp.put(k, Float.parseFloat(v));
					}
				
		}		 
		return mp;		

		
		
		
		
	}
	
	
     @Override
	public  Float Conversion(String a , String b , Float c ) throws IOException 
	{

    	 Document doc=Jsoup.connect("https://www.boursorama.com/bourse/devises/taux-de-change-euro-dinartunisien-"+a+"-"+b+"/").timeout(6000).get();		

 		String res = doc.getElementsByClass("c-instrument c-instrument--previousclose").text();
 		
 		
 		Float d= Float.parseFloat(res)*c ;
 		return d ;
 		
		
		
	}
     
     
     

}
