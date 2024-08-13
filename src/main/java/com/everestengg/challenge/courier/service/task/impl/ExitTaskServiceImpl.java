/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.task.impl;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.service.task.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.EXIT_TASK_BEAN_ID)
public class ExitTaskServiceImpl implements TaskService {

	@Override
	public void performTask(Scanner scanner) {
		log.debug("Exit Task called. System will exit.");
		System.exit(0);
	}

}
