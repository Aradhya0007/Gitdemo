package work.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkqandw {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        // Set a fixed viewport size to ensure consistency
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().deleteAllCookies(); // Clear all cookies to ensure a clean session
        String URL = "https://www.jeevanjali.com/dharm";
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get(URL);

            // Explicitly wait for all images to be present
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("img")));

            // Scroll down to trigger lazy loading if necessary
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(3000); // Allow time for lazy-loaded images to load

            // Collect all image elements
            List<WebElement> images = driver.findElements(By.tagName("img"));

            // Iterate through each image element
            for (WebElement img : images) {
                String imageUrl = img.getAttribute("src");
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    validateImageUrl(imageUrl, img, driver);
                } else {
                    System.out.println("Invalid URL: Image src is empty or null");
                }
            }
        } finally {
            driver.quit(); // Close the browser at the end
        }
    }

    private static void validateImageUrl(String imageUrl, WebElement img, WebDriver driver) {
        try {
            // Ensure the 'src' attribute is fully loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.attributeToBeNotEmpty(img, "src"));

            // Debugging output
            System.out.println("Checking Image URL: " + imageUrl);

            // Check for malformed URLs with multiple '?'
            if (imageUrl.indexOf('?') != imageUrl.lastIndexOf('?')) {
                System.out.println("Invalid URL: " + imageUrl + " - More than one '?' found");
                return;
            }

            URL url = new URL(imageUrl);
            String query = url.getQuery();

            // Check if query parameters exist
            if (query != null) {
                Map<String, String> params = parseQuery(query);

                // Debugging output
                System.out.println("Parsed query parameters: " + params);

                // Validate 'q=50'
                if ("65".equals(params.get("q"))) {
                    // Validate 'w' parameter
                    if (params.containsKey("w")) {
                        int urlWidth = Integer.parseInt(params.get("w"));

                        // Retrieve the natural width of the image using JavaScript
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        Long naturalWidth = (Long) js.executeScript("return arguments[0].naturalWidth", img);

                        if (naturalWidth != null) {
                            int imageWidth = naturalWidth.intValue();

                            // Allowable difference in width
                            int maxDifference = 30;
                            if (Math.abs(urlWidth - imageWidth) <= maxDifference) {
                                System.out.println("Valid URL: " + imageUrl + " - Width matches within allowable difference. URL Width: " + urlWidth + ", Image Width: " + imageWidth);
                            } else {
                                System.out.println("Invalid URL: " + imageUrl + " - Width difference exceeds " + maxDifference + ". URL Width: " + urlWidth + ", Image Width: " + imageWidth);
                            }
                        } else {
                            System.out.println("Warning: Unable to fetch natural width for the image");
                        }
                    } else {
                        System.out.println("Invalid URL: " + imageUrl + " - 'w' parameter missing");
                    }
                } else {
                    System.out.println("Invalid URL: " + imageUrl + " - 'q=50' missing");
                }
            } else {
                System.out.println("Invalid URL: " + imageUrl + " - No query parameters found");
            }
        } catch (Exception e) {
            System.out.println("Error parsing URL: " + imageUrl + " - " + e.getMessage());
        }
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                // Only retain 'q' and 'w' parameters
                if ("q".equals(key) || "w".equals(key)) {
                    params.put(key, value);
                }
            }
        }
        return params;
    }
}
