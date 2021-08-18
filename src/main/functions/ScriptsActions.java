package main.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.constants.FConstants;
import main.constants.FDetectOS;
import main.constants.FMessages;
import main.log.AppLogger;


public class ScriptsActions {
	
	private FConstants constantes = new FConstants();	
	private FDetectOS detecOS = FDetectOS.getInstance();
	private File dir = new File(constantes.getDIR_VIRTUAL_BOX());
	private FMessages messages = FMessages.getInstance();
	private Process p;
	private String msg = messages.getMessage("ERR004");
	private final AppLogger logger = new AppLogger(ScriptsActions.class.getName());
	
	public void listVdi() {
		if (detecOS.getOS().equalsIgnoreCase("linux")) {
			getLinuxVdi();
		} else {
			getWindowsVdi();
		}
	}
	
	private void getLinuxVdi() {
		try {
			final List<String> commands = new ArrayList<String>();
			commands.add("/bin/bash");
			commands.add(constantes.getLIST_HDDS());		
			
			ProcessBuilder pb = new ProcessBuilder(commands);
			Process p = pb.start();
			BufferedReader reader = 
			                new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null) {
				System.out.println(line);   
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(constantes.getDIR_TEMPORAL()+"/"+constantes.getHDDS_FILE()));
				bw.append(builder);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				logger.setWarningLog("Error guardando listado de discos: " + e.getMessage());
			}
		} catch (IOException e) {
			logger.setWarningLog("Error obteniendo la lista de discos virtuales: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}
	
	private void getWindowsVdi() {
		try {
//			p = Runtime.getRuntime().exec("cmd /c start /B " + constantes.getLIST_HDDS() + " &", null, dir);
//			System.out.println("Creado listados de discos correctamente.");
			final List<String> commands = new ArrayList<String>();
			commands.add("cmd.exe");
			commands.add("/C");
			commands.add(constantes.getLIST_HDDS());
			
			ProcessBuilder pb = new ProcessBuilder(commands);
			pb.directory(dir);
			Process p = pb.start();
			BufferedReader reader = 
			                new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null) {
				System.out.println(line);   
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\public\\tmp\\listadoDeDiscos.txt"));
				bw.append(builder);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				logger.setWarningLog("Error guardando listado de discos: " + e.getMessage());
			}
		} catch (IOException e) {
			logger.setWarningLog("Error obteniendo la lista de discos virtuales: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}
	
	public void shrinkVdi(String disk) {
		try {
			p = Runtime.getRuntime().exec("cmd /c start /wait " + constantes.getSHRINK_HDDS() + " " +disk, null, dir);
			p.waitFor();
//			final List<String> commands = new ArrayList<String>();
//			commands.add("cmd.exe");
//			commands.add("/C");
//			commands.add(constantes.getSHRINK_HDDS());
//			commands.add(disk);
//			ProcessBuilder pb = new ProcessBuilder(commands);
//			pb.directory(dir);
//			Process p = pb.start();
//			BufferedReader reader = 
//			                new BufferedReader(new InputStreamReader(p.getInputStream()));
//			StringBuilder builder = new StringBuilder();
//			String line = null;
//			while ( (line = reader.readLine()) != null) {
//				System.out.println(line);   
//				builder.append(line);
//				builder.append(System.getProperty("line.separator"));
//			}
//			p.waitFor();
		} catch (InterruptedException e) {
			logger.setWarningLog("Proceso interrumpido inesperadamente: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		} catch (IOException e) {
			logger.setWarningLog("Error reduciendo el tama�o del disco virtual: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}
	
	public void enlargeVdi() {
		try {
			p = Runtime.getRuntime().exec("cmd /c start " + constantes.getENLARGE_HDDS(), null, dir);
		} catch (IOException e) {
			logger.setWarningLog("Error ampliando el disco virtual: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}

}
