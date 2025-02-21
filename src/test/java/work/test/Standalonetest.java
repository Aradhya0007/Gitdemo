package work.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Standalonetest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String name ="BANARSI SAREE";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.findElement(By.id("userEmail")).sendKeys("aradhyajoshi587@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ajajaj27");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> element=driver.findElements(By.cssSelector(".mb-3"));
        
        WebElement product=element.stream().filter(e->e.findElement(By.tagName("b")).getText().equals (name)).findFirst().orElse(null);
        Thread.sleep(2000);
        WebElement products=product.findElement(By.cssSelector("button:last-child"));
        js.executeScript("arguments[0].click();", products);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        List<WebElement> list=driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
        boolean check=list.stream().anyMatch(l->l.getText().equalsIgnoreCase(name));
        Assert.assertTrue(check);
        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ng-star-inserted')][2]")));
        driver.findElement(By.xpath("//button[contains(@class,'ng-star-inserted')][2] ")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'action__submit')]")));
       // JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement elements=driver.findElement(By.xpath("//a[contains(@class,'action__submit')]"));
        js.executeScript("arguments[0].click();", elements);
        //driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();  
        String msg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals("THANKYOU FOR THE ORDER.", msg);
        System.out.println("gittt");
        System.out.println("h");
        System.out.println("j");
        System.out.println("d");
        System.out.println("e");


        

	}

}