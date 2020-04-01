package Interfaces;

import java.util.List;
import javax.ejb.Local;
import Entities.Portfolio;
import Entities.User;

@Local
public interface PortfolioServiceLocal {
	public int AddPortfolio (Portfolio P);
	public void DeletePortfolio(int IdPortfolio);
	public Portfolio DisplayPortfolio(int IdPortfolio);
	public List<Portfolio> DisplayPortfolios();
	public void EditPortfolio(Portfolio p);
	public void AffectedPortfolio(int idPortfolio,int idUser);
	public float getRisk(int idUser);
	public void moneyBasdPortfoio(float money, int idUser);
}
