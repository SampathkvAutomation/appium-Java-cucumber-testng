package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;

public class DriverManager {

    private static ThreadLocal <AppiumDriver> driver = new ThreadLocal<>();

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void initDriver() throws Exception{
        MyGlobalParams myGlobalParams = new MyGlobalParams();
        AppiumDriver driver = null;
        TestUtils testUtils = new TestUtils();

        if(driver == null) {
            try {
                testUtils.logger().info("Initializing Appium Driver...");
                switch(myGlobalParams.getPlatformName()) {
                    case "Android":
                        driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                        break;
                    case "iOS":
                        driver = new IOSDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                        break;
                }
                if(driver == null) {
                    throw new Exception("Driver is null, ABORT!");
                }
                testUtils.logger().info("Driver initialized!");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                testUtils.logger().fatal("Driver init failure. ABORT" + e.toString());
                throw e;
            }
        }
    }
}
