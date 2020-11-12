package main.constants;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.functions.FilesActions;

public class FMessages {
	
	
	private ArrayList<FMessage> allMessages;
	private FConstants constants = new FConstants();
	private FilesActions fileActions = new FilesActions();
	
	private static FMessages messages;
	
	public static FMessages getInstance() {
        if (messages == null) {
             messages = new FMessages();
        }
        return messages;
    }
	
	private FMessages() {
		allMessages = new ArrayList<>();
		readMessages();
	}
	
	public String getMessage(String code) {
		String returnMessage = "";
		for (FMessage message : allMessages) {
			if (message.getCode().equalsIgnoreCase(code)) {
				returnMessage = message.getMessage();
			}
		}
		return returnMessage;
	}
	
	public void readMessages() {
		FMessage m; 
		try {
			ArrayList<String> messages = new ArrayList<>();
			String s = constants.getMESSAGES_FILE();
			messages = fileActions.readFile(s);
			
			for (int i = 0; i < messages.size(); i++) {
				m = new FMessage();
				String[] line = messages.get(i).split("#");
				m.setCode(line[0]);
				m.setMessage(line[1]);
				allMessages.add(m);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Archivo de mensajes no encontrado: " + e.getMessage());
		}
	}
	
	public ArrayList<FMessage> getAllMessges() {
		
		return allMessages; 
	}

}
