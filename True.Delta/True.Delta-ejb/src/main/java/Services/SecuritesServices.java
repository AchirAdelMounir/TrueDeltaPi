package Services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entities.Bond;
import Entities.Company;
import Entities.Indicators;
import Entities.Portfolio;
import Entities.Security;
import Entities.Stock;
import Interfaces.SecuritiesServicesInterfaceLocal;
import Interfaces.SecuritiesServicesInterfaceRemote;
import yahoofinance.YahooFinance;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.ta4j.core.AnalysisCriterion;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BarSeriesManager;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.criteria.AverageProfitableTradesCriterion;
import org.ta4j.core.analysis.criteria.RewardRiskRatioCriterion;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;
import org.ta4j.core.analysis.criteria.VersusBuyAndHoldCriterion;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.PPOIndicator;
import org.ta4j.core.indicators.ROCIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.WilliamsRIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.PriceVariationIndicator;
import org.ta4j.core.indicators.helpers.TypicalPriceIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.StopGainRule;
import org.ta4j.core.trading.rules.StopLossRule;

@Stateless
@LocalBean

public class SecuritesServices implements SecuritiesServicesInterfaceRemote, SecuritiesServicesInterfaceLocal {
	@PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public int AddSecurity(Security S) {
		if (ifExists(S) == false) {
			if (S.getB() == null) {
				double d = getStockPriceInstantly(S.getCompany().getSymbol());
				S.setType("Stock");
				S.setPrice(d);

			} else if (S.getB() != null) {
				S.setType("Bond");
			}

			em.persist(S);
			System.out.println("Bond:" + S.getId());
			return S.getId();
		} else
			return 0;

	}

	@Override
	public void AddStock(Company c, Stock s) {

		Security st = new Security();
		st.setCompany(c);
		st.setS(s);

		System.out.println(c.getSecurities());

		em.persist(st);

	}

	@Override
	public void DeleteSecurity(int IdSecurity) {
		Security S = new Security();
		S = em.find(Security.class, IdSecurity);
		if (ifExists(S) == true) {
			em.remove(S);
		}

	}

	@Override
	public Security DisplaySecurity(int IdSecurity) {
		Security S = new Security();
		if (ifExists(S) == true) {
			S = em.find(Security.class, IdSecurity);
			return S;
		} else
			return null;

	}

	@Override
	public List<Security> DisplaySecurities() {
		TypedQuery<Security> query = em.createQuery("Select * from Security", Security.class);
		return query.getResultList();

	}

	@Override
	public void EditSecurity(int IdSecurity, Object o) {
		if (o.getClass() == Bond.class) {
			Security old = em.find(Security.class, IdSecurity);
			Bond n = (Bond) o;
			old.setB(n);

		}
		if (o.getClass() == Stock.class) {
			Security old = em.find(Security.class, IdSecurity);
			Stock n = (Stock) o;
			old.setS(n);

		}

	}

	@Override
	public List<Security> SearchByMaturityDate(Date d) {
		return (em.createQuery("select s from Security s where MaturityDate <=" + d, Security.class).getResultList());
	}

	@Override
	public List<Bond> DisplayBonds() {
		List<Security> LS = em.createQuery("select s from Security s where Yield!=null", Security.class)
				.getResultList();
		List<Bond> Lb = new ArrayList<>();
		for (Security s : LS) {
			Bond B = new Bond();
			B.setCoupon(s.getB().getCoupon());
			B.setMaturityDate(s.getB().getMaturityDate());
			B.setPrice(s.getB().getPrice());
			B.setYield(s.getB().getYield());
			Lb.add(B);

		}
		return Lb;

	}

	@Override
	public List<Stock> DisplayStocks() {
		List<Security> LS = em.createQuery("select s from Security s where Yield!=null", Security.class)
				.getResultList();
		List<Stock> Ls = new ArrayList<>();
		for (Security s : LS) {
			Stock n = new Stock();
			n.setAdj_Close(s.getS().getAdj_Close());
			n.setClose(s.getS().getClose());
			n.setDATE(s.getS().getDATE());
			n.setHigh(s.getS().getHigh());
			n.setLow(s.getS().getLow());
			n.setOpen(s.getS().getOpen());
			n.setVolume(s.getS().getVolume());
			Ls.add(n);

		}
		return Ls;
	}

