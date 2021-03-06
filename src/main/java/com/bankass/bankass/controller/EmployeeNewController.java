package com.bankass.bankass.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bankass.bankass.model.Address;
import com.bankass.bankass.model.Employee;
import com.bankass.bankass.model.Phone;
import com.bankass.bankass.model.Role;
import com.bankass.bankass.model.User;
import com.bankass.bankass.service.EmployeeService;
import com.bankass.bankass.service.RoleService;
import com.bankass.bankass.service.UserService;
import com.bankass.bankass.utils.EntityFactory;
import com.bankass.bankass.utils.ValidatorUtils;
import com.bankass.bankass.utils.WindowsUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
@Controller
public class EmployeeNewController extends BaseController {

	public static final String EMPLOYEE_KEY = "employee_key";

	public static final String PATH_FXML = "/fxml/new_employee.fxml";
	public static final String NEW_EMPLOYEE_TITLE_KEY = "new_employee.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@FXML
	private StackPane container;

	@FXML
	private JFXTextField nameTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXTextField cpfTextField;

	@FXML
	private JFXTextField residentialTextField;

	@FXML
	private JFXTextField cellTextField;

	@FXML
	private JFXTextField cepTextField;

	@FXML
	private JFXTextField streetTextField;

	@FXML
	private JFXTextField numberTextField;

	@FXML
	private JFXTextField districtTextField;

	@FXML
	private JFXTextField complementTextField;

	@FXML
	private JFXTextField cityTextField;

	@FXML
	private JFXTextField stateTextField;

	@FXML
	private JFXPasswordField passwordTextField;

	@FXML
	private JFXPasswordField confirmPasswordTextField;

	@FXML
	private JFXComboBox<String> countryComboBox;

	@FXML
	private JFXComboBox<Role> roleComboBox;

