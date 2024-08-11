package com.everestengg.challenge.courier;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.jline.PromptProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rangel
 *
 */
@Slf4j
@SpringBootApplication
@CommandScan
public class CourierServiceApplication {
	
	/*
	 * @Qualifier(CommonConstants.USER_ACTION_SELECTOR_BEAN_ID)
	 * 
	 * @Autowired private UserActionService userActionSelectorPromptService;
	 * 
	 * @Autowired private TaskStrategyFactory taskStratergyFactory;
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.debug("Starting courier serivce application");
		SpringApplication application = new SpringApplication(CourierServiceApplication.class);
		application.setBannerMode(Mode.OFF);
		application.run(args);
		log.debug("Courier service application started.");
	}
	
	@Bean
	public PromptProvider myPromptProvider() {
		return () -> new AttributedString("courier-service-shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	}

	/*
	 * @Override public void run(String... args) throws Exception { boolean
	 * userInputNumber3 = false; try (Scanner scanner = new Scanner(System.in)) { do
	 * { UserOptionSelection userInput = null; try { userInput =
	 * (UserOptionSelection) userActionSelectorPromptService.promptUser(scanner); }
	 * catch(InvalidUserInputException ex) { log.error(ex.getMessage()); }
	 * 
	 * if(userInput != null && userInput.getUserSelection() != null &&
	 * userInput.getUserSelection() == 3) { userInputNumber3 = true; } else
	 * if(userInput != null && userInput.getUserSelection() != null) { TaskService
	 * task =
	 * taskStratergyFactory.getDataStrategy(userInput.getUserSelection().toString())
	 * ; task.performTask(scanner); } } while(!userInputNumber3); }
	 * 
	 * }
	 */

}
