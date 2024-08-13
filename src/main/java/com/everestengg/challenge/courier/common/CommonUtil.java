/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.common;

import org.springframework.stereotype.Component;

/**
 * @author Rangel
 * 
 */
@Component
public class CommonUtil {

	/**
	 * Trims value to the number of decimal places passed as arguments
	 * 
	 * @param value
	 * @param decimalPlaces 
	 * @return
	 */
	public double trim(double value, int decimalPlaces) {
		value = value * Math.pow(10, decimalPlaces); 
        value = Math.floor(value); 
        value = value / Math.pow(10, decimalPlaces); 
        return value;
	}
}
