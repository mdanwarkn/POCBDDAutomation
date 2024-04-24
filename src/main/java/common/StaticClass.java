package common;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class StaticClass {

	//Time Out
    public static final int SHORT_TIMEOUT = 10;
    public static final int MEDIUM_TIMEOUT = 30;
    public static final int LONG_TIMEOUT = 60;
    public static final int IMPLICIT_TIMEOUT = 5;
 
    //Path   
    public static final String RESOURCES_FOLDER_PATH = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"resources";
    public static final String DRIVER_PATH = RESOURCES_FOLDER_PATH + File.separator + "drivers" + File.separator;
    public static final String INPUT_SHEET_PATH = RESOURCES_FOLDER_PATH + File.separator+"data"+File.separator;
    public static final String OUPUT_SHEET_PATH = System.getProperty("user.dir")+ File.separator + "OutputFile"+File.separator;
    public static final String WORKBOOK_NAME = "Output_Results.xlsx";
    public static final String SHEET_NAME = "BOB";
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir")+ File.separator+"Screenshots"+File.separator;
    
    //Date Time Format
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FORMAT1 = DateTimeFormatter.ofPattern("d MMMM yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FORMAT2 = DateTimeFormatter.ofPattern("MMM dd yyyy");

}
