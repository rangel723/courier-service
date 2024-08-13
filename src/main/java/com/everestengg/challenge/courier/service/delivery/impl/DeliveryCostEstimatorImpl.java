/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.delivery.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.Freight;
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.model.PackageSummary;
import com.everestengg.challenge.courier.model.Vehicle;
import com.everestengg.challenge.courier.service.delivery.DeliveryCostEstimator;
import com.everestengg.challenge.courier.service.helper.PackageHelperService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 * 
 */
@Slf4j
@Component
public class DeliveryCostEstimatorImpl implements DeliveryCostEstimator {
	
	@Autowired
	private PackageHelperService packageHelper;
	
	@Override
	public List<DeliveryEstimate> calculateDeliveryCost(PackageSummary packageDetailsSummary,
			List<Package> packageDetails) {
		List<DeliveryEstimate> estimate = new ArrayList<>();
		for(Package pkg: packageDetails) {
			estimate.add(calculateDeliveryCost(packageDetailsSummary, pkg));
		}
		log.debug("Result = "+ estimate);
		return estimate;
	}
	
	private DeliveryEstimate calculateDeliveryCost(PackageSummary packageDetailsSummary,
			Package pkg) {
		int baseDeliveryCost = packageDetailsSummary.getBaseDeliveryCost();
		int deliveryCost = packageHelper.deliveryCost(baseDeliveryCost, pkg.getPkgWeightInKg(), pkg.getDistanceInKm());
		int discount = packageHelper.applyDiscount(pkg, deliveryCost);
		int discountedPrice = deliveryCost - discount;
		return DeliveryEstimate.builder().pkgId(pkg.getPkgId()).discount(discount).totalCost(discountedPrice).build();
	}

	
	@Override
	public List<DeliveryEstimate> calcualteDeliveryTime(PackageSummary packageDetailsSummary,
			List<Package> packagesList, Vehicle vehicleDetails) {
		List<DeliveryEstimate> estimate = new ArrayList<>();
		
		while(!packagesList.isEmpty()) {
			List<List<Package>> possibleCombinations = new ArrayList<>();
			generateAllPackageCombinations(new ArrayList<>(), packagesList, 0, possibleCombinations, vehicleDetails.getMaxCarriableWeight());
			log.debug("Possible combinations:-");
	        possibleCombinations.forEach(c-> log.debug("{}", c));
	        
	        List<Freight> freightList = new ArrayList<>();
	        
	        for(int i=0; i<possibleCombinations.size(); i++) {
	        	freightList.add(Freight.builder().freightItems(possibleCombinations.get(i)).build());
	        }
	        
	        List<Package> freightToTransport = new ArrayList<>();
	        
	        //Sorting the freight so as to pick packages as per business case
	        Comparator<Freight> compareByNoOfPackages = Comparator.comparing(Freight::getNoOfPackages).reversed();
	        Comparator<Freight> compareByWeight = Comparator.comparing(Freight::getFeightWeight).reversed();
	        Comparator<Freight> compareByDeliveryTime = Comparator.comparing(Freight::getDeliveryDistance);
	        
	        /**
	    	 * Package priority:
	    	 * 1. Max no of package
	    	 * 2. If max package count is more than 1, select Heavier
	    	 * 3. If weight also same, consider shorter delivery time.
	    	 */
	        Comparator<Freight> freightPriorityComparator = compareByNoOfPackages.thenComparing(compareByWeight).thenComparing(compareByDeliveryTime);
	        freightList.sort(freightPriorityComparator);
	        
	        freightToTransport.addAll(freightList.get(0).getFreightItems());
	        log.debug("DeliveryDistance- {}", freightList.get(0).getDeliveryDistance());
	        log.debug("DeliveryTime- {}", packageHelper.deliveryTime(freightList.get(0).getDeliveryDistance(), vehicleDetails.getMaxSpeed()));
	        
	        //Vehicle
	        
	        packagesList.removeAll(freightToTransport);
		}
		
		return estimate;
	}
	
	/**
	 * Recursive method to generate all possible package combinations
	 * Also filters by maxCarriableWeight
	 * 
	 * @param current
	 * @param packages
	 * @param start
	 * @param result
	 * @param maxCarriableWeight
	 */
    protected void generateAllPackageCombinations(List<Package> current, List<Package> packages, int start, List<List<Package>> result, int maxCarriableWeight) {
        if (!current.isEmpty() && current.stream().mapToInt(Package::getPkgWeightInKg).sum() <= maxCarriableWeight) {
            result.add(new ArrayList<>(current));  // Add combination to the result which are non-empty & who's total weight is below max weight 
        }
        for (int i = start; i < packages.size(); i++) {
            current.add(packages.get(i));
            generateAllPackageCombinations(current, packages, i + 1, result, maxCarriableWeight);  // Move to the next item
            current.remove(current.size() - 1);  // Backtrack
        }
    }

}
