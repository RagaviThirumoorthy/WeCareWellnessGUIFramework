package com.wecare.generic.listenerUtility;

/**
 * @author RAGAVI
 */

 import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.wecare.generic.BaseTest.BaseClass;

public class ListenerUtility implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		//Extent spark configuration
		Date date = new Date();
		String time = date.toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./Reports/"+time+".html");
		spark.config().setDocumentTitle("WECARE Test Suite Results");
		spark.config().setReportName("WeCARE Report");
		spark.config().setTheme(Theme.DARK);
		
		//Add Env information, Extent test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");				
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		test.log(Status.INFO, methodName+" started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.INFO, methodName+" success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.INFO, methodName+" failed");
		
		String time = new Date().toString().replace(' ', '_').replace(':', '_');
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver ;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("./Screenshots/"+time+".png");
		try {
			FileHandler.copy(src, trg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filePath, methodName+"\t"+time);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.INFO, methodName+" skipped");
	}
	
	

}
