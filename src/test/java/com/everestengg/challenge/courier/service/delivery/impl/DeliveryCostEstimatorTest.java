/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.delivery.impl;

import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import com.everestengg.challenge.courier.common.CommonUtil;
import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties;
import com.everestengg.challenge.courier.model.DiscountCouponCodeProperties.CouponCode;
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.model.PackageSummary;
import com.everestengg.challenge.courier.model.Vehicle;
import com.everestengg.challenge.courier.service.helper.PackageHelperService;
import com.everestengg.challenge.courier.service.helper.impl.PackageHelperServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 * 
 */
@ActiveProfiles("local")
@Slf4j
class DeliveryCostEstimatorTest {

	static final Package PKG1 = Package.builder().pkgId("PKG1").pkgWeightInKg(50).distanceInKm(30).offerCode("OFR001").build();
	static final Package PKG2 = Package.builder().pkgId("PKG2").pkgWeightInKg(75).distanceInKm(125).offerCode("OFR008").build();
	static final Package PKG3 = Package.builder().pkgId("PKG3").pkgWeightInKg(175).distanceInKm(100).offerCode("OFR003").build();
	static final Package PKG4 = Package.builder().pkgId("PKG4").pkgWeightInKg(110).distanceInKm(60).offerCode("OFR002").build();
	static final Package PKG5 = Package.builder().pkgId("PKG5").pkgWeightInKg(155).distanceInKm(95).offerCode("NA").build();
	
	static final DeliveryEstimate DE1 = DeliveryEstimate.builder().pkgId("PKG1").discount(0).totalCost(750).estimatedDeliveryTimeInHours(3.98).build();
	static final DeliveryEstimate DE2 = DeliveryEstimate.builder().pkgId("PKG2").discount(0).totalCost(1475).estimatedDeliveryTimeInHours(1.78).build();
	static final DeliveryEstimate DE3 = DeliveryEstimate.builder().pkgId("PKG3").discount(0).totalCost(2350).estimatedDeliveryTimeInHours(1.42).build();
	static final DeliveryEstimate DE4 = DeliveryEstimate.builder().pkgId("PKG4").discount(105).totalCost(1395).estimatedDeliveryTimeInHours(0.85).build();
	static final DeliveryEstimate DE5 = DeliveryEstimate.builder().pkgId("PKG5").discount(0).totalCost(2125).estimatedDeliveryTimeInHours(4.19).build();

	@InjectMocks
	DeliveryEstimatorImpl underTests = new DeliveryEstimatorImpl();
	
	@Spy
	private CommonUtil commonUtil;
	
	@Spy
	private PackageHelperService packageHelper = new PackageHelperServiceImpl();
	
	@BeforeEach
	void init() {
		List<CouponCode> codes = new ArrayList<>();	
		codes.add(CouponCode.builder().id("OFR001").minDistance(1).maxDistance(200).minWeight(70).maxWeight(200).discountInPercentage(10).build());
		codes.add(CouponCode.builder().id("OFR002").minDistance(50).maxDistance(150).minWeight(100).maxWeight(250).discountInPercentage(7).build());
		codes.add(CouponCode.builder().id("OFR003").minDistance(50).maxDistance(250).minWeight(10).maxWeight(150).discountInPercentage(5).build());
		DiscountCouponCodeProperties discountOffers = new DiscountCouponCodeProperties();
		discountOffers.setCouponCodes(codes);
		discountOffers.setCouponCodesMap(codes.stream().collect(Collectors.toMap(CouponCode::getId, Function.identity())));
		
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(packageHelper, "discountOffers", discountOffers); //Setting this as @spy is used for PackagePricingService
	}
	
	@Test
	void calculateDeliveryCost() {
		List<DeliveryEstimate> result = underTests.calculateDeliveryCost(PackageSummary.builder().baseDeliveryCost(100).noOfPackages(2).build(),
				List.of(PKG1, PKG2, PKG3));
		assertEquals(3, result.size());
	}
	
