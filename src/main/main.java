package main;


import java.awt.Dimension;
import java.awt.Toolkit;

import main.panels.MainWindow;

public class main {

	public static void main (String[] args) {
		
		try {
			MainWindow mw = new MainWindow();
			mw.setVisible(true);
			System.out.println("Aplicación iniciada con éxito!!");
		} catch (Exception e) {
			System.out.println("Ha habido un problema inicializando la aplicacion:\n"+e.getMessage());
		}
		
	}

}
