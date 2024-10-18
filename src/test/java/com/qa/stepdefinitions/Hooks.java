package com.qa.stepdefinitions;

import com.qa.utils.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;

import java.io.IOException;

public class Hooks {

    //@Before
    public void before() {
        TestUtils.logger().info("Start recording...");
        new VideoManager().startRecording();
    }

    //@After
    public void after(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

            TestUtils.logger().error(scenario.getName() + " failed...");
        }

        new VideoManager().stopRecording(scenario.getName());
        TestUtils.logger().info("Stopped recording.");
    }


}
