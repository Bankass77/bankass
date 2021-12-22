package com.bankass.bankass.dowloader;

import java.io.File;
import java.util.Properties;

public class MainDownload {

	public void startDownload(String url,String title){

		DownloadThread thread = new DownloadThread(url, downloadFolder(),title);
		System.setProperty("java.net.preferIPv4Stack" , "true");
		thread.start();		
	}


	/*
	 *  *Method to add proxy configuration in downloader.
	 *  */
	private void setProxy(){
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost","172.16.0.2");
		systemProperties.setProperty("http.proxyPort","8080");
		systemProperties.setProperty("https.proxyHost","172.16.0.2");
		systemProperties.setProperty("https.proxyPort","8080");

	}

	/*
	 * Method create the Folder Downloads in the home/ if does not exists .To store the Download Stuff
	 * */
	/**
	 * @return
	 */
	private String downloadFolder(){
		File home = new File(System.getProperty("user.home"));
		File folder = new File (home,"Downloads");
		if (!folder.exists()){
			folder.mkdir();
		}
		return folder.getAbsolutePath();
	}


}
