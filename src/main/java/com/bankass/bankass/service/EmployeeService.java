package com.bankass.bankass.service;



import com.bankass.bankass.model.Employee;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface EmployeeService extends IBaseService<Employee> {
	
	javafx.concurrent.Service<Long> getTotalEmployees(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
