package ManagedBeans;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Interfaces.ContractServiceLocal;
import Services.ContractService;



@ManagedBean(name="charts")
@ViewScoped
public class ChartsBean {

	/*private List<Object[]> nbsent;
	private List<Object[]> nbinprogress;
	private List<Object[]> nbtreated;*/
	private Long nbfree;
	private Long nbCondition;
	private Long nbAccept;
	private Long nbRefus;
	private Long nbInClass;
	private Long nbApproved0;
	private Long nbApproved1;

	@EJB
	ContractServiceLocal contractService;


@PostConstruct
public void init()
{
	
	//nbtreated=complainService.getNbtreated();

	//nbinprogress=complainService.getNbInprogress();

	//nbsent=complainService.getNbsent();
	nbfree=contractService.NbContractTypeFree();
   nbCondition = contractService.NbContractTypeWithCondition();	
nbAccept=contractService.NbContractAccept();
 nbRefus=contractService.NbContractRefuse();
 nbInClass=contractService.NbContractInclass();
 nbApproved0=contractService.NbContractIsApproved0();
 nbApproved1=contractService.NbContractIsApproved1();
}


public Long getNbfree() {
	return nbfree;
}


public void setNbfree(Long nbfree) {
	this.nbfree = nbfree;
}


public Long getNbCondition() {
	return nbCondition;
}


public void setNbCondition(Long nbCondition) {
	this.nbCondition = nbCondition;
}


public Long getNbAccept() {
	return nbAccept;
}


public void setNbAccept(Long nbAccept) {
	this.nbAccept = nbAccept;
}


public Long getNbRefus() {
	return nbRefus;
}


public void setNbRefus(Long nbRefus) {
	this.nbRefus = nbRefus;
}


public Long getNbInClass() {
	return nbInClass;
}


public void setNbInClass(Long nbInClass) {
	this.nbInClass = nbInClass;
}


public Long getNbApproved0() {
	return nbApproved0;
}


public void setNbApproved0(Long nbApproved0) {
	this.nbApproved0 = nbApproved0;
}


public Long getNbApproved1() {
	return nbApproved1;
}


public void setNbApproved1(Long nbApproved1) {
	this.nbApproved1 = nbApproved1;
}







}
