package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Customer;
import com.dxc.pojos.Mini;

public interface ICustomerServices 
{
	public String enterlogindetails(String cname,String adminid,String password);
	public boolean loginCredentials(String adminid,String password);
	public String debitedTo(String Cid,long Anumber,double Abalance);
	public String SearchAnumber(long Anumber,String cid);
	public List<Customer> depositemoney(long Anumber,double Abalance,String cid);
	public List<Customer> withdraw(long Anumber,double Abalance,String cid);
	public double balance(long Anum,String cid);
	public List<Mini> ministatement(String cid);
	public boolean passwordChange(String password,String s2,String s);

}
