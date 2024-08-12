/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.task.service.impl;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.PackageDetailsSummary;
import com.everestengg.challenge.courier.task.service.TaskService;
import com.everestengg.challenge.courier.useraction.service.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.DELIVERY_COST_ESTIMATOR_TASK_BEAN_ID)
public class DeliveryCostEstimatorTaskServiceImpl implements TaskService {

	@Qualifier(CommonConstants.PACKAGE_DETAILS_BEAN_ID)
	@Autowired
	private UserActionService packageDetailsSummaryPromptService;
	
	@Override
	public void performTask(Scanner scanner) {
		log.debug("Delivery Cost Estimator Task Service called..");
		PackageDetailsSummary userInput = null;
		
		try {
			userInput = (PackageDetailsSummary) packageDetailsSummaryPromptService.promptUser(scanner);	
		} catch(InvalidUserInputException ex) {
			log.error(ex.getMessage());
		}
	}

}
