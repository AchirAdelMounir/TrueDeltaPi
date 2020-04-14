package Interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.User;

@Local

public interface UserServiceLocal {
	public int AddUser (User U);
	public void DeleteUser(int Id);
	public User DisplayUser(int Id);
	public List<User> DisplayUsers();
	public void EditUser(User u);
	public int EstimatedScore (User u);
	public void validateProfile(User u);
	
}
