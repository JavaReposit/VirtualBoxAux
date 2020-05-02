package main.panels;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import main.constants.FConstants;
import main.functions.ManageTemporalPath;
import main.functions.FilesActions;
import main.functions.Utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	/**
	 * DEFAULT SERIAL ID 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ManageTemporalPath temporalPath = new ManageTemporalPath();
	private FilesActions fileAction = new FilesActions();;
	private FConstants constants = new FConstants();
	private DefaultTableModel model;
	private JScrollPane scrollTable;
	private JTable vdiTable;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		setTitle("Virtual Box Auxiliar");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		fl.preferredLayoutSize(this);
		
		contentPane.setLayout(fl);
		
		vdiTable = new JTable();
		vdiTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollTable = new JScrollPane(vdiTable);
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setBounds(10, 10, 200, 100);
		contentPane.add(scrollTable, BorderLayout.CENTER);
		
		inicializarComponentes();
		
		JButton button = new JButton("Shrink");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int pos = vdiTable.getSelectedRow();
			JOptionPane.showMessageDialog(null, vdiTable.getValueAt(pos, 1));
			}
		});
		contentPane.add(button);
		contentPane.add(new JButton("Enlarge"));
	}
	
	public void inicializarComponentes() {		
		if (temporalPath.copyScripts()) {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			ArrayList<String> vdis = fileAction.readFile(constants.getDIR_TEMPORAL()+"\\"+constants.getHDDS_FILE());			
			if (!vdis.isEmpty()) {
				Utils utils = new Utils();
				ArrayList<String> vdiList = utils.cleanListVdis(vdis);
				if (!vdiList.isEmpty()) {
					fillTable(vdiList);
				}
			}
		}		
	}
	
	public void fillTable(ArrayList<String> vdiList) {		
		model = (DefaultTableModel) vdiTable.getModel();
		String colsTitle[] = {"##", "VIRTUAL HDD"};
		model.setColumnIdentifiers(colsTitle);
		vdiTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		vdiTable.getColumnModel().getColumn(1).setPreferredWidth(350);
		for (int i = 0; i< vdiList.size(); i++ ) {
			String[] row = {String.valueOf(i), vdiList.get(i)}; 
			model.addRow(row);
		}
	}
	
}
