package main.panels;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import main.constants.FConstants;
import main.constants.FMessages;
import main.functions.ManageTemporalPath;
import main.functions.FilesActions;
import main.functions.Utils;
import main.log.AppLogger;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class MainWindow extends JFrame {

	/**
	 * DEFAULT SERIAL ID 
	 */
	private static final long serialVersionUID = 1L;
	private FMessages messages;
	private JPanel contentPane;
	private ManageTemporalPath temporalPath = new ManageTemporalPath();
	private FilesActions fileAction = new FilesActions();;
	private FConstants constants = new FConstants();
	private Utils utils = new Utils();
	private DefaultTableModel model;
	private JScrollPane scrollTable;
	private JTable vdiTable;
	private JButton shrinkButton;
	private JButton enlargeButton;
	private JButton refreshButton;
	private String msg;
	private NumberFormat nf = NumberFormat.getInstance();
	private final AppLogger logger = new AppLogger(MainWindow.class.getName());
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		messages = FMessages.getInstance();
		
		createWindow();
		
		createVdiTable();
		
		createButtons();
		getContentPane().setLayout(null);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	temporalPath.deleteTemporalPath();
		            System.exit(0);
		        } 
		    }
		});
	}
	
	private void createWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 610, 471);
		setResizable(false);
		setTitle("VirtualBox Auxiliar");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
	}
	
	private void createVdiTable() {
		vdiTable = new JTable();
		vdiTable.setIntercellSpacing(new Dimension(15, 1));
		vdiTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollTable = new JScrollPane(vdiTable);
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(26, 11, 550, 323);
		contentPane.add(scrollTable, BorderLayout.CENTER);
		
		if (temporalPath.copyScripts()) {
			try {
				generateLoadingFrame();
				generateVdiList();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
	}
	
	private void generateLoadingFrame() {
		LoadingFrame lf = new LoadingFrame();
		lf.setVisible(true);
		lf.refreshLabel();
		lf.closeFrame();
	}
	
	private void refreshVdiList() {
		try {
			utils.findVdi();
			ArrayList<String> vdis = fileAction.readFile(constants.getDIR_TEMPORAL()+"\\"+constants.getHDDS_FILE());			
			if (!vdis.isEmpty()) {
				ArrayList<String> vdiList = utils.cleanListVdis(vdis);
				if (!vdiList.isEmpty()) {
					fillTable(vdiList);
				}
			}
			msg = messages.getMessage("MG003");
			JOptionPane.showMessageDialog(null, msg, "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			msg = e.getMessage();
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
			logger.setErrorLog(msg);
		}
		
	}
	
	private void generateVdiList() {
		utils.findVdi();
		ArrayList<String> vdis = fileAction.readFile(constants.getDIR_TEMPORAL()+"\\"+constants.getHDDS_FILE());			
		if (!vdis.isEmpty()) {
			ArrayList<String> vdiList = utils.cleanListVdis(vdis);
			if (!vdiList.isEmpty()) {
				fillTable(vdiList);
			}
		}
	}
	
	private void fillTable(ArrayList<String> vdiList) {		
		model = (DefaultTableModel) vdiTable.getModel();
		RightTableCellRenderer rightRender = new RightTableCellRenderer();
		String colsTitle[] = {"##", "VIRTUAL HDD", "Size (Mbs)"};
		model.setColumnIdentifiers(colsTitle);
		vdiTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		vdiTable.getColumnModel().getColumn(1).setPreferredWidth(350);
		vdiTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		vdiTable.getColumnModel().getColumn(2).setCellRenderer(rightRender);
		vdiTable.getColumnModel().getColumn(2);
		for (int i = 0; i< vdiList.size(); i++ ) {
			String fileName = vdiList.get(i);
			String fileSize = nf.format(fileAction.getFileSize(vdiList.get(i))/1024000);
			String[] row = {String.valueOf(i+1), fileName, fileSize}; 
			model.addRow(row);
		}
	}
	
	private void createButtons() {
		shrinkButton = new JButton("Shrink");
		contentPane.add(shrinkButton);
		shrinkButton.setBounds(91, 375, 89, 23);
		enlargeButton = new JButton("Enlarge");
		contentPane.add(enlargeButton);
		enlargeButton.setBounds(260, 375, 89, 23);
		refreshButton = new JButton("Refresh");
		contentPane.add(refreshButton);
		refreshButton.setBounds(421, 375, 89, 23);
		
		shrinkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = vdiTable.getSelectedRow();
				if (pos < 0) {
					msg = messages.getMessage("ERR002");
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
					logger.setErrorLog(msg);
				} else {
					try {
						if (utils.seEstaEjecutantoVirtualBox()) {
							msg = messages.getMessage("ERR003");
							JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
							logger.setErrorLog(msg);
						} else {
							String disk = vdiTable.getValueAt(pos, 1).toString();
							utils.shrinkVdi(disk);
						}
					} catch (HeadlessException | IOException err) {
						err.printStackTrace();
						logger.setErrorLog(err.getMessage());
					}				
				}			
			}
		});
		
		enlargeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = vdiTable.getSelectedRow();
				if ( pos < 0) {
					msg = messages.getMessage("ERR002");
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
					logger.setErrorLog(msg);
				} else {
					try {
						if (utils.seEstaEjecutantoVirtualBox()) {
							msg = messages.getMessage("ERR003");
							JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
							logger.setErrorLog(msg);
	 					} else {
	 						msg = messages.getMessage("MG002");
							JOptionPane.showMessageDialog(null, msg, "Lista actualizada", JOptionPane.INFORMATION_MESSAGE);
							logger.setInfoLog(msg);
						}
					} catch (Exception err) {
						System.out.println("Error ejecutando proceso... " + err.getMessage()); 
						logger.setErrorLog(msg);
					}
				}
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vdiTable.getRowCount() > 0) {
					model.setRowCount(0);
					refreshVdiList();
					logger.setInfoLog("lista actualizada");
				} 
			}
		});
		

	}
	
	class RightTableCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		protected  RightTableCellRenderer() {
			setHorizontalAlignment(JLabel.RIGHT);
		} 
	} 
}

