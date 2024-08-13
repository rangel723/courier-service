/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rangel
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PackageSummary implements UserInput {

	Integer baseDeliveryCost;
	
	Integer noOfPackages;
	
}
