package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "usertype") 
@ApplicationScoped
public class User_Type implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.User_Type[] getUser_Type() 
	{ return Enumerations.User_Type.values(); }

}
