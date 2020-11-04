package main.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import main.constants.FConstants;
import main.log.AppLogger;

public class ManageTemporalPath {
	
	private Path temporalPath;
	private FConstants constants = new FConstants();
	private FilesActions fileAction = new FilesActions();
	private Utils utils = new Utils();
	private final AppLogger logger = new AppLogger(FilesActions.class.getName());
	
	
	public boolean copyScripts() {
		temporalPath = Paths.get(constants.getDIR_TEMPORAL());
		if (newTemporalPath(temporalPath)) {	
			File[] listaScripts = new File("scripts").listFiles();
			for (File f : listaScripts) {
				try {
					Path origen = FileSystems.getDefault().getPath(f.toString());
					Path destino = FileSystems.getDefault().getPath(temporalPath.toString()+"\\"+f.getName());
					Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
					logger.setInfoLog("Fichero " + f.getName() + " creado.");
				} catch (FileNotFoundException e) {
					logger.setWarningLog("No se ha encontrado el fichero: " + f.getName() +"\nError: "+ e.getMessage());
				} catch (IOException e) {
					logger.setWarningLog("Error I/O con el fichero: " + f.getName() +"\nError: "+ e.getMessage());
				}
			}
			utils.findVdi();
			return true;
		} else {
			logger.setWarningLog("No existe el directorio de trabajo.");
			return false;
		}
	}
	
	public boolean newTemporalPath(Path workPath) {
		logger.setInfoLog("directorio de trabajo: "+workPath.toString());
		if (Files.notExists(workPath)) {
			try {
				Files.createDirectory(Paths.get(workPath.toString()));
				logger.setInfoLog("directorio creado");
				return true; 
			} catch (SecurityException e) {
				logger.setErrorLog("Error1: "+e.getMessage());
				return false;
			} catch (IOException e) {
				logger.setErrorLog("Error2: "+e.getMessage());
				return false;
			}
		} else {
			logger.setWarningLog("Ya existe el directorio");
			return true;
		}		
	}
	
	public void deleteTemporalPath() {
		String errors = "";
		File[] filesToDelete = temporalPath.toFile().listFiles();
		try {
			errors = fileAction.deleteFiles(filesToDelete);
			errors = fileAction.deletePath(temporalPath);
		} catch (Exception e) {
			errors = e.getMessage();
		}
		if (errors.contentEquals("")) {
			logger.setInfoLog("Directorio de trabajo eliminado correctamente..."); 
		} else {
			logger.setWarningLog("El directorio de trabajo no se ha podido eliminar correctamente."
					+ "\nPuede ser necesario eliminarlo manualmente."
					+ "\nErrores registrados:\n"+errors);
		}
	}
	
}