	@Test
	void generateAllPackageCombinationsTest_FreightSize_200() {
		List<List<Package>> possibleCombinations = new ArrayList<>();
		underTests.generateAllPackageCombinations(new ArrayList<>(), List.of(PKG1, PKG2, PKG3, PKG4, PKG5), 0, possibleCombinations, 200);
		//possibleCombinations.forEach(System.out::println);
		List<Package> c1 = List.of(PKG1);
		List<Package> c2 = List.of(PKG1, PKG2);
		List<Package> c3 = List.of(PKG1, PKG4);
		List<Package> c4 = List.of(PKG2);
		List<Package> c5 = List.of(PKG2, PKG4);
		List<Package> c6 = List.of(PKG3);
		List<Package> c7 = List.of(PKG4);
		List<Package> c8 = List.of(PKG5);
		assertThatCollection(possibleCombinations).containsExactlyInAnyOrder(c1,c2,c3,c4,c5,c6,c7,c8);
	}
	
	@Test
	void generateAllPackageCombinationsTest_FreightSize_240() {
		List<List<Package>> possibleCombinations = new ArrayList<>();
		underTests.generateAllPackageCombinations(new ArrayList<>(), List.of(PKG1, PKG2, PKG3, PKG4, PKG5), 0, possibleCombinations, 240);
		//possibleCombinations.forEach(System.out::println);
		List<Package> c1 = List.of(PKG1);
		List<Package> c2 = List.of(PKG1, PKG2);
		List<Package> c9 = List.of(PKG1, PKG2, PKG4);
		List<Package> c10 = List.of(PKG1, PKG3);
		List<Package> c3 = List.of(PKG1, PKG4);
		List<Package> c11 = List.of(PKG1, PKG5);
		List<Package> c4 = List.of(PKG2);
		List<Package> c5 = List.of(PKG2, PKG4);
		List<Package> c6 = List.of(PKG2, PKG5);
		List<Package> c7 = List.of(PKG3);
		List<Package> c12 = List.of(PKG4);
		List<Package> c8 = List.of(PKG5);
		
		assertThatCollection(possibleCombinations).containsExactlyInAnyOrder(c1,c2,c3,c4,c5,c6,c7,c8, c9, c10, c11, c12);
	}
	
	@Test
	void generateAllPackageCombinationsTest_FreightSize_205() {
		List<List<Package>> possibleCombinations = new ArrayList<>();
		underTests.generateAllPackageCombinations(new ArrayList<>(), List.of(PKG1, PKG2, PKG3, PKG4, PKG5), 0, possibleCombinations, 205);
		//possibleCombinations.forEach(System.out::println);
		List<Package> c1 = List.of(PKG1);
		List<Package> c2 = List.of(PKG1, PKG2);
		List<Package> c3 = List.of(PKG1, PKG4);
		List<Package> c4 = List.of(PKG2);
		List<Package> c5 = List.of(PKG2, PKG4);
		List<Package> c6 = List.of(PKG3);
		List<Package> c7 = List.of(PKG4);
		List<Package> c8 = List.of(PKG5);
		List<Package> c9 = List.of(PKG1, PKG5);
		assertThatCollection(possibleCombinations).containsExactlyInAnyOrder(c1,c2,c3,c4,c5,c6,c7,c8,c9);
	}
	
	@Test
	void calcualteDeliveryTime(){
		PackageSummary summary = PackageSummary.builder().baseDeliveryCost(100).noOfPackages(2).build();
		Vehicle vehicle = Vehicle.builder().noOfVehicles(2).maxSpeed(70).maxCarriableWeight(200).build();
		List<Package> packageDetailsList = new ArrayList<>();
		packageDetailsList.add(PKG1);
		packageDetailsList.add(PKG2);
		packageDetailsList.add(PKG3);
		packageDetailsList.add(PKG4);
		packageDetailsList.add(PKG5);
		
		List<DeliveryEstimate> result = underTests.calcualteDeliveryTime(summary, packageDetailsList,
				vehicle);
		assertThatCollection(result).containsExactlyInAnyOrder(DE1, DE2, DE3, DE4, DE5);
	}
	
	@Test
	void findVehicleIndexWithLowerDeliveryTimeTest() {
		assertEquals(2, underTests.findVehicleIndexWithLowerDeliveryTime(new double[]{1.5,4.3,0.6,2.8,11}));
		assertEquals(0, underTests.findVehicleIndexWithLowerDeliveryTime(new double[]{1.5,4.3,9.6,2.8,11}));
	}
}
