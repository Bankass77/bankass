package com.bankass.bankass.dowloader;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class download {
	private	StringProperty url;
	private	StringProperty fileName;
	private	StringProperty fileStatus;
	public download(String url , String fileName,String fileStatus){
		this.url = new SimpleStringProperty(url);
		this.fileName = new SimpleStringProperty(fileName);
		this.fileStatus = new SimpleStringProperty(fileStatus);
	}
	
}
