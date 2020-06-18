package ManagedBeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "financial") 
@ApplicationScoped

public class FinancialAsset implements Serializable  {
	private static final long serialVersionUID = 1L;
	public Enumerations.FinancialAsset[] getFinancialAsset() 
	{ return Enumerations.FinancialAsset.values(); }

}
