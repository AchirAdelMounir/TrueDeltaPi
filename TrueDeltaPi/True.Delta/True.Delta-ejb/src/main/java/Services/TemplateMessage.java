package Services;

public class TemplateMessage {
	public String template ="";
	
	public TemplateMessage(String message) {
		super();
		this.template= "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
				"<head>\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"<title>Demystifying Email Design</title>\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" + 
				"</head>\n" + 
				"<body style=\"margin: 0; padding: 0;\">\n" + 
				"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> \n" + 
				"        <tr>\n" + 
				"            <td style=\"padding: 10px 0 30px 0;\">\n" + 
				"                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\">\n" + 
				"                    <tr>\n" + 
				"                        <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n" + 
				"                            <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/h1.gif\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\" />\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    <tr>\n" + 
				"                        <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" + 
				"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" + 
				"                                <tr>\n" + 
				"                                    <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n" + 
				"                                        <b>"+message+"</b>\n" + 
				"                                    </td>\n" + 
				"                                </tr>\n" +                 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                    <tr>\n" + 
				"                        <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\n" + 
				"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" + 
				"                                <tr>\n" + 
				"                                    <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\" width=\"75%\">\n" + 
				"                                        &reg;  PIDEV INFINI, Esprit 2020<br/>\n" + 
				"                                    </td>\n" + 
				"                                    <td align=\"right\" width=\"25%\">\n" + 
				"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" + 
				"                                            <tr>\n" + 
				"                                                <td style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;\">\n" + 
				"                                                    <a href=\"http://www.twitter.com/\" style=\"color: #ffffff;\">\n" + 
				"                                                        <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/tw.gif\" alt=\"Twitter\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n" + 
				"                                                    </a>\n" + 
				"                                                </td>\n" + 
				"                                                <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n" + 
				"                                                <td style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;\">\n" + 
				"                                                    <a href=\"http://www.twitter.com/\" style=\"color: #ffffff;\">\n" + 
				"                                                        <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/fb.gif\" alt=\"Facebook\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n" + 
				"                                                    </a>\n" + 
				"                                                </td>\n" + 
				"                                            </tr>\n" + 
				"                                        </table>\n" + 
				"                                    </td>\n" + 
				"                                </tr>\n" + 
				"                            </table>\n" + 
				"                        </td>\n" + 
				"                    </tr>\n" + 
				"                </table>\n" + 
				"            </td>\n" + 
				"        </tr>\n" + 
				"    </table>\n" + 
				"</body>\n" + 
				"</html>\n" + 
				"\n" + 
				"";
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	

	
}
