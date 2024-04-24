package bob.fd.ui.pages;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class PageObjectManager {

	WebDriver driver;
	SoftAssert softAssert;
	
	public PageObjectManager(WebDriver driver , SoftAssert softAssert) {
		this.driver = driver;
		this.softAssert = softAssert;
	}
	
	public HomePage getHomePage() {
		return new HomePage(driver , softAssert);
	}
	
	public FDCalculatorPage getFDCalculatorPage() {
		return new FDCalculatorPage(driver , softAssert);
	}
	
}
