package main.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class UpdatingJd extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;

	/**
	 * Create the dialog.
	 */
	public UpdatingJd() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FlowLayout());
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		add(panel);
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
//		getContentPane().add(progressBar);
		
		panel.add(progressBar);

		

	}
	
	public void refresh() {
		for (int i=0; i < 101; i++) {
			try {
				Thread.sleep(50);
				progressBar.setValue(i);
				progressBar.setStringPainted(true);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}

}
