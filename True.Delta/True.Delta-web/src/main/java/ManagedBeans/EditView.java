package ManagedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Entities.Contract;
import Services.ContractService;

@Named("dtEditView")
@ViewScoped
public class EditView implements Serializable {

    
   private List<Contract> cars1;
   private List<Contract> cars2;
        
   @Inject
   private ContractService service;
    
   @PostConstruct
   public void init() {
       cars1 = service.ListContract();
       cars2 = service.ListContract();
   }

   public List<Contract> getCars1() {
       return cars1;
   }

   public List<Contract> getCars2() {
       return cars2;
   }
}
