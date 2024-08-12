package com.everestengg.challenge.courier;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.everestengg.challenge.courier.common.CommonConstants;
import com.everestengg.challenge.courier.common.InvalidUserInputException;
import com.everestengg.challenge.courier.model.UserOptionSelection;
import com.everestengg.challenge.courier.stratergy.TaskStrategyFactory;
import com.everestengg.challenge.courier.task.service.TaskService;
import com.everestengg.challenge.courier.useraction.service.UserActionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rangel
 *
 */
@Slf4j
@SpringBootApplication
public class CourierServiceApplication implements CommandLineRunner {
	
	@Qualifier(CommonConstants.USER_ACTION_SELECTOR_BEAN_ID)
	@Autowired
	private UserActionService userActionSelectorPromptService;
	
	@Autowired
	private TaskStrategyFactory taskStratergyFactory;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.debug("Starting courier serivce application");
		SpringApplication.run(CourierServiceApplication.class, args);
		log.debug("Courier service application started.");
	}

	@Override
	public void run(String... args) throws Exception {
		boolean userInputNumber3 = false;
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				UserOptionSelection userInput = null;
				try {
					userInput = (UserOptionSelection) userActionSelectorPromptService.promptUser(scanner);	
				} catch(InvalidUserInputException ex) {
					log.error(ex.getMessage());
				}
				
				if(userInput != null && userInput.getUserSelection() != null && userInput.getUserSelection() == 3) {
					userInputNumber3 = true;
				} else if(userInput != null && userInput.getUserSelection() != null) {
					TaskService task = taskStratergyFactory.getDataStrategy(userInput.getUserSelection().toString());
					task.performTask(scanner);
				} 
			} while(!userInputNumber3);
		}
				
	}

}
