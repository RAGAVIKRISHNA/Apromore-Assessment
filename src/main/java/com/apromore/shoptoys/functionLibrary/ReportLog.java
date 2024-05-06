package com.apromore.shoptoys.functionLibrary;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

public class ReportLog {
	   WebDriver driver;
	public static final ExtentReports extentReports = new ExtentReports();
	public ReportLog(WebDriver driver) {
		this.driver=driver;
	}

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");

		spark.config().setReportName("Extent Report for Jupiter Toys Automation");
		extentReports.attachReporter(spark);
		extentReports.setSystemInfo("Blog Name", "Test Automation");
		extentReports.setSystemInfo("Author", "Ragavi Muthukrishnan");
		return extentReports;
	}

}
