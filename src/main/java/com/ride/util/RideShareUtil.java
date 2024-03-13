package com.ride.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RideShareUtil {

	public static Double calculateDistance(Double xAxis,Double yAxis ,Double xAxis2,Double yAxis2)
	{
		double deltaX = xAxis-xAxis2;
	    double deltaY = yAxis-yAxis2;
	    return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
	
	public static Double calculateBill(double distance, double time)
	{
		double billAmt = Constants.intialAmount+ distance*Constants.distanceFareMultiple +time*Constants.timeTakenMultiple;
		billAmt= billAmt+ (double)Constants.percentageMultiple/Constants.double_hundred * billAmt;
		BigDecimal bd = BigDecimal.valueOf(billAmt);
		bd = bd.setScale(Constants.int_two, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	public static HashMap<String, Double>  sortByValue(HashMap<String, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Entry<String, Double>> list
            = new LinkedList<Map.Entry<String, Double> >(
                hm.entrySet());
 
        // Sort the list using lambda expression
        Collections.sort(
            list,
            (i1,
             i2) -> i1.getValue().compareTo(i2.getValue()));
 
        // put data from sorted list to hashmap
        HashMap<String, Double> temp
            = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
