package com.bankass.bankass.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bankass.bankass.model.Employee;
import com.bankass.bankass.model.Product;
import com.bankass.bankass.model.Sale;
import com.bankass.bankass.model.Supplier;
import com.bankass.bankass.model.dto.EmployeeTableDto;
import com.bankass.bankass.model.dto.ProductTableDto;
import com.bankass.bankass.model.dto.SalesTableDto;
import com.bankass.bankass.model.dto.SupplierTableDto;
import com.bankass.bankass.service.EmployeeService;
import com.bankass.bankass.service.ProductService;
import com.bankass.bankass.service.ReportsService;
import com.bankass.bankass.service.SaleService;
import com.bankass.bankass.service.SupplierService;
import com.bankass.bankass.service.UserService;
import com.bankass.bankass.service.impl.TableService;
import com.bankass.bankass.utils.EntityReportFactory;
import com.bankass.bankass.utils.TableUtils;
import com.bankass.bankass.utils.WindowsUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;

@SuppressWarnings("unchecked")
@Controller
public class ReportsController extends BaseController {

	public static final String PATH_FXML = "/fxml/reports.fxml";
	public static final String REPORTS_TITLE_KEY = "reports.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@FXML
	private JFXTreeTableView<SalesTableDto> salesTable;

	@FXML
	private JFXTreeTableView<ProductTableDto> productsTable;

	@FXML
	private JFXTreeTableView<EmployeeTableDto> employeesTable;

	@FXML
	private JFXTreeTableView<SupplierTableDto> suppliersTable;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> saleCodeColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> saleIssueDateColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> saleShipmentDateColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> saleTotalColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> saleNumItemsColumn;

	@FXML
	private JFXTreeTableColumn<SalesTableDto, String> saleClientNameColumn;

	@FXML
	private JFXTreeTableColumn<ProductTableDto, String> productSkuColumn;

	@FXML
	private JFXTreeTableColumn<ProductTableDto, String> productSupplierColumn;

	@FXML
	private JFXTreeTableColumn<ProductTableDto, String> productBuyPriceColumn;

	@FXML
	private JFXTreeTableColumn<ProductTableDto, String> productProductTypeColumn;

	@FXML
	private JFXTreeTableColumn<ProductTableDto, String> productDescriptionColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeNameColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeEmailColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeCpfColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> productTypeColumn;

	@FXML
	private JFXTreeTableColumn<SupplierTableDto, String> supplierNameColumn;

	@FXML
	private JFXTreeTableColumn<SupplierTableDto, String> supplierEmailColumn;

	@FXML
	private JFXTreeTableColumn<SupplierTableDto, String> supplierAddresColumn;

	@FXML
	private Pagination salesPagination;

	@FXML
	private Pagination productsPagination;

	@FXML
	private Pagination employeesPagination;

	@FXML
	private Pagination suppliersPagination;

	@FXML
	private Label totalUserLabel;

	@FXML
	private Label totalSaleLabel;

	@FXML
	private Label totalEmployeeLabel;

	@FXML
	private Label totalSuppliersLabel;

	@FXML
	private JFXSpinner salesSpinner;

	@FXML
	private JFXSpinner productsSpinner;

	@FXML
	private JFXSpinner employeesSpinner;

	@FXML
	private JFXSpinner suppliersSpinner;

	@FXML
	private JFXButton salesReportGenerate;

	@FXML
	private JFXButton productsReportGenerate;

	@FXML
	private JFXButton employeesReportGenerate;

	@FXML
	private JFXButton supplierReportGenerate;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private ReportsService reportsService;

	private ObservableList<ProductTableDto> productsData;
	private ObservableList<EmployeeTableDto> employeesData;
	private ObservableList<SupplierTableDto> suppliersData;
	private ObservableList<SalesTableDto> salesData;

