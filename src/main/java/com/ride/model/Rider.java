package com.ride.model;

public class Rider {

	private String riderId;
	private Double xAxis;
	private Double yAxis;
	public Rider(String riderId, Double xAxis, Double yAxis) {
		super();
		this.riderId = riderId;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	public Double getxAxis() {
		return xAxis;
	}
	
	public Double getyAxis() {
		return yAxis;
	}
	
	public String getRiderId() {
		return riderId;
	}
}
