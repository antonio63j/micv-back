package com.afl.micvback.util.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDownloadFileService {
	
	//String getFilenameGenerado () throws FileNotFoundException, IOException;
	String getFilenameGenerado () throws Exception;
	boolean deleteFilename (String filename);

}
