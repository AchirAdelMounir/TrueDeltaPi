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
import Entities.Ratings;
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
	public int addAssetManager(AssetManager AssetManager) {
	AssetManager.setConfirmation(false);
	AssetManager.setClassification("not defined");
	AssetManager.setRatingAM(0);
	AssetManager.setRatingAM(0);

		em.persist(AssetManager);
		
	Ratings rate= new Ratings() ;  
	rate.setCmntr("");
	rate.setIdVisitor(2);
	rate.setNomAM(AssetManager.getNom());
	rate.setRating(0);
	em.persist(rate);	 
	
	Portfolio prt = new Portfolio();

	prt.setAssetmanager(AssetManager);
	prt.setReturns(1000);
	prt.setTypePortfolio(1);
	prt.setVolatility(12);
	em.persist(prt);

		return (AssetManager.getId());

		
	}
	
	@Override
	public int acceptAssetManager(AssetManager AssetManager) {
	AssetManager.setConfirmation(true);
	TemplateMessage template = new TemplateMessage("test");
	
		return (AssetManager.getId());
	}
	
	
	
	@Override
	public int updateAssetManager(AssetManager AssetManager) {
		em.merge(AssetManager);
		
		return (AssetManager.getId());

		
	}

	@Override
	public AssetManager getAssetManagerById(int id) {
       return em.find(AssetManager.class, id);
}

	@Override
	public AssetManager getAssetManagerByNom(String nom) {
       return em.find(AssetManager.class, nom);
}
	@Override
	public void deleteAssetManager(int id) {
		em.remove(em.find(AssetManager.class, id));
		
	}

	@Override
	public List<String> getall0() {
		TypedQuery<String> query = em.createQuery("SELECT nom   FROM AssetManager f WHERE f.confirmation='0' ", String.class);
		 return query.getResultList();

}
	
	@Override
	public List<String> getall1() {
		TypedQuery<String> query = em.createQuery("SELECT nom   FROM AssetManager f WHERE f.confirmation='1' ", String.class);
		 return query.getResultList();

}

	@Override
	public List<AssetManager> getallConfirmation() {
		TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f WHERE f.confirmation='0'  ", AssetManager.class);
		 return query.getResultList();

}

	
	
	@Override
	public List<AssetManager> getall() {
		TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f WHERE f.confirmation='1' ", AssetManager.class);
		 return query.getResultList();

}

	
	
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
     
     
     @Override
 	public  List<AssetManager> GetHigh( )
 	{
    	 TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f WHERE f.risk='High' ", AssetManager.class);
		 return query.getResultList();

    	 
 	}
     
	
     @Override
  	public  List<AssetManager> GetMedium( )
  	{
     	 TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f WHERE f.risk='Medium' ", AssetManager.class);
 		 return query.getResultList();

     	 
  	}
      
     
     @Override
   	public  List<AssetManager> GetLow( )
   	{
      	 TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f WHERE f.risk='Low' ", AssetManager.class);
  		 return query.getResultList();

      	 
   	}
       
     
    @Override
     public  List<AssetManager> CalculRating()
     
   	{
    	
    	for (AssetManager am : getall() )
    	
    	{
    		

        	TypedQuery<Long> query1 = em.createQuery("SELECT Count(*) FROM Ratings r  WHERE r.nomAM=:hamma ", Long.class)
        			
        			 .setParameter("hamma",getAssetManagerById(am.getId()).getNom());
        	if(query1.getSingleResult()==1)
        	{
        		
          		TypedQuery<Double> query = em.createQuery( "SELECT ROUND(AVG(Rating),2) FROM  Ratings r  WHERE r.nomAM=:hamma )",Double.class)
         				.setParameter("hamma", am.getNom());
          		
               	am.setRatingAM(query.getSingleResult());
        	}
        	else 
        	{
        		

          		TypedQuery<Double> query = em.createQuery( "SELECT ROUND(AVG(Rating),2) FROM  Ratings r  WHERE r.nomAM=:hamma  AND r.Rating>0)",Double.class)
         				.setParameter("hamma", am.getNom());
               	am.setRatingAM(query.getSingleResult());
        	
        	}


 
        	}


    	
		return getall();	
   	}
    
    @Override
    public  Map <String ,List<String>> Commentaire( )
   	{
    	Map <String ,List<String>> mp=new HashMap<>() ;
		List<String> keys = new ArrayList<String>();
		List< List<String>> values = new ArrayList<List<String>>();
    	
      	 TypedQuery<String> query = em.createQuery("SELECT DISTINCT(nomAM) FROM Ratings ", String.class);
      	 keys = query.getResultList() ;
      	 
      	for (int i=0 ; i< getall().size() ; i++ )
      	{
          	 TypedQuery<String> query1 = em.createQuery("SELECT Cmntr FROM Ratings r Where r.nomAM=:hhh ", String.class)
          			.setParameter("hhh", keys.get(i));
        	 
          	 
          	 values.add(query1.getResultList());
         	System.out.print(query1.getResultList());
          	
          	System.out.print("*********************************");
          	
      	}
      	
    	
      	
    	for( int i=0 ; i< keys.size() ; i++)
			
		{
		  String k= keys.get(i);
		  List<String> v= values.get(i);

			mp.put(k, v);
		}
    
    	
    	
      	System.out.print("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+mp);

  		 return mp;

      	 
   	}

    
   @Override
    public  List<AssetManager> CalculReturns()
  	{
    	
	   for (AssetManager am : getall() )
   		
   	{
			if( getAssetManagerById(am.getId()).getRisk().equals("High")) 
			{
        		  
        	 TypedQuery<Double> query = em.createQuery( "SELECT SUM(Returns) FROM  Portfolio r  WHERE r.assetmanager.Id=:hamma",Double.class)
        				 .setParameter("hamma", getAssetManagerById(am.getId()).getId());
        		 
        		 
        	 getAssetManagerById(am.getId()).setScore(((query.getSingleResult())*1)/100);
        	 
               	System.out.print("*********************************");
              	System.out.print(query.getSingleResult());
			}
			
			else if ( getAssetManagerById(am.getId()).getRisk().equals("Medium")) 
			{
        		  
        	 TypedQuery<Double> query = em.createQuery( "SELECT SUM(Returns) FROM  Portfolio r  WHERE r.assetmanager.Id=:hamma",Double.class)
        				 .setParameter("hamma", getAssetManagerById(am.getId()).getId());
        		 
        		 
        	 getAssetManagerById(am.getId()).setScore(((query.getSingleResult())*7)/1000);
        	 
               	System.out.print("*********************************");
              	System.out.print(query.getSingleResult());
			}
			else 
			{
        		  
        	 TypedQuery<Double> query = em.createQuery( "SELECT SUM(Returns) FROM  Portfolio r  WHERE r.assetmanager.Id=:hamma",Double.class)
        				 .setParameter("hamma", getAssetManagerById(am.getId()).getId());
        		 
        		 
        	 getAssetManagerById(am.getId()).setScore(((query.getSingleResult())*5)/1000);
        	 
               	System.out.print("*********************************");
              	System.out.print(query.getSingleResult());
			}
        	  
        	  
        	  
    }  
        	  

 			

    	
  	return getall() ;
  	}

@Override
public List<AssetManager> Classification() {
	
	Date d = new Date () ;
	
	for ( AssetManager am : getall())
	{
	
				if( getAssetManagerById(am.getId()).getRatingAM()>7
				  && getAssetManagerById(am.getId()).getScore()>10000 
				  && 
		((d.getTime() - getAssetManagerById(am.getId()).getDateInscri().getTime())/(1000*60*60*24) ) > 360)

				{
					getAssetManagerById(am.getId()).setClassification("Expert");
	        	    	
				}    	 
	
				else if(     getAssetManagerById(am.getId()).getRatingAM() <=7
					    && getAssetManagerById(am.getId()).getRatingAM() >5
						&& getAssetManagerById(am.getId()).getScore()>5000
						&& ((d.getTime() - getAssetManagerById(am.getId()).getDateInscri().getTime())/(1000*60*60*24) ) >=90 
						&& ((d.getTime() - getAssetManagerById(am.getId()).getDateInscri().getTime())/(1000*60*60*24) ) < 360
				  )

								{
									getAssetManagerById(am.getId()).setClassification("Professionnel");
					        	    	
								}    	 
				else

								{
									getAssetManagerById(am.getId()).setClassification("Amateur");
					        	    	
								}    	 
}
	return getall();

   	
    
}


@Override
public List<Portfolio> getPortfolioByAM(AssetManager am) {

	TypedQuery<Portfolio> query = em.createQuery("SELECT r FROM Portfolio r  WHERE r.assetmanager.Id=:hamma ", Portfolio.class)
			
			 .setParameter("hamma",getAssetManagerById(am.getId()).getId() );
	
			
			
	 return query.getResultList();

	
	
	
}

@Override
public float nbPortfolios( AssetManager am) {
	
	TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Portfolio r  WHERE r.assetmanager.Id=:hamma ", Long.class)
			
			 .setParameter("hamma",getAssetManagerById(am.getId()).getId());
	
	
	float a = (float) query.getSingleResult() ;
	return a ;
}

@Override
public float pourcentageRatingSup( AssetManager am) {


	TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Ratings r  WHERE r.nomAM=:hamma AND r.Rating>8 ", Long.class)
			
			 .setParameter("hamma",getAssetManagerById(am.getId()).getNom());
	
	
	TypedQuery<Long> query1 = em.createQuery("SELECT Count(*) FROM Ratings r  WHERE r.nomAM=:hamma ", Long.class)
			
			 .setParameter("hamma",getAssetManagerById(am.getId()).getNom());
		
		
		float a = (float) query.getSingleResult() ;
		float b = (float) query1.getSingleResult() ;
		
		float ab = (float) (a/b) ;
		
	 return (float) (ab);

	
}


@Override
public long differenceDate( AssetManager am) 

{
	Date d = new Date () ;

	long a =(d.getTime() - getAssetManagerById(am.getId()).getDateInscri().getTime())/(1000*60*60*24)  ;
	
	return a;

}


@Override
public List<String> GetCommentairesByAm( AssetManager am) 

{
  AssetManager as = getAssetManagerById(am.getId());

  TypedQuery<String> query = em.createQuery("SELECT Cmntr FROM Ratings r WHERE r.nomAM=:x ", String.class)
			 .setParameter("x", as.getNom());
	 
	 
	return query.getResultList() ;
  
	
	

}


@Override
public List<AssetManager> BanAM( ) 

{
   for (AssetManager am : getall())
   {
	   if ( getAssetManagerById(am.getId()).getRatingAM()<4)
	   {
		   getAssetManagerById(am.getId()).setBan(true);
		
	   }
   }
	 	 TypedQuery<AssetManager> query = em.createQuery("SELECT f FROM AssetManager f WHERE f.ban='1' ", AssetManager.class);
 		 return query.getResultList();
 		 
 		 
	   
 
  	
	

}

}
