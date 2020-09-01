package com.luv2code.springdemo.customerservice;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService 
{
    public List<Customer> getCustomers();
    public void saveCustomer(Customer theCustomer);
	public Customer getCustomer(int theID);
	public void deleteCustomer(int theId);
	public List<Customer> searchCustomers(String theSearchName);
}
