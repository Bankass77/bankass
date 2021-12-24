package com.bankass.bankass.views;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDegrief implements FrameGridDef{

	public static String COLUMN_NAMES[] = 
		{ "Id", "Firstname", "Lastname", "Address", "Email"};
	public static String COLUMN_DATA_NAMES[] = 
		{ "id", "firstname", "lastname", "address", "email" };
	public static Integer COLUMN_SIZES[] = { 40, 200, 200, 200, 200 };
	public static String TITLE_POPUPS = "Customer";

	@Override
	public List<String> getColumnNames() {
		return Arrays.asList(COLUMN_NAMES);
	}

	@Override
	public List<Integer> getColumnSizes() {
		return Arrays.asList(COLUMN_SIZES);
	}

	@Override
	public List<String> getColumnDataName() {
		return Arrays.asList(COLUMN_DATA_NAMES);
	}

	@Override
	public Class<?> getCreateView() {
		return UserView.class;
	}

	@Override
	public String getTitlePopups() {
		return TITLE_POPUPS;
	}


}
