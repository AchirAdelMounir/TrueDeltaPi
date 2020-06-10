package Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;

import Entities.AssetManager;
import Entities.Visitor;


@Remote
public interface VisitorServiceInterface {

	public int addVisitor(Visitor visitor) ;

	List<Visitor> getall();

	void deleteVisitor(int id);
		
	

}
