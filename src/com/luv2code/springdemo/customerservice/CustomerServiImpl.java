package com.luv2code.springdemo.customerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service  // @component did his magic here:-
public class CustomerServiImpl implements CustomerService 
{
    @Autowired
    private CustomerDAO customerDAO;
    
	@Override
	@Transactional
	public List<Customer> getCustomers() 
	{
	  return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) 
	{
		customerDAO.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theID) 
	{
		return  customerDAO.getCustomer(theID);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) 
	{
	     customerDAO.deleteCustomer(theId);	
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) 
	{
		
		return customerDAO.searchCustomers(theSearchName);
	}

}
