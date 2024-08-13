/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.helper.impl;

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
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.service.helper.PackageHelperService;
import com.everestengg.challenge.courier.service.helper.impl.PackageHelperServiceImpl;

/**
 * @author Rangel
 * 
 */
class PackagePricingServiceTest {

	@Mock
	DiscountCouponCodeProperties discountOffers;

	@InjectMocks
	PackageHelperService underTests = new PackageHelperServiceImpl();
	
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
		double result = underTests.applyDiscount(Package.builder().pkgId("pkg1").offerCode(OFR001).distanceInKm(10).pkgWeightInKg(20).build(), 100);
		assertEquals(0, result);
		result = underTests.applyDiscount(Package.builder().pkgId("pkg1").offerCode(OFR001).distanceInKm(10).pkgWeightInKg(80).build(), 100);
		assertEquals(10, result);
		result = underTests.applyDiscount(Package.builder().pkgId("pkg1").offerCode(OFR003).distanceInKm(100).pkgWeightInKg(10).build(), 700);
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
	
	@Test
	void calculateTime() {
		assertEquals(0.42, underTests.deliveryTime(30, 70));
		assertEquals(1.78, underTests.deliveryTime(125, 70));
		assertEquals(1.42, underTests.deliveryTime(100, 70));
		assertEquals(0.85, underTests.deliveryTime(60, 70));
		assertEquals(1.35, underTests.deliveryTime(95, 70));
		
	}

}
