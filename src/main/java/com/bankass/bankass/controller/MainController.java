package com.bankass.bankass.controller;

import java.io.IOException;

import com.bankass.bankass.model.Product;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Data;


@Data
public class MainController {
	@FXML
	private BorderPane rootBorderPane;

	public Product product = new Product();
	public VBox drawerPane = new VBox();

	public static TabPane tabPane = new TabPane();

	private static Tab firstTab = new Tab("New Tab");
	
	private static final Tab addNewTab = new Tab("+");
	
	public void setFirstTab(Tab firstTab) {
		this.firstTab = firstTab;
	}

	public static Tab getFirstTab() {
		return firstTab;
	}

	// ---set method for TabPane---
	/*
	 * public void setTabPane(TabPane tabPane) { MainController.tabPane = tabPane;
	 * 
	 * // System.out.println("Setter title:"+TabController.getTitle());
	 * getTabPaneView(tabPane, addNewTab); tabPaneChangeListener(tabPane);
	 * 
	 * }
	 */

		public TabPane getTabPane() {
			return tabPane;
		}

		public static Tab getAddNewTab() {
			return addNewTab;
		}

		/*
		 * public void tabPaneChangeListener(TabPane tabpane) {
		 * tabpane.getSelectionModel().selectedItemProperty().addListener(new
		 * ChangeListener<Tab>() {
		 * 
		 * @Override public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab
		 * newSelectedTab) { product.hideProductPane(); } }); }
		 */
		/*
		 * public TabPane getTabPaneView(TabPane tabpane, Tab addNewTab) {
		 * tabpane.getSelectionModel().selectedItemProperty().addListener(new
		 * ChangeListener<Tab>() {
		 * 
		 * @Override
		 * 
		 * public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab
		 * newSelectedTab) {
		 * 
		 * // Closeing tab if first index tab close and size will be the 2 //
		 * https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/package-
		 * summary.html
		 * 
		 * if (tabPane.getTabs().size() == 1) { Platform.exit(); }
		 * 
		 * // The current tab title is set the stage title
		 * //MainClass.getStage().setTitle(tabPane.getSelectionModel().getSelectedItem()
		 * .getText());
		 * 
		 * //The above line was just setting the name of window but according to the
		 * requirement we // just set the fixed name of browser Jfx Browser
		 * 
		 * if (newSelectedTab == addNewTab) {
		 * 
		 * // ---------------New tab is created --------------------
		 * 
		 * Platform.runLater(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * creatNewTab(tabpane, addNewTab);
		 * 
		 * } }); } } });
		 * 
		 * return tabpane;
		 * 
		 * }
		 */

		// end class
		/*
		 * public void creatNewTab(TabPane tabpane, Tab addNewTab) {
		 * 
		 * Tab tab = new Tab("New tab");
		 * 
		 * 
		 * try {
		 * tab.setContent(FXMLLoader.load(getClass().getResource(Main.FXMLS+"Tab.fxml"))
		 * ); // tab.setText(TabController.getWebEngine().getTitle());
		 * 
		 * } catch (IOException e) { System.out.
		 * println("Exception: New tab click but not working in TabPaneView Class"); }
		 * 
		 * tab.getStyleClass().addAll("tab-pane");
		 * 
		 * ObservableList<Tab> tabs = tabpane.getTabs();
		 * 
		 * Platform.runLater(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * tabs.add(tabs.size() - 1, tab);
		 * 
		 * SingleSelectionModel<Tab> selectedTab = tabpane.getSelectionModel();
		 * selectedTab.select(tab);
		 * 
		 * } });
		 * 
		 * }
		 */

		// end class

}
