/* Copyright © Siemens AG 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rangel
 * 
 * Used for user input of menu options
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOptionSelection implements UserInput {
	
	private Integer userSelection;
}
