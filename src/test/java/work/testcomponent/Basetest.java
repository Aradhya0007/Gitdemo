package work.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import work.projectobject.Landingpage;

public class Basetest {
	public WebDriver driver;
	public Landingpage check;
	public  WebDriver initializedriver() throws IOException {
		
        
        
        // how to get to which browser we have to call
        // we use property class that help to get global properties from file with extension .properties
        Properties prop=new Properties();
        //covert file path into input string load method take input string as argument
        FileInputStream fis =new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\mavenwork\\src\\main\\java\\work\\resources\\GlobalData.properties");
        //it will read your file global data
        prop.load(fis);
        //now by method we access global resource from it
        String result=prop.getProperty("browser");
        
        if(result.equalsIgnoreCase("Chrome")) {
        	driver = new ChromeDriver();
        }
        if(result.equalsIgnoreCase("firefox")) {
        	driver=new FirefoxDriver();
        }
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        return driver;

	}
	
	public String screenshot (String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reprts//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reprts//"+testcasename+".png";
	}
	


    public List<HashMap<String, String>> mapping() throws IOException {
        // Convert the JSON file to a string
        String json = FileUtils.readFileToString(
            new File("C:\\Users\\hp\\eclipse-workspace\\mavenwork\\src\\test\\java\\file\\data\\productinfo.json"), 
            StandardCharsets.UTF_8
        );

        // Map the JSON string into HashMaps and store them in a List
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
            json, 
            new TypeReference<List<HashMap<String, String>>>() {}
        );

        return data;
    }

	@BeforeMethod(alwaysRun = true)
	public Landingpage launchaplication() throws IOException {
		this.driver=initializedriver();
		check=new Landingpage(driver);
		check.Goto();
		return check;
	}

}
