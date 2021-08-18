package main.constants;

public class FDetectOS {

	private static FDetectOS detectOS;
	private String os = null;
	
	public static FDetectOS getInstance() {
        if (detectOS == null) {
        	detectOS = new FDetectOS();
        }
        return detectOS;
    }
	
	public String getOS() {
		return os;
	}
	
	private FDetectOS() {
		os = getOperatingSystem();
	}
	
	private String getOperatingSystem() {
		String os = System.getProperty("os.name");
	    System.out.println("Using System Property: " + os);
	    return os;
	}
}
