package com.rahul.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject session Factory for DAO to talk to database
	// DEpendency Injection
	
	@Autowired
	private SessionFactory sessionFactory; // name should be same as in spring-mvc-crud-demo-servlet.xml file.
	
	@Override
	@Transactional // Manages all the begin and end transactions
	public List<Customer> getCustomers() {
		
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer" , Customer.class);
		
		// execute the query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return result list
		return customers;
		
	}

}
