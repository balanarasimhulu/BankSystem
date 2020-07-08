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
import javax.websocket.Session;

import org.apache.catalina.ant.SessionsTask;

import com.dxc.pojos.BankException;
import com.dxc.pojos.Customer;
import com.dxc.services.AdminServices;
import com.dxc.services.IAdminServices;

@WebServlet("/bank")
public class BankServlet extends HttpServlet
{
	List<Customer> list=new ArrayList<>();
	IAdminServices ias =new AdminServices();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String operation="";
		String a=request.getParameter("btn");
		if(a!=null)
		{operation=request.getParameter("btn");}
		if(operation.equals("login as admin"))
		{	String message;
			String adminid=request.getParameter("adminid");
			String password=request.getParameter("password");
			boolean status=ias.loginCredentials(adminid,password);
			if(status==true)
			{	System.out.println("logged in!");
				message="Login successfull!!";
				session.setAttribute("login", message);
				response.sendRedirect("adminoperations.jsp");}
			else
			{	message="Login credentials incorrect!!";
				session.setAttribute("login", message);
				response.sendRedirect("login.jsp");}}
		else if(operation.equals("createCustomer"))
		{
			String Cname=request.getParameter("Cname");
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			Double Abalance=Double.parseDouble(request.getParameter("Abalance"));
			
			String s=ias.createCustomer(Cname,Anumber,Abalance);
			session.setAttribute("message", s);
			response.sendRedirect("view.jsp");
			
		}
		else if(operation.equals("search"))
		{
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			String s=ias.SearchAnumber(Anumber);
			try {
			if(s=="present")
			{
			List<Customer> list=ias.SearchCustomer(Anumber);
			session.setAttribute("list", list);
			System.out.println(list);
			response.sendRedirect("viewlist.jsp");			
			}
			else {throw new BankException();}
			}
			catch(Exception e)
			{
				String s1="Anumber not found";
				System.out.println(s1+e);
				session.setAttribute("message", s1+"  "+e);
				response.sendRedirect("view.jsp");
				
				
			}
			
		}
		else if(operation.equals("searchModify"))
		{
			Long Anumber=Long.parseLong(request.getParameter("Anumber"));
			session.setAttribute("Anumber", Anumber);
			String s=ias.SearchAnumber(Anumber);
			try {
			if(s=="present")
			{
					session.setAttribute("message",s);
					response.sendRedirect("Modifydetails.jsp");
			}
			else
			{
				throw new BankException();
			}
			}catch(Exception e) 
			{

				s="account number not found";
				System.out.println(s+e);
				session.setAttribute("message",s+"  "+e);
				response.sendRedirect("view.jsp");
			}
			
		}
		else if(operation.equals("updatedetails"))
		{
			String Cname=request.getParameter("Cname");
			Double Abalance=Double.parseDouble(request.getParameter("Abalance"));
			long Anumber=(long)session.getAttribute("Anumber");
			
			String message=ias.updatedetails(Cname,Anumber,Abalance);
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
		}
		else if(operation.equals("balance"))
		{
			long Anumber=Long.parseLong(request.getParameter("Anumber"));
			String s=ias.SearchAnumber(Anumber);
			try {
			if(s=="present")
			{
			double balance=ias.balanceInquiry(Anumber);
			session.setAttribute("message", balance);
			response.sendRedirect("view.jsp");
			}
			else
			{
				throw new BankException();
			}
			}catch(Exception e) 
			{

				s="account number not found";
				System.out.println(s+e);
				session.setAttribute("message",s+"  "+e);
				response.sendRedirect("view.jsp");
			}
			
			
		}
		else if(operation.equals("delete"))
		{
			long Anumber=Long.parseLong(request.getParameter("Anumber"));
			String s=ias.deleteAccount(Anumber);
			session.setAttribute("message", s);
			response.sendRedirect("view.jsp");
		}
		else
		{
			List<Customer> l1=ias.display();
			session.setAttribute("list", l1);
			response.sendRedirect("viewlist.jsp");			
		}
		
		
		
		
		
		
		
	}
	

}
