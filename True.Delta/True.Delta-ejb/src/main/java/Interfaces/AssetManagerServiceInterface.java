package Interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;

import Entities.AssetManager;
import Entities.Portfolio;


@Remote
public interface AssetManagerServiceInterface {

	public int addAssetManager(AssetManager AssetManager);

	public int updateAssetManager(AssetManager AssetManager);

	

	public void deleteAssetManager(int id);

	AssetManager getAssetManagerById(int id);

	List<AssetManager> getall();

	public int acceptAssetManager(AssetManager AssetManager);

	public List<AssetManager> getallConfirmation();

	 public Map<String, Float> Change() throws IOException ;


  public Float Conversion(String a, String b, Float c) throws IOException;

  public List<AssetManager> GetHigh();

 public List<AssetManager> GetMedium();

  public List<AssetManager> GetLow();

List<String> devise() throws IOException;

List<String> achat() throws IOException;

AssetManager getAssetManagerByNom(String nom);

List<AssetManager> CalculRating();
public  Map <String ,List<String>> Commentaire( );

List<AssetManager> CalculReturns();



public List<AssetManager> Classification();

List<String> getall1();

List<String> getall0();

List<Portfolio> getPortfolioByAM(AssetManager am);

float pourcentageRatingSup(AssetManager am);

long differenceDate(AssetManager am);

float nbPortfolios(AssetManager am);

List<String> GetCommentairesByAm(AssetManager am);

List<AssetManager> BanAM();




	

	

}
