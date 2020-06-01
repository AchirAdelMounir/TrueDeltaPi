package Entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

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

public class Indicators implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZonedDateTime endTime;
	private double closePrice;
     // Typical price
	 private double typicalPrice;
     // Price variation
     private double priceVariation ;
     // Simple moving averages
     private double shortSma;
     private double longSma ;
     // Exponential moving averages
     private double shortEma ;
     private double longEma ;
     // Percentage price oscillator
     private double ppo ;
     // Rate of change
     private double roc;
     // Relative strength index
     private double rsi ;
     // Williams %R
     private double williamsR ;
     // Average true range
     private double atr ;
     // Standard deviation
   private double sd;

@Override
public String toString() {
	return "Indicators [closePrice=" + closePrice + ", typicalPrice=" + typicalPrice + ", priceVariation="
			+ priceVariation + ", shortSma=" + shortSma + ", longSma=" + longSma + ", shortEma=" + shortEma
			+ ", longEma=" + longEma + ", ppo=" + ppo + ", roc=" + roc + ", rsi=" + rsi + ", williamsR=" + williamsR
			+ ", atr=" + atr + ", sd=" + sd + "]";
}
public Indicators() {
	super();
}
public double getClosePrice() {
	return closePrice;
}
public void setClosePrice(double closePrice) {
	this.closePrice = closePrice;
}
public double getTypicalPrice() {
	return typicalPrice;
}
public void setTypicalPrice(double typicalPrice) {
	this.typicalPrice = typicalPrice;
}
public double getPriceVariation() {
	return priceVariation;
}
public void setPriceVariation(double priceVariation) {
	this.priceVariation = priceVariation;
}
public double getShortSma() {
	return shortSma;
}
public void setShortSma(double shortSma) {
	this.shortSma = shortSma;
}
public double getLongSma() {
	return longSma;
}
public void setLongSma(double longSma) {
	this.longSma = longSma;
}
public double getShortEma() {
	return shortEma;
}
public void setShortEma(double shortEma) {
	this.shortEma = shortEma;
}
public double getLongEma() {
	return longEma;
}
public void setLongEma(double longEma) {
	this.longEma = longEma;
}
public double getPpo() {
	return ppo;
}
public void setPpo(double ppo) {
	this.ppo = ppo;
}
public double getRoc() {
	return roc;
}
public void setRoc(double roc) {
	this.roc = roc;
}
public double getRsi() {
	return rsi;
}
public void setRsi(double rsi) {
	this.rsi = rsi;
}
public double getWilliamsR() {
	return williamsR;
}
public void setWilliamsR(double williamsR) {
	this.williamsR = williamsR;
}
public double getSd() {
	return sd;
}
public void setSd(double sd) {
	this.sd = sd;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public ZonedDateTime getEndTime() {
	return endTime;
}
public void setEndTime(ZonedDateTime endTime) {
	this.endTime = endTime;
}


}
