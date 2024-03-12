package com.ride.service;

public interface RideSharingService {

	public void addDriver(String addDriver);
	public void addRider(String addRider);
	public String match(String match);
	public String startRide(String startRide);
	public String stopRide(String stopRide);
	public String bill(String bill);
}
