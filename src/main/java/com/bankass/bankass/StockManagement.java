package com.bankass.bankass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import com.bankass.bankass.controller.MainController;

import com.bankass.bankass.support.AbstractFxmlView;
import com.bankass.bankass.support.GUIState;
import com.bankass.bankass.support.PropertyReaderHelper;
import com.bankass.bankass.views.MainView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockManagement extends Application {

	private static ConfigurableApplicationContext applicationContext;
	private static List<Image> icons = new ArrayList<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		GUIState.setStage(stage);
		GUIState.setHostServices(this.getHostServices());
		showInitialView();
		applicationContext.publishEvent(new StageReadyEvent(stage));

	}

	/**
	 * Initializes the root layout.
	 */
	@Override
	public void init() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(StockManagement.class);
		builder.application().setWebApplicationType(WebApplicationType.NONE);
		applicationContext =builder.run(getParameters().getRaw().toArray(new String[0]));
		final List<String> fsImages = PropertyReaderHelper.get(applicationContext.getEnvironment(), "javafx.appicons");
		if (!fsImages.isEmpty()) {
			fsImages.forEach((s) -> icons.add(new Image(getClass().getResource(s).toExternalForm())));
		} else {
			icons.add(new Image(getClass().getResource("/images/gear_16x16.png").toExternalForm()));
			icons.add(new Image( getClass().getResource("/images/gear_24x24.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_36x36.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_42x42.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_64x64.png").toExternalForm()));
		}
	}
	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();

	}
	
	private void showInitialView() {
		final String stageStyle = applicationContext.getEnvironment().getProperty("javafx.stage.style");
		if (stageStyle != null) {
			GUIState.getStage().initStyle(StageStyle.valueOf(stageStyle.toUpperCase()));
		} else {
			GUIState.getStage().initStyle(StageStyle.DECORATED);
		}
		showView(MainView.class);
	}

	public static void showView(final Class<? extends AbstractFxmlView> newView) {
		try {
			final AbstractFxmlView view = applicationContext.getBean(newView);

			if (GUIState.getScene() == null) {
				GUIState.setScene(new Scene(view.getView()));
			} else {
				GUIState.getScene().setRoot(view.getView());
			}
			GUIState.getStage().setScene(GUIState.getScene());
			applyEnvPropsToView();
			GUIState.getStage().getIcons().addAll(icons);
			GUIState.getStage().addEventHandler(WindowEvent.WINDOW_SHOWN, e -> {
				if (view.getFxmlLoader().getController() instanceof MainController) {
					MainController mainController = (MainController) view.getFxmlLoader().getController();
					mainController.onWindowShownEvent();
				}
				log.debug("Stage view shown: {} ", view.getClass());
			});
			GUIState.getStage().show();
		} catch (Throwable t) {
			log.error("Failed to load application: ", t);
			showErrorAlert(t);
		}
	}
	
	private static void applyEnvPropsToView() {
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.title", String.class, GUIState.getStage()::setTitle);
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.stage.width", Double.class, GUIState.getStage()::setWidth);
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.stage.height", Double.class, GUIState.getStage()::setHeight);
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.stage.resizable", Boolean.class, GUIState.getStage()::setResizable);
	}	

	private static void showErrorAlert(Throwable throwable) {
		Alert alert = new Alert(AlertType.ERROR, "Oops! An unrecoverable error occurred.\n"  + "Please contact your software vendor.\n\n" + "The application will stop now.");
		alert.showAndWait().ifPresent(response -> Platform.exit());
	}
	static class StageReadyEvent extends ApplicationEvent {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public StageReadyEvent(Stage stage) {
			super(stage);

		}

		public Stage getStage() {

			return (Stage) getSource();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
