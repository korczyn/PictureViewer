package com.capgemini.starterkit.javafx.dataprovider.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.capgemini.starterkit.javafx.dataprovider.DataProvider;
import com.capgemini.starterkit.javafx.dataprovider.data.PictureVO;

public class DataProviderImpl implements DataProvider {

	/*
	 * REV: wynik wyszukiwania nie musi byc przechowywany jako pole w klasie
	 */
	private Collection<PictureVO> pictures = new ArrayList<>();


	@Override
	public Collection<PictureVO> findPictures(String directoryPath) {
		pictures.clear();
		File directory = new File(directoryPath);
		File[] listOfFiles = directory.listFiles();
		/*
		 * REV: lepiej uzyc FilenameFilter
		 */
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				/*
				 * REV: File.getName() zwraca nazwe pliku
				 */
				if(isPicture(listOfFiles[i].getAbsolutePath())){
					pictures.add(new PictureVO(listOfFiles[i].getName(), listOfFiles[i].getAbsolutePath()));
				}
			}
		}
		return pictures;
	}

	private boolean isPicture(String path){
		int dot = path.lastIndexOf(".");
		String ext = path.substring(dot + 1);
		/*
		 * REV: rozszerzenie moze miec taka forme: JPG, JpG
		 */
		if(ext.equals("jpg") || ext.equals("png")){
			return true;
		}
		return false;
	}

}
