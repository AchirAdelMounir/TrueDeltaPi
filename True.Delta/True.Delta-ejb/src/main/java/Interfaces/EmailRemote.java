package Interfaces;

import javax.ejb.Remote;

@Remote
public interface EmailRemote {

	boolean isValidEmailAddress(String email);

	void sendEmail(String toEmail, String subject, String body);

}
