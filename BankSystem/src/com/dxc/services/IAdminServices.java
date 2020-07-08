package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Customer;

public interface IAdminServices 
{
	public boolean loginCredentials(String adminid,String password);
	public String  createCustomer(String Cname,long Anumber,double Abalance);
	public String SearchAnumber(long Anumber);
	public List<Customer> SearchCustomer(long Anumber);
	public String updatedetails(String Cname,long Anumber,double Abalance);
	public double balanceInquiry(long Anumber);
	public String deleteAccount(long Anumber);
	public List<Customer> display();

}
