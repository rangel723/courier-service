//TODO:- Remove Siemens details from copyright 
/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.useraction.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.UserOptionSelection;
import com.everestengg.challenge.courier.service.useraction.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rangel
 */
@Slf4j
@Service(CommonConstants.USER_ACTION_SELECTOR_BEAN_ID)
public class UserActionSelectorPromptServiceImpl implements UserActionService {
	
	@Override
	public UserOptionSelection promptUser(Scanner scanner) {
		log.debug("Prompting user for input.");
		log.info(CommonConstants.PROMPT_USER_ACTIONS_MESSAGE);

		Integer inputValue = null;
		try {
			inputValue = scanner.nextInt();					
		} catch (InputMismatchException ex) {
			log.error(CommonConstants.PROMPT_ERROR_MESSAGE);
			scanner.next();
		}
		
		log.info(CommonConstants.PROMPT_SELECTED, inputValue == null ? "invalid" : inputValue);
		log.debug("Prompt action completed. User input is - {}", inputValue);
		if(inputValue != null && inputValue >= 1 && inputValue <= 3) {
			return new UserOptionSelection(inputValue);
		} 
		throw new InvalidUserInputException(CommonConstants.INVALID_USER_INPUT);
	}
}
