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
import static org.testng.Assert.assertTrue;
import static steps.ViewOpenPosition.OPEN_POSITIONS_NAVBAR_XPATH;
import static steps.ViewOpenPosition.VIEW_ALL_POSITIONS_BUTTON_XPATH;

public class FilterPositionStep extends BaseStep {
    private static final String POSITION_FILTER_XPATH = "//*[@id=\"searchInput\"]"; // Filter input
    private static final String POSITION_SEARCH_XPATH = "//*[@id='search']"; // Search input
    private static final String POSITION_LIST_XPATH = "//*[@id='filterOpenPositions']/div"; // Position list
    private static final String NO_POSITIONS_MESSAGE_XPATH = "//*[@id=\"filterOpenPositions\"]/div/div/div/h3"; // No positions message

    @Given("^the user is on the Baykar Tech career page$")
    public void navigateToCareerPage() {
        getUrl(BASE_URL);
    }

    @When("^the user navigates to the open positions page$")
    public void navigateToOpenPositionsPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement openPositionsLink = driver.findElement(By.xpath(OPEN_POSITIONS_NAVBAR_XPATH));
        wait.until(ExpectedConditions.elementToBeClickable(openPositionsLink)).click();
        WebElement viewAllPositionsButton = driver.findElement(By.xpath(VIEW_ALL_POSITIONS_BUTTON_XPATH));
        wait.until(ExpectedConditions.elementToBeClickable(viewAllPositionsButton)).click();
    }

    @And("^the user filters positions by unit \"([^\"]*)\"$")
    public void filterPositionsByUnit(String unit) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement filterElement = driver.findElement(By.xpath(POSITION_FILTER_XPATH));
        filterElement.sendKeys(unit);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POSITION_LIST_XPATH)));
    }

    @Then("^the user should see positions matching the unit criteria$")
    public void verifyFilteredPositions() {
        List<WebElement> positionList = driver.findElements(By.xpath(POSITION_LIST_XPATH));
        assertFalse(positionList.isEmpty(), "The list of filtered positions should not be empty.");
    }

    @And("^the user searches for the position \"([^\"]*)\"$")
    public void searchForPosition(String position) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchInput = driver.findElement(By.xpath(POSITION_SEARCH_XPATH));
        searchInput.clear();
        searchInput.sendKeys(position);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POSITION_LIST_XPATH)));
    }

    @Then("^the user should see positions matching the search criteria$")
    public void verifySearchedPositions() {
        List<WebElement> positionList = driver.findElements(By.xpath(POSITION_LIST_XPATH));
        assertFalse(positionList.isEmpty(), "The list of positions should not be empty.");
    }

    @Then("^the user should see a message indicating no positions found$")
    public void verifyNoPositionsFound() {
        WebElement noPositionsMessage = driver.findElement(By.xpath(NO_POSITIONS_MESSAGE_XPATH));
        //assertTrue(noPositionsMessage.isDisplayed(), "No Open Positions Found.");
        assertTrue(noPositionsMessage.isDisplayed(), "Açık Pozisyon Bulunamadı");
    }

    @After
    public void tearDown() {
        driverQuit();
    }
}
