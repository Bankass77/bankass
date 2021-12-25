package com.bankass.bankass.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bankass.bankass.model.Employee;
import com.bankass.bankass.model.Product;
import com.bankass.bankass.model.Sale;
import com.bankass.bankass.model.Supplier;
import com.bankass.bankass.model.dto.EmployeeReportDto;
import com.bankass.bankass.model.dto.ProductReportDto;
import com.bankass.bankass.model.dto.SalesReportDto;
import com.bankass.bankass.model.dto.SupplierReportDto;

public class EntityReportFactory {

	public static List<SalesReportDto> createSales(List<Sale> sales) {
		List<SalesReportDto> salesReport = new ArrayList<>();

		for (Sale sale : sales) {
			salesReport.add(createSale(sale));
		}

		return salesReport;
	}

	public static SalesReportDto createSale(Sale sale) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		SalesReportDto SalesReportDto = new SalesReportDto();

		SalesReportDto.setCode(isEmpty(sale.getSaleCode()));
		SalesReportDto
				.setIssueDate(isEmpty(sale.getIssueDate(), formatter.format(sale.getIssueDate())));
		SalesReportDto.setShipmentDate(
				isEmpty(sale.getShipmentDate(), formatter.format(sale.getShipmentDate())));
		SalesReportDto.setTotalUnits(isEmpty(sale.getTotalUnits()));
		SalesReportDto.setTotal(isEmpty(sale.getTotal()));
		SalesReportDto.setClient(isEmpty(sale.getCliente(), sale.getCliente().getUser().getName()));

		return SalesReportDto;
	}

	public static List<ProductReportDto> createProducts(List<Product> products) {
		List<ProductReportDto> productsReport = new ArrayList<>();

		for (Product p : products) {
			productsReport.add(createProduct(p));
		}

		return productsReport;
	}

	public static ProductReportDto createProduct(Product product) {
		ProductReportDto ProductReportDto = new ProductReportDto();

		ProductReportDto.setSku(isEmpty(product.getSku()));
		ProductReportDto.setDescription(isEmpty(product.getDescription()));
		ProductReportDto.setBuyPrice(isEmpty(product.getBuyPrice()));
		ProductReportDto
				.setSupplier(product.getSupplier() == null ? "---" : isEmpty(product.getSupplier().getCompanyName()));
		ProductReportDto
				.setProductType(product.getProductType() == null ? "---" : isEmpty(product.getProductType().getName()));

		return ProductReportDto;
	}

	public static List<EmployeeReportDto> createEmployees(List<Employee> employees) {
		List<EmployeeReportDto> employeeReport = new ArrayList<>();

		for (Employee e : employees) {
			employeeReport.add(createEmployee(e));
		}

		return employeeReport;
	}

	public static EmployeeReportDto createEmployee(Employee employee) {
		EmployeeReportDto EmployeeReportDto = new EmployeeReportDto();

		EmployeeReportDto.setName(employee.getUser() == null ? "---" : isEmpty(employee.getUser().getName()));
		EmployeeReportDto.setEmail(employee.getUser() == null ? "---" : isEmpty(employee.getUser().getEmail()));
		EmployeeReportDto.setCpf(isEmpty(employee.getCpf()));
		EmployeeReportDto.setAdress(employee.getAddress() == null ? "---"
				: isEmpty(
						String.format("%s - %d", employee.getAddress().getStreet(), employee.getAddress().getNumber())));

		return EmployeeReportDto;
	}

	public static List<SupplierReportDto> createSuppliers(List<Supplier> suppliers) {
		List<SupplierReportDto> supplierReport = new ArrayList<>();

		for (Supplier s : suppliers) {
			supplierReport.add(createSupplier(s));
		}

		return supplierReport;
	}

	public static SupplierReportDto createSupplier(Supplier supplier) {
		SupplierReportDto SupplierReportDto = new SupplierReportDto();

		SupplierReportDto.setAdress(supplier.getAddres() == null ? "---"
				: isEmpty(
						String.format("%s - %d", supplier.getAddres().getStreet(), supplier.getAddres().getNumber())));
		SupplierReportDto.setCompanyName(supplier.getCompanyName());
		SupplierReportDto.setEmail(supplier.getEmail());

		return SupplierReportDto;
	}

	private static String isEmpty(String value) {
		return value == null ? "---" : value;
	}

	private static String isEmpty(Object object, String value) {
		return object == null ? "---" : isEmpty(value);
	}

	private static String isEmpty(double value) {
		return value == 0.0 ? "---" : String.format("%.2f", value);
	}

	private static String isEmpty(int value) {
		return value == 0 ? "---" : String.valueOf(value);
	}
}
