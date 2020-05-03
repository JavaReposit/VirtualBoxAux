package main.panels;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class LoadingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public LoadingFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Loading...");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JLabel lbLoading = new JLabel("New label");
		contentPane.add(lbLoading, BorderLayout.CENTER);
		
		for (int i = 0; i < 15; i++) {
			lbLoading.setText(refrescarLabel(i));
		}
		
		
	}
	
	public void cerrarFrame() {
		this.dispose();
	}
	
	public String refrescarLabel(int i) {
		Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("I would be called every 2 seconds");
			}
		}, 0, 2000);
	    if (i%2 == 0)
	    	return "Cargando los archivos de configuración....";
	    else
	    	return "Actualizando la definición de clases...";
	}

}
