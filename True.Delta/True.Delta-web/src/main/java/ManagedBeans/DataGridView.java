package ManagedBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Entities.Contract;
import Services.ContractService;

@ManagedBean
@SessionScoped 
public class DataGridView implements Serializable {
     
	
	


	 
  
    private List<Contract> cars;
     
    private Contract selectedCar;
    
    
     
    @EJB
    private ContractService service;
     
    @PostConstruct
    public void init()   {


    	

    }
 

    public List<Contract> getCars() {
       List<Contract> lf= new ArrayList<>();
       Date current = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Contract p : service.ListContract()) {


            if(p.getEndDate().compareTo(current)<0)
            {
    
            	service.deleteContract1(p.getIDContract());
            }
            else {
            	lf.add(p);
            }
        }
  cars = lf;

    	
	
        return cars;
    }
 
    public void setService(ContractService service) {
        this.service = service;
    }
 
    public Contract getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Contract selectedCar) {
        this.selectedCar = selectedCar;
    }
}
