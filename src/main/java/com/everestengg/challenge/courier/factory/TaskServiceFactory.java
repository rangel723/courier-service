package com.everestengg.challenge.courier.factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.everestengg.challenge.courier.service.task.TaskService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * Factory class to render users task selection. See TaskService implementation classes
 * 
 * @author Rangel
 */
@Component
@RequiredArgsConstructor
public class TaskServiceFactory {

	private final Map<String, TaskService> userTasksServiceMap;

	/**
	 * @param taskServiceType
	 * @return
	 */
	public TaskService getServiceTask(String taskServiceType) {
		return userTasksServiceMap.get(taskServiceType);
	}

}
