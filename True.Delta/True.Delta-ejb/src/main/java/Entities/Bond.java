package Entities;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table( name= "BONDS")

public class Bond implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private int Id;
	@Column(name="COUPON")
	private double Coupon;
	@Column(name="PRICE")
	private double Price;
	@Column(name="YIELD")
	private double Yield;
	@Column(name="MATURITYDATE")
	private Date MaturityDate;
	
	public Bond(int id, double coupon, double price, double yield, Date maturityDate) {
		super();
		Id = id;
		Coupon = coupon;
		Price = price;
		Yield = yield;
		MaturityDate = maturityDate;
	}
	
	public Bond() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
		return "Bond [Id=" + Id + ", Coupon=" + Coupon + ", Price=" + Price + ", Yield=" + Yield + ", MaturityDate="
				+ MaturityDate + "]";
	}
	
	

}
