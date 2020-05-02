package main.functions;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import main.constants.FConstants;

public class Utils {
	
	private FConstants constants = new FConstants();
	private ScriptsActions scriptAction = new ScriptsActions();
	
	public ArrayList<String> cleanListVdis(ArrayList<String> vdis) {
		ArrayList<String> Aux = new ArrayList<>();
		for (String s : vdis) {
			if (s.contains("Location")) {
				String c = s.substring(9);
				Aux.add(c.trim());
			}
		}
		if (Aux.isEmpty()) {
			return null;
		} else {
			return Aux;
		}
	}
	
	public boolean findVdi() {
		if (Files.exists(FileSystems.getDefault().getPath(constants.getLIST_HDDS()))) {
			try {
				scriptAction.listVdi();
				return true;
			} catch (Exception e) {
				System.out.println("Error listando los discos virtuales existentes:\n"+e.getMessage());
				return false;
			}
		} else {
			System.out.println("No se pueden listar los discos virtuales");
			return false;
		}
	}
	

}
