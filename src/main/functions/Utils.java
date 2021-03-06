package main.functions;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import main.constants.FConstants;
import main.constants.FMessages;
import main.log.AppLogger;

public class Utils {
	
	private final AppLogger logger = new AppLogger(Utils.class.getName());
	private FConstants constants = new FConstants();
	private ScriptsActions scriptAction = new ScriptsActions();
	private FMessages messages = FMessages.getInstance();
	private String msg;
	
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
				logger.setErrorLog("Error listando los discos virtuales existentes:\n"+e.getMessage());
				return false;
			}
		} else {
			logger.setWarningLog("No se pueden listar los discos virtuales");
			return false;
		}
	}
	
	public boolean seEstaEjecutantoVirtualBox() throws IOException {
		boolean enEjecucion = false;
		Process process = Runtime.getRuntime().exec("tasklist.exe");
	    Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
	    while (scanner.hasNext()) {
	        if (scanner.nextLine().contains("VirtualBox"))
	        	enEjecucion = true;
	    }
	    scanner.close();
	    return enEjecucion;
	}
	
	public void shrinkVdi(String disk) {
		try {
			scriptAction.shrinkVdi(disk);
			msg = messages.getMessage("MG001");
			JOptionPane.showMessageDialog(null, msg);
			logger.setInfoLog(msg);
		} catch (Exception e) {
			msg = e.getMessage();
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
			logger.setErrorLog(msg);
		}
	}
	

}
