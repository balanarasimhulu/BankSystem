package com.dxc.pojos;

import java.io.Serializable;

public class Customer implements Serializable
{
	int index;
	String name;
	long number;
	double balance;
	public Customer() {
		
	}
	public Customer(String name, long number, double balance) {
		super();
		this.name = name;
		this.number = number;
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", number=" + number + ", balance=" + balance + "]";
	}
	
	

}
