package com.bankass.bankass.dowloader;

import javafx.application.Platform;
import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.controlsfx.control.Notifications;

@Data
public class Notification {
	public Notification(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Notifications notify = Notifications.create().title("Download")
						.text("Download File")
						.hideAfter(javafx.util.Duration.seconds(2))
						.position(Pos.BOTTOM_RIGHT);
				notify.darkStyle();
				notify.showInformation();
			}
		}); 


	}


}
