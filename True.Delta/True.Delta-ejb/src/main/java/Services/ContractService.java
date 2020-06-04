package Services;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.Date;

import java.util.Iterator;
import java.text.DateFormat;
import java.text.ParseException;


import java.io.File;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.messaging.v1.Session;
import com.twilio.type.PhoneNumber;



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
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.apache.pdfbox.contentstream.operator.text.NextLine;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import Entities.AssetManager;
import Entities.Banque;
import Entities.Contract;
import Entities.Customer;
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

	 if (b.getAmount() == u.getCredit() || b.getCredit() == u.getCredit() || b.getRefund() == u.getRefund()   )
		
		 if (b.getIDClient() == u.getId_banque()  )
		 {
			 System.out.println("the same Id" +" " +  b.getIDClient());
		 
			
		
		 if (b.getAmount() == u.getCredit()   )
	 {
		 System.out.println("the same amount" + " " +  b.getAmount());
	 }
		else {
			 System.out.println("check the data entered:" + "Amount entered in the bank table" + " " +  b.getAmount() + " " + "and" + " " + "Amount entered in the user table"+ " " + b.getCredit() );
			
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
			
		}
		
	  
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
    	int score=0;
    	int  idVisitor;
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, Idcon);
    	if (Visitor.getCredit()<1000)
    	{    
    	score += 1;
    	}
    	else if (Visitor.getCredit()>=1000 && Visitor.getCredit()<=3000)
    	{
    	score += 3;
    	}
    	else if (Visitor.getCredit()>3000 && Visitor.getCredit()<10000)
    	{
    	score += 5;
    	}
    	else
    	{
    	score +=10;
    	}

    	

    	if (Visitor.getCivil_Status().equals("marie"))
    	{
    	score +=1;
    	}
    	else if (Visitor.getCivil_Status().equals("celibataire") || Visitor.getCivil_Status().equals("divorce"))
    	{
    	score +=2;
    	}
    	else
    	{
    	score +=0;
    	}

    	if (Visitor.getProfession().equals("financier") || Visitor.getProfession().equals("homme d'affaires"))
    	{
    	score +=10;
    	}
    	else
    	{
    	score += 3;
    	}

    	if(Visitor.getAccount_Number() <=2)
    	{
    	score +=2;
    	}
    	else
    	{
    	score +=4;
    	}

    	if (Visitor.getDevise()== TypeDevise.Dollar || Visitor.getDevise()==TypeDevise.Euro) {
    	score +=3;
    	}
    	else
    	{
    	score+=1;
    	}
    	
    	if (Visitor.getResidence().equals("propriétaire") )
    	{
    	score +=10;
    	}
    	else if (Visitor.getResidence().equals("locataire"))
        {
    		score +=5;	
    	}
    	else
    	{
    	score += 0;
    	}

    	return score;
    	
    	}
		
	 
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
		  FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\djebb\\OneDrive\\Bureau\\Client_BQ.xls"));
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
		int GainAM =0 ;
    	int nbre;
    	int nbrecompte;
    	int score=0;
    	int  idVisitor;
    	//idVisitor=IdVisitor.geUser();
    	User Visitor = em.find(User.class, IDContract);
    	if (Visitor.getCredit()<1000)
    	{    
    	score += 0.1;
    	}
    	else if (Visitor.getCredit()>=1000 && Visitor.getCredit()<=3000)
    	{
    	score += 0.03;
    	}
    	else if (Visitor.getCredit()>3000 && Visitor.getCredit()<10000)
    	{
    	score += 0.05;
    	}
    	else
    	{
    	score +=0.10;
    	}

    	

    	if (Visitor.getCivil_Status().equals("marie"))
    	{
    	score +=0.1;
    	}
    	else if (Visitor.getCivil_Status().equals("celibataire") || Visitor.getCivil_Status().equals("divorce"))
    	{
    	score +=0.2;
    	}
    	else
    	{
    	score +=0;
    	}

    	if (Visitor.getProfession().equals("financier") || Visitor.getProfession().equals("homme d'affaires"))
    	{
    	score +=0.10;
    	}
    	else
    	{
    	score += 0.3;
    	}

    	if(Visitor.getAccount_Number() <=2)
    	{
    	score +=0.2;
    	}
    	else
    	{
    	score +=0.4;
    	}

    	if (Visitor.getDevise()== TypeDevise.Dollar || Visitor.getDevise()==TypeDevise.Euro) {
    	score +=0.3;
    	}
    	else
    	{
    	score+=0.1;
    	}
    	
    	if (Visitor.getResidence().equals("propriétaire") )
    	{
    	score +=0.10;
    	}
    	else if (Visitor.getResidence().equals("locataire"))
        {
    		score +=0.5;	
    	}
    	else
    	{
    	score += 0;
    	}

    	return score*10;
		
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

		 if (b.getAmount() == u.getCredit()  && b.getRefund() == u.getRefund() && b.getDevise() == u.getDevise())   
               return true ;
          else {
			 return false;
		}
				  
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
		
		public static final String ACCOUNT_SID = "AC617d172488dae6423a2669df5d0c1a7c";
		public static final String AUTH_TOKEN = "c5806b1ba4abf165966ef3b1a13f4af1";
		public void SmsSender(String msg) {

			    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			    Message message = Message.creator(new PhoneNumber("+21628609625"),
			        new PhoneNumber("+12134641462"), 
			        msg).create();

			    System.out.println(message.getSid()+ " " + "message Envoyer");
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
			 try
			    {  
			        Class.forName("com.mysql.jdbc.Driver")  ;
			        Connection con=DriverManager.getConnection(  
			        "jdbc:mysql://localhost:3306/true.delta","root","");  

			String excelFilePath = "C:\\Users\\djebb\\OneDrive\\Bureau\\Client_BQ.xls";

			int batchSize = 20;

			Connection connection = null;

			try {
				long start = System.currentTimeMillis();
				
				FileInputStream inputStream = new FileInputStream(excelFilePath);

				Workbook workbook = new HSSFWorkbook(inputStream);

				HSSFSheet Sheet = (HSSFSheet) workbook.getSheetAt(0);
				Iterator<Row> rowIterator = Sheet.iterator();

	          //  connection = DriverManager.getConnection(jdbcURL, username, password);
	            con.setAutoCommit(false);
	 
	            String sql = "INSERT INTO banque (IDClient, Age,Amount ,Credit , Nom,Prenom,Profession,Refund ,Residence, Civil_Status , Devise  ) VALUES (?, ?, ?,?, ?, ?,?, ?,?,?,?)";
	            PreparedStatement statement = con.prepareStatement(sql);		
				
	            int count = 0;
	            
	            rowIterator.next(); // skip the header row
	            
				while (rowIterator.hasNext()) {
					Row nextRow = rowIterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();

					while (cellIterator.hasNext()) {
						Cell nextCell = cellIterator.next();

						int columnIndex = nextCell.getColumnIndex();

						switch (columnIndex) {
						case 0:
							int IDClient = (int) nextCell.getNumericCellValue();
							statement.setInt(1, IDClient);
							break;
						case 1:
							int Age = (int) nextCell.getNumericCellValue();
							statement.setInt(2, Age);
							break;
							
						case 2:
							int Amount = (int) nextCell.getNumericCellValue();
							statement.setInt(3, Amount);
							break;
						case 3:
							int Credit = (int) nextCell.getNumericCellValue();
							statement.setInt(4, Credit);
							break;
						case 4:
							String Nom = nextCell.getStringCellValue();
							statement.setString(5, Nom);
							break;
						
						case 5:
							String Prenom = nextCell.getStringCellValue();
							statement.setString(6, Prenom);
							break;
						case 6:
							String Profession= nextCell.getStringCellValue();
							statement.setString(7, Profession);
							break;

						case 7:
							int Refund= (int) nextCell.getNumericCellValue();
							statement.setInt(8, Refund);
							break;
						case 8:
							String Residence = nextCell.getStringCellValue();
							statement.setString(9,Residence);
							break;
						case 9:
							String Civil_Status = nextCell.getStringCellValue();
							statement.setString(10,Civil_Status);
							break;
						case 10:
							String Devise = nextCell.getStringCellValue();
							statement.setString(11,Devise);
							break;	
						}

					}
					
	                statement.addBatch();
	                
	                if (count % batchSize == 0) {
	                    statement.executeBatch();
	                }				

				}

				workbook.close();
				
	            // execute the remaining queries
	            statement.executeBatch();
	 
	            con.commit();
	            con.close();	
	            
	            long end = System.currentTimeMillis();
	            System.out.printf("Import done in %d ms\n", (end - start));
	            
			} catch (IOException ex1) {
				System.out.println("Error reading file");
				ex1.printStackTrace();
			} catch (SQLException ex2) {
				System.out.println("Database error");
				ex2.printStackTrace();
			}
			} catch (SQLException e) {
			    throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	//////// Créer un fichier PDF	
		
		private void addTableHeader(PdfPTable table) {
		    Stream.of("column header 1", "column header 2", "column header 3")
		      .forEach(columnTitle -> {
		        PdfPCell header = new PdfPCell();
		        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        header.setBorderWidth(2);
		        header.setPhrase(new Phrase(columnTitle));
		        table.addCell(header);
		    });
		}
		
		private void addRows(PdfPTable table) {
		
		    table.addCell("row 1, col 1");
		    table.addCell("row 1, col 2");
		    table.addCell("row 1, col 3");
		}
		
		private void addCustomRows(PdfPTable table)
				  throws URISyntaxException, BadElementException, IOException {
				   // Path path = Paths.get(ClassLoader.getSystemResource("C:\\Users\\djebb\\OneDrive\\Bureau\\Java__logo.png").toURI());
				  //  Image img = Image.getInstance(path.toAbsolutePath().toString());
				  //  img.scalePercent(10);

				  //  PdfPCell imageCell = new PdfPCell(img);
				  //  table.addCell(imageCell);

				    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
				    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				    table.addCell(horizontalAlignCell);

				    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
				    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
				    table.addCell(verticalAlignCell);
				}
		
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
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Long millis = System.currentTimeMillis();
		     Date date = new Date(millis);
		     Contract cont = new Contract(); 
            String out = "C:\\\\\\\\Users\\\\\\\\djebb\\\\\\\\OneDrive\\\\\\\\Bureau\\\\\\\\ReportingSupervision.pdf";
            Document document = new Document(PageSize.A4);
             
            try {
                // etape 2:
                // creation du writer -> PDF ou HTML
                PdfWriter.getInstance(document, new FileOutputStream(out));
                             
                // etape 3: Ouverture du document
                document.open();
                
                // etape 4: Ajout du contenu au document
                document.addTitle("Rapport de supervison");
                document.addCreationDate();
                Paragraph p = new Paragraph("Contract");
                Paragraph p1 = new Paragraph();
                p.setAlignment(1);
                p1.setAlignment(0);
                document.add(p);
                document.add(p1);
               // document.add(icon);
                
                PdfPTable table = new PdfPTable(3);
                addTableHeader(table);
                addRows(table);
                addCustomRows(table);

                document.add(table);
                 
            }
            catch(DocumentException de) {
                System.err.println(de.getMessage());
                 
            }
            catch(IOException ioe) {
                System.err.println(ioe.getMessage());
                
            }
            // etape 5: Fermeture du document
            document.close();
           
         /*   System.out.println("Document '"+out+"' generated");
            JOptionPane.showMessageDialog(this,"PDF Géneré",
                    " Génération du PDF ",
                    JOptionPane.WARNING_MESSAGE);*/	
		}
		
//// Signature éléctronique 
		
	

	static String fname  = "C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\djebb\\\\\\\\\\\\\\\\OneDrive\\\\\\\\\\\\\\\\Bureau\\\\\\\\\\\\\\\\ReportingSupervision.pdf" ;
	static String fnameS = "C:\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\djebb\\\\\\\\\\\\\\\\OneDrive\\\\\\\\\\\\\\\\Bureau\\\\\\\\\\\\\\\\ReportingSupervision_Sign.pdf" ;
	@Override
	public boolean signPdf () throws IOException, Exception
	{
		// Vous devez preciser ici le chemin d'acces a votre clef pkcs12
		String fileKey = "C:\\Users\\MaClef.p12" ;
		// et ici sa "passPhrase"
		String fileKeyPassword  = "12345" ;
 
		try {
			// Creation d'un KeyStore
			KeyStore ks = KeyStore.getInstance("pkcs12");
			// Chargement du certificat p12 dans el magasin
			ks.load(new FileInputStream(fileKey), fileKeyPassword.toCharArray());
			String alias = (String)ks.aliases().nextElement();
			// Recupération de la clef privée
			PrivateKey key = (PrivateKey)ks.getKey(alias, fileKeyPassword.toCharArray());
			// et de la chaine de certificats
			Certificate[] chain = ks.getCertificateChain(alias);
 
			// Lecture du document source
			PdfReader pdfReader = new PdfReader((new File(fname)).getAbsolutePath());
			File outputFile = new File(fnameS);
			// Creation du tampon de signature
			PdfStamper pdfStamper;
			pdfStamper = PdfStamper.createSignature(pdfReader, null, '\0', outputFile);
			PdfSignatureAppearance sap = pdfStamper.getSignatureAppearance();
		//	sap.setCrypto(key, chain, null, PdfSignatureAppearance.SELF_SIGNED);
			sap.setReason("Test SignPDF berthou.mc");
			sap.setLocation("");
			// Position du tampon sur la page (ici en bas a gauche page 1)
			sap.setVisibleSignature(new Rectangle(10, 10, 50, 30), 1, "sign_rbl");
 
			pdfStamper.setFormFlattening(true);
			pdfStamper.close();
 
			return true;
		}
		catch (Exception key) {
			throw new Exception(key);
		}
	}
	
	@Override
	  public void pdfs() throws SQLException {

	           try {
	              Class.forName("com.mysql.jdbc.Driver");
	                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/true.delta","root","");
	     Statement stmt = con.createStatement();
	                       /* Define the SQL query */
	                       ResultSet query_set = stmt.executeQuery("SELECT *From Contract where IDContract=1");
	                       /* Step-2: Initialize PDF documents - logical objects */
	                       Document my_pdf_report = new Document();
	               PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:\\\\Users\\\\djebb\\\\OneDrive\\\\Bureau\\\\pdf_report_from_sql_using_java.pdf"));
	                       my_pdf_report.open();  
	                       Paragraph p = new Paragraph("Wedding photographers are must-have vendors for many couples. An experienced photographer knows how to capture the shots that will be treasured for a lifetime. A wedding photography contract serves as a legal agreement and a checklist for specific photos the couple would like the photographer to take during the event. The wedding photography contract format is designed to include multiple venues, the special shots requested by the bride and groom, photograph size and package options, pricing details and liability information. Most contracts of this type are written in favor of the vendor. Before signing, the bride and groom should read the contract carefully. This wedding photography contract sample is for the fictitious company AAA Photography Studios and spells out the responsibilities of each party and details the limited liability of AAA Photography Studios. It accommodates the signatures of both the bride and the groom and protects the interest of the company and the company’s photographer.");
	                      Paragraph p1 = new Paragraph("--------------------------------------------------Creation Contract---------------------------------------");
	                      Paragraph p2= new Paragraph("\b");

                          my_pdf_report.addTitle("Cration Contract");
	                       //we have four columns in our table
	                       PdfPTable my_report_tableContract = new PdfPTable(1);
	                       PdfPTable my_report_tableContractType = new PdfPTable(2);
	                       PdfPTable my_report_tableConreationDate = new PdfPTable(2);
	                       PdfPTable my_report_tableAmount = new PdfPTable(2);
	                       PdfPTable my_report_tableStartDate = new PdfPTable(2);
	                       PdfPTable my_report_tableEndDate = new PdfPTable(2);
	                       PdfPTable my_report_tableGain = new PdfPTable(2);
	                       PdfPTable my_report_tableRisque = new PdfPTable(2);
	                       PdfPTable my_report_tableGainAsset = new PdfPTable(2);
	                       PdfPTable my_report_tableRisqueAsset = new PdfPTable(2);
	                       PdfPTable my_report_tableGainClient = new PdfPTable(2);
	                       PdfPTable my_report_tableRisqueClient = new PdfPTable(2);
	                       PdfPTable my_report_tableLevel = new PdfPTable(2);
	                       PdfPTable my_report_tableApproved = new PdfPTable(2);
	                       PdfPTable my_report_tableEtatContract = new PdfPTable(2);
	                       PdfPTable my_report_tableFinancial = new PdfPTable(2);

	                       //create a cell object
	                       PdfPCell table_cell;
	                       PdfPCell Contract = new PdfPCell(new Paragraph("Create Contract"));
	                       PdfPCell CreationDate = new PdfPCell(new Paragraph("Creation Date"));
	                       PdfPCell ContractType = new PdfPCell(new Paragraph("Contract Type"));
	                       PdfPCell Amount = new PdfPCell(new Paragraph("Amount"));
	                       PdfPCell StartDate = new PdfPCell(new Paragraph("Start Date"));
	                       PdfPCell EndDate = new PdfPCell(new Paragraph("End Date"));
	                       PdfPCell Gain = new PdfPCell(new Paragraph("Gain"));
	                       PdfPCell Risque = new PdfPCell(new Paragraph("Risque"));
	                       PdfPCell GainAsset = new PdfPCell(new Paragraph("Gain Asset"));
	                       PdfPCell RisqueAsset = new PdfPCell(new Paragraph("Risque Asset"));
	                       PdfPCell GainClient = new PdfPCell(new Paragraph("Gain Client"));
	                       PdfPCell RisqueClient = new PdfPCell(new Paragraph("Risque Client"));
	                       PdfPCell Level = new PdfPCell(new Paragraph("Level"));
	                       PdfPCell Approved = new PdfPCell(new Paragraph("Approved"));
	                       PdfPCell EtatContract = new PdfPCell(new Paragraph("Etat Contract "));
	                       PdfPCell FinancialAsset  = new PdfPCell(new Paragraph("Financial Asset  "));

	                       while (query_set.next()) {      
	                    	   				//Contract
	                                       my_report_tableContract.addCell(Contract);
	                                   
	                                       //Date creation
	                                       String DateCreation=query_set.getString("CreationDate");
	                                       table_cell=new PdfPCell(new Phrase(DateCreation));
	                                       my_report_tableConreationDate.addCell(CreationDate);
	                                       my_report_tableConreationDate.addCell(table_cell);
	                                       //Contract Type
	                                      String Contracttype=query_set.getString("ContartType");
	                                       table_cell=new PdfPCell(new Phrase(Contracttype));
	                                       my_report_tableContractType.addCell(ContractType);
	                                       my_report_tableContractType.addCell(table_cell);
	                                       //financial asset FinancialAsset 
	                                       String financial=query_set.getString("FinancialAsset");
	                                       table_cell=new PdfPCell(new Phrase(financial));
	                                       my_report_tableFinancial.addCell(FinancialAsset);
	                                       my_report_tableFinancial.addCell(table_cell);
	                                       //amount
	                                       String amount=query_set.getString("Amount");
	                                       table_cell=new PdfPCell(new Phrase(amount));
	                                       my_report_tableAmount.addCell(Amount);
	                                       my_report_tableAmount.addCell(table_cell);
	                                       //start date
	                                       String startdate=query_set.getString("StartDate");
	                                       table_cell=new PdfPCell(new Phrase(startdate));
	                                       my_report_tableStartDate.addCell(StartDate);
	                                       my_report_tableStartDate.addCell(table_cell);
	                                       //End date
	                                       String enddate=query_set.getString("EndDate");
	                                       table_cell=new PdfPCell(new Phrase(enddate));
	                                       my_report_tableEndDate.addCell(EndDate);
	                                       my_report_tableEndDate.addCell(table_cell);
	                                       //Gain 
	                                       String gain=query_set.getString("Gain");
	                                       table_cell=new PdfPCell(new Phrase(gain));
	                                       my_report_tableGain.addCell(Gain);
	                                       my_report_tableGain.addCell(table_cell);
	                                       //Risque
	                                       String risque=query_set.getString("Risque");
	                                       table_cell=new PdfPCell(new Phrase(risque));
	                                       my_report_tableRisque.addCell(Risque);
	                                       my_report_tableRisque.addCell(table_cell);
	                                       //Gain Asset
	                                       String gainasset=query_set.getString("GainAset");
	                                       table_cell=new PdfPCell(new Phrase(gainasset));
	                                       my_report_tableGainAsset.addCell(GainAsset);
	                                       my_report_tableGainAsset.addCell(table_cell);
	                                       //Risque Asset
	                                       String risqueasset=query_set.getString("RisqueAsset");
	                                       table_cell=new PdfPCell(new Phrase(risqueasset));
	                                       my_report_tableRisqueAsset.addCell(RisqueAsset);
	                                       my_report_tableRisqueAsset.addCell(table_cell);
	                                       //Gain Client
	                                       String gainclient=query_set.getString("gainClient");
	                                       table_cell=new PdfPCell(new Phrase(gainclient));
	                                       my_report_tableGainClient.addCell(GainClient);
	                                       my_report_tableGainClient.addCell(table_cell);
	                                       //Risque Client
	                                       String risqueclient=query_set.getString("RisqueClient");
	                                       table_cell=new PdfPCell(new Phrase(risqueclient));
	                                       my_report_tableRisqueClient.addCell(RisqueClient);
	                                       my_report_tableRisqueClient.addCell(table_cell);
	                                       //level
	                                       String level=query_set.getString("levelR");
	                                       table_cell=new PdfPCell(new Phrase(level));
	                                       my_report_tableLevel.addCell(Level);
	                                       my_report_tableLevel.addCell(table_cell);
	                                       //Approved
	                                       String approved=query_set.getString("isApproved");
	                                       table_cell=new PdfPCell(new Phrase(approved));
	                                       my_report_tableApproved.addCell(Approved);
	                                       my_report_tableApproved.addCell(table_cell);
	                                       //Etat Contract
	                                       String etat=query_set.getString("EtatContract");
	                                       table_cell=new PdfPCell(new Phrase(etat));
	                                       my_report_tableEtatContract.addCell(EtatContract);
	                                       my_report_tableEtatContract.addCell(table_cell);
	                                       }
	                       
	                     
	                       /* Attach report table to PDF */
	                       my_pdf_report.addTitle("Cration Contract");
	                    //   my_pdf_report.add((Element) title);
	                       my_pdf_report.add(p1);
	                       my_pdf_report.add(p2);
	                       my_pdf_report.add(p);
	                       my_pdf_report.add(p2);
	                       my_pdf_report.add(p2);
	                       my_pdf_report.add(my_report_tableContract);
	                       my_pdf_report.add(my_report_tableConreationDate);
	                       my_pdf_report.add(my_report_tableContractType);
	                       my_pdf_report.add(my_report_tableFinancial);
	                       my_pdf_report.add(my_report_tableAmount);
	                       my_pdf_report.add(my_report_tableStartDate);
	                       my_pdf_report.add(my_report_tableEndDate);
	                       my_pdf_report.add(my_report_tableGain);
	                       my_pdf_report.add(my_report_tableRisque);
	                       my_pdf_report.add(my_report_tableGainAsset);
	                       my_pdf_report.add(my_report_tableRisqueAsset);
	                       my_pdf_report.add(my_report_tableGainClient);
	                       my_pdf_report.add(my_report_tableRisqueClient);
	                       my_pdf_report.add(my_report_tableLevel);
	                       my_pdf_report.add(my_report_tableApproved);
	                       my_pdf_report.add(my_report_tableEtatContract);

	                
	                       my_pdf_report.close();

	                       /* Close all DB related objects */
	                       query_set.close();
	                       stmt.close(); 
	                       con.close();               



	       } catch (FileNotFoundException e) {
	       // TODO Auto-generated catch block
	       e.printStackTrace();
	       } catch (DocumentException e) {
	       // TODO Auto-generated catch block
	       e.printStackTrace();
	       } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	
	
	
	}
	

	
	
	
	
	
		
		
		
		




		



