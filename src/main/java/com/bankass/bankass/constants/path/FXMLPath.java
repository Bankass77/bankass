package com.bankass.bankass.constants.path;

public final class FXMLPath {
	
	private static final String FXML_PREFIX = "/fxml/";
    private static final String FXML_SUFFIX = ".fxml";

    private static final String START_PATH = "start/";
    public static final String MAIN = FXML_PREFIX + START_PATH + "root" + FXML_SUFFIX;
    public static final String SIGN_IN_UP = FXML_PREFIX + START_PATH + "SignInUp" + FXML_SUFFIX;
    public static final String RETRIEVE_PASSWORD = FXML_PREFIX + START_PATH + "RetrievePassword" + FXML_SUFFIX;

    private static final String ADMIN_PATH = "admin/";
    public static final String ADMIN = FXML_PREFIX + ADMIN_PATH + "Admin" + FXML_SUFFIX;
    public static final String USER_MANAGEMENT = FXML_PREFIX + ADMIN_PATH + "UserManagement" + FXML_SUFFIX;
    

    private static final String USER_PATH = "user/";
    public static final String USER = FXML_PREFIX + USER_PATH + "User" + FXML_SUFFIX;
    
    public static final String USER_INFORMATION = FXML_PREFIX + USER_PATH + "UserInformation" + FXML_SUFFIX;

    public static final String MESSAGE_BOX = FXML_PREFIX + "MessageBox" + FXML_SUFFIX;
}
