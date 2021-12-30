package com.bankass.bankass.utils;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import com.bankass.bankass.model.Address;
import com.bankass.bankass.model.Brand;
import com.bankass.bankass.model.Client;
import com.bankass.bankass.model.ClientType;
import com.bankass.bankass.model.Employee;
import com.bankass.bankass.model.Image;
import com.bankass.bankass.model.Item;
import com.bankass.bankass.model.Phone;
import com.bankass.bankass.model.Product;
import com.bankass.bankass.model.ProductType;
import com.bankass.bankass.model.Role;
import com.bankass.bankass.model.Sale;
import com.bankass.bankass.model.Supplier;
import com.bankass.bankass.model.Tag;
import com.bankass.bankass.model.User;

public class EntityFactory {

	public static Supplier createSupplier(String companyName, String email, Address address) {
		return createSupplier(new Supplier(), companyName, email, address);
	}

	public static Supplier createSupplier(Supplier supplier, String companyName, String email, Address address) {

		if (supplier == null) {
			supplier = new Supplier();
		}

		supplier.setCompanyName(companyName);
		supplier.setEmail(email);
		supplier.setAddres(address);

		return supplier;
	}

	public static Employee createEmployee(String cpf, User user, Address address, List<Phone> phones) {
		return createEmployee(new Employee(), cpf, user, address, phones);
	}

	public static Employee createEmployee(Employee employee, String cpf, User user, Address address,
			List<Phone> phones) {

		if (employee == null) {
			employee = new Employee();
		}

		employee.setCpf(cpf);
		employee.setUser(user);
		employee.setAddress(address);
		employee.setPhones(phones);

		return employee;
	}

	public static Product createProduct(String sku, String name, String description, double initialCostPrice,
			double buyPrice, double wholesalePrice, double retailPrice, double weight, double initialStock,
			LocalDateTime createdAt, LocalDateTime upLocalDateTimedAt, Supplier supplier, Brand brand, ProductType productType, List<Image> images,
			List<Tag> tags) {
		return createProduct(new Product(), sku, name, description, initialCostPrice, buyPrice, wholesalePrice,
				retailPrice, weight, initialStock, createdAt, upLocalDateTimedAt, supplier, brand, productType, images, tags);
	}

	public static Product createProduct(Product product, String sku, String name, String description,
			double initialCostPrice, double buyPrice, double wholesalePrice, double retailPrice, double weight,
			double initialStock, LocalDateTime createdAt, LocalDateTime upLocalDateTimedAt, Supplier supplier, Brand brand,
			ProductType productType, List<Image> images, List<Tag> tags) {

		if (product == null) {
			product = new Product();
		}

		product.setSku(sku);
		product.setName(name);
		product.setDescription(description);
		product.setInitialCostPrice(initialCostPrice);
		product.setBuyPrice(buyPrice);
		product.setWholesalePrice(wholesalePrice);
		product.setRetailPrice(retailPrice);
		product.setWeight(weight);
		product.setInitialStock(initialStock);
		product.setCreatedAt(createdAt);
		product.setUpdatedAt(upLocalDateTimedAt);

		product.setBrand(brand);
		product.setProductType(productType);
		product.setSupplier(supplier);

		product.setTags(tags);
		product.setImages(images);

		return product;
	}

	public static Brand createBrand(String name, String email, String additionalInformation) {
		return createBrand(new Brand(), name, email, additionalInformation);
	}

	public static Brand createBrand(Brand brand, String name, String email, String additionalInformation) {

		if (brand == null) {
			brand = new Brand();
		}

		brand.setName(name);
		brand.setEmail(email);
		brand.setAdditionalInformation(additionalInformation);

		return brand;
	}

	public static Sale createSale(String saleCode, Calendar issueLocalDateTime, Calendar shipmentLocalDateTime, String reference,
			String email, String message, String state, int totalUnits, double total, Phone Phone, Client cliente,
			List<Item> items, List<Tag> tags) {
		return createSale(new Sale(), saleCode, issueLocalDateTime, shipmentLocalDateTime, reference, email, message, state, totalUnits,
				total, Phone, cliente, items, tags);
	}

	public static Sale createSale(Sale sale, String saleCode, Calendar issueLocalDateTime, Calendar shipmentLocalDateTime,
			String reference, String email, String message, String state, int totalUnits, double total, Phone Phone,
			Client cliente, List<Item> items, List<Tag> tags) {

		if (sale == null) {
			sale = new Sale();
		}

		sale.setSaleCode(saleCode);
		sale.setIssueDate(issueLocalDateTime.toString());
		sale.setShipmentDate(shipmentLocalDateTime.toString());
		sale.setReference(reference);
		sale.setEmail(email);
		sale.setMessage(message);
		sale.setState(state);
		sale.setTotalUnits(totalUnits);
		sale.setTotal(total);
		sale.setPhone(Phone);
		sale.setCliente(cliente);
		sale.setItems(items);

		return sale;
	}

	public static Client createClient(String cpf, ClientType clientType, Address address, List<Phone> phones,
			User user) {
		return createClient(new Client(), cpf, clientType, address, phones, user);
	}

	public static Client createClient(Client client, String cpf, ClientType clientType, Address address,
			List<Phone> phones, User user) {

		if (client == null) {
			client = new Client();
		}

		client.setAddress(address);
		client.setCpf(cpf);
		client.setClientType(clientType);
		client.setPhones(phones);
		client.setUser(user);

		return client;
	}

	public static ClientType createClientType(String name) {
		ClientType clientType = new ClientType();

		clientType.setName(name);

		return clientType;
	}

	public static ProductType createProductType(String name) {
		ProductType productType = new ProductType();

		productType.setName(name);

		return productType;
	}

	public static Image createImage(String path) {
		Image image = new Image();

		image.setPath(path);

		return image;
	}

	public static Tag createTag(String name) {
		Tag tag = new Tag();

		tag.setName(name);

		return tag;
	}

	public static Address createAddress(String street, int number, String complement, String suburb, String city,
			String state, String country, String cep) {
		Address address = new Address();

		address.setStreet(street);
		address.setNumber(number);
		address.setComplement(complement);
		address.setSuburb(suburb);
		address.setSuburb(suburb);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setCep(cep);

		return address;
	}

	public static User createUser(String email, String name, String photoPath, String password, List<Role> role) {
		User user = new User();

		user.setName(name);
		user.setEmail(email);
		user.setPhotoPath(photoPath);
		user.setPassword(password);
		user.setRoles(role);

		return user;
	}

	public static Role createRole(String role, String roleName) {
		Role r = new Role();

		r.setName(roleName);
		r.setRole(role);

		return r;
	}

	public static Phone createPhone(long number) {
		Phone phone = new Phone();

		phone.setNumber(number);

		return phone;
	}

}
