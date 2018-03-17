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
		
		System.out.println("\n\n Deleted customer is: " + currentSession.get(Customer.class, theId) + "\n\n");
		
		
		// delete the customer with id
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
		
		
	}

	@Override
	public List<Customer> searchCustomers(String theName) {
		
		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query to get Customers using Name
		Query<Customer> theQuery = null;
		
		// Search only by the name if name is not empty
		if (theName != null && theName.trim().length() > 0 ) {
			theQuery = currentSession.createQuery("from Customer where lower(firstName) like :searchName  or lower(lastName) like :searchName ", Customer.class); 
			theQuery.setParameter("searchName", "%" + theName.toLowerCase() + "%" );
		}
		else {
			theQuery = currentSession.createQuery("from Customer",Customer.class);
		}
		
		// get the results
		List<Customer> theCustomers = theQuery.getResultList();
		
		
		// return results
		return theCustomers;
	}

}
