package Services;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;



import java.util.Date;

import java.util.Iterator;
import java.text.DateFormat;
import java.text.ParseException;


import java.io.File;





import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entities.AssetManager;
import Entities.Banque;
import Entities.Contract;
import Entities.Customer;

import Entities.User;

import Enumerations.ContractType;
import Enumerations.Etat_Contract;
import Enumerations.TypeDevise;
import Interfaces.ContractServiceLocal;
import Interfaces.ContractServiceRemote;





@Stateless
@LocalBean
public class ContractService implements  ContractServiceRemote , ContractServiceLocal {
	
	ArrayList<Integer>scoreFree= new ArrayList<Integer>();
	ArrayList<Integer>scoreCondition= new ArrayList<Integer>();
	ArrayList<Integer>scoreProposition= new ArrayList<Integer>();


	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddContract(Contract contract) {
		
		em.persist(contract);
		System.out.println("Contract:"+ contract.getIDContract());
		
		return contract.getIDContract();
		
		
	}
	
	@Override
	public int addContract1(Contract contract, int id_user) {
		User user = em.find(User.class, id_user); 
		if(user!=null)
        {
			  contract.setUser(user);

        
		em.persist(contract);
		return contract.getIDContract();
        }
        else
            return 0;

    	
	}
	
	@Override
	public int AddUser (User user) {
		
		em.persist(user);
		System.out.println("Asset Manager:"+ user.getId());
		
		return user.getId();
	}



	@Override
	public void DeleteContractById(int IdContract) {
	
	Contract	contract=em.find(Contract.class, IdContract);
		em.remove(contract);
		
		//	em.remove(em.find(Contract.class, IdContract));	
		
		//int c = em.createQuery("DELETE c FROM Contract c  INNER JOIN User u ON (u.User_Id = c.IDContract)").executeUpdate();
		//em.remove(query);
			
	}

	@Override
public void EditContractByID(int IdContract , Double Amount) {
		/*Query query = em.createQuery("update Contract c set Amount=:Amount where c.id=:IdContract");
		query.setParameter("Amount", Amount);
		query.setParameter("IDContract", IdContract);
		int modified = query.executeUpdate();
		if(modified == 1){
		System.out.println("successfully updated");
		}else{
		System.out.println("failed to update");
		}*/
		Contract contract = em.find(Contract.class, IdContract);
		contract.setAmount(Amount);

	}

	@Override
	public void AffecterAMAContrat(int IdAM, int IdCpntract) {
	
		User userManagedEntity=em.find(User.class, IdAM);
    	Contract contractManagedEntity=em.find(Contract.class, IdCpntract);
        contractManagedEntity.setUser(userManagedEntity);
    	
		 

	}

	@Override
	public Contract ReadContractById ( int contractId ) {
	
		Contract contracts = em.find(Contract.class, contractId);
			return contracts;
	}

    @Override
    public  List<Contract> ListContract() {
    	
    	 List <Contract>  c = em.createQuery("select c from Contract c",Contract.class).getResultList();
 		
 		return c ; 

    }
    
  /*  @Override
    public User  VerificationBanque(int IdUser) {
    	
    	// int Query;
    	//TypedQuery<User> a =  em.createQuery("select b ,u  from Banque b , User u where u.ID_BANQUE =b.IDClient ",User.class);
    	TypedQuery<User> a =  em.createQuery("select b  from User b ",User.class);
    	return (User) a;
    	 
   		
    }*/
    
    @Override    
    public int ScoreVisitor (int IdVisitor) {
    	int nbre;
    	int nbrecompte;
    	int score=0;
    	int  idVisitor;
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, IdVisitor);
    //	Customer cus = em.find(Customer.class, IdVisitor);
    	

