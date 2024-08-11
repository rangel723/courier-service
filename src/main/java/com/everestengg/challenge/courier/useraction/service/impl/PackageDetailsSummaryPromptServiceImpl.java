//TODO:- Remove Siemens details from copyright 
/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.useraction.service.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.PackageSummaryDetails;
import com.everestengg.challenge.courier.useraction.service.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.PACKAGE_DETAILS_BEAN_ID)
public class PackageDetailsSummaryPromptServiceImpl implements UserActionService {
	
	@Override
	public PackageSummaryDetails promptUser(Scanner scanner) {
		log.debug("Prompting user for package details.");
		log.info(CommonConstants.PROMPT_PACKAGE_DETAILS_MESSAGE);

		Integer baseDeliveryCost = null;
		Integer noOfPackages = null;
		try {
			baseDeliveryCost = scanner.nextInt();	
			noOfPackages = scanner.nextInt();	
		} catch (InputMismatchException ex) {
			log.error(CommonConstants.INVALID_USER_INPUT_GENERIC);
			scanner.next();
		}
		
		if(baseDeliveryCost != null && noOfPackages != null) {
			return new PackageSummaryDetails(baseDeliveryCost, noOfPackages);
		}
		throw new InvalidUserInputException(CommonConstants.INVALID_USER_INPUT_GENERIC);
	}
}
