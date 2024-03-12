package com.ride.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.ride.model.Coordinates;
import com.ride.service.RideSharingService;
@Service
public class RideSharingServiceImpl implements RideSharingService{

	Map<String,Coordinates> drivers = new HashMap();
	Map<String,Coordinates> riders = new HashMap();
	@Override
	public void addDriver(String addDriver) {
		String[] driverArr = addDriver.split(" ");
		Coordinates user = new Coordinates();
		user.setxAxis(driverArr[1]);
		user.setyAxis(driverArr[2]);
		drivers.putIfAbsent(driverArr[0], user);
	}

	@Override
	public void addRider(String addRider) {
		String[] riderArr = addRider.split(" ");
		Coordinates user = new Coordinates();
		user.setxAxis(riderArr[1]);
		user.setyAxis(riderArr[2]);
		drivers.putIfAbsent(riderArr[0], user);
	}

	@Override
	public String match(String match) {	
		String[] matchArr = match.split(" ");
		String riderId = matchArr[1];
		Coordinates rider = riders.get(riderId);
		StringBuilder matched = new StringBuilder();
		List<String> matchedDriver = new ArrayList();
		for(Entry<String,Coordinates> entry : drivers.entrySet())
		{	double deltaX = Double.valueOf(entry.getValue().getxAxis())-Double.valueOf(rider.getxAxis());
		    double deltaY = Double.valueOf(entry.getValue().getyAxis())-Double.valueOf(rider.getyAxis());
		    double result = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		    if(result<5)
		    {
		    	matchedDriver.add(entry.getKey());
		    }
		
		} return String.join(" ", matchedDriver);
	}

	@Override
	public String startRide(String startRide) {
		// TODO Auto-generated method stub
		return "started";
	}

	@Override
	public String stopRide(String stopRide) {
		// TODO Auto-generated method stub
		return "stopped";
	}

	@Override
	public String bill(String bill) {
		// TODO Auto-generated method stub
		return "FREE";
	}

	public static void main(String[] args) {
		
		double deltaX = 4 - 0;
	    double deltaY = 4 - 0d;
	    double result = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	    System.out.println(result);
	}
}
