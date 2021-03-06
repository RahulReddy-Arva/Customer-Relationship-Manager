package com.rahul.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// Create a model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	
	
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		System.out.println("\n\n SAved a new Customer : " + theCustomer + "\n\n");
		
		// We have to redirect our page automatically to customer/list mapping after details are saved.
		return "redirect:/customer/list";
	}
	
	
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the customer from database who has the above id, theId
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set the customer as a model aattribute to pre-populate the form 
		theModel.addAttribute("customer",theCustomer);
		
		System.out.println("\n\n A customer has been updated: " + theCustomer + "\n\n");
		
		// send over to our form
		return "customer-form";
	}

	
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		 
		
		return "redirect:/customer/list";
	}

	
	
	@PostMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("theSearchName")String theName , Model theModel ) {
		
		// Search Customers from service
		List<Customer> theCustomers = customerService.searchCustomers(theName);
		
		//add the customers to model
		// Assign the model name correctly. The model used in jsp is customers . Check the name and assign it.
		theModel.addAttribute("customers" , theCustomers);
		
		System.out.println("\n\n" + "The customers after search are : ");
		for( Customer customer : theCustomers ) {
			System.out.println(customer);
		}
		System.out.println("\n\n");
		
		
		return "list-customer";
	}
	
	
	
	
	
	
	
	
	
	
}
