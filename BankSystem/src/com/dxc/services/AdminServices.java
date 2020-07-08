package com.dxc.services;

import java.util.List;

import com.dxc.Dao.Admindao;
import com.dxc.Dao.IAdmindao;
import com.dxc.pojos.Customer;

public class AdminServices implements IAdminServices
{
	IAdmindao iad =new Admindao();
	public boolean loginCredentials(String adminid,String password) 
	{
		return iad.loginCredentials(adminid,password);
	}
	public String  createCustomer(String Cname,long Anumber,double Abalance)
	{
		return iad.createCustomer(Cname, Anumber, Abalance);
	}
	public List<Customer> SearchCustomer(long Anumber)
	{
		return iad.SearchCustomer(Anumber);
	}
	public String SearchAnumber(long Anumber)
	{
		return iad.SearchAnumber(Anumber);
	}
	public String updatedetails(String Cname,long Anumber,double Abalance)
	{
		return iad.updatedetails(Cname, Anumber, Abalance);
	}
	public double balanceInquiry(long Anumber)
	{
		return iad.balanceInquiry(Anumber);
	}
	public String deleteAccount(long Anumber)
	{
		return iad.deleteAccount(Anumber);
	}
	public List<Customer> display()
	{
		return iad.display();
	}

}