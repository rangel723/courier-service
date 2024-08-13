/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.common;

/**
 * @author Rangel
 * 
 */
public class InvalidUserInputException extends RuntimeException {

	private static final long serialVersionUID = -655408506816485891L;
	
	/**
	 * InvalidUserInputException constructor
	 * @param message
	 */
	public InvalidUserInputException(String message) {
		super(message);
	}
}
