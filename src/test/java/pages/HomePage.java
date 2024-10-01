package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class HomePage extends BasePage {
    final String locatorsFileName = "homeLocators";
    final String homeScreenNotOpenedErrMsg = "Home Screen Not Opened Correctly";
    final String restaurantTitleErrMsg = "Restaurant Title InCorrect";
    final int maxScrollTrials = 10;

    public HomePage(AndroidDriver driver){
        super(driver);
    }

    public void setAddressManually(String address) throws IOException, ParseException {
        clickElement(AppiumBy.id(locators(locatorsFileName).get("allowLocationWhileUseAppId").toString()));
        clickElement(AppiumBy.id(locators(locatorsFileName).get("selectLocationId").toString()));

        clickElement(AppiumBy.id(locators(locatorsFileName).get("searchLocationFieldId").toString()));
        enterText(AppiumBy.id(locators(locatorsFileName).get("searchLocationInputId").toString()), address);
        clickElement(AppiumBy.xpath(locators(locatorsFileName).get("firstAddressOptionXpath").toString()));

        clickElement(AppiumBy.id(locators(locatorsFileName).get("confirmLocationId").toString()));
        clickElement(AppiumBy.xpath(locators(locatorsFileName).get("saveContinueXpath").toString()));
    }

    public void searchRestaurantByTitle(String restaurantTitle) throws IOException, ParseException {
        clickElement(AppiumBy.id(locators(locatorsFileName).get("searchForRestaurantId").toString()));
        enterText(AppiumBy.id(locators(locatorsFileName).get("searchForRestaurantId").toString()), restaurantTitle);
        pressEnter();
    }

    public void selectRestaurant(String restaurantTitle) throws IOException, ParseException {
        waitUntilElementDisplayed(AppiumBy.id(locators(locatorsFileName).get("searchRestaurantContainerId").toString()));
        scrollDownToElement(restaurantTitle, maxScrollTrials);
    }

    public void verifyHomeIconDisplayed() throws IOException, ParseException {

        boolean homeIconDisplayed = checkElementVisible(AppiumBy.id(locators(locatorsFileName).get("homeBottomIconId").toString()));
        Assert.assertTrue(homeScreenNotOpenedErrMsg, homeIconDisplayed);
    }

    public void verifyRestaurantScreenOpened(String expectedRestaurantTitle) throws IOException, ParseException {
        String actualRestaurantTitle = getText(AppiumBy.id(locators(locatorsFileName).get("restaurantTitleId").toString()));
        Assert.assertEquals(restaurantTitleErrMsg,expectedRestaurantTitle, actualRestaurantTitle);
    }
}
