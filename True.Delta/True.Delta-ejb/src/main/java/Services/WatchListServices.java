package Services;

import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import Entities.Company;
import Entities.Security;
import Entities.Stock;
import Entities.User;
import Entities.Watchlist;
import Interfaces.CompaniesServicesInterfaceLocal;
import Interfaces.CompaniesServicesInterfaceRemote;
import Interfaces.WatchListIntLocal;
import Interfaces.WatchListIntRemote;

@Stateless
@LocalBean

public class WatchListServices implements WatchListIntRemote, WatchListIntLocal {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public void AddWatchList(Watchlist C, Company C1) {
		C.getCompanies().add(C1);

		em.merge(C);
		C1.getW().add(C);

	}

	@Override
	public void DeleteWatchList(int id) {
		em.remove(em.find(Watchlist.class, id));

	}

	@Override
	public Watchlist DisplayWatchlist(int id) {
		return (em.find(Watchlist.class, id));

	}

	@Override
	public List<Watchlist> DisplayWatchlistByidUser(User idUser) {

		TypedQuery<Watchlist> query = em.createQuery("SELECT e FROM Watchlist e WHERE e.u=:idUser ", Watchlist.class);
		query.setParameter("idUser", idUser);

		List<Watchlist> user = null;
		try {
			user = query.getResultList();
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		return user;
	}

	@Override
	public Company EditWatchlist(int id, Watchlist C) {
		// TODO Auto-generated method stub
		return null;
	}

}
