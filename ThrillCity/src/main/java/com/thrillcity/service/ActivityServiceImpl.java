package com.thrillcity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thrillcity.exceptions.ActivityException;
import com.thrillcity.exceptions.CustomerException;
import com.thrillcity.model.Activity;
import com.thrillcity.model.ActivityDTO;
import com.thrillcity.model.Customer;
import com.thrillcity.repository.ActivityRepository;
import com.thrillcity.repository.CustomerRepository;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Activity insertActivity(Activity activity) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity updateActivity(Activity activity) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity deleteActivity(Integer activityId) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> viewChargesOfActivities(Double charges) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityDTO> getAllActivityDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
