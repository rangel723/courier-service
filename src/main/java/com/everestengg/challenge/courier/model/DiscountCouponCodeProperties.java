/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;

/**
 * @author Rangel
 * 
 * Pojo class for coupon configuration. Will be read from application.yml config file
 */
@Configuration
@ConfigurationProperties(prefix = "offer-criteria")
@Data
public class DiscountCouponCodeProperties {
	private List<CouponCode> couponCodes;
	private Map<String, CouponCode> couponCodesMap;

	/**
	 * Coupon codes to be applied when calculating discount.
	 */
	@Builder
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
	
	@PostConstruct
	void constructCoponMap() {
		couponCodesMap = couponCodes.stream().collect(Collectors.toMap(CouponCode::getId, Function.identity()));
	}

}