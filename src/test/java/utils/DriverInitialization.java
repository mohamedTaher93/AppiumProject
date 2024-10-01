package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverInitialization extends Helpers {

    private static AndroidDriver driver;
    public void setAppiumDriver() throws IOException {
        driver = new AndroidDriver(new URL(getConfigData().getProperty("appiumServerUrl")),androidOptions());
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(getConfigData().getProperty("defaultWaitTimeOut")), TimeUnit.SECONDS);
    }

    private static UiAutomator2Options androidOptions(){

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(getConfigData().getProperty("deviceName"));
        options.setUdid(getConfigData().getProperty("udId"));
        options.setPlatformName(getConfigData().getProperty("platform"));
        options.setPlatformVersion(getConfigData().getProperty("platformVersion"));
        options.setAppPackage(getConfigData().getProperty("appPackage"));
        options.setAppActivity(getConfigData().getProperty("appActivity"));
        options.setNewCommandTimeout(Duration.ofSeconds(20));

        return options;
    }
    public AndroidDriver getDriver(){
        return driver;
    }
}
