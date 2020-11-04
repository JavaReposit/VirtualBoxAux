package main;


import main.log.AppLogger;
import main.panels.MainWindow;

public class Main {

	public static void main (String[] args) {
		
		final AppLogger logger = new AppLogger(Main.class.getName());
		
		try {
			MainWindow mw = new MainWindow();
			mw.setVisible(true);
			System.out.println();
			logger.setInfoLog("Aplicación iniciada con éxito!!");
		} catch (Exception e) {
			logger.setErrorLog("Ha habido un problema inicializando la aplicacion:\n"+e.getMessage());
		}
		
	}

}
