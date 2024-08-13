/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
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
import com.everestengg.challenge.courier.service.delivery.DeliveryCostEstimator;
import com.everestengg.challenge.courier.service.task.TaskService;
import com.everestengg.challenge.courier.service.useraction.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.DELIVERY_COST_ESTIMATOR_TASK_BEAN_ID)
public class DeliveryCostEstimatorTaskServiceImpl implements TaskService {

	@Qualifier(CommonConstants.PACKAGE_SUMMARY_DETAILS_BEAN_ID)
	@Autowired
	private UserActionService packageDetailsSummaryPromptService;
	
	@Qualifier(CommonConstants.PACKAGE_DETAILS_BEAN_ID)
	@Autowired
	private UserActionService packageDetailsPromptService;
	
	@Autowired
	private DeliveryCostEstimator deliveryCostEstimator;
	
	List<Package> packageDetailsList = new ArrayList<>();
	
	@Override
	public void performTask(Scanner scanner) {
		log.debug("Delivery Cost Estimator Task Service called..");
		PackageSummary userInput = (PackageSummary) packageDetailsSummaryPromptService.promptUser(scanner);	
		
		for(int i=0; i<userInput.getNoOfPackages(); i++) {
			Package packageDetails = (Package) packageDetailsPromptService.promptUser(scanner);
			packageDetailsList.add(packageDetails);
		}
		log.info(CommonConstants.ITEMS_ADDED);
		log.debug("{}", packageDetailsList);
		List<DeliveryEstimate> deliveryEstimates = deliveryCostEstimator.calculateDeliveryCost(userInput, packageDetailsList);
		StringBuilder sb = new StringBuilder();
		deliveryEstimates.stream().forEach(de-> {
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
