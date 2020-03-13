package Interfaces;
import javax.ejb.Remote;

import Entities.Bond;
@Remote

public interface BondsServicesInterface {
	public int AddBonds(Bond B);
	public void DeleteBonds(int IdB);
	public void DisplayBond(int IdB);
	public void DisplayBonds();
	public void EditBond(int IdB);

}
