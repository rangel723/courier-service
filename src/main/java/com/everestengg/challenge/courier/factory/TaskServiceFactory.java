package com.everestengg.challenge.courier.factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.everestengg.challenge.courier.task.service.TaskService;

import lombok.RequiredArgsConstructor;

/**
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
