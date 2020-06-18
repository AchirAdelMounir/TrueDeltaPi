package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "devise") 
@ApplicationScoped
public class TypeDevise implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.TypeDevise[] getTypeDevise() 
	{ return Enumerations.TypeDevise.values(); }

}
