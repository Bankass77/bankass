package com.bankass.bankass.dowloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadThread extends Thread{

	private String url ;
	private String filePath ;
	private String title = "Unknown";
	private static final int BUFFER_SIZE = 4096;


	/**
	 * @param contentType  is a string which tells the types of file 
	 * @param url is a string which refers to the download link of file
	 * @return downloaded file
	 * @throws URISyntaxException url syntax not valid
	 * @throws MalformedURLException not a url
	/**
	 * @return
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	private File createFile(String contentType , String url) throws URISyntaxException, MalformedURLException{
		String fileTitle = title;
		System.out.println(Paths.get(new URI(url).getPath()).getFileName().toString());
		if((url.length()-url.lastIndexOf('.'))==4){
			System.out.println("plain url.");
			fileTitle = url.substring(url.lastIndexOf("/") + 1,url.length());
		}else{

			String[] ext = contentType.split("/");
			System.out.println(ext[1]);
			fileTitle = fileTitle+"."+ext[1];
			System.out.println(fileTitle);
		}
		File downloadFile = new File(filePath+File.separator+fileTitle);		
		if(!downloadFile.exists()){
			try {
				downloadFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Cannot create File to store.");
				//				System.exit(0);
			}
		}
		return downloadFile;
	}
	/**
	 * @param content is a string which contains the file content
	 * @return if the file is an application e.g pdf or exe or audio/video etc
	 */
	public boolean isDownloadable(String content){
		System.out.println(content);
		if(content.contains("application")){
			return true;
		}else if (content.contains("video")){
			return true;
		}else if (content.contains("audio")){
			return true;
		}else if(content.contains("image")){
			return true;
		}
		return false;
	}


}
