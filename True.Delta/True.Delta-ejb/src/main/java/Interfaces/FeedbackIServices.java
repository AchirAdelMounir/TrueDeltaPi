package Interfaces;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import Entities.Feedback;
import Enumerations.LevelRating;



@Local
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
	Long getNbFeedbackByRating(LevelRating rating);
	double getAvgOfRating(LevelRating rating);
	double nbOfStarsById(int idFeedback);
	double noteGlobal();
	Long getNbUser();
	Long getNbActivityByUser(int idUser);
	Map<Integer, Long>  best3ActiveUser();
	//originale//Map<Integer,Long>  best3ActiveUser();
	//Map<String, Long> best3ActiveUser();
	//Map<Integer,Long> sortByValue( Map<Integer,Long> h);
	//String affichageOfBest3(Map<Integer, Long> h);
	List <String> affichageOfBest3();
	Boolean validerFeedback(String Str);
	 
	
	
	
	
	
	

	
	

}