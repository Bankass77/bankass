package com.bankass.bankass.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bankass.bankass.model.Client;
import com.bankass.bankass.repository.ClientRepository;
import com.bankass.bankass.service.BaseCrudService;
import com.bankass.bankass.service.ClientService;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

@Service("clientService")
public class ClientServiceImpl extends BaseCrudService<Client, JpaRepository<Client, Long>> implements ClientService {

	private ClientRepository clientReporitory;

	public ClientServiceImpl(ClientRepository repository) {
		super(repository);

		this.clientReporitory = repository;
	}

	public ClientRepository getClientReporitory() {
		return clientReporitory;
	}

	public void setClientReporitory(ClientRepository clientReporitory) {
		this.clientReporitory = clientReporitory;
	}

	@Override
	public javafx.concurrent.Service<Long> getTotalClients(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Long>() {
			protected Long call() throws Exception {
				return clientReporitory.getTotalClients();
			};
		}, onSucess, beforeStart);
	}

}
