package utilities;

import static common.StaticClass.*;
import static utilities.StaticUtility.*;
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import common.ExtentManager;
import common.StaticClass;

public class CommonUtil{

	protected WebDriver driver;
	protected SoftAssert softAssert;
	 
    public CommonUtil(WebDriver driver , SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
    }
	    
    public void logFailAndTakeScreenShot(String message){    
    	softAssert.assertTrue(false , message);
    	ExtentManager.logFailAndAttachScreenshotFromPath(takeScreenShotAsFile() , message);
    }

    public void logPassAndTakeScreenshot(String message) {
    	softAssert.assertTrue(true);
    	ExtentManager.logPassAndTakeScreenshot(takeScreenShotAsFile() ,message);
    }

    public byte[] takeScreenShotInBytes(){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }

    public String takeScreenShotInBase64(){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BASE64);
    }

    public String takeScreenShotAsFile(){
        try{
            File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String source = StaticClass.SCREENSHOT_PATH + StaticUtility.timestamp() + ".jpg" ;
            File destinationFile = new File(source);
            FileUtils.copyFile(sourceFile , destinationFile);
            return destinationFile.getAbsolutePath();
        }catch (Exception e){
            return null;
        }
    }

    public void verifyElementIsVisible(By locator , int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logPassAndTakeScreenshot(String.format(ELEMENT_VISIBLE,  locator , timeOut));
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            logFailAndTakeScreenShot(String.format(ELEMENT_NOT_VISIBLE ,  locator , timeOut , e.getMessage()));
        }
    }

    public String verifyTextWhenVisible(By locator , String expectedText , int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String actualText = driver.findElement(locator).getText();
            actualText = formatNullToEmpty(actualText);
            if(actualText.equals(expectedText)){
                logPassAndTakeScreenshot(String.format(EXPECTED_TEXT_WITH_LOCATION_MATCHING ,expectedText , locator , timeOut));
            }else{
                logFailAndTakeScreenShot(String.format(EXPECTED_TEXT_WITH_LOCATION_NOT_MATCHING ,expectedText , locator , timeOut ,  actualText));
            }
            return actualText;
        }catch(StaleElementReferenceException | NoSuchElementException e) {
            logFailAndTakeScreenShot(String.format(ELEMENT_NOT_VISIBLE , locator , timeOut ,  e.getMessage()));
            return null;
        }
    }

    public void verifyText(String actualText , String expectedText) {
        actualText = formatNullToEmpty(actualText);
        if(actualText.equals(expectedText)){
            logPassAndTakeScreenshot(String.format(EXPECTED_TEXT_MATCHING , expectedText));
        }else{
            logFailAndTakeScreenShot(String.format(EXPECTED_TEXT_NOT_MATCHING ,expectedText , actualText));
        }
    }

    public void verifyAttributeWhenVisible(By locator , String attribute , String expectedValue , int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String actualValue = driver.findElement(locator).getAttribute(attribute);
            actualValue = formatNullToEmpty(actualValue);
            if(actualValue.equals(expectedValue)){
                logPassAndTakeScreenshot(String.format(EXPECTED_ATTRIBUTE_VALUE_WITH_LOCATION_MATCHING ,attribute , expectedValue , locator , timeOut));
            }else{
                logFailAndTakeScreenShot(String.format(EXPECTED_ATTRIBUTE_VALUE_WITH_LOCATION_NOT_MATCHING , attribute , expectedValue , locator , timeOut , actualValue));
            }
        }catch(StaleElementReferenceException | NoSuchElementException e) {
            logFailAndTakeScreenShot(String.format(ELEMENT_NOT_VISIBLE ,  locator , timeOut , e.getMessage()));
        }
    }

    public void clearAndEnterValueWhenVisible(By locator , String text , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            ExtentManager.logInfo(String.format(SENDING_CLEAR , locator));
            element.clear();
            ExtentManager.logInfo(String.format(ENTERING_TEXT , text , locator));
            element.sendKeys(text);
        }catch(Exception e) {
            logFailAndTakeScreenShot(e.getMessage());
        }
    }

    public void clearAndEnterTextWhenVisibleUsingJS(By locator , String text , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element =  driver.findElement(locator);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            javascriptExecutor.executeScript("arguments[0].innerText=arguments[1];",element,text);
        }catch(Exception e) {
            logFailAndTakeScreenShot(e.getMessage());
        }
    }

    public void clickTheElementWhenVisible(By locator , int timeOut){
        try {
            ExtentManager.logInfo(String.format(CLICKING_THE_ELEMENT , locator , timeOut));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).click();
        }catch(StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
            logFailAndTakeScreenShot(e.getMessage());
        }
    }

    public void clickTheElementWhenVisibleUsingJS(By locator , int timeOut){
        try {
            ExtentManager.logInfo(String.format(CLICKING_THE_ELEMENT , locator , timeOut));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            javascriptExecutor.executeScript("arguments[0].focus();",element);
            javascriptExecutor.executeScript("arguments[0].click();",element);
        }catch(StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
            logFailAndTakeScreenShot(e.getMessage());
        }
    }

    public void clickTheElementWhenExistsUsingJS(By locator , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            javascriptExecutor.executeScript("arguments[0].focus();",element);
            javascriptExecutor.executeScript("arguments[0].click();",element);
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
        }
    }

    public void scrollToElement(By locator , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element =  driver.findElement(locator);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();",element);
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
        }
    }

    public void scrollToBottom(){
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
        }
    }

    public void scrollToTop(){
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            javascriptExecutor.executeScript("window.scrollTo(0 , 0)");
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
        }
    }

    public boolean verifyElementIsEnabled(By locator , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element =  driver.findElement(locator);
             if(element.isEnabled())
                 return true;
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
        }
        return false;
    }

    public  WebElement getWebElement(By locator , int timeOut){
        try {
            FluentWait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NotFoundException.class);

            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator);
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
            return null;
        }
    }

    public List<WebElement> getWebElements(By locator , int timeOut){
        try {
            FluentWait wait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NotFoundException.class);

            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElements(locator);
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
            return null;
        }
    }

    public  void selectByVisibleText(By locator , String option , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element =  driver.findElement(locator);
            Select select = new Select(element);
            select.selectByVisibleText(option);
        }catch(Exception e) {softAssert.assertTrue(false , e.getMessage());
        }
    }


    public  String geTextWhenVisible(By locator ,  int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).getText();
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
            return null;
        }
    }

    public  boolean elementExistsNoReporting(By locator ,  int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public void waitUntilNoOfTabsIsN(WebDriver driver , int N , int timeOut){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.numberOfWindowsToBe(N));
        }catch(Exception e) {
            softAssert.assertTrue(false , e.getMessage());
        }
    }
    
    
}
