package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends MenuPage {

    private AppiumDriver driver;

    public ProductsPage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'PRODUCTS'`]")
    private WebElement productsLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Item title' and @text='Sauce Labs Backpack']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'test-Item title'`][1]")
    private WebElement SLBNameLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Price' and @text='$29.99']")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'test-Price' AND label == '$29.99'")
    private WebElement SLBPriceLabel;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc='test-Item'])[1]/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"\uDB81\uDF41\"`][1]/XCUIElementTypeOther/XCUIElementTypeImage")
    private WebElement SLBProductImg;

    public ProductsPage clickOnSLBProduct() {
        click(SLBProductImg);
        return new ProductsPage();
    }

    public String getTitle() {
        return getText(productsLabel);
    }

    public String getSLBTitle() {
        return getText(SLBNameLabel);
    }

    public String getSLBPrice() {
        return getText(SLBPriceLabel);
    }
}
