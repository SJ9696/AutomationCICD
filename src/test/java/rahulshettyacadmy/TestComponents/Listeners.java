package rahulshettyacadmy.TestComponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacadmy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	// Lect 177  and 178  section 22
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	// Thread locaol method use to create unique id for each test  Letc. 179
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName()); 
		// note- becuse we want method name not class name
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override // Screenshot lect 178 Sec 22
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		try {
			// Interview NOte-I can not use the test method to get driver becuse fileds are assoced in class level not method level
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromBase64String(filePath, result.getMethod().getMethodName());

		// Screenshot lect 178 Sec 22

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		//After crate report you must to wright this statment otherwise report not created 
		extent.flush(); 

	}

}
