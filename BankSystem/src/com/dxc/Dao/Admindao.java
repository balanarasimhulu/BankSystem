package com.dxc.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Customer;

public class Admindao implements IAdmindao
{

	static Connection conn;
	private double Anum;
	static
	{
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("driver connected");
			System.out.println("driver loaded...");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank","postgres","password");
			System.out.println("connection to database...");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean loginCredentials(String adminid,String password)
	{
			int flag=0;
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement("select * from admin");
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
			if(adminid.equals(rset.getString(1)) &&password.equals(rset.getString(2)))
				flag=1;
			}
			} catch (SQLException e){e.printStackTrace();}
		if(flag==1)
			return true;
		else
			return false;
	}
	public String  createCustomer(String Cname,long Anumber,double Abalance)
	{
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("insert into customer values(?,?,?)");	
			pstmt.setString(1, Cname);
			pstmt.setLong(2, Anumber);
			pstmt.setDouble(3, Abalance);
			pstmt.execute();
		} catch (SQLException e){e.printStackTrace();}	
		return "Customer account created!";
	}
	public String SearchAnumber(long Anumber)
	{
		int flag=0;
		try {
			Statement stmt =conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from customer");
			while(rset.next())
			{
				if(Anumber==rset.getLong(2))
				flag=1;
			}	
			}catch(Exception e) {
			e.printStackTrace();
			}
		if(flag==1)
			return "present";
		else
			return "absent";
		
	}
	public List<Customer> SearchCustomer(long Anumber)
	{

		List<Customer> list=new ArrayList();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from customer where Anumber=?");
			pstmt.setLong(1, Anumber);	
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				list.add(new Customer(rset.getString(1), rset.getLong(2), rset.getDouble(3)));
			}}catch (SQLException e){e.printStackTrace();}
			return list;
	}
	
	public String updatedetails(String Cname,long Anumber,double Abalance)
	{
		List<Customer> list= new ArrayList<>();
		try {
			PreparedStatement pstmt=conn.prepareStatement("update customer set Cname=?,Abalance=? where Anumber=?");
			pstmt.setString(1,Cname);
			pstmt.setDouble(2, Abalance);
			pstmt.setLong(3, Anumber);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				list.add(new Customer(rset.getString(1), rset.getLong(2), rset.getDouble(3)));
			}}catch (SQLException e){e.printStackTrace();}
			return "Details are updated";
	}
	public double balanceInquiry(long Anumber)
	{
		try {
			PreparedStatement pstmt=conn.prepareStatement("select Abalance from customer where Anumber=?");
			pstmt.setLong(1, Anumber);	
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
			Anum=rset.getDouble(1);
			}
			}catch (SQLException e){e.printStackTrace();}
		System.out.println(Anum);
			return Anum;
	}
	public String deleteAccount(long Anumber)
	{
		try {
			PreparedStatement pstmt=conn.prepareStatement("delete from customer where Anumber=?");
			pstmt.setLong(1, Anumber);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Account closed";
	}
	public List<Customer> display()
	{
		List<Customer> list=new ArrayList();
		try {
			PreparedStatement pstmt=conn.prepareStatement("select * from customer");	
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				list.add(new Customer(rset.getString(1), rset.getLong(2), rset.getDouble(3)));
			}}catch (SQLException e){e.printStackTrace();}
			return list;
		
	}
	

	
	
	


}