	@FXML
	private JFXButton saveButton;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	private Employee employee;

	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);

		fillComboBoxes();
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}

	@Override
	protected void onClose() {
		userService.onClose();
		roleService.onClose();
		employeeService.onClose();
	}

	private <T> void checkParameters(HashMap<String, T> parameters) {
		if (parameters != null) {
			this.employee = (Employee) parameters.get(EMPLOYEE_KEY);
			updateTextFields();
		}
	}

	private void updateTextFields() {

		WindowsUtils.setTextInTextField(cpfTextField, employee.getCpf());

		if (employee != null) {
			WindowsUtils.setTextInTextField(residentialTextField, employee.getUser().getId());
			WindowsUtils.setTextInTextField(cellTextField, employee.getUser().getId());
		}

		if (employee.getAddress() != null) {
			WindowsUtils.setTextInTextField(cepTextField, employee.getAddress().getCep());
			WindowsUtils.setTextInTextField(streetTextField, employee.getAddress().getStreet());
			WindowsUtils.setTextInTextField(numberTextField, employee.getAddress().getNumber());
			WindowsUtils.setTextInTextField(districtTextField, employee.getAddress().getSuburb());
			WindowsUtils.setTextInTextField(complementTextField, employee.getAddress().getComplement());
			WindowsUtils.setTextInTextField(cityTextField, employee.getAddress().getCity());
			WindowsUtils.setTextInTextField(stateTextField, employee.getAddress().getState());

			WindowsUtils.setSelectedComboBoxItem(countryComboBox, employee.getAddress().getCountry());
		}

		if (employee.getUser() != null) {
			WindowsUtils.setSelectedComboBoxItem(roleComboBox,
					employee.getUser().getRoles().stream().findFirst().get());
			WindowsUtils.setTextInTextField(nameTextField, employee.getUser().getName());
			WindowsUtils.setTextInTextField(emailTextField, employee.getUser().getEmail());
			WindowsUtils.setTextInTextField(passwordTextField, employee.getUser().getPassword());
		}

	}

	private void validateTextFields() {
		ValidatorUtils.addRequiredValidator(nameTextField, "Employee Name is Required!");
		ValidatorUtils.addRequiredValidator(emailTextField, "E-mail is Required!");
		ValidatorUtils.addRequiredValidator(cpfTextField, "CPF is Required!");
		ValidatorUtils.addRequiredValidator(passwordTextField, "Password is Required!");
		ValidatorUtils.addRequiredValidator(confirmPasswordTextField, "Confirm Password is Required!");
		ValidatorUtils.addPasswordAndConfirmPasswordValidator(passwordTextField, confirmPasswordTextField,
				"Password does not match the confirm password");

		ValidatorUtils.addNumberOnlyValidator(numberTextField);
		ValidatorUtils.addNumberOnlyValidator(cpfTextField);
		ValidatorUtils.addNumberOnlyValidator(residentialTextField);
		ValidatorUtils.addNumberOnlyValidator(cellTextField);

		ValidatorUtils.addMaxLengthValidator(cpfTextField, 11);
		ValidatorUtils.addMaxLengthValidator(cepTextField, 8);

		ValidatorUtils.addEmailValidator(emailTextField, "Email does not match");

		ValidatorUtils.addDuplicateUserValidator(emailTextField,
				"An account for the specified email address already exists", userService);

		WindowsUtils.validateTextField(numberTextField);
		WindowsUtils.validateTextField(residentialTextField);
		WindowsUtils.validateTextField(cellTextField);
		WindowsUtils.validateTextField(nameTextField);
		WindowsUtils.validateTextField(emailTextField);
		WindowsUtils.validateTextField(cpfTextField);
		WindowsUtils.validateTextField(passwordTextField);
		WindowsUtils.validateTextField(confirmPasswordTextField);
	}

	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(roleComboBox, roleService);
	}

	private void watchEvents() {
		WindowsUtils.watchEvents(nameTextField, v -> watch());
		WindowsUtils.watchEvents(emailTextField, v -> watch());
		WindowsUtils.watchEvents(cpfTextField, v -> watch());
		WindowsUtils.watchEvents(passwordTextField, v -> watch());
		WindowsUtils.watchEvents(confirmPasswordTextField, v -> watch());
	}

	private void watch() {
		if (isRequiredTextFieldsFilled() && (passwordTextField.validate() && confirmPasswordTextField.validate())) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}

	}

	@FXML
	public void onSave() {
		Role roleSelected = (Role) WindowsUtils.getSelectedComboBoxItem(roleComboBox);

		List<Phone> s = Arrays.asList(EntityFactory.createPhone(WindowsUtils.getLongFromTextField(cellTextField)),
				EntityFactory.createPhone(WindowsUtils.getLongFromTextField(residentialTextField)));

		roleService.findByRole(roleSelected.getRole(), en -> {
			User user = EntityFactory.createUser(WindowsUtils.getTextFromTextField(emailTextField),
					WindowsUtils.getTextFromTextField(nameTextField), null,
					WindowsUtils.getTextFromTextField(passwordTextField), (List<Role>) en.getSource().getValue());

			Address address = null;
			if (isAddressFilled()) {
				address = EntityFactory.createAddress(WindowsUtils.getTextFromTextField(streetTextField),
						WindowsUtils.getIntegerFromTextField(numberTextField),
						WindowsUtils.getTextFromTextField(complementTextField),
						WindowsUtils.getTextFromTextField(districtTextField),
						WindowsUtils.getTextFromTextField(cityTextField),
						WindowsUtils.getTextFromTextField(stateTextField),
						(String) WindowsUtils.getSelectedComboBoxItem(countryComboBox),
						WindowsUtils.getTextFromTextField(cepTextField));
			}

			try {
				employeeService.save(EntityFactory.createEmployee(employee,
						WindowsUtils.getTextFromTextField(cpfTextField), user, address, s), e -> {
							WindowsUtils.createDefaultDialog(container, "Sucess", "Employee save with sucess.", () -> {
								stage.close();
							});
						}, null);
			} catch (Exception e) {
				e.printStackTrace();
				WindowsUtils.createDefaultDialog(container, "Error", "Error saving employee, try again.", () -> {
				});
			}
		}, null);

	}

	private boolean isAddressFilled() {
		return !WindowsUtils.isTextFieldEmpty(streetTextField) || !WindowsUtils.isTextFieldEmpty(numberTextField)
				|| !WindowsUtils.isTextFieldEmpty(districtTextField)
				|| !WindowsUtils.isTextFieldEmpty(complementTextField) || !WindowsUtils.isTextFieldEmpty(cityTextField)
				|| !WindowsUtils.isTextFieldEmpty(stateTextField) || WindowsUtils.isComboBoxSelected(roleComboBox);
	}

	private boolean isRequiredTextFieldsFilled() {
		return !(WindowsUtils.isTextFieldEmpty(nameTextField)) && !(WindowsUtils.isTextFieldEmpty(emailTextField))
				&& !(WindowsUtils.isTextFieldEmpty(cpfTextField)) && !(WindowsUtils.isTextFieldEmpty(passwordTextField))
				&& !(WindowsUtils.isTextFieldEmpty(confirmPasswordTextField));
	}

	@FXML
	public void onCancel() {
		stage.close();
	}

	@FXML
	public void onHelp() {

	}

}
