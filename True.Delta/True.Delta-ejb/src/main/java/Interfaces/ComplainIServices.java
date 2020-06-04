package Interfaces;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import Entities.Complain;
import Enumerations.StatusTypeOfComplain;


@Remote
public interface ComplainIServices {

	int AddComplain(Complain c);

	void DeleteComplainById(int idComplain);
	
	int DeleteComplainByIdUser(int idUser);
	
	int DeleteComplainByStatus(StatusTypeOfComplain status);
	
	int DeleteComplainByDate(String date) ;

	void UpdateComplain(Complain c);
	
	Complain getComplainById(int id);

	List<Complain> getAllComplain();

	List<Complain> getAllComplainByIdCustomer(int idCustomer);
	
	List<Complain> getComplainByStatus(Enumerations.StatusTypeOfComplain status);
	
	List<Complain> getComplainByDate(String date) throws ParseException;
	
	Long getNbComplain();

	Long getNbComplainByCustomer(int idCustomer);

	Long getNbComplainByStatus(Enumerations.StatusTypeOfComplain status);
	
	Long getNbComplainByDate(String date) throws ParseException;

	Complain findById(int id);

	void AnswerComplain(int id,String answer);

	Long getNbComplainVuByCustomer();



	

	

	



	

	



}
