package com.capgemini.starterkit.javafx.dataprovider;

import java.util.Collection;

import com.capgemini.starterkit.javafx.dataprovider.data.PictureVO;
import com.capgemini.starterkit.javafx.dataprovider.impl.DataProviderImpl;

public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();
	Collection<PictureVO> findPictures(String directoryPath);
}
