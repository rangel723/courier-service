/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.model.PackageDetailsSummary;
import com.everestengg.challenge.courier.model.VehicleDetails;
import com.everestengg.challenge.courier.service.DeliveryCostEstimator;
import com.everestengg.challenge.courier.service.PackagePricingService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 * 
 */
@Slf4j
@Component
public class DeliveryCostEstimatorImpl implements DeliveryCostEstimator {
	
	@Autowired
	private PackagePricingService packagePricing;
	
	@Override
	public List<DeliveryEstimate> calculateDeliveryCost(PackageDetailsSummary packageDetailsSummary,
			List<PackageDetails> packageDetails) {
		List<DeliveryEstimate> estimate = new ArrayList<>();
		for(PackageDetails pkg: packageDetails) {
			estimate.add(calculateDeliveryCost(packageDetailsSummary, pkg));
		}
		log.debug("Result = "+ estimate);
		return estimate;
	}
	
	private DeliveryEstimate calculateDeliveryCost(PackageDetailsSummary packageDetailsSummary,
			PackageDetails pkg) {
		int baseDeliveryCost = packageDetailsSummary.getBaseDeliveryCost();
		int deliveryCost = packagePricing.deliveryCost(baseDeliveryCost, pkg.getPkgWeightInKg(), pkg.getDistanceInKm());
		int discount = packagePricing.applyDiscount(pkg, deliveryCost);
		int discountedPrice = deliveryCost - discount;
		return DeliveryEstimate.builder().pkgId(pkg.getPkgId()).discount(discount).totalCost(discountedPrice).build();
	}

	@Override
	public List<DeliveryEstimate> calcualteDeliveryTime(PackageDetailsSummary packageDetailsSummary,
			List<PackageDetails> packageDetails, VehicleDetails vehicleDetails) {
		//TODO:- calculateDeliveryCost
		int noOfPackages = packageDetailsSummary.getNoOfPackages();
		int noOfVehicles = vehicleDetails.getNoOfVehicles();
		int maxSpeed = vehicleDetails.getMaxSpeed();
		int maxCarriableWeight = vehicleDetails.getMaxCarriableWeight();
		
		List<Integer> items = new ArrayList<>();
		items.add(50);
		items.add(75);
		items.add(175);
		items.add(110);
		items.add(155);
		int max = 200;
		
		return null;
	}
	

}
