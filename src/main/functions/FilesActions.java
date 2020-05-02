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


public class FilesActions {
	
	private File file = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	public ArrayList<String> readFile(String fileName) {
		
		ArrayList<String> fileLines = new ArrayList<>();

		try {
			file = new File (fileName);
			fr = new FileReader (file);
			br = new BufferedReader(fr);
			String linea;
			int i = 0;
			System.out.println("Leyendo fichero de discos:");
			while((linea=br.readLine())!=null) {
				fileLines.add(linea);
				i++;
			}
			if (fileLines.size() > 0) {
				System.out.println("líneas totales leidas: "+String.valueOf(i));
				return fileLines;
			} else {
				return null;
			}
				
			
		} catch(Exception e){
			e.printStackTrace();
			return null;
		} finally {
			try{
				if( null != fr ){
					fr.close();
				}
			}catch (Exception e2){ 
		        e2.printStackTrace();
			}
		}
	}
	
	public String deleteFiles(File[] filesToDelete) {
		String errors = "";
		for (File f: filesToDelete) {
			Path p = FileSystems.getDefault().getPath(f.toString());
			System.out.println("Borrando archivo " + p.toString());
			try {
				Files.delete(p);
				System.out.println("Archivo borrado.");
			} catch (NoSuchFileException e) {
				if (errors.contentEquals("")) {
					errors += e.getMessage(); 
				} else {
					errors += "\n"+e.getMessage();
				}
				System.out.println("No se ha encontrado el archivo: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Error I/O borrando el archivo " + e.getMessage());
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
			System.out.println("Borrando el directorio de trabajo...");
			Files.delete(path);
			System.out.println("Directorio borrado.");
		} catch (IOException e) {
			System.out.println("Error I/O directorio el directorio " + e.getMessage());
			if (errors.contentEquals("")) {
				errors += e.getMessage(); 
			} else {
				errors += "\n"+e.getMessage();
			}
		}
		return errors;
	}

}
