package com.bankass.bankass.controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TabController {
	

	@FXML
	private BorderPane borderpane;

	@FXML
	private GridPane navigationBar;

	@FXML
	private Label back;

	@FXML
	private Label forward;

	@FXML
	private Label refresh;

	@FXML
	private JFXTextField searchField;

	@FXML
	private Label search;

	@FXML
	private Label download;

	@FXML
	private Label bookmark;

	@FXML
	private Label htmlAsPdf;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXProgressBar progressbar;

	private String folder;
	private String title;
	private ObservableList<String> options;


}
