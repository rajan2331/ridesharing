package com.ride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ride.service.RideSharingService;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	
	@Autowired
	RideSharingService service;
    @Override
    public void run(String...args) throws Exception {
    	for(String arg : args)
    	{
    		
    		if(arg.contains("ADD_DRIVER"))
    		{	service.addDriver(arg);
    			break;
    		}	if(arg.contains( "ADD_RIDER")){
    			service.addRider(arg);
    			break;}
    		if(arg.contains( "MATCH")){
    			System.out.println(service.match(arg));
    			break;}
    		if(arg.contains( "START_RIDE")){
    			System.out.println(service.startRide(arg));
    			break;}
    		if(arg.contains( "STOP_RIDE")){
    			System.out.println(service.stopRide(arg));
    			break;}
    		if(arg.contains( "BILL"))
    				{		System.out.println(service.bill(arg));
    			break;
    				}else
    			System.out.println("Unknown");

    		}
    	
    	
    }
}
