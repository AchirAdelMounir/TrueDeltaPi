package Interfaces;

import javax.ejb.Remote;

@Remote
public interface FluxServiceRemote {
	public void AffectedStock(int idPortfolio,int idStock,float poids,int nb);
}
