package Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;

import Entities.AssetManager;



@Remote
public interface AssetManagerServiceInterface {

	
	 public Map<String, Float> Change() throws IOException ;


  public Float Conversion(String a, String b, Float c) throws IOException;


List<String> devise() throws IOException;

List<String> achat() throws IOException;

	

}