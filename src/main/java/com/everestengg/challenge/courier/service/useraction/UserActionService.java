/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.useraction;

import java.util.Scanner;

import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.UserInput;

/**
 * @author Rangel
 */
public interface UserActionService {

	/**
	 * Takes input from user on operations to perform
	 * @param scanner 
	 * @return UserInput
	 * @throws InvalidUserInputException 
	 */
	UserInput promptUser(Scanner scanner) throws InvalidUserInputException;
}
