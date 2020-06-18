package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "civil") 
@ApplicationScoped
public class Civil_Status implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.Civil_Status[] getCivil_Status() 
	{ return Enumerations.Civil_Status.values(); }

}
