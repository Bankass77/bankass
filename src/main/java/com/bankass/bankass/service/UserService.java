package com.bankass.bankass.service;

import com.bankass.bankass.model.User;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface UserService extends IBaseService<User> {

	Service<Long> getTotalUsers(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);

	Service<User> findUserByEmail(String email, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Service<User> findByEmailAndPassword(String email, String password, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Service<User> finUserSignIn(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);

	Service<Void> setUserAsSignin(String email, String password, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart);

	Service<Void> setUserAsSignOut(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
	
	Service<User> Signup(User user, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart)
			throws Exception;
}
