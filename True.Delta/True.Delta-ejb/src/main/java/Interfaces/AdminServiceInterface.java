package Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;

import Entities.Administrator;
import Entities.AssetManager;
import Entities.Visitor;


@Remote
public interface AdminServiceInterface {

	public int addAdmin (Administrator admin) ;
		
	

}
