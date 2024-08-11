/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rangel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageSummaryDetails implements UserInput {

	Integer baseDeliveryCost;
	
	Integer noOfPackages;
	
}
