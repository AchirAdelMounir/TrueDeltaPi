package Interfaces;

import java.util.List;
import javax.ejb.Remote;
import Entities.Portfolio;
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
	public float moneyBasdPortfoio(float money, Portfolio p);
	public void volatilityBasedPortfolio(float maxVol, Portfolio p);
}
