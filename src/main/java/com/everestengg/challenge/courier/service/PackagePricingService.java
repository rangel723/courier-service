/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service;

import com.everestengg.challenge.courier.model.PackageDetails;

/**
 * @author Rangel
 * 
 */
public interface PackagePricingService {

	/**
	 * @param packageDetails
	 * @param packagePrice
	 * @return
	 */
	int applyDiscount(PackageDetails packageDetails, int packagePrice);
	
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
	int calculateTime(int distance, int speed);
}
