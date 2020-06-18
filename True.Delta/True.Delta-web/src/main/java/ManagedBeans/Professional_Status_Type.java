package ManagedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "professional") 
@ApplicationScoped
public class Professional_Status_Type {
	private static final long serialVersionUID = 1L;
	public Enumerations.Professional_Status_Type[] getProfessional_Status_Type() 
	{ return Enumerations.Professional_Status_Type.values(); }

}
