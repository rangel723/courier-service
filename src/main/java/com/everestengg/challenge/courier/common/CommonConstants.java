/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.common;

/**
 * 
 * @author Rangel
 *
 */
public final class CommonConstants {
	
	private CommonConstants() {}
	
	//
	public static final String NEXT_LINE = "\n ";
	public static final String NEW_LINE = "\n\n";
	public static final String SPACE = "\t";
	public static final String SPACE_BEFORE ="\n\n\t";
	public static final String ESTIMATION_RESULT ="\n Delivery estimation result is:- ";
	
	// Console prompts
	public static final String PROMPT_USER_ACTIONS_MESSAGE = SPACE_BEFORE + "Welcome to Kiki's courier's. Please select from menu options below:\n\t1. Delivery cost estimator\n\t2. Delivery time estimator\n\t3. Exit" + NEW_LINE;
	
	public static final String PROMPT_ERROR_MESSAGE = SPACE_BEFORE + "You must only input numeric value from the above options. Eg. 1" + NEW_LINE;
	
	public static final String PROMPT_SELECTED = SPACE_BEFORE + "Option selected is {}" + NEW_LINE;
	
	public static final String INVALID_USER_INPUT = SPACE_BEFORE + "Please enter values between 1 & 3." + NEW_LINE;
	
	public static final String PROMPT_PACKAGE_SUMMARY_DETAILS_MESSAGE = SPACE_BEFORE + "Please enter package details in the format: base_delivery_cost no_of_packges" + NEW_LINE;
	
	public static final String PROMPT_PACKAGE_DETAILS_MESSAGE = SPACE_BEFORE + "Please enter package details in the format: pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1" + NEW_LINE;
	
	public static final String PROMPT_VEHICLE_DETAILS_MESSAGE = SPACE_BEFORE + "Please enter vehicle details in the format: pkg_id1 discount1 total_cost1 estimated_delivery_time1_in_hours" + NEW_LINE;
	
	public static final String ITEMS_ADDED = SPACE_BEFORE + "All packages added" + NEW_LINE;

	public static final String PROMPT_ERROR_MESSAGE_GENERIC = SPACE_BEFORE + "Invalid input" + NEW_LINE;
	
	public static final String PROMPT_INPUT_MESSAGE_GENERIC = SPACE_BEFORE + "Input is - {}" + NEW_LINE;
	
	public static final String INVALID_USER_INPUT_GENERIC = SPACE_BEFORE + "Please enter a valid input" + NEW_LINE;
	
	//Bean Ids
	public static final String USER_ACTION_SELECTOR_BEAN_ID = "UserActionSelector";
	
	public static final String PACKAGE_SUMMARY_DETAILS_BEAN_ID = "PackageSummaryDetails";
	
	public static final String PACKAGE_DETAILS_BEAN_ID = "PackageDetails";
	
	public static final String VEHICLE_DETAILS_BEAN_ID = "VehicleDetails";
	
	public static final String DELIVERY_COST_ESTIMATOR_TASK_BEAN_ID = "1";
	
	public static final String DELIVERY_TIME_ESTIMATOR_TASK_BEAN_ID = "2";
	
	public static final String EXIT_TASK_BEAN_ID = "3";
		

}
