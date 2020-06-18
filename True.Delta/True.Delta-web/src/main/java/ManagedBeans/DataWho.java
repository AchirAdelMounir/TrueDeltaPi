package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import Enumerations.Who;

@ManagedBean(name="dataWho")
@ApplicationScoped
public class DataWho implements Serializable {
	private static final long serialVersionUID= 1L;
	public Who[] getWhos() { return Who.values(); }
	
	
}
