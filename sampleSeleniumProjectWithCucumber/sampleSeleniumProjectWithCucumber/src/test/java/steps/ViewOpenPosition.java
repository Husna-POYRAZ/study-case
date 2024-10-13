package steps;

import base.BaseStep;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertFalse;

public class ViewOpenPosition extends BaseStep {
    protected static final String OPEN_POSITIONS_NAVBAR_XPATH = "//*[@id='offcanvasNavbar']/div[2]/ul/li[3]/a";
    protected static final String VIEW_ALL_POSITIONS_BUTTON_XPATH = "//*[@id='fullpage']/section[4]/div/div[2]/div/div[3]/a";
    protected static final String POSITION_LIST_XPATH = "//*[@id=\"filterOpenPositions\"]/div";

    @Given("^the user opens the Baykar Tech homepage$")
    public void navigateToHomePage() {
        getUrl(BASE_URL);
    }

    @When("^the user clicks on the open positions link in the navbar$")
    public void navigateToCareer() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Click on the Open Positions link in the navbar
        WebElement openPositionsLink = driver.findElement(By.xpath(OPEN_POSITIONS_NAVBAR_XPATH));
        wait.until(ExpectedConditions.elementToBeClickable(openPositionsLink)).click();
    }

    @And("^the user clicks the button to view all open positions$")
    public void navigateToOpenPosition() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Click the button to view all positions
        WebElement viewAllPositionsButton = driver.findElement(By.xpath(VIEW_ALL_POSITIONS_BUTTON_XPATH));
        wait.until(ExpectedConditions.elementToBeClickable(viewAllPositionsButton)).click();
    }

    @And("^the user scrolls down the page$")
    public void scrollDownPage() {
        scroll();
    }

    @Then("^the user should see the list of open positions$")
    public void verifyOpenPositionsList() {
        List<WebElement> positionList = driver.findElements(By.xpath(POSITION_LIST_XPATH));
        assertFalse(positionList.isEmpty(), "The list of open positions should not be empty.");
    }

    @After
    public void tearDown() {
        driverQuit();
    }
}
