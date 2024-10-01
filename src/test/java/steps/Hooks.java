package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverInitialization;

import java.io.IOException;

public class Hooks extends DriverInitialization {
    private AndroidDriver driver;

    @Before
    public void setupDriver() throws IOException {
        System.out.println("Before .......");
        driver = getDriver();
        if(driver == null || driver.toString().contains("(null)")){
            setAppiumDriver();
            driver = getDriver();
        }
    }

    @AfterStep
    public void takeScreenShot(Scenario scenario)
    {
        if ((scenario.isFailed())) {
            System.out.println("Take screenshot.....");
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
    @After
    public void tearDown() {
        driver.quit();
    }


}
