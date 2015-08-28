package com.capgemini.starterkit.javafx.picture.model;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.starterkit.javafx.dataprovider.data.PictureVO;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Picture {

	private final StringProperty fileName = new SimpleStringProperty();
	private final StringProperty filePath = new SimpleStringProperty();
	private final ListProperty<PictureVO> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));

	public String getFileName() {
		return fileName.get();
	}

	public void setFilename(String fileName){
		this.fileName.set(fileName);
	}

	public StringProperty fileNameProperty(){
		return fileName;
	}

	public String getFilePath(){
		return filePath.get();
	}

	public void setFilePath(String filePath){
		this.filePath.set(filePath);
	}

	public StringProperty filePathProperty(){
		return filePath;
	}

	public final List<PictureVO> getResult() {
		return result.get();
	}

	public final void setResult(List<PictureVO> value) {
		result.setAll(value);
	}

	public ListProperty<PictureVO> resultProperty() {
		return result;
	}
}
