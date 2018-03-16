package com.rahul.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rahul.springdemo.dao.CustomerDAO;
import com.rahul.springdemo.entity.Customer;

// We want our requests in such a way that whenever a url encounters /customer it will be redirected to CustomerController.
// CustomerController handles the requests that start with /customer and when it encounters / list then it returns the
// corresponding jsp page.

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// we have to first inject DAO into this class.
	// Dependency Injection
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/list")
	public String listCustomer(Model theModel) {
		
		// get customers from DAO
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// add customers to springMVC model
		theModel.addAttribute("customers",theCustomers);  // We are pushing the data we got in line 30 into the model, which can be used in JSP pages.
		
		// To check the data in console
		for ( Customer customer : theCustomers) {
			System.out.println(customer);
		}
				
		return "list-customer";
	}

}
