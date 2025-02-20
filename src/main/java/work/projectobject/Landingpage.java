package work.projectobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import work.abstructclass.Abstarctclass;

public class Landingpage extends Abstarctclass {
	 
	WebDriver driver;
	//elements of class or that webpage only
	public Landingpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//this will find all element in webpage that i have link using driver
		PageFactory.initElements(driver, this);
	}

	//WEB ELEMENT
	
	//pagefactory
	//this will work same as above in run time
	//WebElement useremail= driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement useremail;
	//WebElement password= driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement password;
	//WebElement loginbutton= driver.findElement(By.id("login"));
	@FindBy(id="login")
	WebElement log;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	//.ng-tns-c4-7.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	//ACTION MEATHOD
	By cs=By.cssSelector("[class*='flyInOut']");
	
	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String error() {
		waitforElementinvisible(cs);
		return errormsg.getText();
		
	}
	public void loginapplication(String email,String pass) {
		useremail.sendKeys(email);
		password.sendKeys(pass);
		log.click();
	}



	
	

}
