package work.projectobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import work.abstructclass.Abstarctclass;

public class Ordervalidcheck extends Abstarctclass {

	public Ordervalidcheck(WebDriver driver)  {
		super(driver);
		PageFactory.initElements(driver, this);
	}
		
		
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement order;
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']//td[2]")
	List<WebElement> names;
	
	//action
	public void click() {
		order.click();
	}
	public boolean validate(String productname) {
		Boolean match=names.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		return match;
	}

	

}
