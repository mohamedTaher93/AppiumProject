package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helpers;

import java.time.Duration;
import java.util.List;

public class BasePage extends Helpers {

//    public locators
    private AndroidDriver driver;
    private WebDriverWait wait;
    private WebDriverWait shortWait;


    public BasePage(AndroidDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getConfigData().getProperty("defaultWaitTimeOut"))));
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getConfigData().getProperty("shortWaitTimeOut"))));
    }

    public void clickElement(By by){
        waitUntilElementClickable(by);
        driver.findElement(by).click();
    }

    public void enterText(By by, String text){
        waitUntilElementClickable(by);
        driver.findElement(by).sendKeys(text);
    }

    public void waitUntilElementDisplayed(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }
    public Boolean checkElementVisible(By by){
        try {
            driver.findElement(by).isDisplayed();
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public void waitUntilElementClickable(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitUntilElementInvisible(By by){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public String getText(By by){
        waitUntilElementDisplayed(by);
        return driver.findElement(by).getText();
    }

    public void pressEnter(){
        (driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    public void scrollDownToElement(String restaurantTitle, int maxScrolls)
    {
        int scrollTrials = 1;
        Dimension dimensions = driver.manage().window().getSize();
        int startX = dimensions.getWidth() / 2;
        int startY = dimensions.getHeight() / 2;
        int endY = (int) (dimensions.getHeight() * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(400), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        while(scrollTrials < maxScrolls)
        {
            driver.perform(List.of(scroll));
            try {
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text = '" + restaurantTitle + "']")));
                break;
            }catch (Exception e)
            {
                //try again
            }
            System.out.println(scrollTrials);
            scrollTrials++;
        }
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text = '" + restaurantTitle + "']")).click();
        if(!driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text = '" + restaurantTitle + "']")).isEmpty())
        {
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text = '" + restaurantTitle + "']")).click();
        }
    }

    public void placeAppToBackground()
    {
        driver.runAppInBackground(Duration.ofSeconds(Integer.parseInt(getConfigData().getProperty("shortWaitTimeOut"))));
    }
    public void activateApp()
    {
        driver.activateApp(getConfigData().getProperty("appPackage"));
    }

}
