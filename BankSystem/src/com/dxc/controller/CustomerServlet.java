package com.dxc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.BankException;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Mini;
import com.dxc.services.CustomerServices;
import com.dxc.services.ICustomerServices;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Mini> l=new ArrayList<>();
		List<Customer> list=new ArrayList<>();
		String message;
		ICustomerServices ics=new CustomerServices();
		String need="";
		String dubmy=request.getParameter("btn");
		if(dubmy!=null)
		{need=request.getParameter("btn");}
		HttpSession session=request.getSession();
		if(need.equals("logindetails"))
		{
			String adminid=request.getParameter("customerid");
			session.setAttribute("Cid", adminid);
			String password=request.getParameter("password");
			String cname=request.getParameter("Cname");
			String s=ics.enterlogindetails(cname,adminid,password);
			session.setAttribute("message", s);
			response.sendRedirect("view.jsp");
		}
		else if(need.equals("login as customer"))
		{	
			String adminid=request.getParameter("adminid");
			String password=request.getParameter("password");
			session.setAttribute("customerid", adminid);
			boolean status=ics.loginCredentials(adminid,password);
			System.out.println(status);
			if(status==true)
			{	System.out.println("logged in!");
				
				session.setAttribute("login", adminid);
				response.sendRedirect("customeroperations.jsp");}
			else
			{	message="Login credentials incorrect!!";
				session.setAttribute("login", message);
				response.sendRedirect("login.jsp");}}
		else if(need.equals("search"))
		{
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			String cid=(String)session.getAttribute("customerid");
			String s=ics.SearchAnumber(Anumber,cid);
			System.out.println(s);
			try {
			if(s=="present")
			{
			message ="Account number present";
			response.sendRedirect("debited.jsp");			
			}
			else {throw new BankException();}
			}
			catch(Exception e)
			{
				String s1="Anumber not found";
				System.out.println(s1+e);
				session.setAttribute("message", s1+"  "+e);
				response.sendRedirect("view.jsp");}}
		else if(need.equals("debitTo"))
		{
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			Double Abalance=Double.parseDouble(request.getParameter("Abalance"));
			String Cid=(String)session.getAttribute("customerid");
			System.out.println(Cid);
			String s=ics.debitedTo(Cid,Anumber,Abalance);
			session.setAttribute("message", s);
			response.sendRedirect("view.jsp");
		}
		else if(need.equals("depositemoney"))
		{
			String cid=(String)session.getAttribute("customerid");
			Double Abalance=Double.parseDouble(request.getParameter("Abalance"));
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			list =ics.depositemoney(Anumber,Abalance,cid);
			System.out.println(list.equals(null));
			if(list.isEmpty())
			{
				message="invalid Account number";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
		
			}
			else
			{
				session.setAttribute("list", list);
				response.sendRedirect("viewlist.jsp");
			
				
			}
			
		}
		else if(need.equals("withdraw"))
		{
			String cid=(String)session.getAttribute("customerid");
			Double Abalance=Double.parseDouble(request.getParameter("Abalance"));
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			session.setAttribute("roll_number", Anumber);
			list =ics.withdraw(Anumber,Abalance,cid);
			if(list.isEmpty())
			{
				message="Account number balance insufficient";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
	
			}
			else {
				session.setAttribute("list", list);
				response.sendRedirect("viewlist.jsp");
			}
		
		}
		else if(need.equals("balancedisplay"))
		{
			String cid=(String)session.getAttribute("customerid");
			long Anum=Long.parseLong(request.getParameter("Anumber"));
			double b=ics.balance(Anum,cid);
			try {
			if(b!=0)
			{
			session.setAttribute("message", b);
			response.sendRedirect("view.jsp");
			}
			else {throw new BankException();}
		}
		catch(Exception e)
		{
			String s1="Anumber not found";
			System.out.println(s1+e);
			session.setAttribute("message", s1+"  "+e);
			response.sendRedirect("view.jsp");}}
		
		else if(need.equals("password"))
		{
			String s=(String)request.getParameter("cname");
			String s1=(String)request.getParameter("password");
			String s2=(String)request.getParameter("password1");
			String s3=(String)request.getParameter("password2");
			boolean b=ics.passwordChange(s1,s2,s);
			if(b==true&&s2.equals(s3))
			{
				message="password Changed!!";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
			else if(b==false)
			{
				message="password incorrect";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
			else 
			{
				message="reenter password incorrect";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
		}
		else 
		{
			String cid=(String)session.getAttribute("customerid");
			 l= ics.ministatement(cid);
			 session.setAttribute("list",l);
			 System.out.println();
			 response.sendRedirect("miniprint.jsp");
		}
		
		

	
	

	
	}

}
