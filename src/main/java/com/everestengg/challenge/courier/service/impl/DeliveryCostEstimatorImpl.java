/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.impl;

import java.util.ArrayList;
import java.util.Collections;
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

	/**
	 * Package priority:
	 * 1. Max no of package
	 * 2. If max package count is more than 1, select Heavier
	 * 3. If weight also same, consider shorter delivery time.
	 */
	@Override
	public List<DeliveryEstimate> calcualteDeliveryTime(PackageDetailsSummary packageDetailsSummary,
			List<PackageDetails> packageDetails, VehicleDetails vehicleDetails) {
		//while(!packages.isEmpty()) {
			List<List<PackageDetails>> combinations = new ArrayList<>();
			generateAllPackageCombinations(new ArrayList<>(), packageDetails, 0, combinations, vehicleDetails.getMaxCarriableWeight());
	        combinations.forEach(System.out::println);
	        
	        int[] noOfPackagesArr = new int[combinations.size()];
	        int[] feightWeightArr = new int[combinations.size()];
	        List<List<PackageDetails>> matchingWeightCriteria = new ArrayList<>();
	        
	        for(int i=0; i<combinations.size(); i++) {
	        	int freightweightSum = combinations.get(i).stream().mapToInt(PackageDetails::getPkgWeightInKg).sum();
	        		matchingWeightCriteria.add(combinations.get(i));
	        		feightWeightArr[i] = freightweightSum;
	        		noOfPackagesArr[i] = combinations.get(i).size();
	        }
	        
	        
	        
	        //packages.removeAll()
		//}
		
		return null;
	}
	
	
	/**
     * Finds index which has the max value of packages
     * If max value not available or more than 1 elements have same max value, function returns -1
     * 
	 * @param sumArr
	 * @return
	 */
	protected List<Integer> findFreightIndexWithMaxPackages(List<Integer> noOfPackagesList) {
		if(noOfPackagesList == null || noOfPackagesList.isEmpty()) {
			return Collections.emptyList();
		}
		List<Integer> maxValueIndexArray = new ArrayList<>();
		Integer maxValue = Collections.max(noOfPackagesList);
		for(int i=0; i<noOfPackagesList.size(); i++) {
			if(noOfPackagesList.get(i).equals(maxValue)) {
				maxValueIndexArray.add(i);
			} 
		}
		
		return maxValueIndexArray;
	}

	// Recursive method to generate all possible combinations
    protected void generateAllPackageCombinations(List<PackageDetails> current, List<PackageDetails> packages, int start, List<List<PackageDetails>> result, int maxCarriableWeight) {
        if (!current.isEmpty() && current.stream().mapToInt(PackageDetails::getPkgWeightInKg).sum() <= maxCarriableWeight) {
            result.add(new ArrayList<>(current));  // Add combination to the result which are non-empty & who's total weight is below max weight 
        }
        for (int i = start; i < packages.size(); i++) {
            current.add(packages.get(i));
            generateAllPackageCombinations(current, packages, i + 1, result, maxCarriableWeight);  // Move to the next item
            current.remove(current.size() - 1);  // Backtrack
        }
    }

}
