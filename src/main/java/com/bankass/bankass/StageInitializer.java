package com.bankass.bankass;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.bankass.bankass.StockManagement.StageReadyEvent;

import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
	Stage stage= event.getStage();
		log.debug("stage", stage);
	}

}
