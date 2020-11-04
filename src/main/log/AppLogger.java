package main.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class AppLogger {
	
	private final Logger logger;
	
	
	public AppLogger(String name) {
		BasicConfigurator.configure();
		this.logger = Logger.getLogger(name);
	}
	
	public void setInfoLog(String msg) {
		this.logger.log(Level.INFO, msg);
	}
	
	public void setWarningLog(String msg) {
		this.logger.log(Level.WARN, msg);
	}
	
	public void setErrorLog(String msg) {
		this.logger.log(Level.ERROR, msg);
	}

}
