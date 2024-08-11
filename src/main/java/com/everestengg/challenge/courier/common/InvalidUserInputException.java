/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.common;

import org.springframework.boot.ExitCodeGenerator;

/**
 * @author Rangel
 * 
 */
public class InvalidUserInputException extends RuntimeException implements ExitCodeGenerator {

	private static final long serialVersionUID = -655408506816485891L;
	
	/**
	 * InvalidUserInputException constructor
	 * @param message
	 */
	public InvalidUserInputException(String message) {
		super(message);
	}
	
	@Override
	public int getExitCode() {
		return 0;
	}
}
