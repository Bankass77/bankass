package com.bankass.bankass.constants.path;


public final class CSSPath {
	private static final String CSS_PREFIX = "/css/";
    private static final String CSS_SUFFIX = ".css";

    private static final String START_PATH = "start/";
    public static final String MAIN = CSS_PREFIX + START_PATH + "Main" + CSS_SUFFIX;
    public static final String RETRIEVE_PASSWORD = CSS_PREFIX + START_PATH + "RetrievePassword" + CSS_SUFFIX;
    public static final String SIGN_IN_UP = CSS_PREFIX + START_PATH + "SignInUp" + CSS_SUFFIX;

    private static final String ADMIN_PATH = "admin/";
    public static final String ADMIN = CSS_PREFIX + ADMIN_PATH + "Admin" + CSS_SUFFIX;
    public static final String USER_MANAGEMENT = CSS_PREFIX + ADMIN_PATH + "UserManagement" + CSS_SUFFIX;
    

    private static final String USER_PATH = "user/";
    public static final String USER = CSS_PREFIX + USER_PATH + "User" + CSS_SUFFIX;
  
    public static final String USER_INFORMATION = CSS_PREFIX + USER_PATH + "UserInformation" + CSS_SUFFIX;

    public static final String MESSAGE_BOX = CSS_PREFIX + "MessageBox" + CSS_SUFFIX;

}
