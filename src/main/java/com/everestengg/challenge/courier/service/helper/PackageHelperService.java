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
	 * @return
	 */
	int deliveryCost(int baseDeliveryCost, int weight, int distance);
	
	/**
	 * 
	 * @param distance
	 * @param speed
	 * @return
	 */
	double deliveryTime(int distance, int speed);
	
}
