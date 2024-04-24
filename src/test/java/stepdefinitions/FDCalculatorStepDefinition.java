package stepdefinitions;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import bob.fd.ui.pages.FDCalculatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ExcelUtil;
import utils.TestContextSetUp;
import static common.StaticClass.*;

public class FDCalculatorStepDefinition{

	 private TestContextSetUp context;
	 private FDCalculatorPage fdCalculatorPage;
	    
     public FDCalculatorStepDefinition(TestContextSetUp context) {
    	this.context = context;
    	fdCalculatorPage = this.context.fdCalculatorPage; 
     }
    
     @When("The User selects the Fixed Deposit Type as {string}")
     public void the_user_selects_the_fixed_deposit_type_as(String depositType) {
    	 fdCalculatorPage.selectFixedDepositType(depositType);
     }

     @When("The User enters the Amount of fixed deposit as {string}")
     public void the_user_enters_the_amount_of_fixed_deposit_as(String amount) {
    	 fdCalculatorPage.enterFixedDepositAmount(amount);
     }
     
     @When("The User enters the Tenure date as {string}")
     public void the_user_enters_the_tenure_date_as(String tenure) {
         fdCalculatorPage.enterTenure(tenure);
     }
     @Then("Validate Rate of Interest is {string}")
     public void validate_rate_of_interest_is(String interest) {
         fdCalculatorPage.validateRateOfInterest(interest);
     }
     @Then("Validate Maturity Date And Maturity Value")
     public void validate_maturity_date_and_maturity_value() {
         fdCalculatorPage.validateMaturityDateAndValue();
     }
     @And("Store the FD results in output file")
     public void storeTheFDResultsInOutputFile() throws IOException, InvalidFormatException {
         String fileSource =  OUPUT_SHEET_PATH + WORKBOOK_NAME;
         ExcelUtil.writeDataToExcelFile(fdCalculatorPage.currentRow, fileSource , SHEET_NAME);
     }
     
}
