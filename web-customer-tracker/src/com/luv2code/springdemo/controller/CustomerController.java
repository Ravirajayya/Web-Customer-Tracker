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

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listOfCustomer(Model model, @RequestParam(required=false) String sort)
	{
		// get customers from the service
		List<Customer> listOfCustomers = null;
		
		// check for sort field
		if( sort != null )
		{
			int theSortField = Integer.parseInt(sort);
			listOfCustomers = customerService.getCustomers(theSortField);			
		}
		else 
		{
			// no sort field provided ... default to sorting by last name
			listOfCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		// add the customers to the model
		model.addAttribute("customer", listOfCustomers);
		
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String addCustomer(Model model)
	{
		// create model attribute to bind form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		// save the customer using our service
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFromForupdate")
	public String showFormForupdate(@RequestParam("customerId") int theId, Model model)
	{
		// Get Customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// Set customer as a model attribute to pre-populate form
		model.addAttribute("customer", theCustomer);
		
		// Sent over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		// Get Customer from service
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customer", theCustomers);

        return "list-customer";        
    }
}