	@Override
	public String StockExcelFinder(String Sym, String frequency, String Period1, String Period2) {

		/*
		 * Period1 = "2019-05-05"; Period2 = "2020-02-05";
		 */
		List<Stock> Ls = new ArrayList<>();
		Date localDate1 = Date.valueOf(Period1);
		Date localDate2 = Date.valueOf(Period2);
		long p1 = localDate1.getTime() / 1000;

		long p2 = localDate2.getTime() / 1000;
		String url = "https://query1.finance.yahoo.com/v7/finance/download/" + Sym + "?period1=" + p1 + "&period2=" + p2
				+ "&interval=" + frequency + "&events=history";
		return url;
	}

	@Override
	public List<Stock> StocksDownloader(String Sym, String frequency, String Period1, String Period2) {
		/*
		 * Period1 = "2019-05-05"; Period2 = "2020-02-05";
		 */
		List<Stock> Ls = new ArrayList<>();
		Date localDate1 = Date.valueOf(Period1);
		Date localDate2 = Date.valueOf(Period2);
		long p1 = localDate1.getTime() / 1000;

		long p2 = localDate2.getTime() / 1000;
		String url = "https://query1.finance.yahoo.com/v7/finance/download/" + Sym + "?period1=" + p1 + "&period2=" + p2
				+ "&interval=" + frequency + "&events=history";
		try {
			URL yhoofin = new URL(url);
			URLConnection data = yhoofin.openConnection();

			Scanner input = new Scanner(data.getInputStream()).useDelimiter(",");

			if (input.hasNext()) {
				input.nextLine();
			}
			while (input.hasNextLine()) {

				String line = input.nextLine().replaceAll("(?m)$", ",");
				Scanner newinput = new Scanner(line).useDelimiter(",");

				Stock S = new Stock();
				S.setDATE(Date.valueOf(newinput.next()));
				S.setOpen(Double.parseDouble(newinput.next()));
				S.setHigh(Double.parseDouble(newinput.next()));
				S.setLow(Double.parseDouble(newinput.next()));
				S.setClose(Double.parseDouble(newinput.next()));
				S.setAdj_Close(Double.parseDouble(newinput.next()));
				S.setVolume(Integer.parseInt(newinput.next()));

				Ls.add(S);

			}

		} catch (Exception e) {
			System.err.println(e);

		}
		return Ls;

	}

