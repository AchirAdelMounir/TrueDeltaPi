package Interfaces;

import Entities.User;

public interface UserServiceInterface {

	User getUserByEmailAndPassword(String email, String password);

}
