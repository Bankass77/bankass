package com.bankass.bankass.service;

import java.util.Calendar;
import java.util.List;

import com.bankass.bankass.model.Sale;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface SaleService extends IBaseService<Sale> {

	Service<List<Sale>> findAllOpenSales(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Service<List<Sale>> findAllFinalizedSales(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Service<Long> getTotalSales(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);

	Service<List<Sale>> findSaleByMonth(Calendar date, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Service<Long> getTotalSalesByMonthService(Calendar date, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Long getTotalSalesByMonth(Calendar date);
}
