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
import main.constants.FMessages;
//import main.panels.AnimatedGif;

public class ScriptsActions {
	
	private FConstants constantes = new FConstants();
	private Process p;
	private File dir = new File(constantes.getDIR_VIRTUAL_BOX());
	private FMessages messages = FMessages.getInstance();
	private String msg = messages.getMessage("ERR004");
//	private final AnimatedGif animatedGif = new AnimatedGif();
	
	public void listVdi() {
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
				System.out.println("Error guardando listado de discos: " + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Error obteniendo la lista de discos virtuales: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}
	
	public void shrinkVdi(String disk) {
		try {
//			AnimatedGif gif = new AnimatedGif();
//			gif.setVisible(true);
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
//			gif.interrupt();
		} catch (InterruptedException e) {
			System.out.println("Proceso interrumpido inesperadamente: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		} catch (IOException e) {
			System.out.println("Error error reduciendo el tamaño del disco virtual: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}
	
	public void enlargeVdi() {
		try {
			p = Runtime.getRuntime().exec("cmd /c start " + constantes.getENLARGE_HDDS(), null, dir);
		} catch (IOException e) {
			System.out.println("Error ampliando el disco virtual: "+e.getMessage());
			msg = msg + "\n"+e.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}

}
