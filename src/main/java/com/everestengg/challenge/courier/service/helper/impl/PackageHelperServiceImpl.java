/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.helper.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties;
import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties.CouponCode;
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.service.helper.PackageHelperService;

/**
 * @author Rangel
 * 
 */
@Service
public class PackageHelperServiceImpl implements PackageHelperService {

	@Autowired
	private DiscountCouponCodeProperties discountOffers;

	@Override
	public int applyDiscount(Package packageDetails, int deliveryCost) {
		if (discountOffers.getCouponCodesMap().containsKey(packageDetails.getOfferCode())) {
			CouponCode offer = discountOffers.getCouponCodesMap().get(packageDetails.getOfferCode());
			int actualWeight = packageDetails.getPkgWeightInKg();
			int actualDistance = packageDetails.getDistanceInKm();
			if (actualWeight >= offer.getMinWeight() && actualWeight <= offer.getMaxWeight()
					&& actualDistance >= offer.getMinDistance() && actualDistance <= offer.getMaxDistance()) {
				int discountPercentage = offer.getDiscountInPercentage();
				Double discountInDouble = discountPercentage * 0.01 * deliveryCost;
				return discountInDouble.intValue();
			}
		}
		return 0;
	}

	@Override
	public int deliveryCost(int baseDeliveryCost, int weight, int distance) {
		return baseDeliveryCost + (weight * 10) + (distance * 5);
	}

	@Override
	public double deliveryTime(int distance, int speed) {
		if(speed != 0) {
			double time = (double)distance/speed;
			DecimalFormat df = new DecimalFormat("0.##");
		    df.setRoundingMode(RoundingMode.DOWN);
			return Double.valueOf(df.format(time));
		}
		return 0;
	}
	
}
