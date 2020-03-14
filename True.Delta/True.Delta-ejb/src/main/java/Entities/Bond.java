package Entities;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Embeddable

public class Bond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="COUPON")
	private double Coupon;
	@Column(name="PRICE")
	private double Price;
	@Column(name="YIELD")
	private double Yield;
	@Column(name="MATURITYDATE")
	private Date MaturityDate;
	
	public Bond( double coupon, double price, double yield, Date maturityDate) {
		super();
		
		Coupon = coupon;
		Price = price;
		Yield = yield;
		MaturityDate = maturityDate;
	}
	
	public Bond() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getCoupon() {
		return Coupon;
	}
	public void setCoupon(double coupon) {
		Coupon = coupon;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getYield() {
		return Yield;
	}
	public void setYield(double yield) {
		Yield = yield;
	}
	public Date getMaturityDate() {
		return MaturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		MaturityDate = maturityDate;
	}

	@Override
	public String toString() {
		return "Bond [ Coupon=" + Coupon + ", Price=" + Price + ", Yield=" + Yield + ", MaturityDate="
				+ MaturityDate + "]";
	}
	
	

}
