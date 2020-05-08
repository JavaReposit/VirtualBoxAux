package main.constants;

public class FConstants {
	
	private static final String DIR_VIRTUAL_BOX = "C:\\Program Files\\Oracle\\VirtualBox";
	private static final String DIR_TEMPORAL = "C:\\Users\\Public\\Tmp";
	private static final String LIST_HDDS = "C:\\users\\public\\tmp\\listHdds.bat";
	private static final String SHRINK_HDDS = "C:\\Users\\Public\\Tmp\\shrinkHdds.bat";
	private static final String ENLARGE_HDDS = "C:\\Users\\Public\\Tmp\\enlargeHdds.bat";
	private static final String HDDS_FILE = "listadoDeDiscos.txt";
	private static final String MESSAGES_FILE = "utils\\messages.txt";
	
	public String getDIR_VIRTUAL_BOX() {
		return DIR_VIRTUAL_BOX;
	}

	public String getDIR_TEMPORAL() {
		return DIR_TEMPORAL;
	}

	public String getLIST_HDDS() {
		return LIST_HDDS;
	}

	public String getSHRINK_HDDS() {
		return SHRINK_HDDS;
	}

	public String getENLARGE_HDDS() {
		return ENLARGE_HDDS;
	}

	public String getHDDS_FILE() {
		return HDDS_FILE;
	}

	public String getMESSAGES_FILE() {
		return MESSAGES_FILE;
	}
	
}
