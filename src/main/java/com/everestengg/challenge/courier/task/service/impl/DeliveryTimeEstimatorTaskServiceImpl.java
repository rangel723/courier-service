/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.task.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.model.DeliveryEstimate;
import com.everestengg.challenge.courier.model.PackageDetails;
import com.everestengg.challenge.courier.model.PackageDetailsSummary;
import com.everestengg.challenge.courier.model.VehicleDetails;
import com.everestengg.challenge.courier.service.DeliveryCostEstimator;
import com.everestengg.challenge.courier.task.service.TaskService;
import com.everestengg.challenge.courier.useraction.service.UserActionService;

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
	private DeliveryCostEstimator deliveryCostEstimator;
	
	List<PackageDetails> packageDetailsList = new ArrayList<>();

	@Override
	public void performTask(Scanner scanner) {
		log.debug("Delivery Time Estimator Task Service called..");

		PackageDetailsSummary packageSummary = (PackageDetailsSummary) packageDetailsSummaryPromptService.promptUser(scanner);	
		
		for(int i=0; i<packageSummary.getNoOfPackages(); i++) {
			PackageDetails packageDetails = (PackageDetails) packageDetailsPromptService.promptUser(scanner);
			packageDetailsList.add(packageDetails);
		}
		log.info(CommonConstants.ITEMS_ADDED);
		log.debug("{}", packageDetailsList);
		
		VehicleDetails vehicleDetails = (VehicleDetails) vehicleDetailsPromptService.promptUser(scanner);
		List<DeliveryEstimate> timeEstimates = deliveryCostEstimator.calcualteDeliveryTime(packageSummary, packageDetailsList, vehicleDetails);
		StringBuilder sb = new StringBuilder();
		timeEstimates.stream().forEach(de-> {
			sb.append(CommonConstants.NEXT_LINE);
			sb.append(de.getPkgId());
			sb.append(CommonConstants.SPACE);
			sb.append(de.getDiscount());
			sb.append(CommonConstants.SPACE);
			sb.append(de.getTotalCost());
		});
		log.info(sb.toString());
	
	}

}
