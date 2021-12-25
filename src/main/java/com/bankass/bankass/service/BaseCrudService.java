package com.bankass.bankass.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankass.bankass.service.BaseService;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public abstract class BaseCrudService<T, R extends JpaRepository<T, Long>> extends BaseService {

	private R repository;

	public BaseCrudService(R repository) {
		this.repository = repository;

	}

	public Service<T> save(T obj, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart)
			throws Exception {

		if (obj == null) {
			throw new Exception();
		}

		return createService(new Task<T>() {
			protected T call() throws Exception {
				return repository.save(obj);
			};
		}, onSucess, beforeStart);

	}

	public Service<Set<T>> findAll(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Set<T>>() {
			protected Set<T> call() throws Exception {
				List<T> allLis= repository.findAll();
				return new HashSet<>(allLis);
			};
		}, onSucess, beforeStart);
	}

	public Service<Void> delete(long id, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) throws Exception {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				repository.deleteById(id);;
				return null;
			};
		}, onSucess, beforeStart);

	}

	public Service<T> find(long id, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart)
			throws Exception {
		return createService(new Task<T>() {
			protected T call() throws Exception {
				return repository.getById(id);
			};
		}, onSucess, beforeStart);
	}

}
