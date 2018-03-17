package com.rahul.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rahul.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject session Factory for DAO to talk to database
	// DEpendency Injection
	
	@Autowired
	private SessionFactory sessionFactory; // name should be same as in spring-mvc-crud-demo-servlet.xml file.
	
	@Override
	//@Transactional // Manages all the begin and end transactions . Moved to service layer as transactions are managed from there.
	public List<Customer> getCustomers() {
		
		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... Sort by lastName.
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName" , Customer.class);
		
		// execute the query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return result list
		return customers;
		
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query to save data to database
		// As we are using the same method to both save and update the data, we use hibernate's saveOrUpdate method.
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve the data from database using the primary key Id.
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		// return the result 
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get a hiberante session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the customer with id
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
		
	}

}
