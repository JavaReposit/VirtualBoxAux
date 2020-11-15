package main.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.constants.FConstants;

public class ConfigPanel extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private FConstants constantes = new FConstants();
	private JTextField txtDirVirtualBox, txtDirTrabajo;
	private JLabel lbDirVirtualBox, lcDirTrabajo;
	private JButton editButton, cancelButton, saveButton;
	
	

	public ConfigPanel() {
		setBounds(100, 100, 450, 300);
		setTitle("Ventana de configuración de rutas");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lbDirVirtualBox = new JLabel("Directorio VirtualBox");
		lbDirVirtualBox.setBounds(40, 58, 108, 14);
		getContentPane().add(lbDirVirtualBox);
		
		txtDirVirtualBox = new JTextField();
		txtDirVirtualBox.setEditable(false);
		txtDirVirtualBox.setBounds(158, 55, 211, 20);
		txtDirVirtualBox.setText(constantes.getDIR_VIRTUAL_BOX());
		getContentPane().add(txtDirVirtualBox);
		txtDirVirtualBox.setColumns(10);
		
		lcDirTrabajo = new JLabel("Direct. temporal trabajo");
		lcDirTrabajo.setBounds(40, 100, 108, 14);
		getContentPane().add(lcDirTrabajo);
		
		txtDirTrabajo = new JTextField();
		txtDirTrabajo.setEditable(false);
		txtDirTrabajo.setColumns(10);
		txtDirTrabajo.setBounds(158, 97, 211, 20);
		txtDirTrabajo.setText(constantes.getDIR_TEMPORAL());
		getContentPane().add(txtDirTrabajo);
		
		createButtons();
				
	}
	
	private void createButtons() {
		editButton = new JButton("Edit");
		editButton.setBounds(94, 185, 89, 23);
		getContentPane().add(editButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(242, 185, 89, 23);
		getContentPane().add(cancelButton);
		
		saveButton = new JButton("Save");
		saveButton.setVisible(false);
		saveButton.setBounds(94, 185, 89, 23);
		getContentPane().add(saveButton);
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDirVirtualBox.setEditable(true);
				txtDirTrabajo.setEditable(true);
				editButton.setVisible(false);
				saveButton.setVisible(true);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDirVirtualBox.setEditable(false);
				txtDirTrabajo.setEditable(false);
				saveButton.setVisible(false);
				editButton.setVisible(true);
				constantes.setvritual(txtDirVirtualBox.getText());
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
