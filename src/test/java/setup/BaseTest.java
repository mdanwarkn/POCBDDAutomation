package setup;

import static common.StaticClass.DRIVER_PATH;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.asserts.SoftAssert;

public class BaseTest {
	
	protected WebDriver driver;
	protected SoftAssert softAssert;
	
	public BaseTest() {
		invokeWebDriver();
		invokeSoftAssert();
	}
	
	public WebDriver invokeWebDriver() {
		if(driver==null) {
			System.setProperty("webdriver.edge.driver", DRIVER_PATH + "EdgeDriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver= new EdgeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	public SoftAssert invokeSoftAssert() {
		if(softAssert==null) {
			softAssert = new SoftAssert();
		}
		return softAssert;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public SoftAssert getSoftAssert() {
		return softAssert;
	}
	
	public void closeDriver() {
		if(driver!=null) {
			driver.close();
		}
	}
	
	public void reportAllAssertErrors() {
		if(softAssert!=null) {
			softAssert.assertAll();
		}
	}

}
