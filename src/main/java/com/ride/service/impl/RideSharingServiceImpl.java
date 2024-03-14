package com.ride.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ride.model.Coordinates;
import com.ride.model.Driver;
import com.ride.model.Rider;
import com.ride.model.Rides;
import com.ride.service.RideSharingService;
import com.ride.util.Constants;
import com.ride.util.RideShareUtil;

@Service
public class RideSharingServiceImpl implements RideSharingService{

	List<Driver> drivers = new ArrayList();
	List<Rider> riders = new ArrayList();
	HashMap<String,String> matched = new HashMap();
	List<String> ridesStopped = new ArrayList();
	HashMap<String,Rides> rides = new LinkedHashMap();
	HashMap<String,String> riderRideMapping = new HashMap();
	HashMap<String,String> driverRideMapping = new HashMap();
	
	@Override
	public void addDriver(String addDriver) {
		String[] driverArr = addDriver.split(Constants.space);
		drivers.add(new Driver(driverArr[Constants.int_one], Double.valueOf(driverArr[Constants.int_two]),Double.valueOf(driverArr[Constants.int_three])));
	}

	@Override
	public void addRider(String addRider) {
		String[] riderArr = addRider.split(Constants.space);
		riders.add(new Rider(riderArr[Constants.int_one], Double.valueOf(riderArr[Constants.int_two]),Double.valueOf(riderArr[Constants.int_three])));
	}

	@Override
	public String match(String match) {	
		String[] matchArr = match.split(Constants.space);
		String riderId = matchArr[Constants.int_one];
		Rider rider = riders.stream()
				  .filter(riders -> riderId.equals(riders.getRiderId()))
				  .findAny()
				  .orElse(null);
		HashMap<String,Double> matchedDrivers = new HashMap();
		// calculating distance
		for(Driver driver : drivers)
		{	  
		    double result = RideShareUtil.calculateDistance(driver.getxAxis(), driver.getyAxis(), 
		    		rider.getxAxis(), rider.getyAxis());
		    if(result<=Constants.nearesDistance)
		    	matchedDrivers.putIfAbsent(driver.getDriverId(), result);
		}
		// This is done to sort the map by nearest distance to rider
		Map<String,Double> sortedMap = RideShareUtil.sortByValue(matchedDrivers);
		//Preparing response
		String drivers = String.join(Constants.space, sortedMap.keySet());
		matched.putIfAbsent(riderId, drivers);
		return drivers;
	}

	@Override
	public String startRide(String startRide) {
		String[] rideArr = startRide.split(Constants.space);
		String rideId = rideArr[Constants.int_one];
		if(riderRideMapping.containsKey(rideId))
			return "INVALID_RIDE";
		
		Integer n = Integer.valueOf(rideArr[Constants.int_two]);
		String rider = rideArr[Constants.int_three];
		String drivers  = matched.get(rider);
		String[] matchedDriverArr = drivers.split(Constants.space);
		riderRideMapping.put(rideId,rider);
		if(matchedDriverArr.length<n)
			return "INVALID_RIDE";
		
		driverRideMapping.putIfAbsent(rideId, matchedDriverArr[n-Constants.int_one]);
		return "RIDE_STARTED "+rideId;
	}

	@Override
	public String stopRide(String stopRide) {
		String[] rideArr = stopRide.split(Constants.space);
		String rideId = rideArr[Constants.int_one];
		if(ridesStopped.contains(rideId))
			return "INVALID_RIDE";
		else
			ridesStopped.add(rideId);
		Coordinates coordinates = new Coordinates(Double.valueOf(rideArr[Constants.int_two]),Double.valueOf(rideArr[Constants.int_three]));
		Rides ride = new Rides(rideArr[Constants.int_four],coordinates);
		rides.putIfAbsent(rideId, ride);
		
		return "RIDE_STOPPED "+rideId;
	}

	@Override
	public String bill(String bill) {
		
		String[] billArr = bill.split(Constants.space);
		String rideId= billArr[Constants.int_one];
		Rides ride = rides.get(rideId);
		Coordinates rideCoord= ride.getCoordinates();
		Rider riderCoord = riders.stream()
		  .filter(riders -> riderRideMapping.get(rideId).equals(riders.getRiderId()))
		  .findAny()
		  .orElse(null);
		double result =  RideShareUtil.calculateDistance(rideCoord.getxAxis(), rideCoord.getyAxis(), riderCoord.getxAxis(), riderCoord.getyAxis());
		double amount= 	RideShareUtil.calculateBill( result, Double.valueOf(ride.getTimeTaken()));
	  	return "BILL " + rideId +Constants.space+ driverRideMapping.get(rideId)+Constants.space+amount;
	}

}
