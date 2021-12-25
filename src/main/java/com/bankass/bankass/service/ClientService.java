package com.bankass.bankass.service;

import com.bankass.bankass.model.Client;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface ClientService extends IBaseService<Client> {

	javafx.concurrent.Service<Long> getTotalClients(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);
}
