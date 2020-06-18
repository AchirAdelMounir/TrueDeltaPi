package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "state") 
@ApplicationScoped
public class StatContract implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.Etat_Contract[] getEtat_Contracts()
	{ return Enumerations.Etat_Contract.values(); }

}
