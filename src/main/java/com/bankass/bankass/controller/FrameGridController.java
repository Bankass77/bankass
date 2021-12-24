package com.bankass.bankass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import com.bankass.bankass.model.User;
import com.bankass.bankass.service.FrameService;
import com.bankass.bankass.support.AbstractFxmlView;
import com.bankass.bankass.support.FXMLController;
import com.bankass.bankass.support.PrototypeController;
import com.bankass.bankass.views.FrameGridDef;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@FXMLController
@Scope("prototype")
@Slf4j
public class FrameGridController implements PrototypeController {

		
	
	@Autowired
	private ApplicationContext context;
	@FXML
	private Button addButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button printButton;
	@FXML
	private TableView frameGrid;
	private FrameService frameService;
	private FrameGridDef gridDef;
	private Scene scene;
	
	@FXML
	private void initialize() {
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		printButton.setDisable(true);
		addButton.setOnAction((event) -> { addButtonHandleAction(); });
		editButton.setOnAction((event) -> { editButtonHandleAction(); });
		deleteButton.setOnAction((event) -> { deleteButtonHandleAction(); });
		printButton.setOnAction((event) -> { printButtonHandleAction(); });
	}
	
	private void addButtonHandleAction() {
		AbstractFxmlView fxmlView = showDialog();
		CrudController controller = (CrudController) fxmlView.getFxmlLoader().getController();
		controller.add();
	}

	private void editButtonHandleAction() {
		AbstractFxmlView fxmlView = showDialog();
		CrudController controller = (CrudController) fxmlView.getFxmlLoader().getController();
		User entity = (User) frameGrid.getSelectionModel().getSelectedItem();
		controller.render(entity);
	}
	
	private void deleteButtonHandleAction() {
		User entity = (User) frameGrid.getSelectionModel().getSelectedItem();
		frameService.delete((long) entity.getId());
		loadData();
	}
	
	private void printButtonHandleAction() {
		log.debug("clicked printButton");
	}
	
	public void initializeGrid(FrameService frameService, FrameGridDef gridDef) {
		this.frameService = frameService;
		this.gridDef = gridDef;
		setupGrid();
		loadData();
	}
	
	private void setupGrid() {
		List<String> columnNames = gridDef.getColumnNames();
		List<String> columnDataNames = gridDef.getColumnDataName();
		List<Integer> columnSizes = gridDef.getColumnSizes();		
		for (int i = 0; i < gridDef.getColumnNames().size(); i++) {
	        TableColumn<User, String> column = new TableColumn<>(columnNames.get(i));
	        column.setCellValueFactory(
	            new PropertyValueFactory<User, String>(columnDataNames.get(i))
	        );
			column.setMinWidth(columnSizes.get(i));
	        frameGrid.getColumns().add(column);
		}
		frameGrid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				editButton.setDisable(false);
				deleteButton.setDisable(false);
			} else {
				editButton.setDisable(true);
				deleteButton.setDisable(true);
			}
		});
		frameGrid.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) { editButtonHandleAction(); }
		});		
		frameGrid.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) { editButtonHandleAction(); }
		});
	}

	private void loadData() {
	    ObservableList<User> data = (ObservableList<User>) ((FrameService) FXCollections.observableArrayList(frameService)).getData();
	    if (data != null) {
			log.debug("loadData, data size: {}", data.size());
		    frameGrid.setItems(data);
	    }
	}

	private AbstractFxmlView showDialog() {
		AbstractFxmlView fxmlView = (AbstractFxmlView) context.getBean(gridDef.getCreateView());
		Stage stage = new Stage();
		scene = new Scene(fxmlView.getView());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle(gridDef.getTitlePopups());        
        stage.setOnHidden((event) -> { 
        	stage.close(); 
    		User oldSelected = (User) frameGrid.getSelectionModel().getSelectedItem();
        	loadData();
        	if (oldSelected != null) {
        		frameGrid.getSelectionModel().select(oldSelected);
        	} else {
        		frameGrid.getSelectionModel().select(0);
        	}
        });
        stage.show();
		return fxmlView;
	}
}
