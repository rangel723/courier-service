/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.task.service.impl;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.task.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.DELIVERY_TIME_ESTIMATOR_TASK_BEAN_ID)
public class DeliveryTimeEstimatorTaskServiceImpl implements TaskService {

	@Override
	public void performTask(Scanner scanner) {
		log.debug("Delivery Time Estimator Task Service called..");
		// TODO Auto-generated method stub
		
	}

}
