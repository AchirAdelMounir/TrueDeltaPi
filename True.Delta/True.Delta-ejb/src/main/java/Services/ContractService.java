package Services;



import java.util.List;


import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entities.Banque;
import Entities.Contract;
import Entities.User;
import Entities.Visitor;
import Enumerations.ContractType;
import Enumerations.TypeDevise;
import Interfaces.ContractServiceLocal;
import Interfaces.ContractServiceRemote;




@Stateful
public class ContractService implements  ContractServiceRemote , ContractServiceLocal {

	@PersistenceContext(unitName= "primary")
	EntityManager em;
	
	@Override
	public int AddContract(Contract contract) {
		
		em.persist(contract);
		System.out.println("Contract:"+ contract.getIDContract());
		
		return contract.getIDContract();
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
public void EditContractByID(int IdContract , int Amount) {
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
    
    @Override
    public Boolean  VerificationBanque() {
    	
    	// int Query;
    	//Query a =  em.createQuery("select Credit from Banque b , User u where u.Credit_Balance =b.Credit ");
    	    	 
    	Query banque =  em.createQuery("select * from Banque b , User u where u.CRedit=b.Credit   ") ;
    	
    
    	return false;
    	   
    		
    }
    
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
    	
   



}
