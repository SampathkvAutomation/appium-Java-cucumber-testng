package com.qa.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "html:target/Pixel7/cucumber/report.html", "summary"},
        features = {"src/test/resources"},
        glue = {"com.qa.stepdefinitions"},
        dryRun = false, // WORKS FOR CREATING STEP-DEFs IF NOT CREATED
        monochrome = true, // WORKS FOR LOGS
        tags = "@login or @products"
)
public class MyAndroidPixel7TestRunner extends BaseRunner {
}
