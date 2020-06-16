package Interfaces;

import java.util.List;

import javax.ejb.Local;

import Entities.Reclamations;



@Local
public interface ReclamationInterfaceLocal {
	public int addRec(Reclamations reclamation);
	public void removeRec(int idrec);
	public void updateRec(Reclamations reclamation);
	public Reclamations findRecById(int idrec);
	public List<Reclamations> findAllRecs();
}
