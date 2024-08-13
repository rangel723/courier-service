//TODO:- Remove Siemens details from copyright 
/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.useraction.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.PackageSummary;
import com.everestengg.challenge.courier.service.useraction.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.PACKAGE_SUMMARY_DETAILS_BEAN_ID)
public class PackageDetailsSummaryPromptServiceImpl implements UserActionService {
	
	@Override
	public PackageSummary promptUser(Scanner scanner) {
		log.debug("Prompting user for package details.");
		log.info(CommonConstants.PROMPT_PACKAGE_SUMMARY_DETAILS_MESSAGE);

		Integer baseDeliveryCost = null;
		Integer noOfPackages = null;
		boolean validInput = true;
		do {
			try {
				baseDeliveryCost = scanner.nextInt();	
				noOfPackages = scanner.nextInt();	
				validInput = true;
			} catch (InputMismatchException ex) {
				log.error(CommonConstants.INVALID_USER_INPUT_GENERIC);
				scanner.next();
				validInput = false;
			}
		} while(!validInput);
		
		
		if(baseDeliveryCost != null && noOfPackages != null) {
			return new PackageSummary(baseDeliveryCost, noOfPackages);
		}
		throw new InvalidUserInputException(CommonConstants.INVALID_USER_INPUT_GENERIC);
	}
}
