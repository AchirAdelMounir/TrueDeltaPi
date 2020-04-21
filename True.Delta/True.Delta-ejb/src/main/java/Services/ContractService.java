package Services;



import java.io.File;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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


import Entities.Banque;
import Entities.Contract;
import Entities.User;
import Entities.Visitor;
import Enumerations.ContractType;
import Enumerations.TypeDevise;
import Interfaces.ContractServiceLocal;
import Interfaces.ContractServiceRemote;




@Stateless
public class ContractService implements  ContractServiceRemote , ContractServiceLocal {
	
	ArrayList<Integer>scoreFree= new ArrayList<Integer>();
	ArrayList<Integer>scoreCondition= new ArrayList<Integer>();
	ArrayList<Integer>scoreProposition= new ArrayList<Integer>();

	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddContract(Contract contract) {
		
		/*em.persist(contract);
		System.out.println("Contract:"+ contract.getIDContract());
		
		return contract.getIDContract();*/
		return 0;
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
	
		 

	}

	@Override
	public Contract ReadContractById ( int contractId ) {
		Contract contracts = em.find(Contract.class, contractId);
			return contracts;
	}

    @Override
    public List ListContract() {
    	
    	 List <Contract>  contracts = em.createQuery("select c from Contract c",Contract.class).getResultList();
 		
 		return contracts ; 

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
    	
    	int score1 =0;
    	//score=ScoreVisitor( IdUser);
    	//Contract contract = em.find(Contract.class, IdContract);
    	User contract = em.find(User.class, IdUser);
    	if (contract.getContractType()==ContractType.Free)
    	{
    	score1 +=30;
    	}
    	else if (contract.getContractType()==ContractType.With_Condition)
    	{
    	score1 +=50;
    	}
    	else if (contract.getContractType()==ContractType.With_Proposal)
    	{
    	score1 +=100;
    	}
    	else {
    	score1 +=0;
    	}

    	return score1 ;

    	}

	@Override
	public void VerificationBanque(int idBanque , int idUser) {
		User u = em.find(User.class, idUser);
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
	public double CalculGainAsset(Contract con) {
		
		ContractType f= ContractType.Free;
    	ContractType p= ContractType.With_Proposal;
    	ContractType c= ContractType.With_Condition;
    	double TotalPortefeuille=10.5;
        double	GainAsset1=0;
        		if(con.getContartType().equals(c))
        		{
        			double GainAsset=TotalPortefeuille*0.4;
        			return GainAsset;
        		}	
        	    if(con.getContartType().equals(p))
        	    {
    	           double GainAsset=TotalPortefeuille*0.3;
    	    	   return GainAsset;
             	}	
            	else if(con.getContartType().equals(f) )            		
            	{
                	double GainAsset=TotalPortefeuille*0.2;
                    return GainAsset;
            	}
       return GainAsset1; }
		
	@Override
	public double CalculGainClient(Contract con) {
		
		ContractType free= ContractType.Free;
    	ContractType condition= ContractType.With_Condition;
    	ContractType propo= ContractType.With_Proposal;
    	
    	double TotalPortefeuille=10.5;
        double	GainClientF=0;   
        double	GainClienC=0; 
        double	GainClientP=0;
        	    if(con.getContartType().equals(condition))
        	    {
    	           double GainClient=TotalPortefeuille*0.7;
    	    	   return GainClient;
             	}	
            	else if(con.getContartType().equals(propo) )            		
            	{
                	double GainClient=TotalPortefeuille*0.6;
                    return GainClient;
            	}
            	else if(con.getContartType().equals(free) )            		
            	{
                	double GainClient=TotalPortefeuille*0.5;
                    return GainClient;
            	}
        	   
        	    if(con.getContartType().equals(condition))
        	    {
                   return GainClienC; }
        	    else if(con.getContartType().equals(propo))
        	    {
                    return GainClientP; }
                   else 
                   return GainClientF;
		
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
	
	@Override
	public Object count() {
		
		return em.createQuery("SELECT count(a) FROM Contract a", Contract.class).getResultList(); 
	
	}
	
	@Override
	public List<Integer> findPackProductsdid() {
		 
	    return em.createQuery("SELECT distinct(p.id.IDContract) FROM Contract c INNER JOIN AssetManager p  ON pk.IDContract=p.id.IDContract").getResultList();      
	}
	
	@Override
	public void  DeleteUserFromContract(int idUser, int idContract) {
	   // Query query1 = em.createQuery("DELETE FROM User pp WHERE pp.id.Id= "+idUser+" and pp.id.IDContract ="+idContract);
		Query query1 = em.createQuery("DELETE FROM Contract pp WHERE pp.id.Id = "+idContract+" and pp.id.IDContract ="+idUser);
	    query1.executeUpdate();
	}
	

	
}


		



