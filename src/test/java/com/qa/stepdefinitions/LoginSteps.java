package com.qa.stepdefinitions;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class LoginSteps {

    @Given("I enter username as {string}")
    public void iEnterUsernameAs(String username) {
        new LoginPage().enterUserName(username);
    }

    @Given("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        new LoginPage().enterPassword(password);
    }

    @When("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();
    }

    @Then("login should fail with an error {string}")
    public void loginShouldFailWithAnError(String errorMsg) {
        Assert.assertEquals(new LoginPage().getErrorText(), errorMsg);
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String labelTitle) {
        Assert.assertEquals(new ProductsPage().getTitle(), labelTitle);
    }

    @Then("I logout")
    public void iLogout() {
        new LoginPage().logout();
    }
}
