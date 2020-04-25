package Interfaces;

import java.util.List;
import javax.ejb.Remote;
import Entities.Portfolio;
import Entities.Security;
import Entities.Stock;
import Entities.User;

@Remote
public interface PortfolioServiceRemote {
	public int AddPortfolio (Portfolio P);
	public void DeletePortfolio(int IdPortfolio);
	public Portfolio DisplayPortfolio(int IdPortfolio);
	public List<Portfolio> DisplayPortfolios();
	public void EditPortfolio(Portfolio p);
	public void AffectedPortfolio(int idPortfolio,int idUser);
	public float getRisk(int idUser);
	public List<Stock> Type1Portfolio(float money,List<Stock> ls);
	public void volatilityBasedPortfolio(float maxVol, Portfolio p);
	public Portfolio OptimalPortfolio ();
	public List<Stock> Type2VolPortfolio(float vol,List<Stock> ls);
	public List<Stock> Type2ReturnsPortfolio(float returns,List<Stock> ls);

}
