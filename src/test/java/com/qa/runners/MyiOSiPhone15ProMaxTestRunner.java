package com.qa.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "html:target/iPhone15ProMax/cucumber/report.html", "summary"},
        features = {"src/test/resources"},
        glue = {"com.qa.stepdefinitions"},
        dryRun = false, // WORKS FOR CREATING STEP-DEFs IF NOT CREATED
        monochrome = true, // WORKS FOR LOGS
        tags = "@login or @products"
)
public class MyiOSiPhone15ProMaxTestRunner extends BaseRunner {
}
