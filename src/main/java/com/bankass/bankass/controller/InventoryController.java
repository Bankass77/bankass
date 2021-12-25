package com.bankass.bankass.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bankass.bankass.model.Brand;
import com.bankass.bankass.model.Employee;
import com.bankass.bankass.model.Product;
import com.bankass.bankass.model.Supplier;
import com.bankass.bankass.model.dto.BrandTableDto;
import com.bankass.bankass.model.dto.EmployeeTableDto;
import com.bankass.bankass.model.dto.ProductTableDto;
import com.bankass.bankass.model.dto.SupplierTableDto;
import com.bankass.bankass.service.BrandService;
import com.bankass.bankass.service.EmployeeService;
import com.bankass.bankass.service.ProductService;
import com.bankass.bankass.service.SupplierService;
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
public class InventoryController extends BaseController {

	public static final String PATH_FXML = "/fxml/inventory.fxml";
	public static final String INVENTORY_TITLE_KEY = "inventory.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@FXML
	private StackPane container;

	@FXML
	private JFXTreeTableView<ProductTableDto> productsTable;

	@FXML
	private JFXTreeTableView<EmployeeTableDto> employeeTable;

	@FXML
	private JFXTreeTableView<SupplierTableDto> supplierTable;

	@FXML
	private JFXTreeTableView<BrandTableDto> brandTable;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeNameColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeEmailColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeCpfColumn;

	@FXML
	private JFXTreeTableColumn<EmployeeTableDto, String> employeeAdressColumn;

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
	private JFXTreeTableColumn<SupplierTableDto, String> supplierNameColumn;

	@FXML
	private JFXTreeTableColumn<SupplierTableDto, String> supplierEmailColumn;

	@FXML
	private JFXTreeTableColumn<SupplierTableDto, String> supplierAddresColumn;

	@FXML
	private JFXTreeTableColumn<BrandTableDto, String> brandNameColumn;

	@FXML
	private JFXTreeTableColumn<BrandTableDto, String> brandEmailColumn;

	@FXML
	private JFXTreeTableColumn<BrandTableDto, String> brandAdditionalInformationColumn;

	@FXML
	private Pagination productPagination;

	@FXML
	private Pagination employeePagination;

	@FXML
	private Pagination supplierPagination;

	@FXML
	private Pagination brandPagination;

	@FXML
	private JFXTextField productSearchTextField;

	@FXML
	private JFXTextField employeeSearchTextField;

	@FXML
	private JFXTextField supplierSearchTextField;

	@FXML
	private JFXTextField brandSearchTextField;

	@FXML
	private JFXButton productEditButton;

	@FXML
	private JFXButton productRemoveButton;

	@FXML
	private JFXButton employeeEditButton;

	@FXML
	private JFXButton employeeRemoveButton;

	@FXML
	private JFXButton supplierEditButton;

	@FXML
	private JFXButton supplierRemoveButton;

	@FXML
	private JFXButton brandEditButton;

	@FXML
	private JFXButton brandRemoveButton;

	@Autowired
	private ProductService productService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private BrandService brandService;

	private ObservableList<ProductTableDto> productsData;

	private ObservableList<EmployeeTableDto> employeesData;

	private ObservableList<SupplierTableDto> suppliersData;

	private ObservableList<BrandTableDto> brandsData;

