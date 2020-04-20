package Interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.User;

@Local
public interface UserIServices {
	int AddUser(User U);
	void DeleteUser(int Id);
	int  EditUser(User user, int id_user);
	User DisplayUser(int Id);
	List<User> DisplayUsers();
	User authentication(String login, String password);
	String happyBirthday(int id_user);
	

}
