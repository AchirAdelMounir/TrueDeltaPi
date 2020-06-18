package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;



@ManagedBean(name = "contract") 
@ApplicationScoped

public class ContractType implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.ContractType[] getContractType() 
	{ return Enumerations.ContractType.values(); }

}
