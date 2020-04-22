package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import main.panels.MainWindow;

public class main {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Aplicación iniciada con éxito!!");
		
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		
	}

}
