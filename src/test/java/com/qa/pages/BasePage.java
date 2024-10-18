package com.qa.pages;


import com.qa.utils.DriverManager;
import com.qa.utils.MyGlobalParams;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;


public class BasePage {

    private AppiumDriver driver;

    MyGlobalParams params = new MyGlobalParams();

    public BasePage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public String getText(WebElement element) {
        waitForVisibility(element);
        switch(params.getPlatformName()) {
            case "Android":
                return getAttribute(element, "text");
            case "iOS":
                return getAttribute(element, "label");
        }
        return null;
    }

    public void clearText(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public WebElement scrollToElement() {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().description(\"test-Image Container\"))" + // LOCATOR 1 de donde inicia
                        ".scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"));")); // LOCATOR 2 hasta donde va scroll
    }

    public void scrollToElementIOS() {
        RemoteWebElement element = (RemoteWebElement)driver.findElement(By.className("XCUIElementTypeScrollView")); // LOCATOR hasta donde va scroll
        String elementId = element.getId();
        HashMap<String,String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementId);
        //scrollObject.put("direction", "down");
        //scrollObject.put("predicateString", "label == 'ADD TO CART'");
        scrollObject.put("name", "© 2024 Sauce Labs. All Rights Reserved.");
        //scrollObject.put("toVisible", "");
        driver.executeScript("mobile:scroll", scrollObject);
    }
}
