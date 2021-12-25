package com.bankass.bankass.controller;


import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bankass.bankass.model.Client;
import com.bankass.bankass.model.dto.ClientTableDto;
import com.bankass.bankass.service.ClientService;
import com.bankass.bankass.service.impl.TableService;
import com.bankass.bankass.utils.TableUtils;
import com.bankass.bankass.utils.WindowsUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
@Controller
public class ClientsController extends BaseController {

	public static final String PATH_FXML = "/fxml/clients.fxml";
	public static final String CLIENTS_TITLE_KEY = "clients.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private StackPane container;
	
	@FXML
	private JFXTreeTableView<ClientTableDto> clientsTable;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDto, String> nameColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDto, String> emailColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDto, String> addressColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDto, String> foneColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDto, String> numOrdersColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDto, String> typeColumn;
	
	@FXML
	private Pagination clientPagination;
	
	@FXML
    private JFXTextField clientSearchTextField;
    
    @FXML
    private JFXButton clientEditButton;
	
    @FXML
    private JFXButton clientRemoveButton;
    
    @Autowired
    private ClientService clientService;
    
    private ObservableList<ClientTableDto> data;
    private TableService tableService;
    
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		this.tableService = new TableService(() -> configureTable());
		this.tableService.start();
		
		configureSearch();
		
		TableUtils.configureEditAndRemoveState(clientsTable, clientEditButton, clientRemoveButton);
	}

	@Override
	protected void onClose() {
		clientService.onClose();
	}
	
	private void configureSearch() {
		TableUtils.configureTableSearch(clientSearchTextField, clientsTable, (clientProp, newVal) -> configureSearchTest(clientProp, newVal));
	}

	private boolean configureSearchTest(TreeItem<ClientTableDto> clientProp, String value) {
		final ClientTableDto client = clientProp.getValue();
		
        return client.getName().get().contains(value)
            || client.getEmail().get().contains(value)
            || client.getFone().get().contains(value)
            || client.getAddress().get().contains(value)
            || client.getAddress().get().contains(value)
            || client.getNumOrders().get().contains(value);
	}

	private void configureTable() {
		TableUtils.setupColumn(nameColumn, ClientTableDto::getName);
		TableUtils.setupColumn(emailColumn, ClientTableDto::getEmail);
		TableUtils.setupColumn(addressColumn, ClientTableDto::getAddress);
		TableUtils.setupColumn(foneColumn, ClientTableDto::getFone);
		TableUtils.setupColumn(numOrdersColumn, ClientTableDto::getNumOrders);
		TableUtils.setupColumn(typeColumn, ClientTableDto::getType);
		
		clientService.findAll(e -> {
			TableUtils.configureTable((Set<Client>) e.getSource().getValue(), data, clientsTable, clientPagination, en -> createData(en));
		}, null);
	}
	
	private ClientTableDto createData(Client client) {
		ClientTableDto clientTableDto = new ClientTableDto();
		
		if (client.getUser() != null) {
			clientTableDto.setName( new SimpleStringProperty(client.getUser().getName()));
			clientTableDto.setEmail(new SimpleStringProperty(client.getUser().getEmail()));
		}
		
		if (client.getAddress() != null) {
			clientTableDto.setAddress( new SimpleStringProperty(client.getAddress().getStreet() + " - " + client.getAddress().getNumber()));
		}
		
		clientTableDto.setType(clientTableDto.getType());
		clientTableDto.setNumOrders( new SimpleStringProperty("--"));
		clientTableDto.setOriginalClient(client);
		
		return clientTableDto;
	}
	
	@FXML
	private void onReloadTable() {
		TableUtils.reloadTable(() -> configureTable());
		TableUtils.updateEditAndRemoveButtonState(clientsTable, clientEditButton, clientRemoveButton);
	}
	
	@FXML
	private void onEditTable() throws Exception {
		ClientTableDto clientTableDto = clientsTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(clientsTable, clientTableDto.getOriginalClient(), ClientNewController.PRODUCT_KEY, ClientNewController.PATH_FXML, getWindowTitle(ClientNewController.NEW_CLIENT_TITLE_KEY), ClientNewController.PATH_ICON);
	}
	
	@FXML
	private void onRemoveTable() {
		ClientTableDto clientTableDto = clientsTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, 
										 "Remove Product", "Are you sure you want to delete the " + clientTableDto.getOriginalClient().getUser().getName() + " ?", 
										 () -> { TableUtils.removeItemFromTable(clientService, clientTableDto.getOriginalClient().getId(), clientsTable, data, container); });
	}
	
	@FXML
	private void onNewClient() throws Exception {
		WindowsUtils.openNewWindow(ClientNewController.PATH_FXML, getWindowTitle(ClientNewController.NEW_CLIENT_TITLE_KEY), ClientNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
