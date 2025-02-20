package work.projectobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import work.abstructclass.Abstarctclass;

public class Orderingproduct extends Abstarctclass {
	WebDriver driver;
	public Orderingproduct(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//element 
	
	@FindBy(xpath="//div[@class='cartSection']//h3")
	 List<WebElement> ProductName;
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkout;
	
	//Action method
	public void NumberOfProduct(String name) {
		boolean check=ProductName.stream().anyMatch(l->l.getText().equalsIgnoreCase(name));
		 Assert.assertTrue(check);
		 checkouts();
	}
	// we can also add add to cart to abstarctclass becouse it will show in scren for all pages 
	public void checkouts() {
		checkout.click();
	}
	

}
