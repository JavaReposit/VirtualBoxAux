package main.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;

import main.log.AppLogger;


public class FilesActions {
	
	private File file = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private final AppLogger logger = new AppLogger(FilesActions.class.getName());
	
	public ArrayList<String> readFile(String fileName) {
		
		ArrayList<String> fileLines = new ArrayList<>();

		try {
			file = new File (fileName);
			fr = new FileReader (file);
			br = new BufferedReader(fr);
			String linea;
			int i = 0;
			logger.setInfoLog("Leyendo fichero de discos:");
			while((linea=br.readLine())!=null) {
				fileLines.add(linea);
				i++;
			}
			if (fileLines.size() > 0) {
				logger.setInfoLog("líneas totales leidas: "+String.valueOf(i));
				return fileLines;
			} else {
				return null;
			}
		} catch(Exception e){
			logger.setErrorLog(e.getMessage());
			return null;
		} finally {
			try{
				if( null != fr ){
					fr.close();
				}
			}catch (Exception e2){ 
				logger.setErrorLog(e2.getMessage());
			}
		}
	}
	
	public String deleteFiles(File[] filesToDelete) {
		String errors = "";
		for (File f: filesToDelete) {
			Path p = FileSystems.getDefault().getPath(f.toString());
			logger.setInfoLog("Borrando archivo " + p.toString());
			try {
				Files.delete(p);
				logger.setInfoLog("Archivo " + p.toString() + " borrado.");
			} catch (NoSuchFileException e) {
				if (errors.contentEquals("")) {
					errors += e.getMessage(); 
				} else {
					errors += "\n"+e.getMessage();
				}
				logger.setWarningLog("No se ha encontrado el archivo: " + e.getMessage());
			} catch (IOException e) {
				logger.setErrorLog("Error I/O borrando el archivo " + e.getMessage());
				if (errors.contentEquals("")) {
					errors += e.getMessage(); 
				} else {
					errors += "\n"+e.getMessage();
				}
			}
		}
		return errors;
	}
	
	public String deletePath (Path path) {
		String errors = "";
		try {
			logger.setInfoLog("Borrando el directorio de trabajo...");
			Files.delete(path);
			logger.setInfoLog("Directorio "+path.toString()+" borrado.");
		} catch (IOException e) {
			logger.setErrorLog("Error I/O directorio el directorio " + e.getMessage());
			if (errors.contentEquals("")) {
				errors += e.getMessage(); 
			} else {
				errors += "\n"+e.getMessage();
			}
		}
		return errors;
	}
	
	public long getFileSize(String file) {
		long size = 0;
		File f = new File(file);
		if (f.exists()) {
			size = f.length();
		}
				
		return size;
	}

}
