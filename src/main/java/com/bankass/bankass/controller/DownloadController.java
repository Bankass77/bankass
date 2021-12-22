package com.bankass.bankass.controller;

import com.bankass.bankass.dowloader.download;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadController {
	
	@FXML
	private BorderPane borderPane;

	@FXML
	private JFXTextField searchField;

	@FXML
	private JFXButton downloadMenu;

	@FXML
	private JFXButton search;

	@FXML
	private JFXButton clean;

	@FXML
	private JFXButton cancel;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableView<download> table;

	private ObservableList<download>  list = FXCollections.observableArrayList();


}
