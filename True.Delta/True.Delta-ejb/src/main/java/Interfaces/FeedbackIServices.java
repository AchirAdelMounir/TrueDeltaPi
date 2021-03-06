package Interfaces;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import Entities.Feedback;
import Enumerations.LevelRating;
import Enumerations.Who;



@Remote
public interface FeedbackIServices {
	public int AddFeedback(Feedback f);
	void DeleteFeddbackById(int idFeddback);
	int DeleteFeedbackByIdUser(int idUser);
	int DeleteFeedbackByDate(String date);
	void UpdateFeedback(Feedback f);
	Feedback GetFeedbackById(int idFeedback);
	List<Feedback> getAllFeedback();
	List<Feedback> getAllFeedbackByIdUser(int idUser);
	//Feedback getFeedbackById_JPQL(int idFeedback);
	Feedback findById(int id);
	List<Feedback> getFeedbackByDate(String date) throws ParseException;
	List<Feedback> getAllFeedbackByRating(Enumerations.LevelRating rating);
	Long getNbFeedback();
	Long getNbFeedbackByIdUser(int idUser);
	Long getNbFeedbackByDate(String date) throws ParseException;
	Long getNbFeedbackByRating(LevelRating rating,Who who);
	double getAvgOfRating(LevelRating rating,Who who);
	double nbOfStarsById(int idFeedback);
	double noteGlobal(Who who);
	Long getNbUser();
	Long getNbActivityByUser(int idUser);
	Map<Integer, Long>  best3ActiveUser();
	//originale//Map<Integer,Long>  best3ActiveUser();
	//Map<String, Long> best3ActiveUser();
	//Map<Integer,Long> sortByValue( Map<Integer,Long> h);
	//String affichageOfBest3(Map<Integer, Long> h);
	List <String> affichageOfBest3();
	Boolean validerFeedback(String Str);
	public String wordDanger(String Str);
	Long getNbFeedbackByVuAdmin();
	Long getNbFeedbackByWho(Who who);
	List<Feedback> getAllFeedbackByWho(Who who); 
	
	
	
	
	
	

	
	

}