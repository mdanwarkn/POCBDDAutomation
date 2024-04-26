package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import setup.TestContextSetUp;
import utilities.ExcelUtil;

public class BaseStepDefinition{

    private TestContextSetUp context;
    
    public BaseStepDefinition(TestContextSetUp context) {
    	this.context = context;
    }
    
    @Before("@DataSetFromExcel")
    public void setDataProviderpath(Scenario scenario) {
    	Set<String> scenarioTags = new HashSet(scenario.getSourceTagNames());
    	for(String tag : scenarioTags) {
    		if(tag.contains("@Scenario:")) {
    			context.scenarioKey = tag.replace("@Scenario:", "");
    			break;
    		}
    	}
    	context.dataProviderPath = scenario.getUri().getPath().replace("/", "\\")
    										.replaceFirst("features", "data")
    										.replace(".feature", ".xlsx");
    }
    
    @After
    public void tearDown() {
    	context.closeDriver();
    	context.reportAllAssertErrors();
    }
    
   
    public void configureDataSets(String path , String identifier) throws InvalidFormatException, IOException {
		System.out.println(path+" "+identifier);
		Object[][] dataObject = ExcelUtil.readDataFromExcelFile(path,context.scenarioKey);
		HashMap<String , Map<String,String>> dataSet = new HashMap<>();
		for(int i = 0 ; i<dataObject.length ; i++) {
			Map<String,String> dataRow = (Map<String, String>) dataObject[i][0];
			dataSet.put(dataRow.get("Identifier"),dataRow);
		}
		context.dataSetRow = dataSet.get(identifier);
		System.out.println(context.dataSetRow);
    }
    
    @Given("The User opens the Bank of Baroda Application")
    public void user_opens_the_BOB_application() throws FileNotFoundException, IOException {
    	String URL = context.properties.getProperty("BOBURL");
    	context.getWebDriver().get(URL);
    }
    
    @Then("Configure the data set to test from Excel {string}")
    public void configureDataSet(String identifier) throws InvalidFormatException, IOException {
    	configureDataSets(context.dataProviderPath, identifier);
    }
	
}