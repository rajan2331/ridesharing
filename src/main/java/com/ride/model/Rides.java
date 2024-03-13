package com.ride.model;

public class Rides {

	private String timeTaken;
	private Coordinates coordinates;
	
	
	public Rides(String timeTaken, Coordinates coordinates) {
		super();
		this.timeTaken = timeTaken;
		this.coordinates = coordinates;
	}
	
	public Rides() {
	}

	public String getTimeTaken() {
		return timeTaken;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	
	
}
