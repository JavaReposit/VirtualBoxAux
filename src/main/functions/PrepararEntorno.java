package main.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PrepararEntorno {
	
	Path dirTrabajo; 
	
	public boolean comprobarDirectorioTrabajo(Path dirTrabajo) {
		System.out.println("directorio de trabajo: "+dirTrabajo.toString());
		if (Files.notExists(dirTrabajo)) {
			try {
				Files.createDirectory(Paths.get(dirTrabajo.toString()));
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
			System.out.println("ya existe el directorio");
			return true;
		}
		
	}
	
		
	public void copiarFicheros() {
		
		dirTrabajo = Paths.get("C:\\Users\\Public\\Tmp");
		
		if (comprobarDirectorioTrabajo(dirTrabajo)) {
			
			File[] listaScripts = new File("scripts").listFiles();
			for (File f : listaScripts) {
				try {
					Path origen = FileSystems.getDefault().getPath(f.toString());
					Path destino = FileSystems.getDefault().getPath(dirTrabajo.toString()+"\\"+f.getName());
					
					Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
					
					System.out.println("Fichero " + f.getName() + " creado.");
			
				} catch (FileNotFoundException e) {
					System.out.println("No se ha encontrado el fichero: " + f.getName() +"\nError: "+ e.getMessage());
				} catch (IOException e) {
					System.out.println("Error I/O con el fichero: " + f.getName() +"\nError: "+ e.getMessage());
				}
			}
			
			if (Files.exists(FileSystems.getDefault().getPath("c:\\users\\public\\tmp\\listHdds.bat"))) {
				try {
					File dir = new File("c:\\Program Files\\Oracle\\VirtualBox");
					Process p = Runtime.getRuntime().exec("cmd /c start C:\\Users\\Public\\Tmp\\listHdds.bat", null, dir);
				} catch (Exception e) {
					System.out.println("Error listando los discos virtuales existentes:\n"+e.getMessage());
				}
			} else {
				System.out.println("No se pueden listar los discos virtuales");
			}
		} else {
			System.out.println("no existe el directorio de trabajo.");
		}
		
	}
	
	public void eliminarEntorno() {
		String errores = "";
		File[] arcchivosABorrar = dirTrabajo.toFile().listFiles();
		Path p;
		for (File f: arcchivosABorrar) {
			p = FileSystems.getDefault().getPath(f.toString());
			System.out.println("Borrando archivo " + p.toString());
			try {
				Files.delete(p);
				System.out.println("Archivo borrado.");
			} catch (NoSuchFileException e) {
				if (errores.contentEquals("")) {
					errores += e.getMessage(); 
				} else {
					errores += "\n"+e.getMessage();
				}
				System.out.println("No se ha encontrado el archivo: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Error I/O borrando el archivo " + e.getMessage());
				if (errores.contentEquals("")) {
					errores += e.getMessage(); 
				} else {
					errores += "\n"+e.getMessage();
				}
			}
		}
		try {
			System.out.println("Borrando el directorio de trabajo...");
			Files.delete(dirTrabajo);
			System.out.println("Directorio borrado.");
		} catch (IOException e) {
			System.out.println("Error I/O directorio el directorio " + e.getMessage());
			if (errores.contentEquals("")) {
				errores += e.getMessage(); 
			} else {
				errores += "\n"+e.getMessage();
			}
		}
		if (errores.contentEquals("")) {
			System.out.println("Directorio de trabajo eliminado correctamente..."); 
		} else {
			System.out.println("El directorio de trabajo no se ha podido eliminar correctamente."
					+ "\nPuede ser necesario eliminarlo manualmente");
		}
	}
	
}