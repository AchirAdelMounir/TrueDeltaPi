package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "level") 
@ApplicationScoped
public class LevelOfRisk implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.LevelOfRisk[] getLevelOfRisk() 
	{ return Enumerations.LevelOfRisk.values(); }

}