	private List<TableService> tableService;

	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);

		configureLabels();
		configureTables();
	}

	@Override
	protected void onClose() {
		productService.onClose();
		supplierService.onClose();
		employeeService.onClose();
		saleService.onClose();
		userService.onClose();
		reportsService.onClose();
	}

	private void configureLabels() {
		userService.getTotalUsers(e -> {
			configureLabel(totalUserLabel, (Long) e.getSource().getValue());
		}, null);
		saleService.getTotalSales(e -> {
			configureLabel(totalSaleLabel, (Long) e.getSource().getValue());
		}, null);
		employeeService.getTotalEmployees(e -> {
			configureLabel(totalEmployeeLabel, (Long) e.getSource().getValue());
		}, null);
		supplierService.getTotalSuppliers(e -> {
			configureLabel(totalSuppliersLabel, (Long) e.getSource().getValue());
		}, null);
	}

	private void configureLabel(Label label, long value) {
		WindowsUtils.setTextInLabel(label, String.valueOf(value));
	}

	private void configureTables() {

		tableService = new ArrayList<TableService>();

		tableService.add(new TableService(() -> configureProductTable()));
		tableService.add(new TableService(() -> configureEmployeeTable()));
		tableService.add(new TableService(() -> configureSupplierTable()));
		tableService.add(new TableService(() -> configureSaleTable()));

		tableService.forEach(s -> {
			s.start();
		});
	}

	private void configureProductTable() {
		TableUtils.setupColumn(productSkuColumn, ProductTableDto::getSku);
		TableUtils.setupColumn(productSupplierColumn, ProductTableDto::getSupplier);
		TableUtils.setupColumn(productBuyPriceColumn, ProductTableDto::getBuyPrice);
		TableUtils.setupColumn(productProductTypeColumn, ProductTableDto::getProductType);
		TableUtils.setupColumn(productDescriptionColumn, ProductTableDto::getDescription);

		productService.findAll(e -> {
			TableUtils.configureTable((Set<Product>) e.getSource().getValue(), productsData, productsTable,
					productsPagination, en -> createProductData(en));
		}, null);
	}

	private void configureEmployeeTable() {

		TableUtils.setupColumn(employeeNameColumn, EmployeeTableDto::getName);
		TableUtils.setupColumn(employeeEmailColumn, EmployeeTableDto::getEmail);
		TableUtils.setupColumn(employeeCpfColumn, EmployeeTableDto::getCpf);

		employeeService.findAll(e -> {
			TableUtils.configureTable((Set<Employee>) e.getSource().getValue(), employeesData, employeesTable,
					employeesPagination, en -> createEmployeeData(en));
		}, null);
	}

	private void configureSupplierTable() {
		TableUtils.setupColumn(supplierNameColumn, SupplierTableDto::getCompanyName);
		TableUtils.setupColumn(supplierEmailColumn, SupplierTableDto::getEmail);
		TableUtils.setupColumn(supplierAddresColumn, SupplierTableDto::getAdress);

		supplierService.findAll(e -> {
			TableUtils.configureTable((Set<Supplier>) e.getSource().getValue(), suppliersData, suppliersTable,
					suppliersPagination, en -> createSupplierData(en));
		}, null);
	}

	private void configureSaleTable() {
		TableUtils.setupColumn(saleCodeColumn, SalesTableDto::getCode);
		TableUtils.setupColumn(saleShipmentDateColumn, SalesTableDto::getShipmentDate);
		TableUtils.setupColumn(saleIssueDateColumn, SalesTableDto::getIssueDate);
		TableUtils.setupColumn(saleClientNameColumn, SalesTableDto::getClient);
		TableUtils.setupColumn(saleNumItemsColumn, SalesTableDto::getTotalUnits);
		TableUtils.setupColumn(saleTotalColumn, SalesTableDto::getTotal);

		saleService.findAll(e -> {
			TableUtils.configureTable((Set<Sale>) e.getSource().getValue(), salesData, salesTable, salesPagination,
					en -> createSaleData(en));
		}, null);
	}

	private EmployeeTableDto createEmployeeData(Employee employee) {
		EmployeeTableDto em = new EmployeeTableDto();

		em.setName( new SimpleStringProperty(employee.getUser().getName()));
		em.setEmail( new SimpleStringProperty(employee.getUser().getEmail()));
		em.setCpf( new SimpleStringProperty(employee.getCpf()));

		if (employee.getAddress() != null) {
			em.setAdress( new SimpleStringProperty(employee, employee.getAddress().toString()));
		} else {
			em.setAdress( new SimpleStringProperty("--"));
		}

		em.setOriginalEmployee(employee);

		return em;
	}

	private ProductTableDto createProductData(Product product) {
		ProductTableDto productTableDto = new ProductTableDto();

		productTableDto.setSku(new SimpleStringProperty(product.getSku()));
		productTableDto.setDescription(new SimpleStringProperty(product.getDescription()));
		productTableDto.setBuyPrice(new SimpleStringProperty(product.getDescription()));

		if (product.getSupplier() != null) {
			productTableDto.setSupplier(new SimpleStringProperty(product.getSupplier().getCompanyName()));
		} else {
			productTableDto.setSupplier(new SimpleStringProperty("--"));
		}

		if (product.getProductType() != null) {
			productTableDto.setProductType( new SimpleStringProperty(product.getProductType().getName()));
		} else {
			productTableDto.setProductType( new SimpleStringProperty("--"));
		}

		productTableDto.setOriginalProduct(product);

		return productTableDto;
	}

	private SupplierTableDto createSupplierData(Supplier supplier) {
		SupplierTableDto supplierTableDto = new SupplierTableDto();

		supplierTableDto.setCompanyName( new SimpleStringProperty(supplier.getCompanyName()));
		supplierTableDto.setEmail(  new SimpleStringProperty(supplier.getEmail()));

		if (supplier.getAddres() != null) {
			supplierTableDto.setAdress(new SimpleStringProperty(supplier.getAddres(), null) );
		} else {
			supplierTableDto.setAdress( new SimpleStringProperty("--"));
		}

		supplierTableDto.setOriginalSupplier(supplier);

		return supplierTableDto;

	}

	private SalesTableDto createSaleData(Sale sale) {
		SalesTableDto salesTableDto = new SalesTableDto();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		salesTableDto.setCode( new SimpleStringProperty(sale.getSaleCode()));

		if (sale.getCliente() != null) {
			salesTableDto.setClient(new SimpleStringProperty(sale.getCliente().getUser().getName()));
		}

		if (sale.getShipmentDate() != null) {
			salesTableDto.setShipmentDate(new SimpleStringProperty(formatter.format(sale.getShipmentDate())));
		}

		if (sale.getIssueDate() != null) {
			salesTableDto.setIssueDate( new SimpleStringProperty( formatter.format(sale.getIssueDate())));
		}

		salesTableDto.setTotalUnits(new SimpleStringProperty(String.valueOf(sale.getTotalUnits())));
		salesTableDto.setTotal( new SimpleStringProperty(String.valueOf(sale.getTotal())));
		salesTableDto.setOriginalObject(sale);

		return salesTableDto;
	}

	private <T> void createReport(Set<T> data, String reportTemplatePath, JFXButton reportGenerate,
			JFXSpinner spinner) {
		reportsService.createJasperPrint(reportTemplatePath, data, e -> {
			reportGenerate.setDisable(false);
			spinner.setVisible(false);

			JasperPrint jasperPrint = (JasperPrint) e.getSource().getValue();

			try {
				HashMap<String, JasperPrint> parameters = new HashMap<String, JasperPrint>();
				parameters.put(ReportViewerController.JASPER_PRINT, jasperPrint);

				System.out.println(jasperPrint);

				WindowsUtils.openNewWindow(ReportViewerController.PATH_FXML,
						getWindowTitle(ReportViewerController.REPORT_VIEWER_TITLE_KEY),
						ReportViewerController.PATH_ICON, parameters, Modality.APPLICATION_MODAL);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}, e -> {
			reportGenerate.setDisable(true);
			spinner.setVisible(true);
		});
	}

	@FXML
	public void salesReport() throws Exception {

		saleService.findAll(e -> {
			createReport(EntityReportFactory.createSales((Set<Sale>) e.getSource().getValue()),
					"/reports/sales_template.jrxml", salesReportGenerate, salesSpinner);
		}, null);
	}

	@FXML
	public void productsReport() throws Exception {
		productService.findAll(e -> {
			createReport(EntityReportFactory.createProducts((Set<Product>) e.getSource().getValue()),
					"/reports/products_template.jrxml", productsReportGenerate, productsSpinner);
		}, null);
	}

	@FXML
	public void employeesReport() throws Exception {
		employeeService.findAll(e -> {
			createReport(EntityReportFactory.createEmployees((Set<Employee>) e.getSource().getValue()),
					"/reports/employees_template.jrxml", employeesReportGenerate, employeesSpinner);
		}, null);
	}

	@FXML
	public void suppliersReport() throws Exception {
		supplierService.findAll(e -> {
			createReport(EntityReportFactory.createSuppliers((Set<Supplier>) e.getSource().getValue()),
					"/reports/suppliers_template.jrxml", supplierReportGenerate, suppliersSpinner);
		}, null);
	}

	@FXML
	public void onReloadSaleTable() {
		TableUtils.reloadTable(() -> configureSaleTable());
	}

	@FXML
	public void onReloadProductTable() {
		TableUtils.reloadTable(() -> configureProductTable());
	}

	@FXML
	public void onReloadEmployeeTable() {
		TableUtils.reloadTable(() -> configureEmployeeTable());
	}

	@FXML
	public void onReloadSupplierTable() {
		TableUtils.reloadTable(() -> configureSupplierTable());
	}

}
