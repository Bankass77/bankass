package com.bankass.bankass.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bankass.bankass.model.Sale;
import com.bankass.bankass.model.salesTable;
import com.bankass.bankass.model.dto.SalesTableDto;
import com.bankass.bankass.service.SaleService;
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

@Controller
@Scope("prototype")
public class SaleTableController extends BaseController {

	enum TypeSaleTable {
		OPEN, ALL, FINALIZED
	};

	@FXML
	private StackPane container;

	@FXML
	private JFXTreeTableView<SalesTableDto> salesTable;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> salesCodeColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> salesShipmentDateColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> salesIssueDateColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> salesClientColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> salesTotalUnitsColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> salesTotalColumn;

	@FXML
	private JFXButton salesEditButton;

	@FXML
	private JFXButton salesRemoveButton;

	@FXML
	private JFXButton salesReloadButton;

	@FXML
	private JFXTextField salesSearchTextField;

	@FXML
	private Pagination salesPagination;

	@Autowired
	private SaleService saleService;

	private TypeSaleTable type;
	private ObservableList<SalesTableDto> data;
	private TableService tableService;
	private SalesController salesController;

	public void init(TypeSaleTable type, SalesController salesController) {
		this.salesController = salesController;
		this.type = type;
		this.tableService = new TableService(() -> configureTable());
		this.tableService.start();

		configureSearch();

		TableUtils.configureEditAndRemoveState(salesTable, salesEditButton, salesRemoveButton);
	}

	@SuppressWarnings("unchecked")
	private void configureTable() {
		TableUtils.setupColumn(salesCodeColumn, SalesTableDto::getCode);
		TableUtils.setupColumn(salesShipmentDateColumn, SalesTableDto::getShipmentDate);
		TableUtils.setupColumn(salesIssueDateColumn, SalesTableDto::getIssueDate);
		TableUtils.setupColumn(salesClientColumn, SalesTableDto::getClient);
		TableUtils.setupColumn(salesTotalUnitsColumn, SalesTableDto::getTotalUnits);
		TableUtils.setupColumn(salesTotalColumn, SalesTableDto::getTotal);

		salesController.getSales(type, e -> {
			data = TableUtils.filledDataOnTable((Set<Sale>) e.getSource().getValue(), en -> createData(en));

			TableUtils.configurePagination(salesTable, data, salesPagination);
		}, null);

		salesTable.setShowRoot(false);
		salesTable.setEditable(true);

	}

	private SalesTableDto createData(Sale sale) {
		SalesTableDto salesTableDto = new SalesTableDto();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		salesTableDto.setCode( new SimpleStringProperty(sale.getSaleCode()));

		if (sale.getCliente() != null) {
			salesTableDto.setClient( new SimpleStringProperty(sale.getCliente().getUser().getName()));
		}

		if (sale.getShipmentDate() != null) {
			salesTableDto.setShipmentDate(new SimpleStringProperty(formatter.format(sale.getShipmentDate())));
		}

		if (sale.getIssueDate() != null) {
			salesTableDto.setIssueDate( new SimpleStringProperty(formatter.format(sale.getIssueDate())));
		}

		salesTableDto.setTotalUnits( new SimpleStringProperty(String.valueOf(sale.getTotalUnits())));
		salesTableDto.setTotal( new SimpleStringProperty(String.valueOf(sale.getTotal())));
		salesTableDto.setOriginalObject(sale);

		return salesTableDto;
	}

	private void configureSearch() {
		TableUtils.configureTableSearch(salesSearchTextField, salesTable,
				(saleProp, newVal) -> configureSearchTest(saleProp, newVal));
	}

	private boolean configureSearchTest(TreeItem<SalesTableDto> saleProp, String value) {
		final SalesTableDto sale = saleProp.getValue();

		return sale.getCode().get().contains(value) || sale.getClient().get().contains(value)

				|| sale.getTotal().get().contains(value) || sale.getTotalUnits().get().contains(value)
				|| (sale.getIssueDate() != null || sale.getIssueDate().get().contains(value))
				|| (sale.getShipmentDate() != null || sale.getShipmentDate().get().contains(value));
	}

	@FXML
	public void onEdiSalesTable() throws Exception {
		if (salesTable.getSelectionModel().selectedItemProperty().get() != null) {
			SalesTableDto salesTableDto = salesTable.getSelectionModel().selectedItemProperty().get().getValue();
			TableUtils.editItemFromTable(salesTable, salesTableDto.getOriginalObject(), SalesNewController.SALE_KEY,
					SalesNewController.PATH_FXML, getWindowTitle(SalesNewController.NEW_SALE_TITLE_KEY),
					SalesNewController.PATH_ICON);
		}
	}

	@FXML
	public void onRemoveSalesTable() {
		if (salesTable.getSelectionModel().selectedItemProperty().get() != null) {
			SalesTableDto salesTableDto = salesTable.getSelectionModel().selectedItemProperty().get().getValue();
			WindowsUtils.createDefaultDialog(container, "Remove Sale", "Are you sure you want to delete the sale "
					+ salesTableDto.getOriginalObject().getSaleCode() + " ?", () -> {
						TableUtils.removeItemFromTable(saleService, salesTableDto.getOriginalObject().getId(),
								salesTable, data, container);
					});
		}
	}

	@FXML
	public void onChangeStateSalesTable() {

	}

	@FXML
	public void onReloadSalesTable() {
		TableUtils.reloadTable(() -> configureTable());
		TableUtils.updateEditAndRemoveButtonState(salesTable, salesEditButton, salesRemoveButton);
	}

	@Override
	protected void onClose() {
		saleService.onClose();
	}

}
