package com.bankass.bankass.service;



import com.bankass.bankass.model.Supplier;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface SupplierService extends IBaseService<Supplier> {
	
	Service<Long> getTotalSuppliers(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
