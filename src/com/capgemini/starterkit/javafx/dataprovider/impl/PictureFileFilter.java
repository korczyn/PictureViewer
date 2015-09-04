package com.capgemini.starterkit.javafx.dataprovider.impl;

import java.io.File;
import java.io.FilenameFilter;

public class PictureFileFilter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		if(name.endsWith(".jpg") || name.endsWith(".png")){
			return true;
		}
		return false;
	}

}