	@Override
	public double getStockPriceInstantly(String Sym) {
		yahoofinance.Stock stock = null;
		try {
			stock = YahooFinance.get(Sym);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			double price = stock.getQuote(true).getPrice().doubleValue();
			return price;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public Boolean ifExists(Security S) {
		if (em.find(Company.class, S.getId()) == null)
			return false;
		else
			return true;
	}

	@Override
	public double VolatilityCalculator(String Sym, String Period1, String Period2) {
		System.out.println("HELLO WORLD");
		List<Stock> Ls = StocksDownloader(Sym, "1d", Period1, Period2);
		long ObsNumber = Ls.stream().count();
		System.out.println("ObsNumber" + ObsNumber);
		double Mean = Ls.stream().mapToDouble(e -> e.getAdj_Close()).average().getAsDouble();
		System.out.println("Mean" + Mean);
		double PeriodDeviation = 0;
		double Var = 0;
		double Racine = 0;

		for (Stock i : Ls) {
			System.out.println("PeriodDev" + PeriodDeviation);
			System.out.println("PeriodDev2   h" + (i.getAdj_Close() - Mean));

			Racine = (i.getAdj_Close() - Mean);
			PeriodDeviation += Math.pow((i.getAdj_Close() - Mean), 2);

			System.out.println("PeriodDev" + PeriodDeviation);
		}
		Var = (PeriodDeviation / (ObsNumber - 1));
		System.out.println("Var" + Var);
		System.out.println("Mean" + Mean);
		return Var;
	}

	@Override
	public List<Security> SearchByInput(String SearchField, String operator, Object o) {
		if (o instanceof Integer) {
			int O = (Integer) o;

			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		} else if (o instanceof Double) {
			Double O = (Double) o;
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		} else if (o instanceof String) {
			String O = (String) o;
			return (em
					.createQuery("select s from Security s where " + SearchField + " " + operator + " " + "'" + O + "'",
							Security.class)
					.getResultList());

		} else if (o instanceof BigInteger) {
			BigInteger O = (BigInteger) o;
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		} else if (o instanceof Date) {
			Date O = (Date) o;
			return (em.createQuery("select s from Security s where " + SearchField + " " + operator + " " + O,
					Security.class).getResultList());

		}
		return null;
	}

	public List<Security> GetTopByInput(String Input, int TopN) {
		return (em.createQuery("select s from Security s ORDER BY " + Input + " DESC", Security.class)
				.setMaxResults(TopN).getResultList());

	}

	public List<Security> GetLastByInput(String Input, int TopN) {
		return (em.createQuery("select s from Security s ORDER BY " + Input + " ASC", Security.class)
				.setMaxResults(TopN).getResultList());

	}

	public List<Security> SecuritiesFinder(int Number, String operator, double value) {
		if (Number == -1) {
			return (em.createQuery("select s from Security s WHERE s.price" + operator + "" + value, Security.class)
					.getResultList());

		} else {
			return (em.createQuery("select s from Security s WHERE s.price" + operator + "" + value, Security.class)
					.setMaxResults(Number).getResultList());
		}

	}

	public double CoefOfDeviation(String Sym, String Period1, String Period2) {
		List<Stock> Ls = StocksDownloader(Sym, "1d", Period1, Period2);
		double Mean = Ls.stream().mapToDouble(e -> e.getAdj_Close()).average().getAsDouble();
		double StandardDev = Math.sqrt(VolatilityCalculator(Sym, Period1, Period2));
		return ((StandardDev / Mean) * 100);

	}

	public double StandardDev(String Sym, String Period1, String Period2) {
		return Math.sqrt(VolatilityCalculator(Sym, Period1, Period2));

	}

//	public void TreeClassifier(String[] args) throws Exception {
//	    // load data
//	    ArffLoader loader = new ArffLoader();
//	    loader.setFile(new File(args[0]));
//	    Instances structure = loader.getStructure();
//	    structure.setClassIndex(structure.numAttributes() - 1);
//
//	    // train NaiveBayes
//	    NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
//	    nb.buildClassifier(structure);
//	    Instance current;
//	    while ((current = loader.getNextInstance(structure)) != null)
//	      nb.updateClassifier(current);
//
//	    // output generated model
//	    System.out.println(nb);
//	  }

	@Override
	public List<Security> DisplayStock() {

		Query query = em.createQuery("select s from Security s");
		return query.getResultList();

	}

	@Override
	public int getIdSecurities(Stock s) {
		return 0;
	}

	@Override
	public BarSeries Creating_TimeSeries(String Sym, String frequency, String Period1, String Period2) {
		ZonedDateTime endTime = ZonedDateTime.now();
		int x = 0;
		BarSeries series = new BaseBarSeriesBuilder().withName(Sym + "_Stock").build();
		List<Stock> ls = StocksDownloader(Sym, frequency, Period1, Period2);
		for (Stock i : ls) {
			series.addBar(endTime.plusDays(x), i.getOpen(), i.getHigh(), i.getLow(), i.getClose(), i.getVolume());
			x++;
		}
		return series;

	}

	@Override
	public Strategy Calculate_Indicator(BarSeries series) {
		Num firstClosePrice = series.getBar(0).getClosePrice();
		System.out.println("First close price: " + firstClosePrice.doubleValue());
		// Or within an indicator:
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		// Here is the same close price:
		System.out.println(firstClosePrice.isEqual(closePrice.getValue(0))); // equal to firstClosePrice

		// Getting the simple moving average (SMA) of the close price over the last 5
		// ticks
		SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
		// Here is the 5-ticks-SMA value at the 42nd index
		System.out.println("5-ticks-SMA value at the 42nd index: " + shortSma.getValue(42).doubleValue());

		// Getting a longer SMA (e.g. over the 30 last ticks)
		SMAIndicator longSma = new SMAIndicator(closePrice, 30);
		// Buying rules
		// We want to buy:
		// - if the 5-ticks SMA crosses over 30-ticks SMA
		// - or if the price goes below a defined price (e.g $800.00)
		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma)
				.or(new CrossedDownIndicatorRule(closePrice, 800d));

		// Selling rules
		// We want to sell:
		// - if the 5-ticks SMA crosses under 30-ticks SMA
		// - or if the price loses more than 3%
		// - or if the price earns more than 2%
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma).or(new StopLossRule(closePrice, 3.0))
				.or(new StopGainRule(closePrice, 2.0));

		Strategy strategy = new BaseStrategy(buyingRule, sellingRule);
		return strategy;
	}

