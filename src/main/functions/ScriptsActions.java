package main.functions;

import java.io.File;
import java.io.IOException;

import main.constants.FConstants;

public class ScriptsActions {
	
	private FConstants constantes = new FConstants();
	@SuppressWarnings("unused")
	private Process p;
	
	public void listVdi() {
		try {
			File dir = new File(constantes.getDIR_VIRTUAL_BOX());
			p = Runtime.getRuntime().exec("cmd /c start "+constantes.getLIST_HDDS(), null, dir);
			System.out.println("Creado listados de discos correctamente.");
		} catch (IOException e) {
			System.out.println("Error obteniendo la lista de discos virtuales: "+e.getMessage());
		}
	}
	
	public void shrinkVdi() {
		try {
			File dir = new File(constantes.getDIR_VIRTUAL_BOX());
			p = Runtime.getRuntime().exec("cmd /c start " + constantes.getSHRINK_HDDS(), null, dir);
		} catch (IOException e) {
			System.out.println("Error error reduciendo el tamaño del disco virtual: "+e.getMessage());
		}
	}
	
	public void enlargeVdi() {
		try {
			File dir = new File(constantes.getDIR_VIRTUAL_BOX());
			p = Runtime.getRuntime().exec("cmd /c start " + constantes.getENLARGE_HDDS(), null, dir);
		} catch (IOException e) {
			System.out.println("Error ampliando el disco virtual: "+e.getMessage());
		}
	}

}
