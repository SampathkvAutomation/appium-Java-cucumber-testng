package com.qa.pages;

import com.qa.utils.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends BasePage {

    private AppiumDriver driver;

    public MenuPage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(this.driver, this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(accessibility = "test-Menu")
    public WebElement settingsBtn;

    public void pressSettings() {
        click(settingsBtn);
    }
}
