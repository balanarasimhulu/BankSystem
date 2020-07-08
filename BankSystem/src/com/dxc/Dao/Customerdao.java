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
import com.dxc.pojos.Mini;

public class Customerdao implements ICustomerdao
{
	int count=0,var,a=0;
	int index=0;
	List<Mini> l=new ArrayList<>();
	static Connection conn;
	private double Anum,ab;
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
	public String enterlogindetails(String cname,String adminid,String password)
	{
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("update customer set customerid=?,password=? where cname=?");
		pstmt.setString(1, adminid);
		pstmt.setString(2, password);
		pstmt.setString(3, cname);
		pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "loginaccount created";
	}
		
	public boolean loginCredentials(String adminid,String password)
	{
			int flag=0;
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement("select * from customer");
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				System.out.println(rset.getString(5));
			if(adminid.equals(rset.getString(4)) &&password.equals(rset.getString(5)))
				flag=1;
			}
			} catch (SQLException e){e.printStackTrace();}
		if(flag==1)
			return true;
		else
			return false;
	} 
	public String SearchAnumber(long Anumber,String cid)
	{
		int flag=0;
		try {
			PreparedStatement pstmt=conn.prepareStatement("select Anumber from customer where customerid=?");
			pstmt.setString(1, cid);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		long a=rs.getLong(1);
		if(a!=Anumber)
		{
			Statement stmt =conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from customer");
			while(rset.next())
			{
				if(Anumber==rset.getLong(2))
				{
				flag=1;
				}				
			}
		}
			}catch(Exception e) {
			e.printStackTrace();
			}
		if(flag==1)
			return "present";
		else
			return "absent";
		
	}
	public String debitedTo(String Cid,long Anumber,double Abala)
	{
		PreparedStatement pstmt,pstmt1,pstmt2,pstmt3,pstmt4;
		int flag=1;
		try {
			pstmt2=conn.prepareStatement("select * from customer where customerid=?");
			pstmt2.setString(1, Cid);
			ResultSet rset=pstmt2.executeQuery();
			rset.next();
			if(rset.getDouble(3)<=Abala)
			{
				flag=0;
				
			}
			pstmt4=conn.prepareStatement("select cname from customer where Anumber=?");
			pstmt4.setLong(1, Anumber);
			ResultSet rs=pstmt4.executeQuery();
			rs.next();
			String s=rs.getString(1);
					
		if(flag==1)
		{
			pstmt = conn.prepareStatement("update customer set Abalance=(Abalance+?) where Anumber=?");
		pstmt.setDouble(1, Abala);
		pstmt.setLong(2, Anumber);
		pstmt.execute();
		pstmt1 =conn.prepareStatement("update customer set Abalance=(Abalance-?) where customerid=?");
		
		System.out.println(Cid);
		pstmt1.setDouble(1, Abala);
		pstmt1.setString(2, Cid);
		pstmt1.execute();
		String s1="transfer to"+s;
		pstmt3=conn.prepareStatement("insert into ministatement values(now(),?,?,?)");
		pstmt3.setString(3, Cid);
		pstmt3.setDouble(2, Abala);
		pstmt3.setString(1, s1);
		pstmt3.execute();
		System.out.println(Abala);
		
		System.out.println(Abala);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==1)
		{
			return "amount debited";
		}
		else
		{
			return "Insufficient balance";
		}
		
	}
	public List<Customer> depositemoney(long Anumber,double Abalance,String cid)
	{
		int flag=1;
		System.out.println("starting deposit");
		List<Customer> list=new ArrayList<>();
		PreparedStatement pstmt,pstmt1,pstmt2;
		try {
			pstmt2=conn.prepareStatement("select * from customer where customerid=?");
			pstmt2.setString(1, cid);
			ResultSet rset=pstmt2.executeQuery();
			rset.next();
			if(Anumber!=rset.getLong(2))
			{
				flag=0;
			}
			if(flag==1)
			{
			pstmt = conn.prepareStatement("update customer set Abalance=Abalance+? where Anumber=?");
	
		pstmt.setDouble(1, Abalance);
		pstmt.setLong(2, Anumber);
		pstmt.execute();
			pstmt1=conn.prepareStatement("select * from customer where Anumber=?");
			pstmt1.setLong(1, Anumber);	
			ResultSet rset1=pstmt1.executeQuery();
			while(rset1.next())
			{
				list.add(new Customer(rset1.getString(1), rset1.getLong(2), rset1.getDouble(3)));
			}
			PreparedStatement pstmt3=conn.prepareStatement("insert into ministatement values(now(),'credited',?,?)");
			pstmt3.setDouble(1, Abalance);
			pstmt3.setString(2, cid);
			pstmt3.execute();	
			}
			}catch (SQLException e){e.printStackTrace();}
		System.out.println(list);
			return list;
	}
	public List<Customer> withdraw(long Anumber,double Abalance,String cid)
	{
		int flag=1,stand=1;
		List<Customer> list1=new ArrayList<>();
		PreparedStatement pstmt,pstmt1,pstmt2;
		try {
			pstmt2=conn.prepareStatement("select * from customer where customerid=?");
			pstmt2.setString(1, cid);
			ResultSet rset=pstmt2.executeQuery();
			rset.next();
			if(Anumber!=rset.getLong(2))
			{
				stand=0;
			}
			
			if(stand==1)
			{
			pstmt2=conn.prepareStatement("select * from customer where Anumber=?");
			pstmt2.setLong(1, Anumber);
			ResultSet rset1=pstmt2.executeQuery();
			rset1.next();
			if(rset1.getDouble(3)<=Abalance)
			{
				flag=0;
				
			}
			if(flag==1)
			{
			
			pstmt = conn.prepareStatement("update customer set Abalance=Abalance-? where Anumber=?");
	
		pstmt.setDouble(1, Abalance);
		pstmt.setLong(2, Anumber);
		
		pstmt.execute();
			pstmt1=conn.prepareStatement("select * from customer where Anumber=?");
			pstmt1.setLong(1, Anumber);	
			ResultSet rset2=pstmt1.executeQuery();
			System.out.println(list1);
			while(rset2.next())
			{
				list1.add(new Customer(rset2.getString(1), rset2.getLong(2), rset2.getDouble(3)));
			}
			PreparedStatement pstmt3=conn.prepareStatement("insert into ministatement values(now(),'debited',?,?)");
			pstmt3.setDouble(1, Abalance);
			pstmt3.setString(2, cid);
			pstmt3.execute();
			}
			}
			System.out.println("debited");
		}catch (SQLException e){e.printStackTrace();}
		
		return list1;
	}
	public double balance(long Anum,String cid)
	{
		int flag=1;
		try {
			PreparedStatement pstmt2=conn.prepareStatement("select * from customer where customerid=?");
			pstmt2.setString(1, cid);
			ResultSet rset1=pstmt2.executeQuery();
			rset1.next();
			long num=rset1.getLong(2);
			System.out.println(num);
			
			if(Anum==num)
			{
			PreparedStatement pstmt=conn.prepareStatement("select Abalance from customer where Anumber=?");
			pstmt.setLong(1, Anum);
			ResultSet rset=pstmt.executeQuery();
			rset.next();
			ab=rset.getDouble(1);
			}
			else {
				ab=0.0;
			}
			
		}catch (SQLException e){e.printStackTrace();}
		System.out.println(Anum);
			return ab;
	}
	public List<Mini> ministatement(String cid)
	{
		List<Mini> l1=new ArrayList<>();
		try {
			PreparedStatement pstmt1=conn.prepareStatement("select * from ministatement where cusid=?");
			pstmt1.setString(1, cid);
			ResultSet rs=pstmt1.executeQuery();
			while(rs.next())
			{
				count=count+1;
			}
			PreparedStatement pstmt=conn.prepareStatement("select date,operation,amount from ministatement where cusid=?");
			pstmt.setString(1, cid);
			ResultSet rset=pstmt.executeQuery();
			var=count-5;
			System.out.println(var);
		while(rset.next())
		{		
		a=a+1;
		System.out.println(a);
			System.out.println(count);
			if(a>var )
			{
			l1.add(new Mini(rset.getTimestamp(1), rset.getString(2), rset.getDouble(3)));
			}
			
			
		
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l1;
		}
	public boolean passwordChange(String password,String s2,String s)
	{
		int flag=0;
		PreparedStatement pstmt,pstmt1;
		try {
			pstmt = conn.prepareStatement("select * from customer");
		ResultSet rset=pstmt.executeQuery();
		while(rset.next())
		{
		if(password.equals(rset.getString(5)))
			flag=1;
		}
		pstmt1 = conn.prepareStatement("update customer set password=? where cname=?");
		pstmt1.setString(1, s2);
		pstmt1.setString(2, s);
		pstmt1.execute();
		} catch (SQLException e){e.printStackTrace();}
		System.out.println("password changed");
	if(flag==1)
		return true;
	else
		return false;
	}
}
