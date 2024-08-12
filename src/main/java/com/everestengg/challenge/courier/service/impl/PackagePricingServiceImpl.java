/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties;
import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties.CouponCode;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.service.PackagePricingService;

/**
 * @author Rangel
 * 
 */
@Service
public class PackagePricingServiceImpl implements PackagePricingService {

	@Autowired
	private DiscountCouponCodeProperties discountOffers;

	@Override
	public double calculateDiscount(PackageDetails packageDetails, int deliveryCost) {
		if (discountOffers.getCouponCodesMap().containsKey(packageDetails.getOfferCode())) {
			CouponCode offer = discountOffers.getCouponCodesMap().get(packageDetails.getOfferCode());
			int actualWeight = packageDetails.getPkgWeightInKg();
			int actualDistance = packageDetails.getDistanceInKm();
			if (actualWeight >= offer.getMinWeight() && actualWeight <= offer.getMaxWeight()
					&& actualDistance >= offer.getMinDistance() && actualDistance <= offer.getMaxDistance()) {
				int discountPercentage = offer.getDiscountInPercentage();
				return discountPercentage * 0.01 * deliveryCost;
			}
		}
		return 0;
	}

	@Override
	public int deliveryCost(int baseDeliveryCost, int weight, int distance) {
		return baseDeliveryCost + (weight * 10) + (distance * 5);
	}
	
	
}
