package steps;

import base.BaseStep;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NavbarNavigationStep extends BaseStep {
    private static final String NAVBAR_CLASS = "nav-link";

    @Given("^the user is on the Baykar Tech home page$")
    public void navigateToHomePage() {
        getUrl(BASE_URL);
    }

    @When("^the user retrieves all navbar elements$")
    public void retrieveAllNavbarElements() {
        List<WebElement> navbarElements = driver.findElements(By.className(NAVBAR_CLASS));

        if (navbarElements.isEmpty()) {
            System.out.println("No navbar elements found.");
        } else {
            System.out.println("Found " + navbarElements.size() + " navbar elements.");

            // Iterate over the found elements and print their text
            for (int i = 0; i < navbarElements.size(); i++) {
                WebElement navbarElement = navbarElements.get(i);
                System.out.println("Navbar element " + (i + 1) + ": " + navbarElement.getText());
            }
        }
    }

    @Then("^the user clicks on each navbar element$")
    public void clickOnEachNavbarElement() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Retrieve all elements with the specified class name again
        List<WebElement> navbarElements = driver.findElements(By.className(NAVBAR_CLASS));

        if (navbarElements.isEmpty()) {
            System.out.println("No navbar elements found to click.");
        } else {
            for (int i = 0; i < navbarElements.size(); i++) {
                WebElement navbarElement = navbarElements.get(i);
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(navbarElement)).click();
                    System.out.println("Clicked on navbar element: " + navbarElement.getText());

                    Thread.sleep(1000);

                    navbarElements = driver.findElements(By.className(NAVBAR_CLASS));
                } catch (Exception e) {
                    System.out.println("Error clicking on navbar element: " + e.getMessage());
                }
            }
        }
    }

    @And("^the corresponding page should load without errors$")
    public void correspondingPageShouldLoadWithoutErrors() {
        // Check that the current page loaded correctly
        String currentUrl = getDriver().getCurrentUrl();
        if (currentUrl.contains("#") || currentUrl.contains("error")) {
            throw new RuntimeException("Page did not load correctly: " + currentUrl);
        }
        System.out.println("Page loaded successfully: " + currentUrl);
    }

    @And("^the user should return to the home page after each click$")
    public void the_user_should_return_to_the_home_page_after_each_click() {
        navigateToHomePage();
        String currentUrl = getDriver().getCurrentUrl();
        if (!currentUrl.equals("https://kariyer.baykartech.com/tr/")) {
            throw new RuntimeException("Not on the home page: " + currentUrl);
        }
        System.out.println("Returned to home page successfully.");
    }

    @And("^the browser should close$")
    public void browserShouldClose() {
        driverQuit();
    }
}
