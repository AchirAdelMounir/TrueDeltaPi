package ManagedBeans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.persistence.Embedded;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.primefaces.event.ItemSelectEvent;

import Entities.Bond;
import Entities.Company;
import Entities.Flux;
import Entities.Indicators;
import Entities.Security;
import Entities.Stock;
import Entities.User;
import Entities.Watchlist;
import Services.CompaniesServices;
import Services.SecuritesServices;
import Services.UserService;
import Services.WatchListServices;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@ManagedBean(name = "SecurityBean")
@SessionScoped
public class SecuritiyBean {
	@EJB
	SecuritesServices s;

	@EJB
	CompaniesServices companyS;

	@EJB
	UserService uS;
	@EJB
	WatchListServices wS;
	@ManagedProperty(value = "#{UserBean}")
	UserBean us;
	private int Id;
	private Map<String, Object> sessionMap;
	private String Type;
	private double Price;
	private String SymStock;
	@Embedded
	private Stock S;
	@Embedded
	private Bond B;
	private Company Company;
	private Set<Flux> F;
	private List<Stock> Ls = null;
	private Stock Yesterdaystock;
	private double PriceDifference;
	private double PriceDifferencePercentage;
	private String Sym;
	private String frequency;
	private Date d1, d2;
	private double volatility, sigma, coef;
	private List<Watchlist> w;
	private User u;
	private boolean inWishlist;
	private List<Company> Lc;

	private double livePrice;
	private String change;
	private String changePercentage;
	private boolean changeIsPositive;
	private List<Indicators> i;

	private LineChartModel lineModel;

	public void SecurityBean() {
		createLineModel();
	}

	public void AddSecurity(Security S) {
		s.AddSecurity(S);
	}

	public List<Security> DisplaySecurities() {
		return s.DisplaySecurities();
	}

	public List<Stock> DisplayStockHistory(String Sym, String frequency, String Period1, String Period2) {
		Ls = s.StocksDownloader(Sym, frequency, Period1, Period2);
		return Ls;
	}

	public double VolatilityCalculated(String Sym, String Period1, String Period2) {
		return s.VolatilityCalculator(Sym, Period1, Period2);
	}

	public double CoefOfDeviation(String Sym, String Period1, String Period2) {
		return s.CoefOfDeviation(Sym, Period1, Period2);
	}

	public double StandardDev(String Sym, String Period1, String Period2) {
		return s.StandardDev(Sym, Period1, Period2);
	}

	public List<Security> LastByInput(String Input, int TopN) {
		return s.GetLastByInput(Input, TopN);
	}

	public List<Security> TopByInput(String Input, int TopN) {
		return s.GetTopByInput(Input, TopN);

	}

	public List<Security> FindSecurity(String SearchField, String operator, Object o) {
		return s.SearchByInput(SearchField, operator, o);
	}

	public void GetPriceInstantanly() {

		System.out.println(SymStock);
		Price = s.getStockPriceInstantly(SymStock);

	}

	public List<Stock> DisplayInfosStocks() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		List<Stock> ns = new ArrayList<Stock>();
		Ls = s.StocksDownloader(SymStock, "1d", yes, tod);

