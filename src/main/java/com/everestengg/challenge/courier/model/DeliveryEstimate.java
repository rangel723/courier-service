/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class DeliveryEstimate implements UserInput {

	private String pkgId;
	
	private Integer discount;
	
	private Integer totalCost;
	
	private Float estimatedDeliveryTimeInHours;
	
}
