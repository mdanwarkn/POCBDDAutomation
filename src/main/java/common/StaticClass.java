package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class StaticClass {

	//Reporting 
    public static final String ELEMENT_VISIBLE = "Element - '%s' is visible in '%d' seconds";
    public static final String ELEMENT_NOT_VISIBLE = "Element - '%s' is not visible after waited for '%d' seconds \nException : %s";
    public static final String EXPECTED_TEXT_WITH_LOCATION_MATCHING = "Text - '%s' is present in the location '%s' in '%d' seconds";
    public static final String EXPECTED_TEXT_WITH_LOCATION_NOT_MATCHING = "Text - '%s' is not present in the location '%s' and waited for '%d' seconds <br><br> Actual Text found is `: '%s'";
    public static final String EXPECTED_TEXT_MATCHING = "Expected Text : '%s' found";
    public static final String EXPECTED_TEXT_NOT_MATCHING = "Expected Text '%s' not found \nActual Text found : '%s'";
    public static final String EXPECTED_ATTRIBUTE_VALUE_WITH_LOCATION_MATCHING = "Attribute - '%s' with value - '%s' is present in the location  '%s' in '%d' seconds";
    public static final String EXPECTED_ATTRIBUTE_VALUE_WITH_LOCATION_NOT_MATCHING = "Attribute - '%s' with value - '%s' is not present in the location '%s' in '%d' seconds\nActual value found : %s";
    public static final String CLICKING_THE_ELEMENT = "Clicking the element present in the location - '%s' in '%d' seconds";
    public static final String SENDING_CLEAR = "Clearing the content in the text box - '%s'";
    public static final String ENTERING_TEXT = "Entering the text - '%s' in the text box - '%s'";
	
	//Time Out
    public static int SHORT_TIMEOUT ;
    public static int MEDIUM_TIMEOUT;
    public static int LONG_TIMEOUT;
    public static int IMPLICIT_TIMEOUT;
 
    //Path   
    public static final String RESOURCES_FOLDER_PATH = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"resources";
    public static final String DRIVER_PATH = RESOURCES_FOLDER_PATH + File.separator + "drivers" + File.separator;
    public static final String OUPUT_SHEET_PATH = System.getProperty("user.dir")+ File.separator + "OutputFile"+File.separator;
    public static final String WORKBOOK_NAME = "Output_Results.xlsx";
    public static final String SHEET_NAME = "BOB";
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir")+ File.separator+"Screenshots"+File.separator;
    
    //Date Time Format
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FORMAT1 = DateTimeFormatter.ofPattern("d MMMM yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FORMAT2 = DateTimeFormatter.ofPattern("MMM dd yyyy");

    static {
    	try {
			setTimeOut();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public static void setTimeOut() throws FileNotFoundException, IOException {
    	String pageLoading = (String) PropertyManager.getPropertiesInstance().get("PageLoading");
    	switch(pageLoading.toUpperCase()) {
	    	case "SLOW":
	    		IMPLICIT_TIMEOUT = 10;
	    		SHORT_TIMEOUT = 30;
	    		MEDIUM_TIMEOUT = 45;
	    		LONG_TIMEOUT = 90;    		
	    		break;
	    	
	    	case "NORMAL":
	    	default:
	    		IMPLICIT_TIMEOUT = 5;
	    		SHORT_TIMEOUT = 10;
	    		MEDIUM_TIMEOUT = 30;
	    		LONG_TIMEOUT = 60;
    	}
    }
}
