package steps;

import io.cucumber.java.en.*;
import org.json.simple.parser.ParseException;
import pages.BasePage;
import pages.HomePage;
import utils.DriverInitialization;
import java.io.IOException;

public class HomeTestStepDefinitions extends DriverInitialization {
    HomePage homePage = new HomePage(getDriver());
    BasePage basePage = new BasePage(getDriver());


    @Given("I Set Delivery Location Manually {string}")
    public void setDeliveryAddressManually(String address) throws IOException, ParseException {
        homePage.setAddressManually(address);
    }

    @When("I Search For {string} Title")
    public void searchForRestaurant(String restaurantTitle) throws IOException, ParseException {
        homePage.searchRestaurantByTitle(restaurantTitle);
    }

    @When("I Set The App To Background")
    public void setAppToBackground() {
        basePage.placeAppToBackground();
    }

    @When("I Get Back The App From Background")
    public void getBackAppFromBackground() {
        basePage.activateApp();
    }

    @When("I Select {string} Title")
    public void selectRestaurant(String targetedRestaurant) throws IOException, ParseException, InterruptedException {
        homePage.selectRestaurant(targetedRestaurant);
    }

    @Then("Verify Home Screen Displayed")
    public void verifyHomeScreenDisplayed() throws IOException, ParseException {
        homePage.verifyHomeIconDisplayed();
        System.out.println("Navigate To Home Page");
    }
    @Then("Verify {string} Screen Opened Successfully")
    public void verifyTargetedRestaurantOpened(String restaurantTitle) throws IOException, ParseException {
        homePage.verifyRestaurantScreenOpened(restaurantTitle);
        System.out.println("Navigate To Home Page");
    }
}
