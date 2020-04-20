package ManagedBeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import Entities.Feedback;
import Entities.User;
import Enumerations.LevelRating;
import Enumerations.Who;
import Interfaces.FeedbackIServices;



@ManagedBean(name = "FeedbackBean")
@ApplicationScoped

public class FeedbackBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	FeedbackIServices service;
	
	private int id;
	private User user;
	private Who who;
	private LevelRating rating;
	
	
	public void AddFeedback(Feedback f)
	{
		service.AddFeedback(f);
	}
	public void DeleteFeddbackById(int idFeddback)
	{
		service.DeleteFeddbackById(idFeddback);
	}
	public void DeleteFeedbackByIdUser(int idUser)
	{
		service.DeleteFeedbackByIdUser(idUser);
	}
	public void DeleteFeedbackByDate(String date)
	{
		service.DeleteFeedbackByDate(date);
	}
	public void UpdateFeedback(int idF,Feedback f)
	{
		Feedback updateFeedback = service.findById(idF);

		
		updateFeedback.setRating(f.getRating());
		updateFeedback.setDate(f.getDate());
		updateFeedback.setUser(f.getUser());
        service.UpdateFeedback(updateFeedback);
	}
	public 	Feedback GetFeedbackById(int idFeedback)
	{
		return service.GetFeedbackById(idFeedback);
	}
	public 	List<Feedback> getAllFeedback()
	{
		return service.getAllFeedback();
	}
	public 	List<Feedback> getAllFeedbackByIdUser(int idUser)
	{
		return service.getAllFeedbackByIdUser(idUser);
	}
	public 	Feedback findById(int id)
	{
		return service.findById(id);
	}
	public 	List<Feedback> getFeedbackByDate(String date) throws ParseException
	{
		return service.getFeedbackByDate(date);
	}
	public 	List<Feedback> getAllFeedbackByRating(Enumerations.LevelRating rating)
	{
		return service.getAllFeedbackByRating(rating);
	}
	public 	Long getNbFeedback()
	{
		return service.getNbFeedback();
	}
	public 	Long getNbFeedbackByIdUser(int idUser)
	{
		return service.getNbFeedbackByIdUser(idUser);
	}
	public 	Long getNbFeedbackByDate(String date) throws ParseException
	{
		return service.getNbFeedbackByDate(date);
	}
	public 	Long getNbFeedbackByRating(LevelRating rating)
	{
		return service.getNbFeedbackByRating(rating);
	}
	public 	double getAvgOfRating(LevelRating rating)
	{
		return service.getAvgOfRating(rating);
	}
	public 	double nbOfStarsById(int idFeedback)
	{
		return service.nbOfStarsById(idFeedback);
	}
	public 	double noteGlobal()
	{
		return service.noteGlobal();
	}
	public 	Long getNbUser()
	{
		return service.getNbUser();
	}
	public 	Long getNbActivityByUser(int idUser)
	{
		return service.getNbActivityByUser(idUser);
	}
	public 	Map<Integer, Long>  best3ActiveUser()
	{
		return service.best3ActiveUser();
	}
	public 	List <String> affichageOfBest3()
	{
		return service.affichageOfBest3();
	}
	public 	Boolean validerFeedback(String Str)
	{
		return service.validerFeedback(Str);
	}


















	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Who getWho() {
		return who;
	}
	public void setWho(Who who) {
		this.who = who;
	}
	public LevelRating getRating() {
		return rating;
	}
	public void setRating(LevelRating rating) {
		this.rating = rating;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
