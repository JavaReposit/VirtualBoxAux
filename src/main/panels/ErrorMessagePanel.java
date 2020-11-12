package main.panels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.constants.FMessages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ErrorMessagePanel extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private FMessages messages;
	private JComboBox<String> comboBox;
	private JTextPane textPane;


	/**
	 * Create the frame.
	 */
	public ErrorMessagePanel() {
		setResizable(false);
		
		messages = FMessages.getInstance();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 203);
		contentPane = new JPanel();
		setTitle("Listado de mensajes de error");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(37, 44, 96, 20);
		for (int i = 0; i<messages.getAllMessges().size(); i++) {
			comboBox.addItem(messages.getAllMessges().get(i).getCode());
		}
		comboBox.setSelectedIndex(-1);
		contentPane.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = messages.getMessage(comboBox.getSelectedItem().toString());
				textPane.setContentType("text/html");
				textPane.setText(msg);
			}
		});
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textPane.setBounds(173, 44, 284, 73);
		contentPane.add(textPane);
		
		JLabel lbCodMensaje = new JLabel("C\u00F3digo Mensaje");
		lbCodMensaje.setHorizontalAlignment(SwingConstants.LEFT);
		lbCodMensaje.setBounds(37, 11, 96, 26);
		contentPane.add(lbCodMensaje);
		
		JLabel lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lbDescripcion.setBounds(173, 11, 284, 26);
		contentPane.add(lbDescripcion);
		
	}
}
