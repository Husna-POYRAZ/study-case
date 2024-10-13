package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseStep {
    protected WebDriver driver;
    public static final String BASE_URL = "https://kariyer.baykartech.com";

    public BaseStep() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 10000);");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
