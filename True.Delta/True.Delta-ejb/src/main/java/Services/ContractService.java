package Services;


import java.util.Date;
import java.text.ParseException;


import java.io.File;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import Entities.AssetManager;
import Entities.Banque;
import Entities.Contract;
import Entities.Proposition;
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
		Contract contract=new Contract();
		contract=em.find(Contract.class, IdContract);
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
    	if (Visitor.getCredit()<1000)
    	{    
    	score += 10;
    	}
    	else if (Visitor.getCredit()>=1000 && Visitor.getCredit()<=3000)
    	{
    	score += 30;
    	}
    	else if (Visitor.getCredit()>3000 && Visitor.getCredit()<10000)
    	{
    	score += 50;
    	}
    	else
    	{
    	score +=100;
    	}

    	if (Visitor.getAge()<=30)
    	{
    	score +=10;
    	}
    	else if (Visitor.getAge()>30 && Visitor.getAge()<55)
    	{
    	score +=30;
    	}
    	else
    	{
    	score +=50;
    	}

    	if (Visitor.getCivil_Status().equals("marie"))
    	{
    	score +=10;
    	}
    	else if (Visitor.getCivil_Status().equals("celibataire") || Visitor.getCivil_Status().equals("divorce"))
    	{
    	score +=20;
    	}
    	else
    	{
    	score +=0;
    	}

    	if (Visitor.getProfession().equals("financier") || Visitor.getProfession().equals("homme d'affaires"))
    	{
    	score +=100;
    	}
    	else
    	{
    	score += 30;
    	}

    	if(Visitor.getAccount_Number() <=2)
    	{
    	score +=20;
    	}
    	else
    	{
    	score +=40;
    	}

    	if (Visitor.getDevise()== TypeDevise.Dollar || Visitor.getDevise()==TypeDevise.Euro) {
    	score +=30;
    	}
    	else
    	{
    	score+=10;
    	}
    	
    	if (Visitor.getResidence().equals("propriétaire") )
    	{
    	score +=100;
    	}
    	else if (Visitor.getResidence().equals("locataire"))
        {
    		score +=50;	
    	}
    	else
    	{
    	score += 0;
    	}

    	return score;
    	
    	}
    
    @Override
    public int ScoreContract(int IdUser)
    {
    	
    	int score1 ;
    	int score ;
    	//score=ScoreVisitor( IdUser);
    	//Contract contract = em.find(Contract.class, IdContract);
    	User contract = em.find(User.class, IdUser);
    	score1 =ScoreVisitor(IdUser);
    	
    	if (contract.getContractType()==ContractType.Free)
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

    	return score1 ;
    	

    	}

	@Override
	public void VerificationBanque(int idBanque , int idUser) {
	/*	User u = em.find(User.class, idUser);
		Banque b = em.find(Banque.class, idBanque);

	// if (c.getAmount() == u.getAmount() && c.getCredit() == u.getCredit() && c.getRefund() == u.getRefund()   )
		
		 if (b.getIDClient() == u.getId_banque()  )
		 {
			 System.out.println("the same Id" + b.getIDClient());
		 
			
		
		 if (b.getAmount() == u.getAmount()   )
	 {
		 System.out.println("the same amount" + b.getAmount());
	 }
		else {
			 System.out.println("check the data entered:" + "Amount entered in the bank table" + b.getAmount() + " " + "and" + " " + "Amount entered in the user table" +  u.getAmount() );
			
		}
		 
	
		 if (b.getCredit() == u.getCredit()  )
		 {
			 System.out.println("the same credit" + " " + b.getCredit());
		 }
			else {
				 System.out.println("check the data entered:" + "credit entered in the bank table" + " " + b.getCredit() + " " + "and" + " " + "credit entered in the user table" + " " +  u.getCredit());
				
			}
		 
		 if (b.getRefund() == u.getRefund()  )
		 {
			 System.out.println("the same refund" + " " + b.getRefund());
		 }
			else {
				 System.out.println("check the data entered:" + "refund entered in the bank table" + " " + b.getRefund()+ " " + "and" + " " + "refund entered in the user table" + " " +  u.getRefund());
				
			}
		 }
		 
		 else if (!(b.getIDClient() == u.getId_banque())   ) {
			 System.out.println("check the data entered"  );
			
		}*/
		
	  
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
		
		ContractType f= ContractType.Free;
    	ContractType p= ContractType.With_Proposal;
    	ContractType c= ContractType.With_Condition;
    	ContractType type = null;
    	Contract contract = em.find(Contract.class, Idcon);
    	double TotalPortefeuille =	contract.getAmount();
        double	GainAsset=0;
     	if (contract.getContartType()==ContractType.Free)
        	
        		{
        			 GainAsset=TotalPortefeuille*0.4;
        			
        		}	
     	if (contract.getContartType()==ContractType.With_Proposal)
        	    {
    	            GainAsset=TotalPortefeuille*0.3;
    	    	  
             	}	
     	if (contract.getContartType()==ContractType.With_Condition)          		
            	{
                	 GainAsset=TotalPortefeuille*0.2;
                   
            	}
       return GainAsset; }
		
	@Override
	public double CalculGainClient(int Idcon) {
		
		int nbre;
    	int nbrecompte;
    	int GainClient=0;
    	int  idVisitor;
    	int score = ScoreVisitor(Idcon);
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, Idcon);
    	if (Visitor.getCredit()<1000)
    	{    
    		 GainClient += score*0.1;
    	}
    	else if (Visitor.getCredit()>=1000 && Visitor.getCredit()<=3000)
    	{
    		 GainClient += score*0.2;
    	}
    	else if (Visitor.getCredit()>3000 && Visitor.getCredit()<10000)
    	{
    		 GainClient += score*0.3;
    	}
    	else
    	{
    	 GainClient += score/100;
    	}
    	
    	
    	if(Visitor.getAccount_Number() <=2)
    	{
    		 GainClient += score*0.2;
    	}
    	else
    	{
    		 GainClient += score*0.3;
    	}

    	if (Visitor.getDevise()== TypeDevise.Dollar || Visitor.getDevise()==TypeDevise.Euro) {
    		 GainClient += score*0.4;
    	}
    	else
    	{
    		 GainClient += score;
    	}
    	
    	
    	return (GainClient*10)/100 ;
        	    
        	  
        	    
		
	}
	
