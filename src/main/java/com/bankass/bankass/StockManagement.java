package com.bankass.bankass;

import java.util.Locale;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import com.bankass.bankass.controller.LoginController;
import com.bankass.bankass.controller.RootController;
import com.bankass.bankass.model.Language;
import com.bankass.bankass.model.User;
import com.bankass.bankass.service.LanguageService;
import com.bankass.bankass.service.UserService;
import com.bankass.bankass.utils.I18N;
import com.bankass.bankass.utils.WindowsUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockManagement extends Application {

	public static ConfigurableApplicationContext applicationContext;
	public static I18N i18n;
	public static HostServices hostServices;

	private UserService userService;
	private LanguageService languageService;

	@Override
	public void start(Stage stage) throws Exception {

		hostServices = this.getHostServices();

		userService.finUserSignIn(e -> {
			try {
				User user = (User) e.getSource().getValue();

				log.debug("User is :{}", user);

				if (user == null) {
					WindowsUtils.openNewWindow(stage, LoginController.PATH_FXML,
							i18n.getString(LoginController.LOGIN_TITLE_KEY), LoginController.PATH_ICON, null);
				} else {
					WindowsUtils.openNewWindow(stage, RootController.PATH_FXML,
							i18n.getString(RootController.ROOT_TITLE_KEY), RootController.PATH_ICON, null);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}, null);
		applicationContext.publishEvent(new StageReadyEvent(stage));

	}

	/**
	 * Initializes the root layout.
	 */
	@Override
	public void init() {
		applicationContext = new SpringApplicationBuilder(BankassApplication.class).run();
		userService = applicationContext.getBean(UserService.class);
		languageService = applicationContext.getBean(LanguageService.class);

		initI18N();

	}

	private void initI18N() {
		Language languageDefault = languageService.findDefaultLanguage();

		if (languageDefault != null) {
			i18n = new I18N(new Locale(languageDefault.getLanguageCode(), languageDefault.getCountryCode()));
		} else {
			i18n = new I18N(I18N.FRENCH);
		}

	}

	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();

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

}
