package com.bankass.bankass.service;

import java.util.Set;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportsService {

	<D> Service<JasperPrint> createJasperPrint(String reportTemplatePath, Set<D> data,
			EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);

	void onClose();
}
