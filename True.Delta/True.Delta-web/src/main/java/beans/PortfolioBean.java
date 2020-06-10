package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Entities.AssetManager;
import Entities.Portfolio;
import Entities.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.chart.PieChartModel;
import Entities.Ratings;
import Entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import Interfaces.AssetManagerServiceInterface;
import Interfaces.PortfolioServiceInterface;
import Interfaces.RatingServiceInterface;
import Interfaces.VisitorServiceInterface;

@ManagedBean(name = "PortfolioBean")
@SessionScoped
public class PortfolioBean {

@EJB 	
PortfolioServiceInterface es ;

@EJB 	
AssetManagerServiceInterface as ;
private List<Portfolio> portfolios ;
private double Returns;
private int TypePortfolio;
private int Volatility ;
private int id;



@ManagedProperty(value = "#{AssetManagerBean}")
private AssetManagerBean amb;

public AssetManagerBean getAmb() {
	return amb;
}
public void setAmb(AssetManagerBean amb) {
	this.amb = amb;
}
public PortfolioBean() {
	super();
	// TODO Auto-generated constructor stub
}
public double getReturns() {
	return Returns;
}
public void setReturns(double returns) {
	Returns = returns;
}
public int getTypePortfolio() {
	return TypePortfolio;
}
public void setTypePortfolio(int typePortfolio) {
	TypePortfolio = typePortfolio;
}
public int getVolatility() {
	return Volatility;
}
public void setVolatility(int volatility) {
	Volatility = volatility;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}




public String add() {
	Portfolio Vh = new Portfolio();
	
	AssetManager am= as.getAssetManagerById(this.getId());
	
	Vh.setAssetmanager(am);
    Vh.setReturns(this.getReturns());
	
	Vh.setTypePortfolio(this.getTypePortfolio());
	
	
	Vh.setVolatility(this.getVolatility());
 
	

 
 	es.addPortfolio(Vh);

	
	
	

	return "/Template/ListAM?faces-redirect=true";
	
}



public String getportfoliobyAM() {
	
	portfolios =es.getPortfolioByAM(amb.getId()) ;
	return ("/Template/ShowDetailsPortfolio?faces-redirect=true");

	

}
public List<Portfolio> getPortfolios() {
	return portfolios;
}
public void setPortfolios(List<Portfolio> portfolios) {
	this.portfolios = portfolios;
}

}
