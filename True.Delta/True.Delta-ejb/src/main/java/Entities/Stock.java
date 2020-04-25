package Entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable

public class Stock implements Serializable, Comparator<Stock>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="Date")
	private Date DATE;
	@Column(name="OPEN")
    private double Open;
	@Column(name="HIGH")
	private double High;
	@Column(name="LOW")
	private double Low;
	@Column(name="CLOSE")
	private double Close;
	@Column(name="ADJ_CLOSE")
	private double Adj_Close;
	@Column(name="VOLUME_TRADED")
	private int Volume;
	
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Stock(Date dATE, double open, double high, double low, double close, double adj_Close,
			int volume) {
		super();
		
		DATE = dATE;
		Open = open;
		High = high;
		Low = low;
		Close = close;
		Adj_Close = adj_Close;
		Volume = volume;
	}
	
	
	
	
	
	

	public Date getDATE() {
		return DATE;
	}
	public void setDATE(Date dATE) {
		DATE = dATE;
	}
	public double getOpen() {
		return Open;
	}
	public void setOpen(double open) {
		Open = open;
	}
	public double getHigh() {
		return High;
	}
	public void setHigh(double high) {
		High = high;
	}
	public double getLow() {
		return Low;
	}
	public void setLow(double low) {
		Low = low;
	}
	public double getClose() {
		return Close;
	}
	public void setClose(double close) {
		Close = close;
	}
	public double getAdj_Close() {
		return Adj_Close;
	}
	public void setAdj_Close(double adj_Close) {
		Adj_Close = adj_Close;
	}
	public int getVolume() {
		return Volume;
	}
	public void setVolume(int volume) {
		Volume = volume;
	}
	
	


	@Override
	public String toString() {
		return "Stock [DATE=" + DATE + ", Open=" + Open + ", High=" + High + ", Low=" + Low + ", Close=" + Close
				+ ", Adj_Close=" + Adj_Close + ", Volume=" + Volume + "]";
	}

	@Override
	public int compare(Stock o1, Stock o2) {
		if(o1.getClose() < o2.getClose())
		return 1;
		return -1;
	}



	
	

}