    	return score;
    	
    	}
    
    @Override
    public int ScoreContract(int IdUser)
    {
    	
    	/*int score1 ;
    	int score ;
    	//score=ScoreVisitor( IdUser);
    	//Contract contract = em.find(Contract.class, IdContract);
    	User contract = em.find(User.class, IdUser);
    	score1 =ScoreVisitor(IdUser);
    	
    	if (contract.getContratcs()==ContractType.Free)
    	{
    	score1 +=50;
    	}
    	else if (contract.getContractType()==ContractType.With_Condition)
    	{
    	score1 +=100;
    	}
    	else if (contract.getContractType()==ContractType.With_Proposal)
    	{
    	score1 +=150;
    	}
    	else {
    	score1 +=0;
    	}

    	return score1 ;*/
    	
    	return 0;
    	

    	}

	@Override
	public void VerificationBanque(int idBanque , int idUser) {
		User u = em.find(User.class, idUser);
		Banque b = em.find(Banque.class, idBanque);

		//Contract c = em.find(Contract.class*, primaryKey)

	
	  
	}
	
	@Override
	public int AffecterContratAClient (Contract contract) {
		int Amount = 0 ;
		ContractType type = null;
		//Contract contract = null ;
		if (type == ContractType.With_Condition && Amount >50000)
		{
			AddContract(contract);
		}
		else 
			 if (type ==ContractType.Free && Amount < 50000 ) {
				 AddContract(contract);
				
			}
		else 
			 if (type == ContractType.With_Proposal && Amount >100000)
			 {
				 AddContract(contract);
			 }
		else {
			System.out.println("Envoyer un mail");
		}
		return contract.getIDContract();

		
		
	}
	
	@Override
	public double CalculGainAsset(int Idcon) {
		
    	int GainAM =0 ;
    	int nbre;
    	int nbrecompte;
    	int score=5;
    	int  idVisitor;
    	//idVisitor=IdVisitor.geUser();
    	
    	return score;
    	
    	}
		
	 
	@Override
	public double CalculGainClient(int Idcon) {
		
		int nbre;
    	int nbrecompte;
    	int GainClient=30;
    	int  idVisitor;
    	int score = ScoreVisitor(Idcon);
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, Idcon);
    	
    	return (GainClient*10)/100 ;
        	    
        	  
        	    
		
	}
	
/*	@Override
	public int EstimatedScore(Contract contrat,int id) {
		int scoreF =0;
		int scoreC=0;
		int scoreP=0;
		int score =0;

Asset
		ContractType C= ContractType.With_Condition;
    	ContractType P= ContractType.With_Proposal;
    	ContractType F= ContractType.Free;
    	
	    if((contrat.getContartType().equals(C)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
	    	   { for (int i = 0; i<scoreCondition.size(); i++) 
		    		scoreCondition.add(scoreC);}
	    	score=scoreC+30;}
	    	else 
	    	{
	  return scoreC; }
	    
	    if((contrat.getContartType().equals(P)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
	    	   { for (int i = 0; i<scoreProposition.size(); i++) 
		    		scoreProposition.add(scoreP);}
	    	score=scoreP+20; }
	    	else {
	    	return scoreP;
	    }
	  
	   
	    if((contrat.getContartType().equals(F)) && (contrat.getGain()<CalculGainClient(contrat))){
		   { for (int i = 0; i<scoreFree.size(); i++) 
	    		scoreFree.add(scoreF);}
		   score=scoreF+10;}
	    else {
         return scoreF;}
	    return score;
         
}*/
		   
		 //  scoreP+=10;}
	   // return scoreP;
	  

	    //return 0;
	    
	 
	  /*  if((contrat.getContartType().equals(F)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
	    	{ for (int i = 0; i<scoreFree.size(); i++) 
	    		scoreFree.add(scoreF);}
       
           return scoreF;}
	    if((contrat.getContartType().equals(C)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
         
	        { for (int i = 0; i<scoreCondition.size(); i++) 
	           scoreCondition.add(scoreC);}
	            return scoreC; }
	    
	              else  { for (int i = 0; i<scoreProposition.size(); i++) 
	           scoreProposition.add(scoreP);}
	    {
       return scoreP;}*/
