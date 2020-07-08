package com.dxc.pojos;

import java.util.Date;

public class Mini 
{
	Date date;
	String s;
	double amount;
	
	public Mini()
	{
		
	}
	public Mini(Date date, String s, double amount) {
		super();
		this.date = date; 
		this.s = s;
		this.amount = amount;
		
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Mini [date=" + date + ", s=" + s + ", amount=" + amount + "]";
	}
	
}
