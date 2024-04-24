package stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import utils.TestContextSetUp;

public class BaseStepDefinition{

    private TestContextSetUp context;
    
    public BaseStepDefinition(TestContextSetUp context) {
    	this.context = context;
    }
    
    @After
    public void tearDown() {
    	context.closeDriver();
    	context.reportAllAssertErrors();
    }
    
    @Given("The User opens the Bank of Baroda Application")
    public void user_opens_the_BOB_application() throws FileNotFoundException, IOException {
    	String URL = context.properties.getProperty("BOBURL");
    	context.getWebDriver().get(URL);
    }  
	
}