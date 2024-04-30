package bob.fd.ui.pages;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import common.BasePage;
import common.ExtentManager;
import utilities.StaticUtility;

public class FDCalculatorPage extends BasePage {
   
	public Map<String,String> details;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final String DEPOSIT_TYPE_DROPDOWN_OPTION = "//*[@id='fdType']/li[normalize-space(text())='%s']";

    public FDCalculatorPage(WebDriver driver , SoftAssert softAssert){
        super(driver , softAssert);
        details = new HashMap<>();
        setDefaultDepositType();
    }

    public void setDefaultDepositType(){
        details.put("FDDepositType","Reinvestment (Cumulative)");
    }

    public void selectFixedDepositType(String depositType){
        ExtentManager.printHeader("Selecting the option - "+depositType +" in the drop down "+ " - Type of Fixed Deposit:");
        commonUtil.clickTheElementWhenVisibleUsingJS(getLocator(FOLLOWING_SIBLING + SPAN , "Type of Fixed Deposit:") , 30);
        commonUtil.clickTheElementWhenVisibleUsingJS(getLocator(DEPOSIT_TYPE_DROPDOWN_OPTION , depositType) , 30);
        details.put("FDDepositType",depositType);
    }

    public void enterFixedDepositAmount(String amount){
        ExtentManager.printHeader("Entering the fixed deposit amount - "+amount);
        commonUtil.clearAndEnterValueWhenVisible(getLocator(FOLLOWING_INPUTBOX ,"Amount of Fixed Deposit:") , String.valueOf(amount) ,30);
        details.put("FDAmount",amount);
    }

    public void enterTenure(String tenure){
        ExtentManager.printHeader("Entering fixed deposit Tenure - "+tenure);

        String years = tenure.split(":")[0].replaceAll("[^\\d+]","");
        String months = tenure.split(":")[1].replaceAll("[^\\d+]","");
        String days = tenure.split(":")[2].replaceAll("[^\\d+]","");

        ExtentManager.printHeader("Entering fixed deposit - Year duration : "+years);
        commonUtil.clearAndEnterValueWhenVisible(getLocator(EM_WITH_INDEX ,"1") , years , 10);

        ExtentManager.printHeader("Entering fixed deposit - Month duration : "+months);
        commonUtil.clearAndEnterValueWhenVisible(getLocator(EM_WITH_INDEX ,"2") , months , 10);

        ExtentManager.printHeader("Entering fixed deposit - Days duration : "+days);
        commonUtil.clearAndEnterValueWhenVisible(getLocator(EM_WITH_INDEX ,"3") , days , 10);
        details.put("FDTenure",tenure);

        LocalDate depositedDate = LocalDate.now();
        LocalDate expectedMaturityDate = depositedDate.plusYears(Integer.parseInt(years)).plusMonths(Integer.parseInt(months)).plusDays(Integer.parseInt(days));
        details.put("DepositedDate",depositedDate.format(dateTimeFormatter));
        details.put("FDTenureDays", String.valueOf(ChronoUnit.DAYS.between(depositedDate , expectedMaturityDate)));
    }

    public void validateRateOfInterest(String interest){
        ExtentManager.printHeader("Validating FD rate of interest displayed in the screen - "+interest);
        details.put("FDRateOfInterest",commonUtil.verifyTextWhenVisible(getLocator(FOLLOWING_SIBLING,"Rate of Interest (%)") , interest , 10));
    }

    public void validateMaturityDateAndValue(){
        String expectedMaturityDate = LocalDate.parse(details.get("DepositedDate"),dateTimeFormatter)
                                                        .plusDays(Integer.parseInt(details.get("FDTenureDays")))
                                                        .format(dateTimeFormatter);
        ExtentManager.printHeader("Checking Maturity date is populated correctly");
        details.put("FDMaturityDate",commonUtil.verifyTextWhenVisible(getLocator(FOLLOWING_SIBLING,"Maturity Date") , expectedMaturityDate , 30));

        ExtentManager.printHeader("Retrieving the Maturity value displayed in the screen");
        String actualMaturityValue = commonUtil.geTextWhenVisible(getLocator(FOLLOWING_SIBLING,"Maturity Value") , 10);

        if(details.get("FDDepositType").equals("Quarterly Payout")) {
            ExtentManager.printHeader("Selected Deposit type - Quarterly Payout");
            float interest = Float.parseFloat(details.get("FDRateOfInterest"));
            int days = Integer.parseInt(details.get("FDTenureDays"));
            int depositedAmount  = Integer.parseInt(details.get("FDAmount"));
            int years = days / 365;
            int totalInterestAmount  = (int)((depositedAmount * years * interest) / 100);
            int interestPerQuarter = totalInterestAmount / (years * 4);

            DecimalFormat inrFormatter = StaticUtility.getDecimalFormatForIndianCurrency(0);
            
            ExtentManager.printHeader("Checking Aggregate Interest Amount");
            details.put("AggregateInterestAmount",commonUtil.verifyTextWhenVisible(getLocator(FOLLOWING_SIBLING,"Aggregate Interest Amount") , inrFormatter.format(totalInterestAmount) , 30));

            ExtentManager.printHeader("Checking Interest Per Quarter");
            details.put("InterestPerQuarter",commonUtil.verifyTextWhenVisible(getLocator(FOLLOWING_SIBLING,"Interest Per Quarter ₹") , inrFormatter.format(interestPerQuarter).replace("₹","") , 30));
           
        }
        details.put("FDMaturityValue",actualMaturityValue);
    }



}
