/* Copyright © 2023 ALL RIGHTS RESERVED. */
package com.everestengg.challenge.courier.service.task;

import java.util.Scanner;

import com.everestengg.challenge.courier.factory.TaskServiceFactory;

/**
 * 
 * @see TaskServiceFactory
 * 
 * @author Rangel
 */
public interface TaskService {

	/**
	 * Perform task
	 * @param scanner 
	 */
	void performTask(Scanner scanner);
}
