package main.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.security.auth.login.FailedLoginException;

import main.constants.FConstants;

public class ManageTemporalPath {
	
	private Path temporalPath;
	private FConstants constants = new FConstants();
	private FilesActions fileAction = new FilesActions();
	private Utils utils = new Utils();
	
	
	public boolean copyScripts() {
		temporalPath = Paths.get(constants.getDIR_TEMPORAL());
		if (newTemporalPath(temporalPath)) {	
			File[] listaScripts = new File("scripts").listFiles();
			for (File f : listaScripts) {
				try {
					Path origen = FileSystems.getDefault().getPath(f.toString());
					Path destino = FileSystems.getDefault().getPath(temporalPath.toString()+"\\"+f.getName());
					Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Fichero " + f.getName() + " creado.");
				} catch (FileNotFoundException e) {
					System.out.println("No se ha encontrado el fichero: " + f.getName() +"\nError: "+ e.getMessage());
				} catch (IOException e) {
					System.out.println("Error I/O con el fichero: " + f.getName() +"\nError: "+ e.getMessage());
				}
			}
			utils.findVdi();
			return true;
		} else {
			System.out.println("No existe el directorio de trabajo.");
			return false;
		}
	}
	
	public boolean newTemporalPath(Path workPath) {
		System.out.println("directorio de trabajo: "+workPath.toString());
		if (Files.notExists(workPath)) {
			try {
				Files.createDirectory(Paths.get(workPath.toString()));
					System.out.println("directorio creado");
					return true; 
			} catch (SecurityException e) {
				System.out.println("Error1: "+e.getMessage());
				return false;
			} catch (IOException e) {
				System.out.println("Error2: "+e.getMessage());
				return false;
			}
		} else {
			System.out.println("Ya existe el directorio");
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
			System.out.println("Directorio de trabajo eliminado correctamente..."); 
		} else {
			System.out.println("El directorio de trabajo no se ha podido eliminar correctamente."
					+ "\nPuede ser necesario eliminarlo manualmente."
					+ "\nErrores registrados:\n"+errors);
		}
	}
	
	
	
}