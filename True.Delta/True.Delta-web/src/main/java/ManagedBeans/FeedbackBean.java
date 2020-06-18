package ManagedBeans;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.BarChartModel;
import org.chartistjsf.model.chart.BarChartSeries;
import org.chartistjsf.model.chart.ChartSeries;
import org.primefaces.event.ItemSelectEvent;

import Entities.Feedback;
import Entities.User;
import Enumerations.LevelRating;
import Enumerations.Who;
import Services.FeedbackServices;

@ManagedBean(name = "FeedbackBean")
//@ApplicationScoped
//@ViewScoped
//@RequestScoped
@SessionScoped
public class FeedbackBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	FeedbackServices service;

	private int id;
	private User user;
	private Who who;
	private LevelRating rating;
	private Date date;
	private String notice;
	private String x = " ";
	private Feedback selectedFeedback;
	private List<Feedback> feedbackList = new ArrayList<>();
	private List<Feedback> filteredFeedback = new ArrayList<>();
	private Feedback f;
	private CommonUtils util;
	private User user1;
	private int ind=19;
	private Boolean valider=false;
	private String word="";
	private BarChartModel barChartModel;
	private Boolean admin_vu;
	private LevelRating nulle =LevelRating.Null;
	private LevelRating bad =LevelRating.Bad;
	private LevelRating middling =LevelRating.Middling;
	private LevelRating good =LevelRating.Good;
	private LevelRating very_good =LevelRating.Very_good;
	private LevelRating perfect =LevelRating.Perfect;
	private Who overall=Who.Overall;
	private Who assetManager=Who.AssetManager;
	private Who article=Who.Article;



	
	
	public void AddFeedback() {
		Feedback test = new Feedback();
		User user1 = new User();

		user1.setId(43);
		user1.setNom("DRIDI");
		user1.setPrenom("SALEH");
		user1.setValid(true);
//		
//		
//		//il faut changer
		test.setUser(user1);
		test.setWho(who);
		test.setRating(rating);
		test.setNotice(notice);
		System.out.println(test.toString());
	
		service.AddFeedback(test);
		// System.out.println("a:" + a);
		clear();

		//util.redirectWithGet();
	}

	public String action() {
		// ...
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}

	public void refresh() {
		getNbFeedbackByVuAdmin();
		action();
	}
	public void refreshAd() {
		action();
		
	}
	public void refresh1() {
		listv(ind);
		action();
		}
	public void val(String s) {
		validerFeedback(s);
		wordDanger(s);
		System.out.println("word est :"+word);
		action();
		
	}
	public void changeVuAdmin(int id) {
		Feedback f =GetFeedbackById(id);
		f.setAdmin_vu(true);
		service.UpdateFeedback(f);
		refresh();
	}

	int genererInt(int borneInf, int borneSup) {
		Random random = new Random();
		int nb;
		nb = borneInf + random.nextInt(borneSup - borneInf);
		return nb;
	}

	public List<Feedback> listv(int id) {
		List<Feedback> lt = new ArrayList<>();
		List<Feedback> lv = new ArrayList<>();
		List<Integer> lts = new ArrayList<Integer>();

		lt = getAllFeedbackByRating(LevelRating.Perfect);
		for (int i = 0; i < lt.size(); i++) {
			lts.add(lt.get(i).getId());
		}

		lv.add(findById(id));
		System.out.println("aaaaaaaaaaaaaaaaaaa" + lt);
		System.out.println("c est la liste totale mais en id de feedback" + lts);

		System.out.println("saleh saleh saleh" + id);
		System.out.println("wajih wajih wajih" + findById(id));
		System.out.println("aziz aziz aziz" + lts.indexOf(GetFeedbackById(id).getId()));
		int j = lts.indexOf(GetFeedbackById(id).getId());
		lt.remove(j);
		lts.remove(j);

		for (int i = 0; i < 2; i++) {
			int x = genererInt(0, lt.size() - 1);
			lv.add(lt.get(x));
			lt.remove(x);
			lts.remove(x);

		}
		return lv;
	}

	public void clear() {
		notice = "";
		who = null;
		rating = null;
		valider=false;
	}

	public int arrond(long x) {
		return (int)Math.rint((double)x);
	}
	public int rest(int x) {
		return 5-x;
	}
	public void DeleteFeddbackById(int idFeddback) {
		service.DeleteFeddbackById(idFeddback);

		
	}

	public void DeleteFeedbackByIdUser(int idUser) {
		service.DeleteFeedbackByIdUser(idUser);
	}

	public void DeleteFeedbackByDate(String date) {
		service.DeleteFeedbackByDate(date);
	}

	public void UpdateFeedback(int idF, Feedback f) {
		Feedback updateFeedback = service.findById(idF);

		updateFeedback.setRating(f.getRating());
		updateFeedback.setDate(f.getDate());
		updateFeedback.setUser(f.getUser());
		service.UpdateFeedback(updateFeedback);
	}

	public Feedback GetFeedbackById(int idFeedback) {
		return service.GetFeedbackById(idFeedback);
	}

	public List<Feedback> getAllFeedback() {
		return service.getAllFeedback();
	}

	public List<Feedback> getAllFeedbackByIdUser(int idUser) {
		return service.getAllFeedbackByIdUser(idUser);
	}

	public Feedback findById(int id) {
		return service.findById(id);
	}

	public List<Feedback> getFeedbackByDate(String date) throws ParseException {
		return service.getFeedbackByDate(date);
	}

	public List<Feedback> getAllFeedbackByRating(Enumerations.LevelRating rating) {
		return service.getAllFeedbackByRating(rating);
	}

	public Long getNbFeedback() {
		return service.getNbFeedback();
	}

	public Long getNbFeedbackByIdUser(int idUser) {
		return service.getNbFeedbackByIdUser(idUser);
	}

	public Long getNbFeedbackByDate(String date) throws ParseException {
		return service.getNbFeedbackByDate(date);
	}
	
	public Long getNbFeedbackByWho(Who who) {
		return service.getNbFeedbackByWho(who);
	}

	public Long getNbFeedbackByRating(LevelRating rating,Who who) {
		return service.getNbFeedbackByRating(rating,who);
	}
	
	public Long getNbFeedbackByVuAdmin() {
		return service.getNbFeedbackByVuAdmin();
	}

	public int getAvgOfRating(LevelRating rating,Who who) {
		return (int)service.getAvgOfRating(rating,who);
	}

	public String nbOfStarsById(int idFeedback) {
		return Integer.toString((int) (service.nbOfStarsById(idFeedback)));
	}

	public double noteGlobal(Who who) {
		return service.noteGlobal(who);
	}

	public Long getNbUser() {
		return service.getNbUser();
	}

	public Long getNbActivityByUser(int idUser) {
		return service.getNbActivityByUser(idUser);
	}

	public Map<Integer, Long> best3ActiveUser() {
		return service.best3ActiveUser();
	}

	public List<String> affichageOfBest3() {
		return service.affichageOfBest3();
	}

	public Boolean validerFeedback(String Str) {
		System.out.println("heyyyyyyyyeeeeeeyyyy");
		setValider(service.validerFeedback(Str));
		System.out.println("T ou F"+valider);
		return valider;
	}
	public String wordDanger(String Str) {
		word=service.wordDanger(Str);
		return word;
	}
	public BarChartModel chart() {
		barChartModel = new BarChartModel();
		barChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		barChartModel.addLabel("Null");
		barChartModel.addLabel("Bad");
		barChartModel.addLabel("Middling");
		barChartModel.addLabel("Good");
		barChartModel.addLabel("Very_good");
		barChartModel.addLabel("Perfect");
		
		BarChartSeries series1 = new BarChartSeries();
		series1.setName("Overrall Rating");
		series1.set(getNbFeedbackByRating(LevelRating.Null,Who.Overall));
		series1.set(getNbFeedbackByRating(LevelRating.Bad,Who.Overall));
		series1.set(getNbFeedbackByRating(LevelRating.Middling,Who.Overall));
		series1.set(getNbFeedbackByRating(LevelRating.Good,Who.Overall));
		series1.set(getNbFeedbackByRating(LevelRating.Very_good,Who.Overall));
		series1.set(getNbFeedbackByRating(LevelRating.Perfect,Who.Overall));
		
		BarChartSeries series2 = new BarChartSeries();
		series2.setName("Asset Manager Rating");
		series2.set(getNbFeedbackByRating(LevelRating.Null,Who.AssetManager));
		series2.set(getNbFeedbackByRating(LevelRating.Bad,Who.AssetManager));
		series2.set(getNbFeedbackByRating(LevelRating.Middling,Who.AssetManager));
		series2.set(getNbFeedbackByRating(LevelRating.Good,Who.AssetManager));
		series2.set(getNbFeedbackByRating(LevelRating.Very_good,Who.AssetManager));
		series2.set(getNbFeedbackByRating(LevelRating.Perfect,Who.AssetManager));
		
		BarChartSeries series3 = new BarChartSeries();
		series3.setName("Article Rating");
		series3.set(getNbFeedbackByRating(LevelRating.Null,Who.Article));
		series3.set(getNbFeedbackByRating(LevelRating.Bad,Who.Article));
		series3.set(getNbFeedbackByRating(LevelRating.Middling,Who.Article));
		series3.set(getNbFeedbackByRating(LevelRating.Good,Who.Article));
		series3.set(getNbFeedbackByRating(LevelRating.Very_good,Who.Article));
		series3.set(getNbFeedbackByRating(LevelRating.Perfect,Who.Article));
		
		Axis xAxis = barChartModel.getAxis(AxisType.X);
		xAxis.setShowGrid(false);
		Axis yAxis = barChartModel.getAxis(AxisType.Y);
		yAxis.setScaleMinSpace(80);
		
		barChartModel.addSeries(series1);
		barChartModel.addSeries(series2);
		barChartModel.addSeries(series3);
		barChartModel.setShowTooltip(true);
		
		barChartModel.setSeriesBarDistance(15);
		barChartModel.setAnimateAdvanced(true);
		
		return  barChartModel;
	}
	public void barItemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
		+ ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getData().get(
		event.getItemIndex()) + ", Series name:"
		+ ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getName());
		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
		}

	
	

	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Who getWho() {
		return who;
	}

	public void setWho(Who who) {
		this.who = who;
	}

	public LevelRating getRating() {
		return rating;
	}

	public void setRating(LevelRating rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public Feedback getSelectedFeedback() {
		return selectedFeedback;
	}

	public void setSelectedFeedback(Feedback selectedFeedback) {
		this.selectedFeedback = selectedFeedback;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public FeedbackServices getService() {
		return service;
	}

	public void setService(FeedbackServices service) {
		this.service = service;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public List<Feedback> getFilteredFeedback() {
		return filteredFeedback;
	}

	public void setFilteredFeedback(List<Feedback> filteredFeedback) {
		this.filteredFeedback = filteredFeedback;
	}

	public Feedback getF() {
		return f;
	}

	public void setF(Feedback f) {
		this.f = f;
	}

	public void setUtil(CommonUtils util) {
		this.util = util;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	public Boolean getValider() {
		return valider;
	}

	public void setValider(Boolean valider) {
		this.valider = valider;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	public BarChartModel getBarChartModel() {
		return barChartModel;
	}
	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public Boolean getAdmin_vu() {
		return admin_vu;
	}

	public void setAdmin_vu(Boolean admin_vu) {
		this.admin_vu = admin_vu;
	}

	public LevelRating getNulle() {
		return nulle;
	}

	public void setNulle(LevelRating nulle) {
		this.nulle = nulle;
	}

	public LevelRating getBad() {
		return bad;
	}

	public void setBad(LevelRating bad) {
		this.bad = bad;
	}

	public LevelRating getMiddling() {
		return middling;
	}

	public void setMiddling(LevelRating middling) {
		this.middling = middling;
	}

	public LevelRating getGood() {
		return good;
	}

	public void setGood(LevelRating good) {
		this.good = good;
	}

	public LevelRating getVery_good() {
		return very_good;
	}

	public void setVery_good(LevelRating very_good) {
		this.very_good = very_good;
	}

	public LevelRating getPerfect() {
		return perfect;
	}

	public void setPerfect(LevelRating perfect) {
		this.perfect = perfect;
	}

	public Who getOverall() {
		return overall;
	}

	public void setOverall(Who overall) {
		this.overall = overall;
	}

	public Who getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(Who assetManager) {
		this.assetManager = assetManager;
	}

	public Who getArticle() {
		return article;
	}

	public void setArticle(Who article) {
		this.article = article;
	}
	public String FeedbacksAdmin() throws ParseException  
	{	
		return "/Template/FeedbacksAdmin?faces-redirect=true";		
	}
	public String FeedbacksCustomer() throws ParseException  
	{	
		return "/Template/FeedbacksCustomer?faces-redirect=true";		
	}
	public String ComplainsAdmin() throws ParseException  
	{	
		return "/Template/ComplainsAdmin?faces-redirect=true";		
	}
	public String ComplainsCustomer() throws ParseException  
	{	
		return "/Template/ComplainsCustomer?faces-redirect=true";		
	}




	
		
	
	
	
}
