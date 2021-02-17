package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDao;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	@Transactional
	public List<Customer> getCustomer() 
	{
		return customerDao.getCustomer();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustommer) {
		customerDao.saveCustomer(theCustommer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId)
	{
		return customerDao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDao.deleteCustomer(theId);
	}

	@Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {

        return customerDao.searchCustomers(theSearchName);
    }

	@Override
    @Transactional
	public List<Customer> getCustomers(int theSortField) {
		return customerDao.getCustomers(theSortField);
	}

}
