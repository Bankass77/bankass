package com.bankass.bankass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import com.bankass.bankass.config.PropertiesConfig;
import com.bankass.bankass.model.MenuItem;
import com.bankass.bankass.service.FrameService;
import com.bankass.bankass.service.MenuItemService;
import com.bankass.bankass.support.FXMLController;
import com.bankass.bankass.views.FrameGridDef;
import com.bankass.bankass.views.FrameGridView;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

@FXMLController
public class MainController {
	private static String ADDITIONAL_TAB_TITLE = "     ";

	@Autowired
	private ApplicationContext context;

	@Autowired
	private PropertiesConfig config;

	@Autowired
	private MenuItemService menuItemService;

	@FXML
	private VBox mainPanel;

	@FXML
	private SplitPane mainSplitPanel;

	@FXML
	private TabPane tabPane;
	
	TreeView<MenuItem> treeView;

	@FXML
	private void initialize() {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		mainPanel.setPrefWidth(primaryScreenBounds.getWidth() * 0.8);
		mainPanel.setPrefHeight(primaryScreenBounds.getHeight() * 0.8);

		if (config.getJavafxMainToolbar()) {
			ToolBar toolbar = buildToolBar();
			mainPanel.getChildren().add(1, toolbar);
		}

		if (config.getJavafxMainTree()) {
			MenuItem rootMenuItem = menuItemService.getMenuItemRoot();
			TreeItem<MenuItem> rootNode = new TreeItem<>();
			rootNode.setValue(rootMenuItem);
			rootNode.setExpanded(rootMenuItem.getExpanded());
			treeView = new TreeView<>();
			treeView.setRoot(rootNode);
			buildTreeItems(rootNode);
			mainSplitPanel.getItems().add(0, treeView);
			treeView.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					onSelectItemAction(treeView);
				}
			});
			treeView.setOnMouseClicked((event) -> {
				if (event.getClickCount() == 2) {
					onSelectItemAction(treeView);
				}
			});
		}

	}

	public void onWindowShownEvent() {
		if (treeView != null) {
			MultipleSelectionModel<TreeItem<MenuItem>> msm = treeView.getSelectionModel();
			msm.select(treeView.getRoot());
			treeView.requestFocus();
		};
	}


	private void buildTreeItems(TreeItem<MenuItem> parentNode) {
		Long parentId = -1l;
		if (parentNode.getValue() != null) {
			parentId = parentNode.getValue().getId();
		}
		List<MenuItem> menuItems = menuItemService.getMenuItemsByParent(parentId);
		for (MenuItem item : menuItems) {
			TreeItem<MenuItem> itemNode = new TreeItem<>();
			itemNode.setValue(item);
			itemNode.setExpanded(item.getExpanded());
			if (!item.getImage().isEmpty()) {
				itemNode.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/" + item.getImage()))));
			}
			parentNode.getChildren().add(itemNode);
			buildTreeItems(itemNode);
		}
	}

	private void onSelectItemAction(TreeView <MenuItem> treeView) {
		TreeItem<MenuItem> item = treeView.getSelectionModel().getSelectedItem();
		if (item == null)
			return;
		int tabIndex = findTabIndex(item.getValue().getValue());
		if (tabIndex == -1) {
			tabPane.getTabs().contains((Object) item.getValue());
			showItemContent(item.getValue());
		} else {
			tabPane.getSelectionModel().select(tabIndex);
		}
	}

	private void showItemContent(MenuItem menuItem) {
		if (menuItem.getService() == null) {
			return;
		}

		FrameGridView gridView = (FrameGridView) context.getBean(FrameGridView.class);
		FrameGridController controller = (FrameGridController) context.getBean(FrameGridController.class);
		gridView.setController(controller);

		Tab tab = new Tab(menuItem.getValue() + ADDITIONAL_TAB_TITLE);
		tab.setContent(gridView.getView());
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tabPane.getTabs().size() - 1);

		FrameService frameService = (FrameService) context.getBean(menuItem.getService());
		FrameGridDef frameGridDef = (FrameGridDef) context.getBean(menuItem.getGridDef());
		controller.initializeGrid(frameService, frameGridDef);
	}

	private ToolBar buildToolBar() {
		ToolBar toolbar = new ToolBar();
		List<MenuItem> menuItems = menuItemService.getMenuItemsByParent(0l);
		for (MenuItem item : menuItems) {
			Button button = new Button();
			button.setTooltip(new Tooltip(item.getValue()));
			if (!item.getImage().isEmpty()) {
				button.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/" + item.getImage()))));
			}
			button.setOnAction((event) -> { onToolBarButtonAction(item); });
			toolbar.getItems().add(button);
		}
		return toolbar;
	}

	private void onToolBarButtonAction(MenuItem item) {
		int tabIndex = findTabIndex(item.getValue());
		if (tabIndex == -1) {
			tabPane.getTabs().contains((Object) item.getValue());
			showItemContent(item);
		} else {
			tabPane.getSelectionModel().select(tabIndex);
		}
	}
	
	private int findTabIndex(String title) {
		for (int i = 0; i < tabPane.getTabs().size(); i++) {
			Tab tab = tabPane.getTabs().get(i);
			if (tab.getText().equals(title + ADDITIONAL_TAB_TITLE)) {
				return i;
			}
		}
		return -1;
	}

}