	private Set<TableService> tableService;

	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);

		configureTables();
		configureSearchs();
	}

	@Override
	protected void onClose() {
		productService.onClose();
		supplierService.onClose();
		employeeService.onClose();
		brandService.onClose();
	}

	private void configureTables() {
		tableService = new HashSet<TableService>();

		tableService.add(new TableService(() -> configureProductTable()));
		tableService.add(new TableService(() -> configureEmployeeTable()));
		tableService.add(new TableService(() -> configureSupplierTable()));
		tableService.add(new TableService(() -> configureBrandTable()));

		tableService.forEach(s -> {
			s.start();
		});

		TableUtils.configureEditAndRemoveState(productsTable, productEditButton, productRemoveButton);
		TableUtils.configureEditAndRemoveState(employeeTable, employeeEditButton, employeeRemoveButton);
		TableUtils.configureEditAndRemoveState(supplierTable, supplierEditButton, supplierRemoveButton);
		TableUtils.configureEditAndRemoveState(brandTable, brandEditButton, brandRemoveButton);
	}

	private void configureSearchs() {
		TableUtils.configureTableSearch(productSearchTextField, productsTable,
				(productProp, newVal) -> configureProductSearchTest(productProp, newVal));
		TableUtils.configureTableSearch(employeeSearchTextField, employeeTable,
				(employeeProp, newVal) -> configureEmployeeSearchTest(employeeProp, newVal));
		TableUtils.configureTableSearch(supplierSearchTextField, supplierTable,
				(supplierProp, newVal) -> configureSupplierSearchTest(supplierProp, newVal));
		TableUtils.configureTableSearch(brandSearchTextField, brandTable,
				(brandProp, newVal) -> configureBrandSearchTest(brandProp, newVal));
	}

	private boolean configureProductSearchTest(TreeItem<ProductTableDto> productProp, String value) {
		final ProductTableDto product = productProp.getValue();
		return product.getSku().get().contains(value) || product.getSupplier().get().contains(value)
				|| product.getBuyPrice().get().contains(value) || product.getDescription().get().contains(value)
				|| product.getProductType().get().contains(value);
	}

	private boolean configureEmployeeSearchTest(TreeItem<EmployeeTableDto> employeeProp, String value) {
		final EmployeeTableDto employee = employeeProp.getValue();
		return employee.getName().get().contains(value) || employee.getEmail().get().contains(value)
				|| employee.getCpf().get().contains(value) || employee.getAdress().get().contains(value);
	}

	private boolean configureSupplierSearchTest(TreeItem<SupplierTableDto> supplierProp, String value) {
		final SupplierTableDto supplier = supplierProp.getValue();
		return supplier.getCompanyName().get().contains(value) || supplier.getEmail().get().contains(value)
				|| supplier.getAdress().get().contains(value);
	}

	private boolean configureBrandSearchTest(TreeItem<BrandTableDto> brandProp, String value) {
		final BrandTableDto brand = brandProp.getValue();
		return brand.getName().get().contains(value) || brand.getEmail().get().contains(value)
				|| brand.getAdditionalInformation().get().contains(value);
	}

	private void configureProductTable() {
		TableUtils.setupColumn(productSkuColumn, ProductTableDto::getSku);
		TableUtils.setupColumn(productSupplierColumn, ProductTableDto::getSupplier);
		TableUtils.setupColumn(productBuyPriceColumn, ProductTableDto::getBuyPrice);
		TableUtils.setupColumn(productProductTypeColumn, ProductTableDto::getProductType);
		TableUtils.setupColumn(productDescriptionColumn, ProductTableDto::getDescription);

		productService.findAll(e -> {
			TableUtils.configureTable((Set<Product>) e.getSource().getValue(), productsData, productsTable,
					productPagination, en -> createProductData(en));
		}, null);
	}

	private void configureEmployeeTable() {

		TableUtils.setupColumn(employeeNameColumn, EmployeeTableDto::getName);
		TableUtils.setupColumn(employeeEmailColumn, EmployeeTableDto::getEmail);
		TableUtils.setupColumn(employeeCpfColumn, EmployeeTableDto::getCpf);
		TableUtils.setupColumn(employeeAdressColumn, EmployeeTableDto::getAdress);

		employeeService.findAll(e -> {
			TableUtils.configureTable((Set<Employee>) e.getSource().getValue(), employeesData, employeeTable,
					employeePagination, en -> createEmplyeeData(en));
		}, null);
	}

	private void configureSupplierTable() {
		TableUtils.setupColumn(supplierNameColumn, SupplierTableDto::getCompanyName);
		TableUtils.setupColumn(supplierEmailColumn, SupplierTableDto::getEmail);
		TableUtils.setupColumn(supplierAddresColumn, SupplierTableDto::getAdress);

		supplierService.findAll(e -> {
			TableUtils.configureTable((Set<Supplier>) e.getSource().getValue(), suppliersData, supplierTable,
					supplierPagination, en -> createSupplierData(en));
		}, null);
	}

	private void configureBrandTable() {
		TableUtils.setupColumn(brandNameColumn, BrandTableDto::getName);
		TableUtils.setupColumn(brandEmailColumn, BrandTableDto::getEmail);
		TableUtils.setupColumn(brandAdditionalInformationColumn, BrandTableDto::getAdditionalInformation);

		brandService.findAll(e -> {
			TableUtils.configureTable((Set<Brand>) e.getSource().getValue(), brandsData, brandTable, brandPagination,
					en -> createBrandData(en));
		}, null);
	}

	private EmployeeTableDto createEmplyeeData(Employee employee) {
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

		productTableDto.setSku( new SimpleStringProperty(product.getSku()));
		productTableDto.setDescription(new SimpleStringProperty(product.getDescription()));
		productTableDto.setBuyPrice( new SimpleStringProperty( String.valueOf(product.getBuyPrice())));

		if (product.getSupplier() != null) {
			productTableDto.setSupplier(new SimpleStringProperty(product.getSupplier().getCompanyName()));
		} else {
			productTableDto.setSupplier( new SimpleStringProperty("--"));
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
		supplierTableDto.setEmail( new SimpleStringProperty(supplier.getEmail()));

		if (supplier.getAddres() != null) {
			supplierTableDto.setAdress( new SimpleStringProperty(supplier, supplier.getAddres().toString()));
		} else {
			supplierTableDto.setAdress( new SimpleStringProperty("--"));
		}

		supplierTableDto.setOriginalSupplier(supplier);

		return supplierTableDto;

	}

	private BrandTableDto createBrandData(Brand brand) {
		BrandTableDto brandTableDto = new BrandTableDto();

		brandTableDto.setName( new SimpleStringProperty(brand.getName()));
		brandTableDto.setEmail( new SimpleStringProperty(brand.getEmail()));
		brandTableDto.setAdditionalInformation( new SimpleStringProperty(brand.getAdditionalInformation()));
		brandTableDto.setOriginalBrand(brand);

		return brandTableDto;
	}

	@FXML
	private void onReloadProductTable() {
		TableUtils.reloadTable(() -> configureProductTable());
		TableUtils.updateEditAndRemoveButtonState(productsTable, productEditButton, productRemoveButton);
	}

	@FXML
	private void onReloadEmployeeTable() {
		TableUtils.reloadTable(() -> configureEmployeeTable());
		TableUtils.updateEditAndRemoveButtonState(employeeTable, employeeEditButton, employeeRemoveButton);
	}

	@FXML
	private void onReloadSupplierTable() {
		TableUtils.reloadTable(() -> configureSupplierTable());
		TableUtils.updateEditAndRemoveButtonState(supplierTable, supplierEditButton, supplierRemoveButton);
	}

	@FXML
	private void onReloadBrandTable() {
		TableUtils.reloadTable(() -> configureBrandTable());
		TableUtils.updateEditAndRemoveButtonState(brandTable, brandEditButton, brandRemoveButton);
	}

	@FXML
	private void onEditProductTable() throws Exception {
		ProductTableDto productTableValue = productsTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(productsTable, productTableValue.getOriginalProduct(),
				ProductNewController.PRODUCT_KEY, ProductNewController.PATH_FXML,
				getWindowTitle(ProductNewController.NEW_PRODUCT_TITLE_KEY), ProductNewController.PATH_ICON);
	}

	@FXML
	private void onEditEmployeeTable() throws Exception {
		EmployeeTableDto employeeTableValue = employeeTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(employeeTable, employeeTableValue.getOriginalEmployee(),
				EmployeeNewController.EMPLOYEE_KEY, EmployeeNewController.PATH_FXML,
				getWindowTitle(EmployeeNewController.NEW_EMPLOYEE_TITLE_KEY), EmployeeNewController.PATH_ICON);
	}

	@FXML
	private void onEditSupplierTable() throws Exception {
		SupplierTableDto supplierTableValue = supplierTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(supplierTable, supplierTableValue.getOriginalSupplier(),
				SupplierNewController.SUPPLIER_KEY, SupplierNewController.PATH_FXML,
				getWindowTitle(SupplierNewController.NEW_SUPPLIER_TITLE_KEY), SupplierNewController.PATH_ICON);
	}

	@FXML
	private void onEditBrandTable() throws Exception {
		BrandTableDto brandTableValue = brandTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(brandTable, brandTableValue.getOriginalBrand(), BrandNewController.BRAND_KEY,
				BrandNewController.PATH_FXML, getWindowTitle(BrandNewController.NEW_BRAND_TITLE_KEY),
				BrandNewController.PATH_ICON);
	}

	@FXML
	private void onRemoveProductTable() {
		ProductTableDto productTableValue = productsTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, "Remove Product",
				"Are you sure you want to delete the " + productTableValue.getOriginalProduct().getName() + " ?",
				() -> {
					TableUtils.removeItemFromTable(productService, productTableValue.getOriginalProduct().getId(),
							productsTable, productsData, container);
				});
	}

	@FXML
	private void onRemoveEmployeeTable() {
		EmployeeTableDto employeeTableValue = employeeTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, "Remove Employee", "Are you sure you want to delete the "
				+ employeeTableValue.getOriginalEmployee().getUser().getName() + " ?", () -> {
					TableUtils.removeItemFromTable(employeeService, employeeTableValue.getOriginalEmployee().getId(),
							employeeTable, employeesData, container);
				});
	}

	@FXML
	private void onRemoveSupplierTable() {
		SupplierTableDto supplierTableValue = supplierTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, "Remove Supplier", "Are you sure you want to delete the "
				+ supplierTableValue.getOriginalSupplier().getCompanyName() + " ?", () -> {
					TableUtils.removeItemFromTable(supplierService, supplierTableValue.getOriginalSupplier().getId(),
							supplierTable, suppliersData, container);
				});
	}

	@FXML
	private void onRemoveBrandTable() {
		BrandTableDto brandTableValue = brandTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, "Remove Supplier",
				"Are you sure you want to delete the " + brandTableValue.getOriginalBrand().getName() + " ?", () -> {
					TableUtils.removeItemFromTable(brandService, brandTableValue.getOriginalBrand().getId(), brandTable,
							brandsData, container);
				});
	}

	@FXML
	public void onNewProduct() throws Exception {
		WindowsUtils.openNewWindow(ProductNewController.PATH_FXML,
				getWindowTitle(ProductNewController.NEW_PRODUCT_TITLE_KEY), ProductNewController.PATH_ICON, null,
				Modality.APPLICATION_MODAL);
	}

	@FXML
	public void onNewEmployee() throws Exception {
		WindowsUtils.openNewWindow(EmployeeNewController.PATH_FXML,
				getWindowTitle(EmployeeNewController.NEW_EMPLOYEE_TITLE_KEY), EmployeeNewController.PATH_ICON, null,
				Modality.APPLICATION_MODAL);
	}

	@FXML
	public void onNewSupplier() throws Exception {
		WindowsUtils.openNewWindow(SupplierNewController.PATH_FXML,
				getWindowTitle(SupplierNewController.NEW_SUPPLIER_TITLE_KEY), SupplierNewController.PATH_ICON, null,
				Modality.APPLICATION_MODAL);
	}

	@FXML
	public void onNewBrand() throws Exception {
		WindowsUtils.openNewWindow(BrandNewController.PATH_FXML, getWindowTitle(BrandNewController.NEW_BRAND_TITLE_KEY),
				BrandNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