	@Override
	public void Trading_Strategy(BarSeries series, Strategy strategy) {
		BarSeriesManager manager = new BarSeriesManager(series);
		TradingRecord tradingRecord = manager.run(Calculate_Indicator(series));
		System.out.println("Number of trades for our strategy: " + tradingRecord.getTradeCount());

		AnalysisCriterion profitTradesRatio = new AverageProfitableTradesCriterion();
		System.out.println("Profitable trades ratio: " + profitTradesRatio.calculate(series, tradingRecord));
		// Getting the reward-risk ratio
		AnalysisCriterion rewardRiskRatio = new RewardRiskRatioCriterion();
		System.out.println("Reward-risk ratio: " + rewardRiskRatio.calculate(series, tradingRecord));

		// Total profit of our strategy
		// vs total profit of a buy-and-hold strategy
		AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new TotalProfitCriterion());
		System.out.println("Our profit vs buy-and-hold profit: " + vsBuyAndHold.calculate(series, tradingRecord));
	}
	@Override

	public List<String> trader(String Sym, String frequency, String Period1, String Period2,double stop_loss_earns,double stop_loss_drops,double stop_loss) {
		List<String> Trading=new ArrayList<>();
		ZonedDateTime endTime = ZonedDateTime.now();
		int x = 0;
		BarSeries series = new BaseBarSeriesBuilder().withName(Sym + "_Stock").build();
		List<Stock> ls = StocksDownloader(Sym, frequency, Period1, Period2);
		for (Stock i : ls) {
			series.addBar(endTime.plusDays(x), i.getOpen(), i.getHigh(), i.getLow(), i.getClose(), i.getVolume());
			x++;
		}

		Num firstClosePrice = series.getBar(0).getClosePrice();
		System.out.println("First close price: " + firstClosePrice.doubleValue());
		Trading.add("First close price: " + firstClosePrice.doubleValue());
		// Or within an indicator:
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		// Here is the same close price:
		System.out.println(firstClosePrice.isEqual(closePrice.getValue(0))); // equal to firstClosePrice

		// Getting the simple moving average (SMA) of the close price over the last 5
		// ticks
		SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
		// Here is the 5-ticks-SMA value at the 42nd index
		System.out.println("5-ticks-SMA value at the 42nd index: " + shortSma.getValue(42).doubleValue());
		Trading.add("5-ticks-SMA value at the 42nd index: " + shortSma.getValue(42).doubleValue());

		// Getting a longer SMA (e.g. over the 30 last ticks)
		SMAIndicator longSma = new SMAIndicator(closePrice, 30);
		// Buying rules
		// We want to buy:
		// - if the 5-ticks SMA crosses over 30-ticks SMA
		// - or if the price goes below a defined price (e.g $800.00)
		Num second= DoubleNum.valueOf(stop_loss);
		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma)
				.or(new CrossedDownIndicatorRule(closePrice, stop_loss));

		// Selling rules
		// We want to sell:
		// - if the 5-ticks SMA crosses under 30-ticks SMA
		// - or if the price loses more than 3%
		// - or if the price earns more than 2%
		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma).or(new StopLossRule(closePrice, stop_loss_drops))
				.or(new StopGainRule(closePrice,stop_loss_earns));

		Strategy strategy = new BaseStrategy(buyingRule, sellingRule);
		

		BarSeriesManager manager = new BarSeriesManager(series);
		TradingRecord tradingRecord = manager.run(Calculate_Indicator(series));
		System.out.println("Number of trades for our strategy: " + tradingRecord.getTradeCount());
		Trading.add("Number of trades for our strategy: " + tradingRecord.getTradeCount());

		AnalysisCriterion profitTradesRatio = new AverageProfitableTradesCriterion();
		System.out.println("Profitable trades ratio: " + profitTradesRatio.calculate(series, tradingRecord));
		Trading.add("Profitable trades ratio: " + profitTradesRatio.calculate(series, tradingRecord));
		// Getting the reward-risk ratio
		AnalysisCriterion rewardRiskRatio = new RewardRiskRatioCriterion();
		System.out.println("Reward-risk ratio: " + rewardRiskRatio.calculate(series, tradingRecord));
		Trading.add("Reward-risk ratio: " + rewardRiskRatio.calculate(series, tradingRecord));

		// Total profit of our strategy
		// vs total profit of a buy-and-hold strategy
		AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new TotalProfitCriterion());
		System.out.println("Our profit vs buy-and-hold profit: " + vsBuyAndHold.calculate(series, tradingRecord));
		Trading.add("Our profit vs buy-and-hold profit: " + vsBuyAndHold.calculate(series, tradingRecord));
		System.out.println("faarstoo"+firstClosePrice.toString());
		return Trading;

	}
	@Override
	public List<Indicators> Indicators_Calculator(String Sym, String frequency, String Period1, String Period2) {
		List<Indicators> Indica=new ArrayList<>();
		ZonedDateTime endTime = ZonedDateTime.now();
		int x = 0;
		BarSeries series = new BaseBarSeriesBuilder().withName(Sym + "_Stock").build();
		List<Stock> ls = StocksDownloader(Sym, frequency, Period1, Period2);
		for (Stock i : ls) {
			series.addBar(endTime.plusDays(x), i.getOpen(), i.getHigh(), i.getLow(), i.getClose(), i.getVolume());
			x++;
		}
		  // Close price
	    ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
	    // Typical price
	    TypicalPriceIndicator typicalPrice = new TypicalPriceIndicator(series);
	    // Price variation
	    PriceVariationIndicator priceVariation = new PriceVariationIndicator(series);
	    // Simple moving averages
	    SMAIndicator shortSma = new SMAIndicator(closePrice, 8);
	    SMAIndicator longSma = new SMAIndicator(closePrice, 20);
	    // Exponential moving averages
	    EMAIndicator shortEma = new EMAIndicator(closePrice, 8);
	    EMAIndicator longEma = new EMAIndicator(closePrice, 20);
	    // Percentage price oscillator
	    PPOIndicator ppo = new PPOIndicator(closePrice, 12, 26);
	    // Rate of change
	    ROCIndicator roc = new ROCIndicator(closePrice, 100);
	    // Relative strength index
	    RSIIndicator rsi = new RSIIndicator(closePrice, 14);
	    // Williams %R
	    WilliamsRIndicator williamsR = new WilliamsRIndicator(series, 20);
	    // Average true range
	    ATRIndicator atr = new ATRIndicator(series, 20);
	    // Standard deviation
	    StandardDeviationIndicator sd = new StandardDeviationIndicator(closePrice, 14);
	   

	    /*
	     * Building header
	     */
	   /* StringBuilder sb = new StringBuilder(
	            "timestamp,close,typical,variation,sma8,sma20,ema8,ema20,ppo,roc,rsi,williamsr,atr,sd\n");*/

	    /*
	     * Adding indicators values
	     */
	    final int nbBars = series.getBarCount();
	    for (int i = 0; i < nbBars; i++) {
	    	
	    	Indicators in=new Indicators();
	    	
	    	in.setEndTime(series.getBar(i).getEndTime());
	    	in.setClosePrice(closePrice.getValue(i).doubleValue());
	    	in.setLongEma(longEma.getValue(i).doubleValue());
	    	in.setLongSma(longSma.getValue(i).doubleValue());
	    	in.setPpo(ppo.getValue(i).doubleValue());
	    	in.setPriceVariation(priceVariation.getValue(i).doubleValue());
	    	in.setRoc(roc.getValue(i).doubleValue());
	    	in.setRsi(rsi.getValue(i).doubleValue());
	    	in.setSd(sd.getValue(i).doubleValue());
	    	in.setTypicalPrice(typicalPrice.getValue(i).doubleValue());
	    	in.setWilliamsR(williamsR.getValue(i).doubleValue());
	    	in.setShortSma(shortSma.getValue(i).doubleValue());
	    	in.setShortEma(shortEma.getValue(i).doubleValue());
	    	Indica.add(in);
	       /* sb.append(series.getBar(i).getEndTime()).append(',').append(closePrice.getValue(i)).append(',')
	                .append(typicalPrice.getValue(i)).append(',').append(priceVariation.getValue(i)).append(',')
	                .append(shortSma.getValue(i)).append(',').append(longSma.getValue(i)).append(',')
	                .append(shortEma.getValue(i)).append(',').append(longEma.getValue(i)).append(',')
	                .append(ppo.getValue(i)).append(',').append(roc.getValue(i)).append(',').append(rsi.getValue(i))
	                .append(',').append(williamsR.getValue(i)).append(',').append(atr.getValue(i)).append(',')
	                .append(sd.getValue(i)).append('\n');*/
	    }

	    /*
	     * Writing CSV file
	     */
	    /*BufferedWriter writer = null;
	    try {
	        writer = new BufferedWriter(new FileWriter(new File("D:/csv/", "indicators.csv")));
	        writer.write(sb.toString());
	    } catch (IOException ioe) {
	        Logger.getLogger("nine").log(Level.SEVERE, "Unable to write CSV file", ioe);
	    } finally {
	        try {
	            if (writer != null) {
	                writer.close();
	            }
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	    }*/
	    return Indica;

	}
		

	
	
	
	

}