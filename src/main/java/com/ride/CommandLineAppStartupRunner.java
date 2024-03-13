package com.ride;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    	
    	 Path path = Paths.get(args[0]); //"D://Personal/input.txt"

    	    List<String> read = Files.readAllLines(path);
    	   
    	    
    	    
    	for(String arg : read)
    	{
    		
    		if(arg.contains("ADD_DRIVER"))
    		{	service.addDriver(arg);
    			
    		}else	if(arg.contains( "ADD_RIDER")){
    			service.addRider(arg);
    			}
    		else	if(arg.contains( "MATCH")){
    			System.out.println("DRIVERS_MATCHED " +service.match(arg));
    			}
    		else if(arg.contains( "START_RIDE")){
    			System.out.println(service.startRide(arg));
    			}
    		else if(arg.contains( "STOP_RIDE")){
    			System.out.println(service.stopRide(arg));
    			}
    		else if(arg.contains( "BILL"))
    				{		System.out.println(service.bill(arg));
    			
    				}else
    			System.out.println("Unknown");

    		}
    	
    	
    }
}
