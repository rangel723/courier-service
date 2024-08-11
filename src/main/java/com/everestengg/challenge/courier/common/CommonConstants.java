/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.common;

/**
 * 
 * @author Rangel
 *
 */
public final class CommonConstants {
	
	private CommonConstants() {}
	
	// Console prompts
	public static final String PROMPT_USER_ACTIONS_MESSAGE = "\n\nWelcome to Kiki's courier's. Please select from menu options below:\n1. Delivery cost estimator\n2. Delivery time estimator\n3. Exit";
	
	public static final String PROMPT_ERROR_MESSAGE = "You must only input numeric value from the above options. Eg. 1";
	
	public static final String PROMPT_SELECTED = "\nOption selected is {}";
	
	public static final String INVALID_USER_INPUT = "\nPlease enter values between 1 & 3.";
	
	public static final String PROMPT_PACKAGE_DETAILS_MESSAGE = "\nPlease enter package details in the format: base_delivery_cost no_of_packges";

	public static final String PROMPT_ERROR_MESSAGE_GENERIC = "Invalid input";
	
	public static final String PROMPT_INPUT_MESSAGE_GENERIC = "Input is - {}";
	
	public static final String INVALID_USER_INPUT_GENERIC = "\nPlease enter a valid input";
	
	//Bean Ids
	public static final String USER_ACTION_SELECTOR_BEAN_ID = "UserActionSelector";
	
	public static final String PACKAGE_DETAILS_BEAN_ID = "PackageDetails";
	
	public static final String DELIVERY_COST_ESTIMATOR_TASK_BEAN_ID = "1";
	
	public static final String DELIVERY_TIME_ESTIMATOR_TASK_BEAN_ID = "2";
	
	public static final String EXIT_TASK_BEAN_ID = "3";
	

}
