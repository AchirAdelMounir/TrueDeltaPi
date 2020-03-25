package Interfaces;

import java.util.List;

import javax.ejb.Remote;
import Entities.User;

@Remote
public interface UserServiceRemote {
	public int AddUser (User U);
	public void DeleteUser(int Id);
	public User DisplayUser(int Id);
	public List<User> DisplayUsers();
	public void EditUser(User u);
	public int EstimatedScore (User u);
	public void validateProfile(User u);
}
