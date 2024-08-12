/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Rangel
 * 
 * Coupon configuration will be read from application.yml config file
 */
@Configuration
@ConfigurationProperties(prefix = "offer-criteria")
@Data
public class DiscountCouponCodeProperties {
	private List<CouponCode> couponCodes;

	/**
	 * Coupon codes to be applied when calculating discount.
	 */
	@Data
	public static class CouponCode 
	{
		private String id;
		private Integer minDistance;
		private Integer maxDistance;
		private Integer minWeight;
		private Integer maxWeight;
		private Integer discountInPercentage;
	}

}