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
public interface DeliveryCostEstimator {

	/**
	 * @param packageDetailsSummary 
	 * @param packageDetails
	 * @return
	 */
	List<DeliveryEstimate> calculateDeliveryCost(PackageSummary packageDetailsSummary, List<Package> packageDetails);	
	
	/**
	 * @param packageDetailsSummary
	 * @param packageDetails
	 * @param vehicleDetails 
	 * @return
	 */
	List<DeliveryEstimate> calcualteDeliveryTime(PackageSummary packageDetailsSummary, List<Package> packageDetails, Vehicle vehicleDetails);

}
