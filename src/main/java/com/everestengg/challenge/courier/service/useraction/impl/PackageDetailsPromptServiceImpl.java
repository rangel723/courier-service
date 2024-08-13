//TODO:- Remove Siemens details from copyright 
/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.useraction.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.Package;
import com.everestengg.challenge.courier.service.useraction.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.PACKAGE_DETAILS_BEAN_ID)
public class PackageDetailsPromptServiceImpl implements UserActionService {

	@Override
	public Package promptUser(Scanner scanner) {
		log.debug("Prompting user for package details.");
		log.info(CommonConstants.PROMPT_PACKAGE_DETAILS_MESSAGE);

		String packageId = null;
		Integer packageWeight = null;
		Integer deliveryDistance = null;
		String offerCode = null;
		boolean validInput = true;
		do {
			try {
				packageId = scanner.next();
				packageWeight = scanner.nextInt();
				deliveryDistance = scanner.nextInt();
				offerCode = scanner.next();
			} catch (InputMismatchException ex) {
				log.error(CommonConstants.INVALID_USER_INPUT_GENERIC);
				scanner.next();
			}
		} while (!validInput);

		if (packageId != null && packageWeight != null && deliveryDistance != null && offerCode != null) {
			return new Package(packageId, packageWeight, deliveryDistance, offerCode);
		}
		throw new InvalidUserInputException(CommonConstants.INVALID_USER_INPUT_GENERIC);
	}
}