/*	@Override
	public int EstimatedScore(Contract contrat,int id) {
		int scoreF =0;
		int scoreC=0;
		int scoreP=0;
		int score =0;

	
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
		 FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\djebb\\OneDrive\\Bureau\\Bonds.xls"));
		    HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		    HSSFSheet datasheet = workbook.getSheetAt(0);
		    String s="";
		    String s1="";
		    double r=0;
		    List<String> l = new ArrayList<String>();
		    for(int i=2;i<76;i++) {
		   
		    Cell cell=datasheet.getRow(i).getCell(2);
		double c=cell.getNumericCellValue();
		    if(c == Amount) {
		   
		    	   for(int j=0; j<10;j++) {
		    		   
		    		    Cell cell1=datasheet.getRow(i).getCell(j);
		    		  CellType cellType = cell1.getCellTypeEnum();
		    		    switch (cellType) {
		    		                case NUMERIC:
		    		                    r=cell1.getNumericCellValue();
		    		                    s1=String.valueOf(r);
		    		                    break;
		    		                case STRING:
		    		                    s1=cell1.getStringCellValue();
		    		                    break;
		               
		    }
		    s+="    "+s1+"     ";
		    }
		   
		    }
		    l.add(s);
		   
		    }
		   

		return l;
		
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
		public void affecterPropositionContract(int PropId,int ContractId) {
		    	Proposition propositionManagedEntity=em.find(Proposition.class, PropId);
		    	Contract contractManagedEntity=em.find(Contract.class, ContractId);
		        contractManagedEntity.setProposition(propositionManagedEntity);
		    
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
	   	    Contract c = em.find(Contract.class, id); 
	    	   Date d= c.getCreationDate() ;
	        @SuppressWarnings("deprecation")
			int year=d.getYear();  
	        System.out.println("Year for date object is :"+year);  
	             return year;
	            		 
	       }
		 
			@Override
			 public double Somme(int year) {
				 List<Contract> contract =getContractAproved();
	             double somme = 0;
		    	    for(Contract c:contract) {
		    int yar=Extractyear(c.getIDContract());  	    	
		    	if(yar==year) {
			    
			    	double s = c.getRisque();
			    	
			    	 somme=somme+s;
		    	}
		    	   
		    	    }
		    	    return  somme;
	 }

	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";

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
		
		int nbre;
    	int nbrecompte;
    	int GainClient=0;
    	int  idVisitor;
    	int score = ScoreVisitor(IDContract);
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, IDContract);
    	if (Visitor.getCredit()<1000)
    	{    
    		 GainClient += score*0.09;
    	}
    	else if (Visitor.getCredit()>=1000 && Visitor.getCredit()<=3000)
    	{
    		 GainClient += score*0.02;
    	}
    	else if (Visitor.getCredit()>3000 && Visitor.getCredit()<10000)
    	{
    		 GainClient += score*0.03;
    	}
    	else
    	{
    	 GainClient += score*0.01;
    	}
    	
    	
    	if(Visitor.getAccount_Number() <=2)
    	{
    		 GainClient += score*0.02;
    	}
    	else
    	{
    		 GainClient += score*0.03;
    	}

    	if (Visitor.getDevise()== TypeDevise.Dollar || Visitor.getDevise()==TypeDevise.Euro) {
    		 GainClient += score*0.04;
    	}
    	else
    	{
    		 GainClient += score;
    	}
    	
    	
    	return (GainClient*10)/100 ;
        	    		
	}
	
	@Override 
	public double RisqueAsset (int IDContract)
	
	{
    	Contract contract = em.find(Contract.class, IDContract);
    	double TotalPortefeuille =	contract.getAmount();
        double	RisqueAsset=0;
     	if (contract.getContartType()==ContractType.Free)
        	
        		{
     		RisqueAsset=TotalPortefeuille*0.6;
        			
        		}	
     	if (contract.getContartType()==ContractType.With_Proposal)
        	    {
     		RisqueAsset=TotalPortefeuille*0.7;
    	    	  
             	}	
     	if (contract.getContartType()==ContractType.With_Condition)          		
            	{
     		RisqueAsset=TotalPortefeuille*0.8;
                   
            	}
       return RisqueAsset; 
		
		
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
	

	

		
	
}


		



