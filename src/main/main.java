package main;


import main.panels.MainWindow;

public class Main {

	public static void main (String[] args) {
		
		try {
			MainWindow mw = new MainWindow();
			mw.setVisible(true);
			System.out.println("Aplicaci�n iniciada con �xito!!");
		} catch (Exception e) {
			System.out.println("Ha habido un problema inicializando la aplicacion:\n"+e.getMessage());
		}
		
	}

}
