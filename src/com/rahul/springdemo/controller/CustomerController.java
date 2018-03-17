package com.rahul.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rahul.springdemo.entity.Customer;
import com.rahul.springdemo.service.CustomerService;

// We want our requests in such a way that whenever a url encounters /customer it will be redirected to CustomerController.
// CustomerController handles the requests that start with /customer and when it encounters / list then it returns the
// corresponding jsp page.

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// we have to first inject DAO into this class. Now changed to service because service layer is added in between.
	// Dependency Injection
	@Autowired
	private CustomerService customerService;
	
	// Only GET method works here. So its better to use GetMapping instead of RequestMapping. 
	// @RequestMapping(path="/list", method = RequestMethod.GET). Both are same.
	@GetMapping("/list")   // We are only handling get method.
	public String listCustomer(Model theModel) {
		
		// get customers from DAO. Now we ask service layer for data which in turn asks DAO.
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add customers to springMVC model
		theModel.addAttribute("customers",theCustomers);  // We are pushing the data we got in the above line from service into the model, which can be used in JSP pages.
		
		// To check the data in console
		for ( Customer customer : theCustomers) {
			System.out.println(customer);
		}
				
		return "list-customer";
	}

}
