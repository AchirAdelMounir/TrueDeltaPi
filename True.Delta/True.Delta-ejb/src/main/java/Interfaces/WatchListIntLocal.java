package Interfaces;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import Entities.Company;
import Entities.Security;
import Entities.Stock;
import Entities.User;
import Entities.Watchlist;
@Local

public interface WatchListIntLocal {
	public void AddWatchList(Watchlist C,Company C1);
	public void DeleteWatchList(int id);
	public Watchlist DisplayWatchlist(int id);
	public List<Watchlist> DisplayWatchlistByidUser(User idUser);
	public Company EditWatchlist(int id,Watchlist C);
	
	
	

}
