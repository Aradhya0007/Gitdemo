package work.abstructclass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstarctclass {
	WebDriver driver;
	public Abstarctclass(WebDriver driver) {
		this.driver=driver;
	}
	public void waitforElement(By findelemnt) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		//loactor type is by
		wait.until(ExpectedConditions.visibilityOfElementLocated(findelemnt));
	}
	public void waitforElementinvisible(By findelemnt) {
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
		//loactor type is by
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findelemnt));
	}
	
	public void javascriptclick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}
