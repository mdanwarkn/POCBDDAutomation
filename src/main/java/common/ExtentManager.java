package common;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.DriverUtil;

public class ExtentManager {
	
	
	static String innerText = "<div style=\"text-align: center;\">\r\n"
			+ "      <div style=\"margin: 0 auto; background: #808080; color: #fff;\">%s</div>\r\n"
			+ "    </div>";

	
	public static void logInfo(String msg) {
		ExtentCucumberAdapter.getCurrentStep().info(msg);
	}
	
	public static void printHeader(String msg) {
		System.out.println(ExtentCucumberAdapter.getCurrentStep());
		ExtentCucumberAdapter.getCurrentStep().info(String.format(innerText, msg));
	}
	
	public static void printNewLine() {
		ExtentCucumberAdapter.getCurrentStep().info("<br>");
	}
	
	public static void logInfo(String msg , String json , CodeLanguage codeLanguage) {
		logInfo(msg);
		ExtentCucumberAdapter.getCurrentStep().info(MarkupHelper.createCodeBlock(json , codeLanguage));
	}
	
	public static void logPass(String msg) {
		ExtentCucumberAdapter.getCurrentStep().pass(MarkupHelper.createLabel(msg, ExtentColor.GREEN));
		ExtentManager.printNewLine();
	}
	
	public static void logPassAndTakeScreenshot(DriverUtil util , String msg) {
		ExtentCucumberAdapter.getCurrentStep().pass(MarkupHelper.createLabel(msg, ExtentColor.GREEN));
		ExtentCucumberAdapter.getCurrentStep().addScreenCaptureFromPath(util.takeScreenShotAsFile());
		ExtentManager.printNewLine();
	}
	
	public static void logFail(String msg) {
		ExtentCucumberAdapter.getCurrentStep().fail(MarkupHelper.createLabel(msg, ExtentColor.RED));
		ExtentManager.printNewLine();
	}
	
	public static void logFailAndTakeScreenshot(DriverUtil util , String msg) {
		ExtentCucumberAdapter.getCurrentStep().fail(MarkupHelper.createLabel(msg, ExtentColor.RED));
		ExtentCucumberAdapter.getCurrentStep().addScreenCaptureFromPath(util.takeScreenShotAsFile());
		ExtentCucumberAdapter.getCurrentStep().addScreenCaptureFromBase64String(util.takeScreenShotInBase64());
		ExtentManager.printNewLine();
	}
	
}
