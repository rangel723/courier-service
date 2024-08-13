/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties;
import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties.CouponCode;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.service.PackagePricingService;
import com.everestengg.challenge.courier.service.impl.PackagePricingServiceImpl;

/**
 * @author Rangel
 * 
 */
class PackagePricingServiceTest {

	@Mock
	DiscountCouponCodeProperties discountOffers;

	@InjectMocks
	PackagePricingService underTests = new PackagePricingServiceImpl();
	
	static final String OFR001 = "OFR001";
	static final String OFR002 = "OFR002";
	static final String OFR003 = "OFR003";

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void calculateDiscountTest() {
		Map<String, CouponCode> couponsMap = new HashMap<>();
		couponsMap.put(OFR001, CouponCode.builder().id(OFR001).minDistance(1).maxDistance(200).minWeight(70).maxWeight(200).discountInPercentage(10).build()); 
		couponsMap.put(OFR002, CouponCode.builder().id("OFR002").minDistance(50).maxDistance(150).minWeight(100).maxWeight(250).discountInPercentage(7).build()); 
		couponsMap.put(OFR003, CouponCode.builder().id("OFR003").minDistance(50).maxDistance(250).minWeight(10).maxWeight(150).discountInPercentage(5).build());
		when(discountOffers.getCouponCodesMap()).thenReturn(couponsMap);
		double result = underTests.applyDiscount(PackageDetails.builder().pkgId("pkg1").offerCode(OFR001).distanceInKm(10).pkgWeightInKg(20).build(), 100);
		assertEquals(0, result);
		result = underTests.applyDiscount(PackageDetails.builder().pkgId("pkg1").offerCode(OFR001).distanceInKm(10).pkgWeightInKg(80).build(), 100);
		assertEquals(10, result);
		result = underTests.applyDiscount(PackageDetails.builder().pkgId("pkg1").offerCode(OFR003).distanceInKm(100).pkgWeightInKg(10).build(), 700);
		assertEquals(35, result);
	}

	@Test
	void deliveryCostTest() {
		int result = underTests.deliveryCost(100, 10, 10);
		assertEquals(250, result);
		result = underTests.deliveryCost(100, 5, 5);
		assertEquals(175, result);
		result = underTests.deliveryCost(100, 15, 5);
		assertEquals(275, result);
		result = underTests.deliveryCost(100, 10, 100);
		assertEquals(700, result);
	}

}
