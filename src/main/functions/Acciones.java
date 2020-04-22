package main.functions;

import java.io.File;
import java.io.IOException;

public class Acciones {
	
	public void ejecutarScript() {
		try {
			File dir = new File("c:\\Program Files\\Oracle\\VirtualBox");
			Process p = Runtime.getRuntime().exec("cmd /c start C:\\Users\\Public\\Tmp\\listHdds.bat", null, dir);
		} catch (IOException e) {
			System.out.println("Error ejecutando el script: "+e.getMessage());
		}
	}

}
