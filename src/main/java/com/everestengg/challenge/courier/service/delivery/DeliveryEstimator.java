/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.delivery;

import java.util.List;

import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.model.PackageSummary;
import com.everestengg.challenge.courier.model.Vehicle;

/**
 * @author Rangel
 * 
 */
public interface DeliveryEstimator {

	/**
	 * @param packageSummary 
	 * @param packages
	 * @return the delivery estimate for the packages including delivery cost
	 */
	List<DeliveryEstimate> calculateDeliveryCost(PackageSummary packageSummary, List<Package> packages);	
	
	/**
	 * @param packageSummary
	 * @param packages
	 * @param vehicleDetails 
	 * @return the delivery estimate for the packages including delivery cost & delivery time
	 */
	List<DeliveryEstimate> calcualteDeliveryTime(PackageSummary packageSummary, List<Package> packages, Vehicle vehicleDetails);

}
