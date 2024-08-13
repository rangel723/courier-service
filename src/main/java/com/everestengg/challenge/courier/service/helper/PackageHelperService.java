/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.helper;

import com.everestengg.challenge.courier.model.Package;

/**
 * @author Rangel
 * 
 */
public interface PackageHelperService {

	/**
	 * @param packageDetails
	 * @param packagePrice
	 * @return
	 */
	int applyDiscount(Package packageDetails, int packagePrice);
	
	/**
	 * @param baseDeliveryCost
	 * @param weight
	 * @param distance
	 * @return the computed delivery cost based on the formula - <strong> Delivery cost = Base Delivery Cost + (Package Total Weight * 10) + (Distance to Destination * 5)</strong>  
	 */
	int deliveryCost(int baseDeliveryCost, int weight, int distance);
	
	/**
	 * 
	 * @param distance
	 * @param speed
	 * @return the delivery time. Formula - <strong> time = distance / speed </strong>
	 */
	double deliveryTime(int distance, int speed);
	
}
