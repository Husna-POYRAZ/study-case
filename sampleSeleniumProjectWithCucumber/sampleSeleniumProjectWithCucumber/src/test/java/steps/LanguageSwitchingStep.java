package steps;

import base.BaseStep;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class LanguageSwitchingStep extends BaseStep {
    private static final String LANGUAGE_XPATH = "//*[@id=\"offcanvasNavbar\"]/div[2]/ul/div/li[2]/a";

    private static final Map<String, String> SUPPORTED_LANGUAGES = new HashMap<>();

    static {
        SUPPORTED_LANGUAGES.put("English", "From the Roots");
        SUPPORTED_LANGUAGES.put("Turkish", "Köklerden");
        // Add more languages as needed
    }

    @Given("^the user is on the Baykar Tech website$")
    public void navigateHomePage() {
        getUrl(BASE_URL);
    }

    @When("^the user switches the language to \"([^\"]*)\"$")
    public void switchLanguage(String language) {
        if (!SUPPORTED_LANGUAGES.containsKey(language)) {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }

        WebElement languageSwitch = driver.findElement(By.xpath(LANGUAGE_XPATH));
        languageSwitch.click();
    }

    @Then("^the website should display the correct content in \"([^\"]*)\"$")
    public void verifyLanguage(String language) throws InterruptedException {
        Thread.sleep(5000);
        String expectedText = SUPPORTED_LANGUAGES.get(language);
        WebElement contentElement = driver.findElement(By.id("pageTitle"));
        assertEquals(expectedText, contentElement.getText());
    }

    @After
    public void tearDown() {
        driverQuit();
    }
}
