package Interfaces;

import java.util.List;

import Entities.User;

public interface UserIServices {
	int AddUser(User U);
	void DeleteUser(int Id);
	void EditUser(User u);
	User DisplayUser(int Id);
	List<User> DisplayUsers();
	Boolean authentication(int id_user, String login, String password);
	String happyBirthday(int id_user);
	

}
