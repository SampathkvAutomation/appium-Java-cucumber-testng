package com.qa.pages;

import com.qa.utils.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends BasePage {

    private AppiumDriver driver;


    public SettingsPage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'test-LOGOUT'`]")
    private WebElement logoutBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Close']/android.widget.ImageView")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'test-Close'")
    private WebElement closeMenuBtn;

    public LoginPage pressLogoutBtn() {
        click(logoutBtn);
        return new LoginPage();
    }
}
