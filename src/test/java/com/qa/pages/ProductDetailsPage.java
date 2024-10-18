package com.qa.pages;

import com.qa.utils.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends MenuPage {

    private AppiumDriver driver;

    public ProductDetailsPage() {
        this.driver = new DriverManager().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @AndroidFindBy(accessibility = "test-ADD TO CART")
    @iOSXCUITFindBy(accessibility = "test-ADD TO CART")
    private WebElement addToCartButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Backpack']")
    @iOSXCUITFindBy(accessibility = "Sauce Labs Backpack")
    private WebElement SLBNameLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.']")
    @iOSXCUITFindBy(accessibility = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.")
    private WebElement SLBDescrLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Price']")
    @iOSXCUITFindBy(accessibility = "test-Price")
    private WebElement SLBPriceLabel;
    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    @iOSXCUITFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement backToProductsBtn;

    public String getSLBTitle() {
        return getText(SLBNameLabel);
    }

    public String getSLBDescription() {
        return getText(SLBDescrLabel);
    }

    public String getSLBPrice() {
        return getText(SLBPriceLabel);
    }

    public ProductsPage pressBackToProductsBtn() {
        click(backToProductsBtn);
        return new ProductsPage();
    }

    public String scrollToAddToCartBtn() {
        return getText(scrollToElement());
    }

    public void scrollToAddToCartBtnIOS() {
        scrollToElementIOS();
    }

    public Boolean isAddToCartBtnDisplayed() {
        return addToCartButton.isDisplayed();
    }

}
