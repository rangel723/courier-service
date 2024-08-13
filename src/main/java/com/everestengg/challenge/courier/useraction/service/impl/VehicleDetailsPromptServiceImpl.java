//TODO:- Remove Siemens details from copyright 
/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.useraction.service.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.model.VehicleDetails;
import com.everestengg.challenge.courier.useraction.service.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.VEHICLE_DETAILS_BEAN_ID)
public class VehicleDetailsPromptServiceImpl implements UserActionService {
	
	@Override
	public VehicleDetails promptUser(Scanner scanner) {
		log.debug("Prompting user for vehicle details input.");
		log.info(CommonConstants.PROMPT_VEHICLE_DETAILS_MESSAGE);

		Integer noOfVehicles = null;
		Integer maxSpeed = null;
		Integer maxCarriableWeight = null;
		
		boolean validInput = true;
		do {
			try {
				noOfVehicles = scanner.nextInt();
				maxSpeed = scanner.nextInt();
				maxCarriableWeight = scanner.nextInt();
			} catch (InputMismatchException ex) {
				log.error(CommonConstants.INVALID_USER_INPUT_GENERIC);
				scanner.next();
			}
		} while (!validInput);
		
		VehicleDetails vehicleDetails = new VehicleDetails(noOfVehicles, maxSpeed, maxCarriableWeight); 
		log.debug("Prompt action completed. User input is - {}", vehicleDetails);
		return vehicleDetails;
	}
}
