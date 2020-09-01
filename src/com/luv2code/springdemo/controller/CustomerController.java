package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.customerservice.CustomerService;
import com.luv2code.springdemo.entity.Customer;


@Controller
@RequestMapping("/customers")
public class CustomerController 
{
//inject the DAO in the controller
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model theModel)
	{
		//get customers from the DAO
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customer";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
		//the model.addAttribute we are bind the theCustomer object with page
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//save the customer using our service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customers/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormforCustomer(@RequestParam("customerId")int theID,Model theModel)
	{
		//get customer from customerService
		Customer theCustomer = customerService.getCustomer(theID);
		
		//set customer as model attribute
		theModel.addAttribute(theCustomer);
		
		//send over to form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		//delete the customer
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customers/list";
		
	}
	
	@GetMapping("search")
	public String searchCustomer(@RequestParam("theSearchName")String theSearchName ,Model theModel)
	{
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		theModel.addAttribute("customers",theCustomers);
		return "list-customer";
	}

}

