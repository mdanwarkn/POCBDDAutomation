package stepdefinitions;

import bob.fd.ui.pages.HomePage;
import io.cucumber.java.en.Given;
import utils.TestContextSetUp;

public class HomePageStepDefinition{

    private TestContextSetUp context;
    private HomePage homePage;
    
    public HomePageStepDefinition(TestContextSetUp context) {
    	this.context = context;
    }
    
    @Given("User navigates to the {string} screen")
    public void user_navigates_to_screen(String calculatorType) {
    	homePage = context.pageObjectManager.getHomePage();
    	homePage.navigateToCalculatorScreen(calculatorType);
    	context.fdCalculatorPage = context.pageObjectManager.getFDCalculatorPage();
    }
    
	
}