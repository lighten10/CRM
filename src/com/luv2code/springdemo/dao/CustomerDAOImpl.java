package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO 
{
	//need to inject session Factory in dao class
	@Autowired 
	private SessionFactory sessionFactory;
	
	
@Override
	public List<Customer> getCustomers()
	{
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		//create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName",Customer.class);
		
		//execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results;
		 
		return customers;
	}


@Override
public void saveCustomer(Customer theCustomer) 
{
	Session currentSession = sessionFactory.getCurrentSession();
	
	currentSession.saveOrUpdate(theCustomer);
	
}


@Override
public Customer getCustomer(int theID) 
{
	// get hibernate session
	Session currentSession = sessionFactory.getCurrentSession();
	
	//now reterive/read from database  using primary key
	Customer theCustomer = currentSession.get(Customer.class, theID);
	
	
	//return customer;
	
	 return theCustomer;
}


@Override
public void deleteCustomer(int theId) 
{
//get hibernate session
	Session currentSession = sessionFactory.getCurrentSession();
	
	//delete object using primary key
	Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
	
	theQuery.setParameter("customerId", theId);
	
	
	theQuery.executeUpdate();
	
	
}


@Override
public List<Customer> searchCustomers(String theSearchName) 
{
	Session currentSession = sessionFactory.getCurrentSession();
	
	Query theQuery = null;
	if(theSearchName!=null && theSearchName.trim().length()>0)
	{
		theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName",Customer.class);
		theQuery.setParameter("theName","%"+theSearchName.toLowerCase()+"%");
	}
	else
	{
		theQuery = currentSession.createQuery("from Customer",Customer.class);
		
	}
	List<Customer> customers = theQuery.getResultList();
	
	return customers;
}

}
