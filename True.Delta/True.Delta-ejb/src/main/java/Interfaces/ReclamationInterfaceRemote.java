package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.Reclamations;

@Remote
public interface ReclamationInterfaceRemote {
	public int addRec(Reclamations reclamation);
	public void removeRec(int idrec);
	public void updateRec(Reclamations reclamation);
	public Reclamations findRecById(int idrec);
	public List<Reclamations> findAllRecs();
}
