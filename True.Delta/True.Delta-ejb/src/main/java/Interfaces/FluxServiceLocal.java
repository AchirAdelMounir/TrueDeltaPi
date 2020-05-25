package Interfaces;

import javax.ejb.Local;

@Local
public interface FluxServiceLocal {
	public void AffectedStock(int idPortfolio,int idStock,float poids,int nb);

}
