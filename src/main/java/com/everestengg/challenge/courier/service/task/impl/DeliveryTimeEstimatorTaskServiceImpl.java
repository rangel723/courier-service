/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.model.PackageSummary;
import com.everestengg.challenge.courier.model.Vehicle;
import com.everestengg.challenge.courier.service.delivery.DeliveryEstimator;
import com.everestengg.challenge.courier.service.task.TaskService;
import com.everestengg.challenge.courier.service.useraction.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.DELIVERY_TIME_ESTIMATOR_TASK_BEAN_ID)
public class DeliveryTimeEstimatorTaskServiceImpl implements TaskService {
	
	@Qualifier(CommonConstants.PACKAGE_SUMMARY_DETAILS_BEAN_ID)
	@Autowired
	private UserActionService packageDetailsSummaryPromptService;
	
	@Qualifier(CommonConstants.PACKAGE_DETAILS_BEAN_ID)
	@Autowired
	private UserActionService packageDetailsPromptService;
	
	@Qualifier(CommonConstants.VEHICLE_DETAILS_BEAN_ID)
	@Autowired
	private UserActionService vehicleDetailsPromptService;
	
	@Autowired
	private DeliveryEstimator deliveryEstimator;
	
	List<Package> packageDetailsList = new ArrayList<>();

	@Override
	public void performTask(Scanner scanner) {
		log.debug("Delivery Time Estimator Task Service called..");

		PackageSummary packageSummary = (PackageSummary) packageDetailsSummaryPromptService.promptUser(scanner);	
		
		for(int i=0; i<packageSummary.getNoOfPackages(); i++) {
			Package packageDetails = (Package) packageDetailsPromptService.promptUser(scanner);
			packageDetailsList.add(packageDetails);
		}
		log.info(CommonConstants.ITEMS_ADDED);
		log.debug("{}", packageDetailsList);
		
		Vehicle vehicleDetails = (Vehicle) vehicleDetailsPromptService.promptUser(scanner);
		List<DeliveryEstimate> timeEstimates = deliveryEstimator.calcualteDeliveryTime(packageSummary, packageDetailsList, vehicleDetails);
		StringBuilder sb = new StringBuilder();
		sb.append(CommonConstants.NEW_LINE);
		sb.append(CommonConstants.ESTIMATION_RESULT);
		sb.append("\n pkg_id1  discount1  total_cost1  estimated_delivery_time1_in_hours");
		sb.append(CommonConstants.NEXT_LINE);
		timeEstimates.stream().forEach(de-> {
			sb.append(CommonConstants.NEXT_LINE);
			sb.append(de.getPkgId());
			sb.append(CommonConstants.SPACE);
			sb.append(de.getDiscount());
			sb.append(CommonConstants.SPACE);
			sb.append(de.getTotalCost());
			sb.append(CommonConstants.SPACE);
			sb.append(de.getEstimatedDeliveryTimeInHours());
			sb.append(CommonConstants.SPACE);
		});
		sb.append(CommonConstants.NEXT_LINE);
		log.info(sb.toString());
	
	}

}
