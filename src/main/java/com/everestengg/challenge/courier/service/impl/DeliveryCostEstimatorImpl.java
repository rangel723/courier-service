/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.model.PackageDetailsSummary;
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
	public List<DeliveryEstimate> calcualteDeliveryCost(PackageDetailsSummary packageDetailsSummary,
			List<PackageDetails> packageDetails) {
		List<DeliveryEstimate> estimate = new ArrayList<>();
		int baseDeliveryCost = packageDetailsSummary.getBaseDeliveryCost();
		for(PackageDetails pkg: packageDetails) {
			int deliveryCost = packagePricing.deliveryCost(baseDeliveryCost, pkg.getPkgWeightInKg(), pkg.getDistanceInKm());
			int discount = packagePricing.calculateDiscount(pkg, deliveryCost);
			int discountedPrice = deliveryCost - discount;
			estimate.add(DeliveryEstimate.builder().pkgId(pkg.getPkgId()).discount(discount).totalCost(discountedPrice).build());
		}
		log.debug("Result = "+ estimate);
		return estimate;
	}

	@Override
	public List<DeliveryEstimate> calcualteDeliveryTime(PackageDetailsSummary packageDetailsSummary,
			List<PackageDetails> packageDetails) {
		// TODO Auto-generated method stub
		return null;
	}


}
