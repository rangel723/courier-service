/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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
import com.everestengg.challenge.courier.service.PackagePricingService;

/**
 * @author Rangel
 * 
 */
class DeliveryCostEstimatorTest {

	static final PackageDetails PKG1 = PackageDetails.builder().pkgId("PKG1").pkgWeightInKg(50).distanceInKm(30).offerCode("OFR001").build();
	static final PackageDetails PKG2 = PackageDetails.builder().pkgId("PKG2").pkgWeightInKg(75).distanceInKm(125).offerCode("OFR008").build();
	static final PackageDetails PKG3 = PackageDetails.builder().pkgId("PKG3").pkgWeightInKg(175).distanceInKm(100).offerCode("OFR003").build();
	static final PackageDetails PKG4 = PackageDetails.builder().pkgId("PKG4").pkgWeightInKg(110).distanceInKm(60).offerCode("OFR002").build();
	static final PackageDetails PKG5 = PackageDetails.builder().pkgId("PKG5").pkgWeightInKg(155).distanceInKm(95).offerCode("NA").build();

	@InjectMocks
	DeliveryCostEstimatorImpl underTests = new DeliveryCostEstimatorImpl();
	
	@Mock
	private PackagePricingService packagePricing;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void calculateDeliveryCost() {
		List<DeliveryEstimate> result = underTests.calculateDeliveryCost(PackageDetailsSummary.builder().baseDeliveryCost(100).noOfPackages(2).build(),
				List.of(PKG1, PKG2, PKG3));
		assertEquals(3, result.size());
	}
	
	@Test
	void findFreightWithMaxPackagesTest() {
		List<Integer> input = null;
		List<Integer> result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly().isEmpty();
		
		input = new ArrayList<>();
		result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly().isEmpty();
		
		input = List.of(5,7,8,4,11,2,9,8,4,2);
		result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly(4);
		
		input = List.of(5,7,8,4,2,9,8,4,2);
		result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly(5);
		
		input = List.of(5,7,8,4,2,8,4,2);
		result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly(2,5);
		
		input = List.of(8,7,1,4,2,8,4,2);
		result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly(0,5);
		
		input = List.of(8,7,1,4,2,7,4,2);
		result = underTests.findFreightIndexWithMaxPackages(input);
		assertThat(result).containsExactly(0);
	}
	
	@Test
	void generateAllPackageCombinationsTest() {
		List<List<PackageDetails>> possibleCombinations = new ArrayList<>();
		underTests.generateAllPackageCombinations(new ArrayList<>(), List.of(PKG1, PKG2, PKG3, PKG4, PKG5), 0, possibleCombinations, 200);
		possibleCombinations.forEach(System.out::println);
		List<PackageDetails> c1 = List.of(PKG1);
		List<PackageDetails> c2 = List.of(PKG1, PKG2);
		List<PackageDetails> c3 = List.of(PKG1, PKG4);
		List<PackageDetails> c4 = List.of(PKG2);
		List<PackageDetails> c5 = List.of(PKG2, PKG4);
		List<PackageDetails> c6 = List.of(PKG3);
		List<PackageDetails> c7 = List.of(PKG4);
		List<PackageDetails> c8 = List.of(PKG5);
		assertArrayEquals(possibleCombinations.toArray(), List.of(c1,c2,c3,c4,c5,c6,c7,c8).toArray());

	}
	
	@Test
	void calcualteDeliveryTime(){
		PackageDetailsSummary summary = PackageDetailsSummary.builder().baseDeliveryCost(100).noOfPackages(2).build();
		VehicleDetails vehicle = VehicleDetails.builder().noOfVehicles(2).maxSpeed(70).maxCarriableWeight(200).build();
		
		/*
		 * List<DeliveryEstimate> result = underTests.calcualteDeliveryTime(summary,
		 * List.of(PKG1, PKG2, PKG3, PKG4, PKG5), vehicle);
		 */
	}
	
}
