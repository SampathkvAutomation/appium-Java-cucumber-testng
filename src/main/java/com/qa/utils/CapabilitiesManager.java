package com.qa.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManager {

    TestUtils testUtils = new TestUtils();

    public DesiredCapabilities getCaps() throws IOException {
        MyGlobalParams myGlobalParams = new MyGlobalParams();
        String url = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources";
        Properties props = new PropertyManager().getProperties();
        try{
            testUtils.logger().info("Getting Capabilities...");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", myGlobalParams.getPlatformName());
            caps.setCapability("appium:udid", myGlobalParams.getUdid());
            caps.setCapability("appium:deviceName", myGlobalParams.getDeviceName());

            switch(myGlobalParams.getPlatformName()){
                case "Android":
                    caps.setCapability("appium:automationName", props.getProperty("androidAutomationName"));
                    caps.setCapability("appium:appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appium:appActivity", props.getProperty("androidAppActivity"));
                    //caps.setCapability("appium:url", url + props.getProperty("androidAppLocation"));
                    caps.setCapability("appium:systemPort", myGlobalParams.getSystemPort());
                    caps.setCapability("appium:chromeDriverPort", myGlobalParams.getChromeDriverPort());
                    break;
                case "iOS":
                    caps.setCapability("appium:maxTypingFrequency", 30);
                    caps.setCapability("appium:automationName", props.getProperty("iOSAutomationName"));
                    caps.setCapability("appium:bundleId", props.getProperty("iOSBundleId"));
                    //caps.setCapability("appium:appLocation", props.getProperty("iOSAppLocation"));
                    //caps.setCapability("appium:url", url + props.getProperty("iOSAppLocation"));
                    caps.setCapability("appium:wdaLocalPort", myGlobalParams.getWdaLocalPort());
                    caps.setCapability("appium:webkitDebugProxyPort", myGlobalParams.getWebkitDebugProxyPort());
                    break;
            }
            return caps;
        } catch(Exception e) {
            e.printStackTrace();
            testUtils.logger().fatal("Failed to load capabilities");
            throw e;
        }
    }

}
