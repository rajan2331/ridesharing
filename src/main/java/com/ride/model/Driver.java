package com.ride.model;

public class Driver {

	private String driverId;
	private Double xAxis;
	private Double yAxis;
	public Driver(String driverId, Double xAxis, Double yAxis) {
		super();
		this.driverId = driverId;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	public Double getxAxis() {
		return xAxis;
	}
	
	public Double getyAxis() {
		return yAxis;
	}
	
	public String getDriverId() {
		return driverId;
	}
	
	
}
