package com.capgemini.starterkit.javafx.dataprovider.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;

import com.capgemini.starterkit.javafx.dataprovider.DataProvider;
import com.capgemini.starterkit.javafx.dataprovider.data.PictureVO;

public class DataProviderImpl implements DataProvider {


	@Override
	public Collection<PictureVO> findPictures(String directoryPath) {
		Collection<PictureVO> pictures = new ArrayList<>();
		File dir = new File(directoryPath);
		FilenameFilter filter = new PictureFileFilter();
		File[] files = dir.listFiles(filter);

		for(int i = 0; i < files.length; i++){
			pictures.add(new PictureVO(files[i].getName(), files[i].getAbsolutePath()));
		}

		return pictures;
	}
}