//} }
	
	@Override
	public List<String> matchingContract(Double Amount) throws IOException{
		 

		return null;
		}

	@Override
	public int EstimatedScore(Contract contrat, int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	///----------------------------------------------------------------
	
	@Override
	public Long count() {
		
	TypedQuery<Long> query = em.createQuery("select COUNT (p) from Contract p",Long.class);
		
		return query.getSingleResult();
	
	}
	
	@Override
	public List<Integer> findPackProductsdid() {
		 
	    return em.createQuery("SELECT distinct(p.id.IDContract) FROM Contract c INNER JOIN AssetManager p  ON pk.IDContract=p.id.IDContract").getResultList();      
	}
	
	@Override
	public void  DeleteUserFromContract(int idUser, int idContract) {
	   // Query query1 = em.createQuery("DELETE FROM User pp WHERE pp.id.Id= "+idUser+" and pp.id.IDContract ="+idContract);
		Query query1 = em.createQuery("DELETE FROM Contract pp WHERE pp.id.Id_User = "+idContract+" and pp.id.IDContract ="+idUser);
	    query1.executeUpdate();
	}
	
	@Override
	    public List listontractByType( ContractType type) {
		
	    	// List <Contract>  contracts = em.createQuery("select c from Contract c where c.ContartType=:type",Contract.class).getResultList();
	 		
	 		//return contracts ; 
		return null ;

	    }
	// ------------------------------------------------------------------------------------------------------------
	  
	  @Override
		///tous les contracts des clients 
		public List<Contract> getContractPostedByClient(User c) {
			TypedQuery<Contract> query = em.createQuery("select m from Contract m where m.IDContract=:c.Id " , Contract.class);
			query.setParameter("Id", c.getId());
			return query.getResultList();
		}
	
	  @Override
      public int isAproved(Contract c) {
     	 if(c.getIsApproved()==true) {
     		 return 1;
     	 }
     	 else 
     		 return 0;
      }
	  
		@Override
		public void updateContractDescription(String desc, int ContractId) {
	    	Contract contractManagedEntity=em.find(Contract.class, ContractId);
			    contractManagedEntity.setDescription(desc);
		}
         
		public void UpAssetContrat(Contract c) {
			Contract contrat = em.find(Contract.class,c.getIDContract()); 
	            contrat.setComission(c.getComission());
		        contrat.setRisque(c.getRisque());
		        contrat.setIsApproved(true);
		     
		}
		
		@Override
	    public int deleteContract1(int ContractId) {
	
				em.remove(em.find(Contract.class,ContractId));	
				return ContractId;
			
			}
		
		
		@Override
		// contract acceepter par l'asset manager  
		public List<Contract> getContractAproved() {
			TypedQuery<Contract> query = em.createQuery("select m from Contract m where m.isApproved =:true " , Contract.class);
			query.setParameter("true", true);
			return query.getResultList();
		}
		
		 @Override
	       public int Extractyear(int id) {  
	   	  /*  Contract c = em.find(Contract.class, id); 
	    	   Date d= c.getCreationDate() ;
	        @SuppressWarnings("deprecation")
			int year=d.getYear();  
	        System.out.println("Year for date object is :"+year);  
	             return year;*/
			 return 0;
	            		 
	       }
		 
			@Override
			 public double Somme(int year) {
				/* List<Contract> contract =getContractAproved();
	             double somme = 0;
		    	    for(Contract c:contract) {
		    int yar=Extractyear(c.getIDContract());  	    	
		    	if(yar==year) {
			    
			    	double s = c.getRisque();
			    	
			    	 somme=somme+s;
		    	}
		    	   
		    	    }
		    	    return  somme;*/
				return 0; 
	 }


	@Override
	public List ListContractByType(ContractType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateContract(Contract contract) {
		Contract cont = em.find(Contract.class, contract.getIDContract()); 
		cont.setAmount(contract.getAmount());
		cont.setComission(contract.getComission());
		cont.setCreationDate(contract.getCreationDate());
		cont.setDescription(contract.getDescription());
		cont.setEndDate(contract.getEndDate());
		cont.setStartDate(contract.getStartDate());
		cont.setEtatContract(contract.getEtatContract());
		cont.setContartType(contract.getContartType());
		cont.setFinancialAsset(contract.getFinancialAsset());
		cont.setGain(contract.getGain());
		cont.setRisque(contract.getRisque());
		cont.setScore(contract.getScore());
		cont.setIsApproved(contract.getIsApproved());

		
	}
	
	
	@Override
	public void AffectedPortfolio(int idPortfolio,int idUser) {
		User u = new User();
		Contract p = new Contract();
		u=em.find(User.class, idUser);
		p=em.find(Contract.class, idPortfolio);
		p.setUser(u);
		em.merge(u);
		em.merge(p);
		//Erreur
	}

	@Override
	public void AffectedContract(int idPortfolio, int idUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SelectUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contract ReadContractByEtat(String EtatContract) {
		Contract contracts = em.find(Contract.class, EtatContract);
		return contracts;
	}

	@Override
	public double CalculGainAsset(Contract con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double CalculGainClient(Contract con) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override 
	public double RisqueClient (int IDContract)
	{
		
    	
    	
    	return (30*10)/100 ;
        	    		
	}
	
	@Override 
	public double RisqueAsset (int IDContract)
	
	{
		int GainAM =0 ;
    	int nbre;
    	int nbrecompte;
    	int score=0;
    	int  idVisitor;
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, IDContract);
    	
    	
    	
    	return 0.5;

    	

    	
		
	}

	
	@Override
	 public List<Contract> getAllContractByIdUser(int idUser) {
	
		 TypedQuery<Contract> query= em.createQuery("Select c from Contract c where c.User.Id=:idUser",Contract.class);
			query.setParameter("idUser",idUser);
			return query.getResultList();
		}
	
	@Override
	public List<Contract> getContractByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			Date datec = dateFormat.parse(date);
			TypedQuery<Contract> query = em.createQuery("select c from Contract as c where DATEDIFF (DATE_FORMAT(c.CreationDate,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Contract.class);
			query.setParameter("datec", datec);
			return query.getResultList();
	}
	
	@Override
	 public List<Contract> getAllContractByType(Enumerations.ContractType ContartType) {
		 TypedQuery<Contract> query= em.createQuery("Select c from Contract c where c.ContartType=:ContartType ",Contract.class);
			query.setParameter("ContartType",ContartType);
			return query.getResultList();

		}
	
	@Override
	public Long getNbContractByIdUser(int idUser) {
		TypedQuery<Long> query = em.createQuery("select COUNT (c) from Contract c where c.User.Id=:idUser", Long.class);
		query.setParameter("idUser", idUser);
		return query.getSingleResult();

	}
	
	@Override
	public Long NbrContractByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date datec = dateFormat.parse(date);
		TypedQuery<Long> query = em.createQuery("select COUNT (c) from Contract c where DATEDIFF (DATE_FORMAT(c.CreationDate,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Long.class);
		query.setParameter("datec", datec);
		return query.getSingleResult();
	}
	
	@Override
	public Long getNbContractByContractType(Enumerations.ContractType ContartType) {
		TypedQuery<Long> query = em.createQuery("select COUNT (c) from Contract c where c.ContartType=:ContartType", Long.class);
		query.setParameter("ContartType", ContartType);
		return query.getSingleResult();

	}
	
	@Override
	public double getAvgOfContractType(Enumerations.ContractType ContractType ) {
		double Avg;
		Avg=((double)getNbContractByContractType(ContractType)/(double)count())*100;
		return Avg;
	}
	
	@Override
	public Long NbUser() {
		TypedQuery<Long> query = em.createQuery("select COUNT(*) from User ", Long.class);
		return query.getSingleResult();
	}
	
	@Override
	public Contract findById(int id) {
        return em.find(Contract.class, id);
    }
	
	@Override
	public void UpdateContract1(Contract c) {
		 em.merge(c);
	 }
	
	@Override
	public List<Contract> getAllContract() {
			List<Contract> C = em.createQuery("Select c from Contract c ", Contract.class).getResultList();
			System.out.println(C);
			return C;
		}
	
	 @Override
	 public int DeleteContractByIdUser(int idUser) {
		Query query = em.createQuery("delete  from Contract as c  where c.User.Id=:idUser").setParameter("idUser", idUser);
		int result=query.executeUpdate();
		return result;
		 }
	 
	 @Override
	 public int DeleteContractByDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datec = dateFormat.parse(date);
			Query query = em.createQuery("delete from Contract as c where DATEDIFF (DATE_FORMAT(c.CreationDate,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0").setParameter("datec", datec);
			int result=query.executeUpdate();
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		
		 }
	 
	 
		public Boolean VerificationBanqueUser(int idBanque , int idUser) {
			User u = em.find(User.class, idUser);
			Banque b = em.find(Banque.class, idBanque);
			//Contract c = em.find(Contract.class*, primaryKey)

		  
               return true ;
        
				  
		}
	
		@Override
		public List<Object[]> NbrContractByDate1(String date) throws ParseException {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date datec = dateFormat.parse(date);
			Query query = em.createQuery("select COUNT (c) from Contract c where DATEDIFF (DATE_FORMAT(c.CreationDate,\'%d%m%y\'),DATE_FORMAT(:datec,\'%d%m%y\'))=0", Long.class);
			List<Object[]> results = query.getResultList();
		    return results;
		}
		
	
		@Override
		public List<Object[]> count1() {
			
		Query query = em.createQuery("select COUNT (p) from Contract p",Long.class);
			
		 List<Object[]> results = query.getResultList();
		 return results;
		
		}
		
		@Override
		public  List<Object[]> nbcontractstartdate() {
			

			
			 Query query = em.createQuery("SELECT StartDate,Count(pk)  FROM Contract pk GROUP BY pk.StartDate");
			 List<Object[]> results = query.getResultList();
	 
			    return results;
			}
		
		@Override
		public  List<Object[]> nbcontractenddate() {
			

			
			 Query query = em.createQuery("SELECT EndDate,Count(pk)  FROM Contract pk GROUP BY pk.EndDate");
			 List<Object[]> results = query.getResultList();
				


			    return results;
			}

		@Override
		public Long  NbContractTypeFree() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where ContartType= 'Free'  " , Long.class);
			    return query.getSingleResult();
		}
		
		
		@Override
		public Long  NbContractTypeWithCondition() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where ContartType= 'With_Condition'  " , Long.class);
			    return query.getSingleResult();
		}
		
		@Override
		public Long  NbContractAccept() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where EtatContract= 'Accept'  " , Long.class);
			    return query.getSingleResult();
		}
		
		@Override
		public Long  NbContractInclass() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where EtatContract= 'In_class'  " , Long.class);
			    return query.getSingleResult();
		}
		
		@Override
		public Long  NbContractRefuse() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where EtatContract= 'Refuse'  " , Long.class);
			    return query.getSingleResult();
		}
		
		@Override
		public Long  NbContractIsApproved0() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where isApproved= '0'  " , Long.class);
			    return query.getSingleResult();
		}
		
		@Override
		public Long  NbContractIsApproved1() {
			TypedQuery<Long> query = em.createQuery("SELECT Count(*) FROM Contract where isApproved= '1'  " , Long.class);
			    return query.getSingleResult();
		}

		@Override
		public List<Object[]> nbpackenddate() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		 @Override
		public void SmsSenderAdmin(String msg) {

		  
	}

		@Override

		public List<Contract> GetTopByInput(Date amount,int TopN)
		{
			return (em.createQuery("select c from Contract c ORDER BY "+amount+" DESC",
		          Contract.class).setMaxResults(TopN).getResultList());
			
		}
		
		@Override
		
		public List<Contract> GetLastByInput(String Input,int TopN)
		{
			return (em.createQuery("select c from Contract c ORDER BY "+Input+" ASC",
					Contract.class).setMaxResults(TopN).getResultList());
			
		}
		
		@Override
		public Boolean ifExists(Contract C) {
			if (em.find(Contract.class, C.getAmount()) == null)
				return false;
			else
				return true;

		}
		
		
		@Override
		public Contract DisplayContract(String sym) {
			Contract C = new Contract();
			C = em.find(Contract.class, sym);
			if (ifExists(C) == true) {
				return C;
			} else
				return null;

		}
		
		
		@Override
		public List<Contract> SearchByInput(Integer SearchField, String operator, Object o) {
			if (o instanceof Integer) {
				int O = (Integer) o;
				return (em.createQuery("select c from Contract c where " + SearchField + " " + operator + " " + O,
						Contract.class).getResultList());

			}
			else if (o instanceof Double) {
				Double O = (Double) o;
				return (em.createQuery("select c from Contract c where " + SearchField + " " + operator + " " + O,
						Contract.class).getResultList());

			}
	
			else if(o instanceof Date)
			 {
				Date O = (Date) o;
				return (em.createQuery("select c from Contract c where " + SearchField + " " + operator + " " + O,
						Contract.class).getResultList());

			}
			return null;
		}

		@Override
		public List<Contract> SearchByInput(String SearchField, String operator, Object o) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Contract> SearchByInput(ContractType SearchField, Integer operator, Object o) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Contract> SearchByInput(ContractType SearchField, Integer operator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Contract> SearchByInput(ContractType SearchField) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void AffectedContract1(int idContract,int idUser) {
			User u = new User();
			Contract c =  new Contract();
			u=em.find(User.class, idUser);
			c= em.find(Contract.class, idContract);
		//	u.setContract(c);
			c.setUser(u);
			em.merge(u);
			em.merge(c);


		}

		@Override
		public int addContract1(Contract contract, Long id_user) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void Excel2DatabaseTest()  {
			

		}
		
	//////// Créer un fichier PDF	
		
		
		
		@Override
		public void PDF () throws IOException, URISyntaxException {
			/*PDDocument document1 = new PDDocument();
			PDPage page = new PDPage();
			document1.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document1, page);	
			contentStream.setFont(PDType1Font.COURIER,5);
			contentStream.beginText();
			contentStream.showText("Hello World");
			contentStream.endText();
			contentStream.close();
			document1.save("C:\\\\Users\\\\djebb\\\\OneDrive\\\\Bureau\\\\pdfBoxHelloWorld.pdf");
			document1.close();*/
			
			
		}
		
//// Signature éléctronique 
		
	

	static String fname  = "C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\djebb\\\\\\\\\\\\\\\\OneDrive\\\\\\\\\\\\\\\\Bureau\\\\\\\\\\\\\\\\ReportingSupervision.pdf" ;
	static String fnameS = "C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\djebb\\\\\\\\\\\\\\\\OneDrive\\\\\\\\\\\\\\\\Bureau\\\\\\\\\\\\\\\\ReportingSupervision_Sign.pdf" ;
	@Override
	public boolean signPdf () throws IOException, Exception
	{
 
			return true;
	
		
	}
	
	@Override
	  public void pdfs( ) throws SQLException {

	           
		
		
	  }

	@Override
	public Long verif(int idC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contract> GetTopByInput(String Input, int TopN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contract> GetTopByInput(int amount, int TopN) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@Override
	public Object GetAmountByContratId(int IDContract) {
		
		 Query query = em.createQuery("SELECT c.Amount FROM Contract c where c.IDContract=:IDContract");
		 query.setParameter("IDContract",IDContract);
		 Object results = query.getSingleResult();
			
		    return results;
		}
	
	@Override
	public List<Object[]> GetContractBYAmount(Double  Amount) {
		
		 Query query = em.createQuery("SELECT c FROM Contract c where c.Amount=:Amount");
		 query.setParameter("Amount",Amount);
		 List<Object[]> results = query.getResultList();
			
		    return results;
		}
	
	@Override
	public List<Object[]> GetContractBYStartDate(Date StartDate) {
		
		 Query query = em.createQuery("SELECT c FROM Contract c where c.StartDate=:StartDate");
		 query.setParameter("StartDate",StartDate);
		 List<Object[]> results = query.getResultList();
			
		    return results;
		}
	
	@Override
	public List<Object[]> GetContractBYEndDate(Date EndDate) {
		
		 Query query = em.createQuery("SELECT c FROM Contract c where c.EndDate=:EndDate");
		 query.setParameter("EndDate",EndDate);
		 List<Object[]> results = query.getResultList();
			
		    return results;
		}

	@Override
	public void pdfs(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void affecterClientToContract(int idClient) {
		
		System.out.println("orezzsibqmeo:)");
		Contract contract = new Contract();

		User client = em.find(User.class, idClient);
	
		 contract.setUser(idClient);
		 System.out.println("jhkhlhm" +client);

		em.persist(contract);
		//em.flush();

	}

	@Override
	public void SmsSender(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affecterPropositionContract(int PropId, int ContractId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SmsSenderAM(String msg) {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
	
	
	}
	

	
	
	
	
	
		
		
		
		




		



