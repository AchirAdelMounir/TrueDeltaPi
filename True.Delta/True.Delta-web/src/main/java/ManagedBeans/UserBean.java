package ManagedBeans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import Entities.Customer;
import Entities.Portfolio;
import Entities.User;
import Enumerations.Active_account_Type;
import Enumerations.Bank_type;
import Enumerations.Portfolio_Type;
import Enumerations.Professional_Status_Type;
import Enumerations.Residency_Status_Type;
import Enumerations.Type_of_contract_type;
import Enumerations.UserType;
import Services.PortfolioService;
import Services.UserService;

@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean {

	@EJB
	UserService us ;
	@EJB
	PortfolioService up ;
	
	public User u;
	public String mail;
	public String pass;
	public String firstname;
	public String lastname;
	public Date birthdate;
	public String login;
	public String t;
	public String profession="Employee";
	public String contrattype="FA";
	public String seniority;
	public String residency="Owner";
	public String resources;
	public String bank="BIAT";
	public String credit;
	public String income;
	public String refund;
	public String repayment="2020";
	public String typep="RA";
	public String navigateTo =null;
	public String verification;
	public String code;
	public String nom;
	public String prenom;
	public String retu;
	public String nbact;
	public String price;
	public String typeport;
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRetu() {
		return retu;
	}

	public void setRetu(String retu) {
		this.retu = retu;
	}

	public String getNbact() {
		return nbact;
	}

	public void setNbact(String nbact) {
		this.nbact = nbact;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTypeport() {
		return typeport;
	}

	public void setTypeport(String typeport) {
		this.typeport = typeport;
	}

	private Map<String, Object> sessionMap;

	private ExternalContext externalContext;
	
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getResidency() {
		return residency;
	}

	public void setResidency(String residency) {
		this.residency = residency;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public String getRepayment() {
		return repayment;
	}

	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}

	public String getTypep() {
		return typep;
	}

	public void setTypep(String typep) {
		this.typep = typep;
	}

	public String getContrattype() {
		return contrattype;
	}

	public void setContrattype(String contrattype) {
		this.contrattype = contrattype;
	}

	public String getSeniority() {
		return seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	



	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	
	public String CreateCustomer()
	{
		
		try {
		User u = new User();
		u.setNom(firstname);
		u.setPrenom(lastname);
		u.setDate(birthdate);
		u.setLogin(login);
		u.setAdresseMail(mail);
		u.setPassword(pass);
		u.setType(UserType.Customer);
		Customer c = new Customer();
		c.setDateB(birthdate);
		c.setResidency_Status(null);
		c.setProfession(null);
		c.setSeniority(0);
		c.setNetIncome(0);
		c.setRefund(0);
		c.setResource(0);
		c.setBank(null);
		c.setRisk(0);
		c.setCredit(0);
		c.setTypeofcontract(null);
		c.setScore(0);
		c.setActive(Active_account_Type.Not_Active);
		c.setRepayment("0");
		u.setCustomer(c);
		us.AddUser(u);
		clear();
		
		 externalContext = FacesContext.getCurrentInstance().getExternalContext();
		sessionMap = externalContext.getSessionMap();
		sessionMap.put("user", u);
		
		navigateTo="/CustomerDetails?faces-redirect=true";
		
		externalContext.redirect("CustomerDetails.xhtml");
		setFirstname(u.getNom());
		setLastname(u.getPrenom());
		setMail(u.getAdresseMail());
		
		}
		catch (Exception e) {
			// TODO: handle exception
			t=e.getMessage();
			clear();
		}
		return "/CustomerDetails?faces-redirect=true";
		
	}
	
	public void CustomerDetail() throws IOException
	{
		User u = (User) sessionMap.get("user");
		int s=0;
		if(residency.equals("Owner"))
		{
			u.getCustomer().setResidency_Status(Residency_Status_Type.Owner);
		}
		else
		{
			u.getCustomer().setResidency_Status(Residency_Status_Type.Tenant);
		}
		if(profession.equals("Employee"))
		{
			u.getCustomer().setProfession(Professional_Status_Type.Employee);
		}
		else if(profession.equals("Entrepreneur"))
		{
			u.getCustomer().setProfession(Professional_Status_Type.Entrepreneur);
		}
		else
		{
			u.getCustomer().setProfession(Professional_Status_Type.other);
		}
		
		if(bank.equals("BIAT"))
		{
			u.getCustomer().setBank(Bank_type.Biat);
		}
		else if (bank.equals("BT"))
		{
			
			u.getCustomer().setBank(Bank_type.Banque_de_tunisie);
		}
		else
		{
			u.getCustomer().setBank(Bank_type.Amen_Banque);
		}
		
		if(contrattype.equals("IC"))
		{
			u.getCustomer().setTypeofcontract(Type_of_contract_type.The_Initiation_into_the_World_of_Work_Scheme);
		}
		else if (contrattype.equals("FT"))
		{
			u.getCustomer().setTypeofcontract(Type_of_contract_type.Fixed_term_contract);
		}
		else if(contrattype.equals("PW"))
		{
			u.getCustomer().setTypeofcontract(Type_of_contract_type.permanent_work_contract);
		}
		else {
			u.getCustomer().setTypeofcontract(Type_of_contract_type.other);
		}
		
		
		u.getCustomer().setSeniority(Integer.parseInt(seniority));
		u.getCustomer().setNetIncome(Integer.parseInt(income));
		u.getCustomer().setRefund(Integer.parseInt(refund));
		u.getCustomer().setResource(Integer.parseInt(resources));
		
		u.getCustomer().setCredit(Integer.parseInt(credit));
	
		u.getCustomer().setRepayment(repayment);
		s=us.EstimatedScore(u);
		u.getCustomer().setScore(s);
		if(s>200)
		{
			u.getCustomer().setActive(Active_account_Type.Active);
		}
		else
		{
			u.getCustomer().setActive(Active_account_Type.Not_Active);
		}
		
		Portfolio p;
		if(typep.equals("RA"))
		{
			 p = new Portfolio();
			p.setTypePortfolio(Portfolio_Type.risk_averse);
			p.setPrice(0);
			p.setVolatility(0);
			p.setRatio(0);
			p.setReturns(0);
			u.setPortfolio(p);
			up.AddPortfolio(p);
		}
		else if(typep.equals("RN"))
		{
			 p = new Portfolio();
			p.setTypePortfolio(Portfolio_Type.risk_neutral);
			p.setPrice(0);
			p.setVolatility(0);
			p.setRatio(0);
			p.setReturns(0);
			u.setPortfolio(p);
			up.AddPortfolio(p);
		}
		else
		{
			 p = new Portfolio();
			p.setTypePortfolio(Portfolio_Type.risk_seeking);
			p.setPrice(0);
			p.setVolatility(0);
			p.setRatio(0);
			p.setReturns(0);
			u.setPortfolio(p);
			up.AddPortfolio(p);
			
		}
		
		u.setComplains(null);
		u.setFeedbacks(null);
		u.setContratcs(null);
		us.EditUser(u);
		
		
		 externalContext = FacesContext.getCurrentInstance().getExternalContext();
			sessionMap = externalContext.getSessionMap();
			sessionMap.put("user", u);
			sessionMap.put("port",p);
			
		externalContext.redirect("VerificationCustomer.xhtml");
	}
	
	public void clear(){
	    setFirstname(null);
	    setLastname(null);
	    setBirthdate(null);
	    setLogin(null);
	    setMail(null);
	    setPass(null);
	    setT(null);
		setNom("No portfolio affected to your account");
		setPrenom("");
		setRetu("-.--");
		setTypeport("NAAN");
		setPrice("-.--");
		setNbact("---");
	}
	
		public void VerifCustomer() throws IOException
			{
			User u = (User) sessionMap.get("user");
			Portfolio p = (Portfolio) sessionMap.get("port");
				if(verification.equals(code))
				{
					if(u.getCustomer().getActive().equals(Active_account_Type.Not_Active))
					{
						externalContext = FacesContext.getCurrentInstance().getExternalContext();
						
						clear();
						externalContext.redirect("Not_Active_Customer.xhtml");
					}
					else
					{
						
						up.AffectedPortfolio(p.getIdPortfolio(), u.getId());
						externalContext = FacesContext.getCurrentInstance().getExternalContext();
						
						Portfolio pf =up.DisplayPortfolio(p.getIdPortfolio());
						setNom(u.getNom());
						setPrenom(u.getPrenom());
						setRetu(""+pf.getReturns());
						setTypeport(""+pf.getTypePortfolio());
						setPrice(""+pf.getPrice());
						setNbact("0");
						
						
						
						externalContext.redirect("ProfileCustomer.xhtml");
						
					}
				}
				else
				{
					 externalContext = FacesContext.getCurrentInstance().getExternalContext();
						
						
					externalContext.redirect("VerificationCustomer.xhtml");
					setT("Please verify code !");
				}
			}
	
	@PostConstruct
	public void init()
	{
		
		
		 int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 5;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		 
		  
		setCode(generatedString);
		setProfession("PROFESSION");
		setContrattype("TYPE OF CONTRACT");
		setResidency("TYPE OF CONTRACT");
		setBank("BANK");
		setRepayment("REPAYMENT DATE");
		setTypep("TYPE OF PORTFOLIO *");
	
	}

	public void gobacktoregister() throws IOException
	{
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
	externalContext.redirect("RegisterCustomer.xhtml");
	}
	
	public void editProfile() throws IOException
	{
		
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		User u = (User) sessionMap.get("user");
		setFirstname(u.getNom());
		setLastname(u.getPrenom());
		setMail(u.getAdresseMail());
		setBirthdate(u.getDate());
		setLogin(u.getLogin());
		setPass(u.getPassword());
		externalContext.redirect("EditProfile.xhtml");
	}
	public void editUpdate() throws IOException
	{
		User u = (User) sessionMap.get("user");
		User uf = us.DisplayUser(u.getId());
		uf.setNom(firstname);
		uf.setPrenom(lastname);
		uf.setDate(birthdate);
		uf.setLogin(login);
		uf.setAdresseMail(mail);
		uf.setPassword(pass);
		us.EditUser(uf);
		externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("ProfileCustomer.xhtml");
		
		
		
	}
	
	
	public void giveup()
	{
		User u = (User) sessionMap.get("user");
		
		
		
		u.setPortfolio(null);
		us.EditUser(u);
		clear();
	}
	
		

		
	
		
	
	
}
