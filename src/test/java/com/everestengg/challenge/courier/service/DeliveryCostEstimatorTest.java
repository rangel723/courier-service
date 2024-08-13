/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.model.PackageDetailsSummary;
import com.everestengg.challenge.courier.model.VehicleDetails;
import com.everestengg.challenge.courier.service.impl.DeliveryCostEstimatorImpl;

/**
 * @author Rangel
 * 
 */
class DeliveryCostEstimatorTest {

	@InjectMocks
	DeliveryCostEstimator underTests = new DeliveryCostEstimatorImpl();
	
	@Mock
	private PackagePricingService packagePricing;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void calculateDeliveryCost() {
		List<DeliveryEstimate> result = underTests.calculateDeliveryCost(PackageDetailsSummary.builder().baseDeliveryCost(100).noOfPackages(2).build(),
				List.of(PackageDetails.builder().pkgId("PKG1").pkgWeightInKg(50).distanceInKm(30).offerCode("OFR001").build(), 
						PackageDetails.builder().pkgId("PKG2").pkgWeightInKg(75).distanceInKm(125).offerCode("OFR008").build(),
						PackageDetails.builder().pkgId("PKG3").pkgWeightInKg(175).distanceInKm(100).offerCode("OFR003").build()));
		assertEquals(3, result.size());
	}
	
	@Test
	void calcualteDeliveryTime(){
		List<DeliveryEstimate> result = underTests.calcualteDeliveryTime(PackageDetailsSummary.builder().baseDeliveryCost(100).noOfPackages(2).build(),
				List.of(PackageDetails.builder().pkgId("PKG1").pkgWeightInKg(50).distanceInKm(30).offerCode("OFR001").build(), 
						PackageDetails.builder().pkgId("PKG2").pkgWeightInKg(75).distanceInKm(125).offerCode("OFR008").build(),
						PackageDetails.builder().pkgId("PKG3").pkgWeightInKg(175).distanceInKm(100).offerCode("OFR003").build(),
						PackageDetails.builder().pkgId("PKG4").pkgWeightInKg(110).distanceInKm(60).offerCode("OFR002").build(),
						PackageDetails.builder().pkgId("PKG5").pkgWeightInKg(155).distanceInKm(95).offerCode("NA").build()), 
				VehicleDetails.builder().noOfVehicles(2).maxSpeed(70).maxCarriableWeight(200).build());
	}
	
}
