package ManagedBeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

import Entities.Complain;
import Entities.Feedback;
import Entities.User;
import Enumerations.LevelRating;
import Enumerations.StatusTypeOfComplain;
import Enumerations.Who;
import Services.ComplainsServices;



@ManagedBean(name = "ComplainBean")
@SessionScoped

public class ComplainBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	ComplainsServices service;
	
	private int id; 
	private String subject;
	private String description;
    private StatusTypeOfComplain status;
	private Date date ;
	private String answer="";
	private User user;
	private List<Complain> complainList = new ArrayList<>();
	private Complain selectedComplain;
	private String x = " ";
	private StatusTypeOfComplain statusNotRead=StatusTypeOfComplain.Not_Read;
	private StatusTypeOfComplain statusInProgress =StatusTypeOfComplain.In_Progress;
	private StatusTypeOfComplain statusAnswered=StatusTypeOfComplain.Answered;
	private Boolean customer_vu;
	private String ch;
	private Complain selectedComplain1;
	private String NomPrenom;
	private int number;
	
	
	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}



	public String getNomPrenom() {
		return NomPrenom;
	}



	public void setNomPrenom(String nomPrenom) {
		NomPrenom = nomPrenom;
	}



	@PersistenceContext(unitName = "primary")
	EntityManager em;
	@PostConstruct
	private void postConstruct() {
		// Bar chart
		
		// star rating
		complainList.clear();

		List<Integer> listOfInd = new ArrayList<>();

		listOfInd = em.createQuery("Select c.id from Complain c ", Integer.class).getResultList();
		for (int i = 0; i < listOfInd.size(); i++) {
			Complain complain = new Complain();
			int j = listOfInd.get(i);
			complain.setId(j);
			User user = getComplainById(j).getUser();
			String subject = getComplainById(j).getSubject();
			String description = getComplainById(j).getDescription();
			StatusTypeOfComplain status = getComplainById(j).getStatus();
			String answer =getComplainById(j).getAnswer();
			Date date = getComplainById(j).getDate();
			complain.setUser(user);
			complain.setSubject(subject);
			complain.setDescription(description);
			complain.setStatus(status);
			complain.setDate(date);
			complain.setAnswer(answer);

			complainList.add(complain);
			answer="";
		}
		//System.out.println(complainList);

	}
	
	
	
	public String action() {
	    // ...
	    FacesContext context = FacesContext.getCurrentInstance();
	    return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}
	public void refresh() {
		postConstruct();
		getNbComplainByStatus(statusNotRead);
		action();
		
	}
	public String login(){

	    

	    return "login?faces-redirect=true";
	}
	
	public void changeStatus (int id) {
		Complain updateComplain=getComplainById(id);
		StatusTypeOfComplain newstatus; 
		newstatus=StatusTypeOfComplain.In_Progress;
		if(getComplainById(id).getStatus()==StatusTypeOfComplain.Not_Read) {
			updateComplain.setStatus(newstatus);
			service.UpdateComplain(updateComplain);
		}
	}
	public void vu(int id) {
		changeStatus(id);
		getNbComplainByStatus(statusNotRead);
		refresh();
	}
	public void reply(int id,String answer) {
		AnswerComplain(id, answer);
		answer="";
		refresh();
	}
	
	
	public void AddComplain()
	{
		Complain test = new Complain();
		User user1 = new User();

		user1.setId(43);
		user1.setNom("DRIDI");
		user1.setPrenom("SALEH");
		user1.setValid(true);
//		
//		
//		//il faut changer
		test.setUser(user1);
		test.setAnswer("");
		test.setDescription(description);
		test.setSubject(subject);
		test.setStatus(StatusTypeOfComplain.Not_Read);
		System.out.println(test.toString());
	
		service.AddComplain(test);
		// System.out.println("a:" + a);
		clear();
		
	}
	
	public void clear() {
		subject = "";
		description = "";
		
	}
	public void changeVuCustomer(int id) {
		Complain c =service.getComplainById(id);
		c.setCustomer_vu(true);
		service.UpdateComplain(c);
		refresh();

	}
	public int testVu(int id) {
		if(service.getComplainById(id).getStatus()==StatusTypeOfComplain.Answered && service.getComplainById(id).getCustomer_vu()==false) {
			return 1;
		}
		return 0;
	}
	public void selectionComplain(Complain c) {
		setSelectedComplain1(c);
		NomPrenom=selectedComplain1.getUser().getNom()+" "+selectedComplain1.getUser().getPrenom();
		
		refresh();
	}
	public void DeleteComplainById(int idComplain)
	{
		service.DeleteComplainById(idComplain);
		postConstruct();
	}
	public void DeleteComplainByIdUser(int idUser)
	{
		service.DeleteComplainByIdUser(idUser);
	}
	public void DeleteComplainByStatus(StatusTypeOfComplain status)
	{
		service.DeleteComplainByStatus(status);
	}
	public void DeleteComplainByDate(String date)
	{
		service.DeleteComplainByDate(date);
	}
	public void UpdateComplain(int idC,Complain c) 
	{
		Complain updateComplain = service.findById(idC);

        updateComplain.setSubject(c.getSubject());
        updateComplain.setDescription(c.getDescription());
        updateComplain.setStatus(c.getStatus());
        updateComplain.setDate(c.getDate());
        updateComplain.setUser(c.getUser());
        service.UpdateComplain(updateComplain);
	}
	public Complain getComplainById(int id)
	{
		return service.getComplainById(id);
	}
	public List<Complain> getAllComplain()
	{
		return service.getAllComplain();
	}
	public 	List<Complain> getAllComplainByIdCustomer(int idCustomer)
	{
		return service.getAllComplainByIdCustomer(idCustomer);
	}
	public 	List<Complain> getComplainByStatus(StatusTypeOfComplain status)
	{
		return service.getComplainByStatus(status);
	}
	public 	List<Complain> getComplainByDate(String date) throws ParseException
	{
		return service.getComplainByDate(date);
	}
	public 	Long getNbComplain()
	{
		return service.getNbComplain();
	}
	public 	Long getNbComplainByCustomer(int idCustomer)
	{
		return service.getNbComplainByCustomer(idCustomer);
	}
	public 	Long getNbComplainByStatus(StatusTypeOfComplain status)
	{
		return service.getNbComplainByStatus(status);
	}
	public 	Long getNbComplainByDate(String date) throws ParseException
	{
		return service.getNbComplainByDate(date);
	}
	public 	Complain findById(int id)
	{
		return service.findById(id);
	}
	public void AnswerComplain(int id,String answer) 
	{
		service.AnswerComplain(id, answer);
	}
	public 	Long getNbComplainVuByCustomer()
	{
		return service.getNbComplainVuByCustomer();
	}
 
	public void increment()
	{
		
		if(number==100)
		{
			
		}else
		{
			number++;
		}
	}






	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusTypeOfComplain getStatus() {
		return status;
	}
	public void setStatus(StatusTypeOfComplain status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Complain getSelectedComplain() {
		return selectedComplain;
	}
	public void setSelectedComplain(Complain selectedComplain) {
		this.selectedComplain = selectedComplain;
	}
	public List<Complain> getComplainList() {
		return complainList;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}



	public StatusTypeOfComplain getStatusNotRead() {
		return statusNotRead;
	}



	public void setStatusNotRead(StatusTypeOfComplain statusNotRead) {
		this.statusNotRead = statusNotRead;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	public StatusTypeOfComplain getStatusAnswered() {
		return statusAnswered;
	}



	public void setStatusAnswered(StatusTypeOfComplain statusAnswered) {
		this.statusAnswered = statusAnswered;
	}



	public Boolean getCustomer_vu() {
		return customer_vu;
	}



	public void setCustomer_vu(Boolean customer_vu) {
		this.customer_vu = customer_vu;
	}



	public String getCh() {
		return ch;
	}



	public void setCh(String ch) {
		this.ch = ch;
	}



	public void setComplainList(List<Complain> complainList) {
		this.complainList = complainList;
	}



	public Complain getSelectedComplain1() {
		return selectedComplain1;
	}



	public void setSelectedComplain1(Complain selectedComplain1) {
		this.selectedComplain1 = selectedComplain1;
	}
	
	
	public StatusTypeOfComplain getStatusInProgress() {
		return statusInProgress;
	}



	public void setStatusInProgress(StatusTypeOfComplain statusInProgress) {
		this.statusInProgress = statusInProgress;
	}



	
	
	

}
