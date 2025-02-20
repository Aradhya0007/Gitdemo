package work.projectobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import work.abstructclass.Abstarctclass;

public class PersonalDetail extends Abstarctclass {
	WebDriver driver;
	public PersonalDetail(WebDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	}
	//Element
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ng-star-inserted')][2]")
	WebElement select;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement orderdone;
	
	//Action method
	public void SelectCountry(String countrys) {
		country.sendKeys(countrys);
		select.click();
		PlaceOrder();
	}
	
	public void PlaceOrder() {
		javascriptclick(orderdone);
		 String msg=driver.findElement(By.cssSelector(".hero-primary")).getText();
	     Assert.assertEquals("THANKYOU FOR THE ORDER.", msg);
	}

}
