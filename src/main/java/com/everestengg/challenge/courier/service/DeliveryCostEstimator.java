/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service;

import java.util.List;

import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.model.PackageDetailsSummary;
import com.everestengg.challenge.courier.model.VehicleDetails;

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
	List<DeliveryEstimate> calculateDeliveryCost(PackageDetailsSummary packageDetailsSummary, List<PackageDetails> packageDetails);	
	
	/**
	 * @param packageDetailsSummary
	 * @param packageDetails
	 * @param vehicleDetails 
	 * @return
	 */
	List<DeliveryEstimate> calcualteDeliveryTime(PackageDetailsSummary packageDetailsSummary, List<PackageDetails> packageDetails, VehicleDetails vehicleDetails);

}
