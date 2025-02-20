package work.projectobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import work.abstructclass.Abstarctclass;
//import work.abstructclass.by;

public class Productcatalouge extends Abstarctclass {
	WebDriver driver;
	public Productcatalouge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//this will find all element in web page that i have link using driver
		PageFactory.initElements(driver, this);
	}
	//WEB ELEMENT
	

	@FindBy(css=".mb-3")
	List<WebElement> product;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement click;
	
	By producwait=By.cssSelector(".mb-3");
	
	//ACTION MEATHOD
	public List<WebElement> getproduct() {
		waitforElement(producwait);
		return product;
		
	}
	
	public WebElement getproductname(String productname) {
        WebElement product=getproduct().stream().filter(e->e.findElement(By.tagName("b")).getText().equals (productname)).findFirst().orElse(null);
        return product;
	}
	By tost=By.id("toast-container");
	By animation=By.cssSelector(".ng-animating");
	public void addtocart(String productname) {
		WebElement javaclick=getproductname(productname).findElement(By.cssSelector("button:last-child"));
		javascriptclick(javaclick);
		waitforElement(tost);
		waitforElementinvisible(animation);
		click.click();
	}

}
