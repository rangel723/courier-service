/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import java.util.List;
import java.util.OptionalInt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rangel
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Freight {

	List<Package> freightItems;
	
	/**
	 * Count of packages in freight
	 * 
	 * @return
	 */
	public int getNoOfPackages() {
		return freightItems != null && !freightItems.isEmpty() ? freightItems.size() : 0;
	}
	
	/**
	 * Sum of weights of all packages
	 * 
	 * @return
	 */
	public int getFeightWeight() {
		return freightItems.stream().mapToInt(Package::getPkgWeightInKg).sum();
	}
	
	/**
	 * Max delivery time is considered from the list of packages
	 * 
	 * @return
	 */
	public int getDeliveryDistance() {
		OptionalInt deliveryTime = freightItems.stream().mapToInt(Package::getDistanceInKm).max();
		return deliveryTime.isPresent() ? deliveryTime.getAsInt() : 0;
	}

}
