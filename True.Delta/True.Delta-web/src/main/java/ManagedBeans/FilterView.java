package ManagedBeans;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Entities.Contract;
import Services.ContractService;


@ManagedBean 
@ViewScoped
public class FilterView  {
	private List<Contract> employeeList;
    private List<Contract> filteredEmployeeList;

   
    public List<Contract> getEmployeeList() {
        return employeeList;
    }

    public List<Contract> getFilteredEmployeeList() {
        return filteredEmployeeList;
    }

    public void setFilteredEmployeeList(List<Contract> filteredEmployeeList) {
        this.filteredEmployeeList = filteredEmployeeList;
    }

  
}
