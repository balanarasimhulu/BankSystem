package com.dxc.services;

import java.util.List;  

import com.dxc.Dao.Customerdao;
import com.dxc.Dao.ICustomerdao;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Mini;

public class CustomerServices implements ICustomerServices
{
	ICustomerdao icd=new Customerdao();
	public String enterlogindetails(String cname,String adminid,String password)
	{
		return icd.enterlogindetails(cname,adminid,password);
	}
	public boolean loginCredentials(String adminid,String password) 
	{
		return icd.loginCredentials(adminid,password);
	}
	public String debitedTo(String Cid,long Anumber,double Abalance)
	{
		return icd.debitedTo(Cid,Anumber, Abalance);
	}
	public String SearchAnumber(long Anumber,String cid)
	{
		return icd.SearchAnumber(Anumber,cid);
	}
	public List<Customer> depositemoney(long Anumber,double Abalance,String cid)
	{
		return icd.depositemoney(Anumber,Abalance,cid);
	}
	public List<Customer> withdraw(long Anumber,double Abalance,String cid)
	{
		return icd.withdraw(Anumber,Abalance,cid);
	}
	public double balance(long Anum,String cid)
	{
		return icd.balance(Anum,cid);
	}
	public List<Mini> ministatement(String cid)
	{
		return icd.ministatement(cid);
	}
	public boolean passwordChange(String password,String s2,String s)
	{
		return icd.passwordChange(password,s2,s);
	}
}
