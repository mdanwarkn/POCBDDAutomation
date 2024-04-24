package bob.fd.ui.pages;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import common.BasePage;
import static common.StaticClass.*;


public class HomePage extends BasePage{

	 public HomePage(WebDriver driver , SoftAssert softAssert){
		 super(driver , softAssert);
	 }
	 
	 public void navigateToCalculatorScreen(String calculatorType) {
		 scrollToElement(getLocator(LINK_TEXT, calculatorType) , MEDIUM_TIMEOUT);
		 clickTheElementWhenVisibleUsingJS(getLocator(LINK_TEXT , calculatorType), SHORT_TIMEOUT);
	 }
}
