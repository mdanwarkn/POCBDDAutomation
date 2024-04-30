package common;

import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;
import utilities.CommonUtil;

public class BasePage {
 
    //Input Locators
	 protected static final String INPUT_WITH_TITLE = "//input[@title='%s']";
	 protected static final String INPUT_WITH_PLACEHOLDER = "//input[@placeholder='%s']";
	 protected static final String INPUT_TAG = "//input";
	   
	 
	//Button Locators
	 protected static final String BUTTON_WITH_TITLE = "//button[@title='%s']";
	 protected static final String BUTTON_WITH_TEXT = "//button[text()='%s']";
	    
	//Generic Locators
	 protected static final String ELEMENT_WITH_TEXT_INDEX = "(//*[text()='%s'])[%s]";
	 protected static final String ELEMENT_WITH_ARIALABEL = "//*[@aria-label='%s']";
	 protected static final String ELEMENT_WITH_CLASS = "//*[@class='%s']";
	 protected static final String ELEMENT_WITH_CLASS_WITH_INDEX = "(//*[@class='%s'])[%s]";
	 protected static final String ELEMENT_WITH_PLACEHOLDER = "//*[@placeholder='%s']";
	 protected static final String ELEMENT_CONTAINS_CLASS = "//*[contains(@class,'%s')]";
     protected static final String ELEMENT_CONTAINS_CLASS_WITH_INDEX = ELEMENT_CONTAINS_CLASS + "[%s]";
     protected static final String ELEMENT_CONTAINS_ARIALABEL = "//*[contains(@aria-label,'%s')]";
     protected static final String CONTAINS_TEXT = "//*[contains(text() , '%s')]";
     protected static final String ELEMENT_WITH_STRING = "//*[string()='%s']";
     protected static final String TEXT = "//*[text()='%s']";
     protected static final String TEXT_SINGLE_INVERTED = "//*[text()=\"%s\"]";
     protected static final String ANY_FOLLOWING_SIBLING = "/following-sibling::*";
     protected static final String FOLLOWING_INPUTBOX = TEXT + "/following::input[1]";
     protected static final String FOLLOWING_SIBLING = "//*[normalize-space(text())='%s']/following-sibling::*";
     protected static final String ANCESTOR_CONTAINS_CLASS = "/ancestor::*[contains(@class,'%s')]";
     
	//IFrame Locators
	 protected static final String IFRAME_WITH_TITLE = "//iframe[@title='f%s']";
     protected static final String IFRAME_CONTAINS_TITLE = "//iframe[contains(@title,'%s')]";
    
    //Header Locators
     protected static final String H3 = "//h3";
     protected static final String H2_STRING = "//h2[string()='%s']";
    
    //Other Locators
     protected static final String LINK_TEXT = "//a[text()='%s']";    
     protected static final String LIST_CONTAINS_STRING = "//li[contains(string() , '%s')]";
     protected static final String DIV_TEXT = "//div[text()='%s']";
     protected static final String SELECT_WITH_ARIALABEL = "//select[@aria-label='%s']";
     protected static final String EM_WITH_INDEX = "(//em)[%s]";
     protected static final String LABEL_TEXT = "//label[text()='%s']";
     protected static final String SPAN = "//span";
  
     protected WebDriver driver;
     protected SoftAssert softAssert;
     protected CommonUtil commonUtil;
    
    protected BasePage(WebDriver driver , SoftAssert softAssert){
    	this.driver =  driver;
        this.softAssert = softAssert;
        this.commonUtil = new CommonUtil(driver , softAssert);
    }
        
    public static By getLocator(String locator) {
        return By.xpath(locator);
    }

    public static By getLocator(String locator , String value1) {
        return By.xpath(String.format(locator, value1));
    }

    public static By getLocator(String locator , String value1 , String value2) {
        return By.xpath(String.format(locator, value1 , value2));
    }
}
