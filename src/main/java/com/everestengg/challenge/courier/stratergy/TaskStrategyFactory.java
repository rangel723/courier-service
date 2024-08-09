package com.everestengg.challenge.courier.stratergy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.everestengg.challenge.courier.task.service.TaskService;

import lombok.RequiredArgsConstructor;

/**
 * @author Rangel
 */
@Component
@RequiredArgsConstructor
public class TaskStrategyFactory {

	private final Map<String, TaskService> userTasksStrategyMap;

	/**
	 * @param userTaskStrategyType
	 * @return
	 */
	public TaskService getDataStrategy(String userTaskStrategyType) {
		return userTasksStrategyMap.get(userTaskStrategyType);
	}

}
