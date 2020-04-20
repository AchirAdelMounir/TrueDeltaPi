package ManagedBeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import Entities.Complain;
import Entities.User;
import Enumerations.StatusTypeOfComplain;
import Interfaces.ComplainIServices;



@ManagedBean(name = "ComplainBean")
@ApplicationScoped

public class ComplainBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	ComplainIServices service;
	
	private int id; 
	private String subject;
	private String description;
    Enumerations.StatusTypeOfComplain status;
	private Date date ;
	private User user;
	
	public void AddComplain(Complain c)
	{
		service.AddComplain(c);
	}
	public void DeleteComplainById(int idComplain)
	{
		service.DeleteComplainById(idComplain);
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
	public Enumerations.StatusTypeOfComplain getStatus() {
		return status;
	}
	public void setStatus(Enumerations.StatusTypeOfComplain status) {
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
	
	

}
