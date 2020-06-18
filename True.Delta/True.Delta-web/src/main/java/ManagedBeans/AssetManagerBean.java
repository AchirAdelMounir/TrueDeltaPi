package ManagedBeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;

import Entities.AssetManager;
import Entities.Portfolio;


import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.chart.PieChartModel;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import Interfaces.AssetManagerServiceInterface;


@ManagedBean(name = "AssetManagerBean")
@SessionScoped
public class AssetManagerBean {

	
	
	
@EJB 	
AssetManagerServiceInterface es ;

private String dev1;
private String dev2;
private Float montant ;
private Float resultat ;
private AssetManager am ;




private List<String> keys = new ArrayList<>();
private List<String> achats = new ArrayList<>();





public Map<String, Float> changes =new HashMap<>();








public Map<String, Float> getChanges() throws IOException {
	
	changes =es.Change();
	return changes;
}
public void setChanges(Map<String, Float> changes) {
	this.changes = changes;
}



public void Convertir () throws IOException
{
	   resultat = es.Conversion(dev1,dev2,montant);
	
}
public String gotoconv()
{
	return ("/Template/Convertisseur?faces-redirect=true");
	
}
public String gototaux()
{
	return ("/Template/TauxChange?faces-redirect=true");
	
}
public String gotoc()
{
	return ("/Template/AddContractFree?faces-redirect=true");
	
}







public String getDev1() {
	return dev1;
}
public void setDev1(String dev1) {
	this.dev1 = dev1;
}
public String getDev2() {
	return dev2;
}
public void setDev2(String dev2) {
	this.dev2 = dev2;
}
public Float getMontant() {
	return montant;
}
public void setMontant(Float montant) {
	this.montant = montant;
}
public Float getResultat() {
	return resultat;
}
public void setResultat(Float resultat) {
	this.resultat = resultat;
}
public List<String> getKeys() throws IOException {
	keys = es.devise();

	return keys;
}
public void setKeys(List<String> keys) {
	this.keys = keys;
}
public List<String> getAchats() throws IOException {
	achats=es.achat();
	return achats;
}
public void setAchats(List<String> achats) {
	this.achats = achats;
}



}
