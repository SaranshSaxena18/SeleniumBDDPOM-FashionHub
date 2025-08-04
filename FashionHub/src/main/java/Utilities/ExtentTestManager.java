package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//ExtentSparkReporter - responsible for the UI of the report
//ExtentReports - responsible for the common info on the report
//ExtentTest - responsible for the test case entries in the report and update the status of the test method
public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();//threadLocal will make extentTest as thread safe i.e. each thread have its own extenTest object
    private static ExtentReports extent = getExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent.html");//responsible for the UI of the report
            spark.config().setDocumentTitle("FashionHub Automation Report");
            spark.config().setReportName("Automation Report");
            spark.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            ConfigReader configReader = new ConfigReader("extent.properties");
            configReader.getProperty("Application.Name");
            extent.setSystemInfo("Computer Name",configReader.getProperty("computer.name"));
            extent.setSystemInfo("Environment",configReader.getProperty("environment"));
            extent.setSystemInfo("Tester Name",configReader.getProperty("qa.name"));
            extent.setSystemInfo("OS",configReader.getProperty("os"));
        }
        return extent;
    }

    public static synchronized ExtentTest createTest(String testName) {//synchronized - is to make it thread safe
        ExtentTest test = extent.createTest(testName);//creating test case entries
        extentTest.set(test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();//getTest returns the ExtentTest object specific to the current thread.
    }

    public static synchronized void flush() {
        extent.flush();//writes all the logged information from Extent Reports to the output report file.
    }
}
//every thing is static as methods can be called directly without creating the instance of the class which still being thread safe due to ThreadLocal
