package com.thrillcity.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.thrillcity.exceptions.ActivityException;
import com.thrillcity.exceptions.CustomerException;
import com.thrillcity.exceptions.TicketException;
import com.thrillcity.model.Activity;
import com.thrillcity.model.ActivityDTO;
import com.thrillcity.model.Customer;
import com.thrillcity.model.CustomerDTO;
import com.thrillcity.model.Ticket;
import com.thrillcity.repository.ActivityRepository;
import com.thrillcity.repository.CustomerRepository;

import ch.qos.logback.core.joran.spi.ActionException;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		List<Activity> activities = customer.getActivities();
		for(Activity activity: activities) {
			activity.getCustomers().add(customer);
		}
		Customer customer2 = customerRepository.save(customer);
		return customer2;
	}
	

	@Override
	public Customer updateCustomer(Integer customerId, String address, String mobileNumber, String email) throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer cust = opt.get();
			cust.setAddress(address);
			cust.setMobileNumber(mobileNumber);
			cust.setEmail(email);
			customerRepository.save(cust);
			return cust;
		}
		else throw new CustomerException("Customer not found: "+customerId);
	}
	

	@Override
	public Customer deleteCustomer(Integer customerID) throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findById(customerID);
		if (opt.isPresent()) {
			Customer customer = opt.get();
			customerRepository.delete(customer);
			return customer;
		} else {
			throw new CustomerException("No customer found with the ID:" + customerID);
		}
	}
	
	@Override
	public CustomerDTO getCustomerAllDetails(Integer customerId) throws CustomerException {
		
		Optional<CustomerDTO> custDTO= customerRepository.getCustomerAllDetails(customerId);
		if(custDTO.isEmpty()) throw new CustomerException("Customer not available with this id = "+ customerId);
		CustomerDTO cdto= custDTO.get();
		return cdto;
	}
	
	
	@Override
	public List<CustomerDTO> getAllCustomer() throws CustomerException {
		
		List<CustomerDTO> custDtos= customerRepository.getAllCustomer();
		if(custDtos.isEmpty()) throw new CustomerException("Record not found");
		return custDtos;
	}
	
	

	@Override
	public List<ActivityDTO> getAllActivities() throws ActivityException {
		
		List<ActivityDTO> list = activityRepository.getOnlyActivityDetails();
		if(list == null) {
			throw new ActivityException("No activities found.");
		}
		return list;
	}
	
	

	@Override
	public Customer useActivity(Integer activityId) throws CustomerException, ActivityException {
		return null;
	}


	@Override
	public List<CustomerDTO> listOfCustomers(LocalDate d1, LocalDate d2) throws CustomerException, ActivityException {
		
		List<Customer> list = customerRepository.findAll();
		
		if(list.size() == 0) {
			throw new CustomerException("no customer exist in the system");
		}
		
		System.out.println(list);
		
		System.out.println(list.size());
		List<CustomerDTO> list2 = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			Ticket t = list.get(i).getTickets();
			if(t.getDateTime().isAfter(d1.atStartOfDay()) && t.getDateTime().isBefore(d2.atStartOfDay())) {
				Integer id = list.get(i).getCustomerID();
				String email = list.get(i).getEmail();
				String address = list.get(i).getAddress();
				LocalDate dob = list.get(i).getDob();
				String mobileNumber = list.get(i).getMobileNumber();
				CustomerDTO cd = new CustomerDTO(id, address, mobileNumber, email, dob);
				list2.add(cd);
			}
		}
		
		if(list2.isEmpty()) throw new CustomerException("No customer found between these dates");
		System.out.println("\n" + list2);
		return list2;
	}
}
