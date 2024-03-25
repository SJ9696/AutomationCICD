package rahulshettyacadmy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	

	public static ExtentReports getReportObject()
	{
		//Extent sparkreport  // sect 22 Lect 176 and 177
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("WebAutomation Result Sagar"); // report ka name set hota he
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sagar Jadahav");// report me tester ka name ayega
		return extent;
		
	}
}
