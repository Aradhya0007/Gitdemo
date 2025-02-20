package work.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extent {
	static ExtentReports extent;  
      
      // Use @BeforeAll for JUnit 5 initialization
    public static ExtentReports extent()  {
        // Set the report path dynamically based on the current working directory
        String path = System.getProperty("user.dir") + "//report//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        
        // Customize report settings
        reporter.config().setReportName("Web Automation");
        reporter.config().setDocumentTitle("Test Report");

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Aradhya Joshi");
        return extent;
    }
}