		return Ls;
	}

	public String Redirect() {

		u = us.getU();

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		SymStock = params.get("Symbol");
		System.out.println(SymStock);

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		double price = s.getStockPriceInstantly(SymStock);

		List<Stock> Ls1 = s.StocksDownloader(SymStock, "1d", yes, tod);
		Yesterdaystock = Ls1.get(0);

		PriceDifference = price - Yesterdaystock.getAdj_Close();
		PriceDifferencePercentage = (PriceDifference / price) * 100;
		System.out.println("selem");
		if (u != null) {
			verifyInwatchlist(companyS.DisplayCompany(SymStock), uS.DisplayUser(u.getId()));
		}
		createLineModel();

		return ("/Template/Views/SecuritiesViews/StockDisplay?faces-redirect=true");
	}

	public String DisplayWeeklyHistory() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		System.out.println(SymStock);
		Ls = s.StocksDownloader(SymStock, "1d", yes, tod);
		return ("/Template/Views/SecuritiesViews/StockHistory?faces-redirect=true");

	}

	public String DisplayWeeklyHistory(String sym) {
		SymStock = sym;
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		System.out.println(SymStock);
		Ls = s.StocksDownloader(SymStock, "1d", yes, tod);
		return ("/Template/Views/SecuritiesViews/StockHistory?faces-redirect=true");

	}

	public String gotocomp() {
		return ("/Template/Views/CompaniesViews/DisplayCompanies?faces-redirect=true");

	}

	public void updateStockPrice(String symbol) throws IOException {
		/*
		 * FacesContext context = FacesContext.getCurrentInstance(); Map<String,String>
		 * params = context.getExternalContext().getRequestParameterMap(); livePrice =
		 * Double.valueOf(params.get("adj_close"));
		 */
		StockQuote stockQuote = YahooFinance.get(symbol).getQuote(true);
		if (stockQuote == null) {
			livePrice = 0;
			change = " ";
			changePercentage = " ";
			changeIsPositive = true;
			System.out.println("WARNING: NULL STOCK REQUEST AT " + symbol);
		} else {
			livePrice = stockQuote.getPrice().doubleValue();
			change = String.valueOf(stockQuote.getChange().doubleValue());
			changePercentage = String.format("%.3g%n", (stockQuote.getChange().doubleValue() * 100) / livePrice);
			changeIsPositive = (Double.valueOf(change) >= 0);
		}
		System.out.println("Méthode livestock: company=" + symbol + " prix=" + livePrice + " change=" + change
				+ " change%=" + changePercentage);
	}

	public String DisplayCustomHistory() {
		if ((d1 == null) || (d2 == null)) {
			String yes, tod;
			if (frequency != "1mo") {
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				final Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, 0);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				yes = dateFormat.format(cal.getTime());
				tod = dateFormat.format(cal1.getTime());

			} else {
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -15);
				final Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, 0);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				yes = dateFormat.format(cal.getTime());
				tod = dateFormat.format(cal1.getTime());
			}

			Ls = s.StocksDownloader(SymStock, frequency, yes, tod);
			sigma = s.StandardDev(SymStock, yes, tod);
			volatility = s.VolatilityCalculator(SymStock, yes, tod);
			coef = s.CoefOfDeviation(SymStock, yes, tod);
			return ("/Template/Views/SecuritiesViews/StockHistory?faces-redirect=true");
		} else {
			String yes, tod;

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			yes = dateFormat.format(d1);
			tod = dateFormat.format(d2);
			System.out.println(yes + "pl" + tod);

			if (frequency == null) {
				Ls = s.StocksDownloader(SymStock, "1d", yes, tod);
				sigma = s.StandardDev(SymStock, yes, tod);
				volatility = s.VolatilityCalculator(SymStock, yes, tod);
				coef = s.CoefOfDeviation(SymStock, yes, tod);
			} else {
				Ls = s.StocksDownloader(SymStock, frequency, yes, tod);
				sigma = s.StandardDev(SymStock, yes, tod);
				volatility = s.VolatilityCalculator(SymStock, yes, tod);
				coef = s.CoefOfDeviation(SymStock, yes, tod);
			}

			return ("/Template/Views/SecuritiesViews/StockHistory?faces-redirect=true");
		}

	}

	public String DisplayByDate() {
		String yes, tod;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		yes = dateFormat.format(d1);
		tod = dateFormat.format(d2);
		System.out.println(yes + "pl" + tod);

		Ls = s.StocksDownloader(SymStock, "1d", yes, tod);
		return ("/Template/Views/SecuritiesViews/StockHistory?faces-redirect=true");

	}

	public void Also_Watch(User u) { /*
										 * double Avg;
										 * Avg=w.stream().map(e->e.getCompanies()).collect(Collectors.toList()).
										 * stream().mapToDouble(e -> ((Company) e).getPrice()).average().getAsDouble();
										 * List<List<Entities.Company>>
										 * Lcwish=w.stream().map(e->e.getCompanies()).collect(Collectors.toList());
										 * List<Company> ALLCOMP=companyS.DisplayCompanies();
										 * 
										 * for (Company wa:ALLCOMP) { if(wa.getPrice()-Avg<=0.05) { Lc.add(wa); }
										 * 
										 * 
										 * }
										 */

	}

	public void DownloadExcel() throws IOException {
		String yes, tod;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		if ((d1 == null) || (d2 == null)) {
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			final Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, 0);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			yes = dateFormat.format(cal.getTime());
			tod = dateFormat.format(cal1.getTime());
			externalContext.redirect(s.StockExcelFinder(SymStock, frequency, yes, tod));
		} else {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			yes = dateFormat.format(d1);
			tod = dateFormat.format(d2);

			externalContext.redirect(s.StockExcelFinder(SymStock, frequency, yes, tod));
		}
	}

	public String AddToWatchList() {
		Watchlist c = new Watchlist();
		List<Company> set = new ArrayList<Company>();
		System.out.println(companyS.DisplayCompany(SymStock));
		Company c4 = companyS.DisplayCompany(SymStock);
		set.add(c4);
		set.forEach(e -> System.out.println(e));
		c.setCompanies(set);
		c.setU(uS.DisplayUser(u.getId()));
		verifyInwatchlist(c4, uS.DisplayUser(u.getId()));
		if (inWishlist == false) {
			wS.AddWatchList(c, c4);
			w = wS.DisplayWatchlistByidUser(uS.DisplayUser(u.getId()));
		}

		return ("/Template/Views/SecuritiesViews/Wishlist?faces-redirect=true");

	}

	public void verifyInwatchlist(Company c4, User u) {
		List<Watchlist> DbW = wS.DisplayWatchlistByidUser(u);
		DbW.forEach(e -> System.out.println(e + "*********************"));
		for (Watchlist wa : DbW) {
			if (wa.getCompanies().contains(c4) == true) {
				inWishlist = true;
				System.out.println(wa);
				System.out.println(wa.getCompanies().contains(c4));
				System.out.println(inWishlist);
				return;

			} else {
				inWishlist = false;
			}

		}

	}

	public String getPriceInst(String sym) {
		double p;
		System.out.println(sym);
		p = s.getStockPriceInstantly(SymStock);
		return String.valueOf(p);

	}

	public String SeeWatchList() {
		w = wS.DisplayWatchlistByidUser(uS.DisplayUser(u.getId()));
		return ("/Template/Views/SecuritiesViews/Wishlist?faces-redirect=true");

	}

	public String SeeIndicators() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		i = s.Indicators_Calculator(SymStock, "1d", yes, tod);
		return ("/Template/Views/SecuritiesViews/Indicators?faces-redirect=true");
	}

	public String DisplayCustomIndicators() {
		if ((d1 == null) || (d2 == null)) {
			String yes, tod;
			if (frequency != "1mo") {
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				final Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, 0);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				yes = dateFormat.format(cal.getTime());
				tod = dateFormat.format(cal1.getTime());

			} else {
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -15);
				final Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DATE, 0);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				yes = dateFormat.format(cal.getTime());
				tod = dateFormat.format(cal1.getTime());
			}

			i = s.Indicators_Calculator(SymStock, frequency, yes, tod);

			return ("/Template/Views/SecuritiesViews/Indicators?faces-redirect=true");
		} else {
			String yes, tod;

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			yes = dateFormat.format(d1);
			tod = dateFormat.format(d2);
			System.out.println(yes + "pl" + tod);

			if (frequency == null) {
				i = s.Indicators_Calculator(SymStock, "1d", yes, tod);

			} else {
				i = s.Indicators_Calculator(SymStock, frequency, yes, tod);

			}
			createLineModelIndicators();
			return ("/Template/Views/SecuritiesViews/Indicators?faces-redirect=true");
		}

	}

	public void createLineModel() {

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		final Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String yes = dateFormat.format(cal.getTime());
		String tod = dateFormat.format(cal1.getTime());
		List<Stock> st = s.StocksDownloader(SymStock, "1d", yes, tod);

		lineModel = new LineChartModel();
		lineModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		for (Stock si : st) {
			lineModel.addLabel(si.getDATE().toString());
		}

		LineChartSeries series1 = new LineChartSeries();
		LineChartSeries series2 = new LineChartSeries();
		LineChartSeries series3 = new LineChartSeries();
		series1.setName(SymStock + " Adjusted Close");
		series2.setName(SymStock + " Open");
		series3.setName(SymStock + " Close");
		for (Stock si : st) {
			series1.set(si.getAdj_Close());
			series2.set(si.getOpen());
			series3.set(si.getClose());

		}

		Axis xAxis = lineModel.getAxis(AxisType.X);
		lineModel.addSeries(series1);
		lineModel.addSeries(series2);
		lineModel.addSeries(series3);

		lineModel.setAnimateAdvanced(true);

		lineModel.setShowTooltip(true);
	}
	public void createLineModelIndicators() {


		lineModel = new LineChartModel();
		lineModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		for (Indicators si : i) {
			lineModel.addLabel(si.getEndTime().toString());
		}

		LineChartSeries series1 = new LineChartSeries();
		LineChartSeries series2 = new LineChartSeries();
		LineChartSeries series3 = new LineChartSeries();
		LineChartSeries series4 = new LineChartSeries();
		LineChartSeries series5 = new LineChartSeries();
		series1.setName(SymStock + "Price Close");
		series2.setName(SymStock + " Long EMa");
		series3.setName(SymStock + " Short EMa");
		series4.setName(SymStock + " Long SMa");
		series5.setName(SymStock + " Short SMa");
		for (Indicators si : i) {
			series1.set(si.getClosePrice());
			series2.set(si.getLongEma());
			series3.set(si.getShortEma());
			series4.set(si.getLongSma());
			series5.set(si.getShortSma());

		}

		Axis xAxis = lineModel.getAxis(AxisType.X);
		lineModel.addSeries(series1);
		lineModel.addSeries(series2);
		lineModel.addSeries(series3);
		lineModel.addSeries(series4);
		lineModel.addSeries(series5);

		lineModel.setAnimateAdvanced(true);

		lineModel.setShowTooltip(true);
	}


	public void itemSelect(ItemSelectEvent event) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
				"Item Value: "
						+ ((ChartSeries) lineModel.getSeries().get(event.getSeriesIndex())).getData()
								.get(event.getItemIndex())
						+ ", Series name:"
						+ ((ChartSeries) lineModel.getSeries().get(event.getSeriesIndex())).getName());

		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
	}

	/**
	 * @return the lineModel
	 */
	public LineChartModel getLineModel() {
		return lineModel;
	}

	/**
	 * @param lineModel the lineModel to set
	 */
	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	private int number = 100;

	public int getNumber() {
		return number;
	}

	public void increment() {
		System.out.println("Incrementing....");
		number++;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public Stock getS() {
		return S;
	}

	public void setS(Stock s) {
		S = s;
	}

	public Bond getB() {
		return B;
	}

	public void setB(Bond b) {
		B = b;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	public Set<Flux> getF() {
		return F;
	}

	public void setF(Set<Flux> f) {
		F = f;
	}

	public String getSymStock() {
		return SymStock;
	}

	public void setSymStock(String symStock) {
		SymStock = symStock;
	}

	public List<Stock> getLs() {
		return Ls;
	}

	public void setLs(List<Stock> ls) {
		Ls = ls;
	}

	public Stock getYesterdaystock() {
		return Yesterdaystock;
	}

	public void setYesterdaystock(Stock yesterdaystock) {
		Yesterdaystock = yesterdaystock;
	}

	public double getPriceDifference() {
		return PriceDifference;
	}

	public void setPriceDifference(double priceDifference) {
		PriceDifference = priceDifference;
	}

	public double getPriceDifferencePercentage() {
		return PriceDifferencePercentage;
	}

	public void setPriceDifferencePercentage(double priceDifferencePercentage) {
		PriceDifferencePercentage = priceDifferencePercentage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getD1() {
		return d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public Date getD2() {
		return d2;
	}

	public void setD2(Date d2) {
		this.d2 = d2;
	}

	public double getVolatility() {
		return volatility;
	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}

	public double getSigma() {
		return sigma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	public double getCoef() {
		return coef;
	}

	public void setCoef(double coef) {
		this.coef = coef;
	}

	public List<Watchlist> getW() {
		return w;
	}

	public void setW(List<Watchlist> w) {
		this.w = w;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public boolean isInWishlist() {
		return inWishlist;
	}

	public void setInWishlist(boolean inWishlist) {
		this.inWishlist = inWishlist;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public UserBean getUs() {
		return us;
	}

	public void setUs(UserBean us) {
		this.us = us;
	}

	public double getLivePrice() {
		return livePrice;
	}

	public void setLivePrice(double livePrice) {
		this.livePrice = livePrice;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getChangePercentage() {
		return changePercentage;
	}

	public void setChangePercentage(String changePercentage) {
		this.changePercentage = changePercentage;
	}

	public boolean isChangeIsPositive() {
		return changeIsPositive;
	}

	public void setChangeIsPositive(boolean changeIsPositive) {
		this.changeIsPositive = changeIsPositive;
	}

	public List<Indicators> getI() {
		return i;
	}

	public void setI(List<Indicators> i) {
		this.i = i;
	}

}
