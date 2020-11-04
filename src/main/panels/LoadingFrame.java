package main.panels;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

public class LoadingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea TALoading;
	private JProgressBar progressBar;
	private String[] loadingMessages = new String[4];
	

	/**
	 * Create the frame.
	 */
	public LoadingFrame() {
		loadingMessages = messages(loadingMessages.length);
		createCompleteFrame();
	}
	
	private void createCompleteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 254);
		setTitle("Loading...");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		getContentPane().setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(87, 30, 250, 26);
		contentPane.add(progressBar);
		
		TALoading= new JTextArea();
		TALoading.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		TALoading.setOpaque(false);
		TALoading.setBounds(87, 81, 250, 97);
		contentPane.add(TALoading);
	}
	
	public void refreshLabel() {
		for (int i = 0; i < 5; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
				progressBar.setValue(i*25);
				TALoading.append(loadingMessages[i]+"\n");
				progressBar.setStringPainted(true);
			} catch (InterruptedException e) { 
				System.out.println("Procedimiento de carga interrumpido: "+e.getMessage());
			} catch (Exception e) {
				System.out.println("La carga de la aplicación se ha visto interrumpida");
			}
		}
	}
	
	public void closeFrame() {
		dispose();
	}
	
	public String[] messages(int size) {
		String[] loadMessages = new String[size];
		loadMessages[0] = "Comprobando los requisitos ...";
		loadMessages[1] = "Preparando el entorno de trabajo...";
		loadMessages[2] = "Copiando los archivos necesarios...";
		loadMessages[3] = "Recopilando información de VirtualBox...";
		return loadMessages;
	}
}
