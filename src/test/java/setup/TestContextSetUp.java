package setup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import bob.fd.ui.pages.FDCalculatorPage;
import bob.fd.ui.pages.HomePage;
import bob.fd.ui.pages.PageObjectManager;
import common.PropertyManager;

public class TestContextSetUp extends BaseTest{

	public HomePage homePage;
	public FDCalculatorPage fdCalculatorPage;
	public Properties properties;
	public PageObjectManager pageObjectManager;
	public String dataProviderPath;
	public String scenarioKey;
	public Map<String,String> dataSetRow;
	
	public TestContextSetUp() throws FileNotFoundException, IOException {
		super();
		properties = PropertyManager.getPropertiesInstance();
		pageObjectManager = new PageObjectManager(driver , softAssert);
	}
	
	
}
