package Interfaces;
import javax.ejb.Remote;

import Entities.Stock;
@Remote

public interface StocksServicesInterface {
	public int AddStock(Stock S);
	public void DeleteStock(int IdStock);
	public void DisplayStock(int IdStock);
	public void DisplayStocks();
	public void EditStock(int IdStock);
	

}
