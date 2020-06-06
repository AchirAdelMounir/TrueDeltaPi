package beans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;

import Entities.AssetManager;
import Entities.Portfolio;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.chart.PieChartModel;
import Services.TemplateMessage;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.EmailRemote;

@ManagedBean(name = "AssetManagerBean")
@SessionScoped
public class AssetManagerBean {

	
	
	
@EJB 	
AssetManagerServiceInterface es ;
@EJB
EmailRemote emails;
private int Id;
private float returnsRating;
private double returnsDateInsri;
private double returnsClassification;
private double returnsnbPortfolios;
private double total;

private List<String> cmntrs ;

public List<String> getCmntrs() {
	return cmntrs;
}


public void setCmntrs(List<String> cmntrs) {
	this.cmntrs = cmntrs;
}


public double getReturnsnbPortfolios() {
	return returnsnbPortfolios;
}


public void setReturnsnbPortfolios(double returnsnbPortfolios) {
	this.returnsnbPortfolios = returnsnbPortfolios;
}


public double getTotal() {
	return total;
}


public void setTotal(double total) {
	this.total = total;
}

private String nom;
private String password;
private List<Portfolio> portfolios ;

@ManagedProperty(value = "#{loginBean}")
private LoginBean login;






public LoginBean getLogin() {
	return login;
}


public void setLogin(LoginBean login) {
	this.login = login;
}



public List<Portfolio> getPortfolios() {
	return portfolios;
}
public void setPortfolios(List<Portfolio> portfolios) {
	this.portfolios = portfolios;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

private String adresseMail ;
private String dev1;
private String dev2;
private Float montant ;
private AssetManager am ;


public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

public String getAdresseMail() {
	return adresseMail;
}
public void setAdresseMail(String adresseMail) {
	this.adresseMail = adresseMail;
}

private String experience;
private double score ;
private double sc ;

public double getSc() {
	return sc;
}


public void setSc(double sc) {
	this.sc = sc;
}

private double ratingAM;
private String risk;
private Date date ;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
private boolean confirmation ;
private Float resultat=(float) 0 ;






private List<String> logins = new ArrayList<>();

private List<String> keys = new ArrayList<>();
private List<String> achats = new ArrayList<>();
private List<String> risks = new ArrayList<> (Arrays.asList("High", "Medium","Low"));
private PieChartModel pieModel2;
private PieChartModel pieModel3;





public PieChartModel getPieModel3() {
	return pieModel3;
}


public void setPieModel3(PieChartModel pieModel3) {
	this.pieModel3 = pieModel3;
}


public PieChartModel getPieModel2() {
	return pieModel2;
}
public void setPieModel2(PieChartModel pieModel2) {
	this.pieModel2 = pieModel2;
}
public List<String> getRisks() {
	return risks;
}
public void setRisks(List<String> risks) {
	this.risks = risks;
}
private List<AssetManager> assetManagers = new ArrayList<>();
private List<AssetManager> assetManagersConfir=new ArrayList<>();
private List<AssetManager> assetManagersHigh=new ArrayList<>();
private List<AssetManager> assetManagersLow=new ArrayList<>();
private List<AssetManager> assetManagersMedium=new ArrayList<>();
private List<AssetManager> assetManagersHigh1=new ArrayList<>();
private List<AssetManager> assetManagersLow1=new ArrayList<>();
private List<AssetManager> assetManagersMedium1=new ArrayList<>();
private List<AssetManager> assetManagersExpert=new ArrayList<>();
private List<AssetManager> assetManagersDebutant=new ArrayList<>();
private List<AssetManager> assetManagersAmateur=new ArrayList<>();




public List<AssetManager> getAssetManagersHigh1() {
	return assetManagersHigh1;
}
public void setAssetManagersHigh1(List<AssetManager> assetManagersHigh1) {
	this.assetManagersHigh1 = assetManagersHigh1;
}
public List<AssetManager> getAssetManagersLow1() {
	return assetManagersLow1;
}
public void setAssetManagersLow1(List<AssetManager> assetManagersLow1) {
	this.assetManagersLow1 = assetManagersLow1;
}
public List<AssetManager> getAssetManagersMedium1() {
	return assetManagersMedium1;
}
public void setAssetManagersMedium1(List<AssetManager> assetManagersMedium1) {
	this.assetManagersMedium1 = assetManagersMedium1;
}
public List<AssetManager> getAssetManagersLow() {
	this.assetManagersLow= es.GetLow();

	return assetManagersLow;
}
public void setAssetManagersLow(List<AssetManager> assetManagersLow) {
	
	this.assetManagersLow = assetManagersLow;
}
public List<AssetManager> getAssetManagersMedium() {
	this.assetManagersMedium= es.GetMedium();

	return assetManagersMedium;
}
public void setAssetManagersMedium(List<AssetManager> assetManagersMedium) {
	this.assetManagersMedium = assetManagersMedium;
}
public Map<String, Float> changes =new HashMap<>();

public Map <String ,List<String>> commentaires =new HashMap<>();

public Map<String, List<String>> getCommentaires()
{
	
	return  es.Commentaire();
}
public void setCommentaires(Map<String, List<String>> commentaires) {
	this.commentaires = commentaires;
}

public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}
public double getRatingAM() {
	return ratingAM;
}
public void setRatingAM(double rating) {
	this.ratingAM = rating;
}
public String getRisk() {
	return risk;
}
public void setRisk(String risk) {
	this.risk = risk;
}
public boolean getConfirmation() {
	return confirmation;
}
public void setConfirmation(boolean confirmation) {
	this.confirmation = confirmation;
}
public List<AssetManager> getAssetManagers() {
	
	es.CalculRating();
	es.CalculReturns();
    this.assetManagers= es.getall();
	return assetManagers;
}
public void setAssetManagers(List<AssetManager> assetManagers) {
	this.assetManagers = assetManagers;
}
public List<AssetManager> getAssetManagersConfir() {
	this.assetManagersConfir= es.getallConfirmation();
	return assetManagersConfir;
} 


public void setAssetManagersHigh(List<AssetManager> assetManagers) {
	this.assetManagers = assetManagers;
}
public List<AssetManager> getAssetManagersHigh() {
	this.assetManagersHigh= es.GetHigh();
	return assetManagersHigh;
} 







public Map<String, Float> getChanges() throws IOException {
	
	changes =es.Change();
	return changes;
}
public void setChanges(Map<String, Float> changes) {
	this.changes = changes;
}
public void setAssetManagersConfir(List<AssetManager> assetManagers) {
	this.assetManagersConfir = assetManagers;
}


public String add() {
	int ver =0;
	AssetManager Vh = new AssetManager();
	Vh.setNom(this.getNom());
	Vh.setPassword(this.getPassword());
	Vh.setAdresseMail(this.getAdresseMail());
	Vh.setExperience(this.getExperience());
	Vh.setScore(0);
	Vh.setRatingAM(0);
	Vh.setRisk(this.getRisk());
	Date d = new Date();
	Vh.setDateInscri(d);
	for (AssetManager am : getAll())
	{
	     if (this.getNom().equals(es.getAssetManagerById(am.getId()).getNom()))
	     {   ver=1 ;  }
 
	}
	
	
	if ( ver==1 )
		
	{      
		return "/Template/AddAM?faces-redirect=true";

		
	}
	
	else {
	
	
 
 	es.addAssetManager(Vh);

 	Vh.setNom("");
 	
 	Vh.setAdresseMail("");
	Vh.setExperience("");
	Vh.setScore(0);
	Vh.setRatingAM(0);
	Vh.setRisk("");
	
	
	
	

	return "/Template/ListAM?faces-redirect=true";
	}	

}


public void delete( int id) {

	es.deleteAssetManager(id);;
	
}




public String updateAssetManager(AssetManager Vh) {

	this.setNom(Vh.getNom());
	this.setPassword(Vh.getPassword());

	this.setAdresseMail(Vh.getAdresseMail());

	this.setExperience(Vh.getExperience());
    this.setRisk(Vh.getRisk());
    this.setRisk(Vh.getRisk());
    this.setPassword(Vh.getPassword());

	es.updateAssetManager(Vh);
	
	return "/Template/UpdateAM?faces-redirect=true";
	
	
	}

	
public String update() {
	AssetManager Vh = new AssetManager();
	Vh.setNom(this.getNom());
	Vh.setAdresseMail(this.getAdresseMail());
	Vh.setPassword(password);
	Vh.setExperience(this.getExperience());
	Vh.setScore(this.getScore());
	Vh.setRisk(this.getRisk());
	Vh.setClassification("not specified");
	
	
	
	es.updateAssetManager(Vh);
	

	
	Vh.setNom("");
	Vh.setAdresseMail("");

	Vh.setExperience("");
	Vh.setScore(0);

	Vh.setRisk("");
	
	
	
	
	
	return "/Template/ListAM?faces-redirect=true";
	
}

public String acceptAssetManager(AssetManager am) {
	
	am.setConfirmation(true);
	TemplateMessage template = new TemplateMessage("Asset Manager Status");
	emails.sendEmail("ghada.aissaoui@esprit.tn", "Asset Manager " +am.getNom() + " "  + " accepted ", template.getTemplate());
 	
	logins.add(am.getNom());
	
	es.updateAssetManager(am);

	
	
	
	
	return "/Template/ListConfAM?faces-redirect=true";
	
	
	}


public List<String> getLogins() {
	return logins;
}
public void setLogins(List<String> logins) {
	this.logins = logins;
}
public void Convertir () throws IOException
{
	  resultat =es.Conversion(dev1,dev2,montant);
	
}









public List<AssetManager> getAll()
{
	/*for (AssetManager assetmanager : es.getall()) {

	assetmanager.setRatingAM(1);
	}*/
	es.CalculRating();
	es.CalculReturns();
	es.Classification();
	return es.getall() ;
}



public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getDev1() {
	return dev1;
}
public void setDev1(String dev1) {
	this.dev1 = dev1;
}
public String getDev2() {
	return dev2;
}
public void setDev2(String dev2) {
	this.dev2 = dev2;
}
public Float getMontant() {
	return montant;
}
public void setMontant(Float montant) {
	this.montant = montant;
}
public Float getResultat() {
	return resultat;
}
public void setResultat(Float resultat) {
	this.resultat = resultat;
}
public List<String> getKeys() throws IOException {
	keys = es.devise();

	return keys;
}
public void setKeys(List<String> keys) {
	this.keys = keys;
}
public List<String> getAchats() throws IOException {
	achats=es.achat();
	return achats;
}
public void setAchats(List<String> achats) {
	this.achats = achats;
}
private void createPieModel() {
    
    createPieModel2();
    
}
private void createPieModel2() {
    pieModel2 = new PieChartModel();

	
	for (AssetManager assetmanager : es.getall()) {
		

		if( assetmanager.getRisk().equals("High")) {assetManagersHigh1.add(assetmanager);  }
		else if(assetmanager.getRisk().equals("Medium")){assetManagersMedium1.add(assetmanager); }
		else if(assetmanager.getRisk().equals("Low")){assetManagersLow1.add(assetmanager);  }
		System.out.println("//////////////////1///////////////////////////////////////////////////");

		
    pieModel2.set("High", assetManagersHigh1.size());
    pieModel2.set("Medium", assetManagersMedium1.size());
    pieModel2.set("Low", assetManagersLow1.size());
	System.out.println("***************************************************************");

    pieModel2.setTitle("Stat Risk");
    pieModel2.setLegendPosition("e");
    pieModel2.setFill(false);
    pieModel2.setShowDataLabels(true);
    pieModel2.setDiameter(300);
    pieModel2.setShadow(true);
   
	}
	
	System.out.println("---------------------------------------------------------");
	


}
private void createPieModel1() {
    
    createPieModel3();
    
}
private void createPieModel3() {
    pieModel3 = new PieChartModel();

	
	for (AssetManager assetmanager : es.getall()) {
		

		if( assetmanager.getClassification().equals("Expert")) {assetManagersExpert.add(assetmanager);  }
		else if(assetmanager.getClassification().equals("Amateur")){assetManagersAmateur.add(assetmanager); }
		else if(assetmanager.getClassification().equals("Debutant")){assetManagersDebutant.add(assetmanager);  }
		
		System.out.println("//////////////////1///////////////////////////////////////////////////");

		
    pieModel3.set("Expert", assetManagersExpert.size());
    pieModel3.set("Amateur", assetManagersAmateur.size());
    pieModel3.set("Debutant", assetManagersDebutant.size());
	System.out.println("***************************************************************");

    pieModel3.setTitle("Stat classification");
    pieModel3.setLegendPosition("e");
    pieModel3.setFill(false);
    pieModel3.setShowDataLabels(true);
    pieModel3.setDiameter(300);
    pieModel3.setShadow(true);
   
	}
	
	System.out.println("---------------------------------------------------------");
	


}
@PostConstruct
public void init() {
	for (AssetManager assetmanager : es.getall()) {
		
		if( assetmanager.getRisk().equals("High")) {assetManagersHigh1.add(assetmanager);  }
		else if(assetmanager.getRisk().equals("Medium")){assetManagersMedium1.add(assetmanager); }
		else if(assetmanager.getRisk().equals("Low")){assetManagersLow1.add(assetmanager);  }
		
		
	}
for (AssetManager assetmanager : es.getall()) {
		
		if( assetmanager.getClassification().equals("Expert")) {assetManagersExpert.add(assetmanager);  }
		else if(assetmanager.getClassification().equals("Amateur")){assetManagersAmateur.add(assetmanager); }
		else if(assetmanager.getClassification().equals("Debutant")){assetManagersDebutant.add(assetmanager);  }
		
		
	}
	System.out.println("/////////////////////////////////////////////////////////////////////");
	System.out.println(this.assetManagers);
	
	createPieModel();
	createPieModel1();
	
	} 



public AssetManager getAssetManagerByNom(String nom) 
{
	return( es.getAssetManagerByNom(nom)) ;



}
public float CalculRating()
{
	es.CalculRating();

	
	return 5;
	}


public  float Commentaire( )

{
	
	es.Commentaire();
	return 5 ;
	
}



public  Float CalculReturns( )

{
	es.CalculReturns();
	return  (float) 5;
	
	
}

public  Float Classification( )

{
	es.Classification();
	return  (float) 5;
	
	
}


public String DetailsAM(int id)
{
    am=es.getAssetManagerById(id);

	return ("/Template/ShowDetails?faces-redirect=true");

}

public String DetailsCmntr()
{
   cmntrs= es.GetCommentairesByAm(am);
    
    

	return ("/Template/ListeCmntrs?faces-redirect=true");

}

public String GoToAM()
{
    

	return "/Template/ListAM?faces-redirect=true";

}




public String getportfoliobyAM() {
	
	portfolios = es.getPortfolioByAM(am) ;
	return ("/Template/ShowDetailsPortfolio?faces-redirect=true");

	

}






public void CalculReturnsRating ()
{
	
  AssetManager am = es.getAssetManagerById(login.user.getId());
 
 if ( es.pourcentageRatingSup(am)>=0.9) {  returnsRating=(float)1000;  }
  
  else if ( es.pourcentageRatingSup(am)>=0.8 && es.pourcentageRatingSup(am) <0.9) {  returnsRating=(float)800;  }
  else if ( es.pourcentageRatingSup(am)>=0.7 && es.pourcentageRatingSup(am) <0.8) {  returnsRating=es.pourcentageRatingSup(am);  }
  else if ( es.pourcentageRatingSup(am)>=0.6 && es.pourcentageRatingSup(am) <0.7) {  returnsRating=(float)500;  }
  else {returnsRating = (float) 0 ; }
 




}
public void calculReturnsnumberport ()
{
	
	  AssetManager am = es.getAssetManagerById(login.user.getId());
	if (es.nbPortfolios(es.getAssetManagerById(am.getId())) >=2)
		
	{ returnsnbPortfolios=200;  }

}

public void calculReturnsDate ()
{
	
  AssetManager am = es.getAssetManagerById(login.user.getId());
  sc = es.getAssetManagerById(am.getId()).getScore() ;
 
  if ( es.differenceDate(es.getAssetManagerById(am.getId())) >= 1400 ) 
		 { 
	 
	      returnsDateInsri =(double) 2000;
	 
		 }

 else if ( es.differenceDate(es.getAssetManagerById(am.getId())) >= 720   && es.differenceDate(es.getAssetManagerById(am.getId())) <1400 ) 
 { 

  returnsDateInsri =(double) 1500;

 }
 else if ( es.differenceDate(es.getAssetManagerById(am.getId())) >= 360  && es.differenceDate(es.getAssetManagerById(am.getId())) <720) 
 { 

  returnsDateInsri =(double) 500;

 }
 else {   returnsDateInsri =(double) 0; }




}

public String paiement () 
{
	CalculReturns();
	calculReturnsDate();
	CalculReturnsRating();
	calculReturnsnumberport();
	total=returnsDateInsri+returnsnbPortfolios+returnsRating+sc;
	return ("/Template/Returns?faces-redirect=true");
}

public float getReturnsRating() {
	return returnsRating;
}
public void setReturnsRating(float returnsRating) {
	this.returnsRating = returnsRating;
}
public double getReturnsDateInsri() {
	return returnsDateInsri;
}
public void setReturnsDateInsri(double returnsDateInsri) {
	this.returnsDateInsri = returnsDateInsri;
}
public double getReturnsClassification() {
	return returnsClassification;
}
public void setReturnsClassification(double returnsClassification) {
	this.returnsClassification = returnsClassification;
}
public AssetManager getAm() {
	return am;
}
public void setAm(AssetManager am) {
	this.am = am;
}
}
