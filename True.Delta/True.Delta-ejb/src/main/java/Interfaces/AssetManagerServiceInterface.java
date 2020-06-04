package Interfaces;

import java.util.List;

import javax.ejb.Remote;

import Entities.AssetManager;

@Remote
public interface AssetManagerServiceInterface {

	public void addAssetManager(AssetManager AssetManager);

	public void updateAssetManager(AssetManager AssetManager);

	

	public void deleteAssetManager(int id);

	AssetManager getAssetManagerById(int id);

	List<AssetManager> getall();

	

}
