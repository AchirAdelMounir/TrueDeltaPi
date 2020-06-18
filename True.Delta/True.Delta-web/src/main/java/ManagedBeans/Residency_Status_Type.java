package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "residency") 
@ApplicationScoped
public class Residency_Status_Type implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.Residency_Status_Type[] getResidency_Status_Type() 
	{ return Enumerations.Residency_Status_Type.values(); }

}
