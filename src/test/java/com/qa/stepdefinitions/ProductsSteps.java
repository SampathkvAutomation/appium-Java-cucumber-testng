package com.qa.stepdefinitions;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductsSteps {

    @Given("I'm logged in")
    public void iAmLoggedIn() {
        new LoginPage().login("standard_user","secret_sauce");
    }

    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListed(String title, String price) {
        Boolean checkTitle = new ProductsPage().getSLBTitle().equalsIgnoreCase(title);
        Boolean checkPrice = new ProductsPage().getSLBPrice().equalsIgnoreCase(price);

        Assert.assertTrue(checkTitle & checkPrice);
    }

    @When("I click product title {string}")
    public void clickProduct(String titleName) {
        switch(titleName) {
            case "Sauce Labs Backpack":
                new ProductsPage().clickOnSLBProduct();
                break;
            case "":
                break;
        }
    }

    @Then("I should be on product details page with title {string}, price {string} and description {string}")
    public void loginShouldFailWithAnError(String title, String price, String description) {
        Boolean checkTitle = new ProductDetailsPage().getSLBTitle().equalsIgnoreCase(title);
        Boolean checkPrice = new ProductDetailsPage().getSLBPrice().equalsIgnoreCase(price);
        Boolean checkDescr = new ProductDetailsPage().getSLBDescription().equalsIgnoreCase(description);

        Assert.assertTrue(checkTitle & checkPrice & checkDescr);
    }
}
