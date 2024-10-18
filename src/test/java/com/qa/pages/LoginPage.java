package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.MyGlobalParams;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginPage extends BasePage {

    private AppiumDriver driver;

    public LoginPage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }
        @AndroidFindBy(accessibility = "test-Username")
        @iOSXCUITFindBy(accessibility = "test-Username")
        private WebElement usernameInput;
        @AndroidFindBy(accessibility = "test-Password")
        @iOSXCUITFindBy(accessibility = "test-Password")
        private WebElement passwordInput;
        @AndroidFindBy(accessibility = "test-LOGIN")
        @iOSXCUITFindBy(accessibility = "test-LOGIN")
        private WebElement loginButton;
        @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Username')]")
        @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'test-Error message'`]")
        private WebElement errorLabel;

        public LoginPage enterUserName(String username) {
            clearText(usernameInput);
            sendKeys(usernameInput, username);
            return this;
        }

        public LoginPage enterPassword(String password) {
            clearText(passwordInput);
            sendKeys(passwordInput, password);
            return this;
        }

        public ProductsPage pressLoginBtn() {
            click(loginButton);
            return new ProductsPage();
        }

        public ProductsPage login(String username, String password) {
            enterUserName(username);
            enterPassword(password);
            return pressLoginBtn();
        }

    public ProductsPage logout() {
        Map<String, Object> params = new HashMap<>();
        params.put("x", 43);
        params.put("y", 66);

        if(Objects.equals(new MyGlobalParams().getPlatformName(), "iOS"))
            driver.executeScript("mobile: tap", params);
        else if(Objects.equals(new MyGlobalParams().getPlatformName(), "Android"))
            new ProductsPage().pressSettings();
        new SettingsPage().pressLogoutBtn();

        return new ProductsPage();
    }

        public String getErrorText() {
            return getText(errorLabel);
        }
}
