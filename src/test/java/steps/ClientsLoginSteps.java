package steps;

import com.microsoft.playwright.Page;
import config.BrowserManager;
import config.TestConfig;
import config.TestDataManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import pages.ClientLoginPage;
import pages.UserHeaderPage;


import java.io.ByteArrayInputStream;

import static org.testng.Assert.*;
import static utils.ScreenshotUtils.takeScreenshot;

/**
 * Step definitions for Client's login feature
 */
@Epic("Authentication")
@Feature("ClientsLogin")
public class ClientsLoginSteps {
    
    private Page page;
    private ClientLoginPage clientLoginPage;
    private UserHeaderPage userHeaderPage;

    // Removed @Before and @After annotations
    // Page lifecycle is now managed by TestHooks only
    public void setUp() {
        // This method can be called manually if needed
        // Page is available through BrowserManager.getCurrentPage()
        page = BrowserManager.getCurrentPage();
        if (page == null) {
            throw new RuntimeException("No page available - TestHooks should have created one");
        }
        clientLoginPage = new ClientLoginPage(page);
        userHeaderPage = new UserHeaderPage(page);
    }

    public void tearDown() {
        // Page cleanup is handled by TestHooks only
        // No action needed here
    }

    /**
     * Initialize page objects using current page from BrowserManager
     * Called automatically when step definitions are accessed
     */
    private void initializePageObjects() {
        if (page == null) {
            page = BrowserManager.getCurrentPage();
            if (page == null) {
                throw new RuntimeException("No page available - browser not initialized");
            }
            clientLoginPage = new ClientLoginPage(page);
            userHeaderPage = new UserHeaderPage(page);
        }
    }

    @Given("I am on the Client's login page")
    @Step("Navigate to the Client's login page")
    public void iAmOnTheClientsLoginPage() {
        initializePageObjects();
        clientLoginPage.navigateToClientLoginPage();
    }

    @When("I enter valid email {string}")
    @Step("Enter valid email: {email}")
    public void iEnterValidEmail(String email) {
        initializePageObjects();
        clientLoginPage.enterEmail(email);
    }
    
    @When("I enter valid credentials")
    @Step("Enter valid login credentials")
    public void iEnterValidCredentials() {
        initializePageObjects();
        clientLoginPage.enterValidCredentials();
    }

    @When("I enter valid password {string}")
    @Step("Enter valid password")
    public void iEnterValidPassword(String password) {
        initializePageObjects();
        clientLoginPage.enterPassword(password);
    }

    @When("I click on the Sign in button")
    @Step("Click on the Sign in button")
    public void iClickOnTheSignInButton() {
        initializePageObjects();
        clientLoginPage.clickSignInButton();
    }

    @Then("I redirect to the User Account page")
    @Step("Verify redirect to User Account page")
    public void iRedirectToTheUserAccountPage() {
        initializePageObjects();
        page.waitForURL("**/activity/list", new Page.WaitForURLOptions().setTimeout(TestConfig.getElementTimeout()));
        assertTrue(userHeaderPage.getHeaderAccountButtonLocator().isVisible(),"Account button should be visible after successful login");
    }

//    @When("I enter invalid email {string}")
//    @Step("Enter invalid email")
//    public void iEnterInvalidEmail(String email) {
//        clientLoginPage.enterInvalidEmail();
//    }
    
    @When("I enter invalid email")
    @Step("Enter invalid email")
    public void iEnterInvalidEmail() {
        initializePageObjects();
        clientLoginPage.enterInvalidEmail();
    }
    
    @When("I enter valid email")
    @Step("Enter valid email")
    public void iEnterValidEmail() {
        initializePageObjects();
        clientLoginPage.enterValidEmail();
    }

//    @When("I enter invalid password {string}")
//    @Step("Enter invalid password")
//    public void iEnterInvalidPassword(String password) {
//        clientLoginPage.enterPassword(password);
//    }
    
    @When("I enter invalid password")
    @Step("Enter invalid password")
    public void iEnterInvalidPassword() {
        initializePageObjects();
        clientLoginPage.enterInvalidPassword() ;
    }
    
    @When("I enter valid password")
    @Step("Enter valid password")
    public void iEnterValidPassword() {
        initializePageObjects();
        clientLoginPage.enterValidPassword();
    }

    @Then("I should see email error message {string}")
    @Step("Verify email error message is displayed")
    public void iShouldSeeEmailErrorMessage(String expectedMessage) {
        initializePageObjects();
        assertTrue(clientLoginPage.isEmailErrorPresent());
        assertEquals(expectedMessage, clientLoginPage.getEmailErrorText());
    }
    
    @Then("I should see invalid email error message")
    @Step("Verify invalid email error message")
    public void iShouldSeeInvalidEmailErrorMessage() {
        initializePageObjects();
        assertTrue(clientLoginPage.isEmailErrorMessageDisplayed());
    }

    @Then("I should see password error message {string}")
    @Step("Verify password error message is displayed")
    public void iShouldSeePasswordErrorMessage(String expectedMessage) {
        initializePageObjects();
        assertTrue(clientLoginPage.isPasswordErrorPresent());
        assertEquals(expectedMessage, clientLoginPage.getPasswordErrorText());
    }
    
    @Then("I should see password mismatch error message")
    @Step("Verify password mismatch error message")
    public void iShouldSeePasswordMismatchErrorMessage() {
        initializePageObjects();
        assertTrue(clientLoginPage.isPasswordErrorMessageDisplayed());
    }

    @Then("I should see empty email error message {string}")
    @Step("Verify empty email error message is displayed")
    public void iShouldSeeEmptyEmailErrorMessage(String expectedMessage) {
        initializePageObjects();
        assertTrue(clientLoginPage.isEmptyEmailErrorMessageDisplayed());
        assertEquals(expectedMessage, clientLoginPage.getEmptyEmailErrorText());
    }
    
    @Then("I should see required email error message")
    @Step("Verify required email error message")
    public void iShouldSeeRequiredEmailErrorMessage() {
        initializePageObjects();
        assertTrue(clientLoginPage.isEmptyEmailErrorMessageDisplayed());
    }

    @Then("I should see empty password error message {string}")
    @Step("Verify empty password error message is displayed")
    public void iShouldSeeEmptyPasswordErrorMessage(String expectedMessage) {
        initializePageObjects();
        assertTrue(clientLoginPage.isEmptyPasswordErrorMessageDisplayed());
        assertEquals(expectedMessage, clientLoginPage.getEmptyPasswordErrorText());
    }
    
    @Then("I should see required password error message")
    @Step("Verify required password error message")
    public void iShouldSeeRequiredPasswordErrorMessage() {
        initializePageObjects();
        assertTrue(clientLoginPage.isEmptyPasswordErrorMessageDisplayed());
    }
    
//    @Then("I should be redirected to the homepage")
//    @Step("Verify successful redirect to homepage")
//    public void iShouldBeRedirectedToTheHomepage() {
//        iRedirectToTheHomepage();
//    }
    
    @Then("I should see the user dashboard")
    @Step("Verify user dashboard is displayed")
    public void iShouldSeeTheUserDashboard() {
        initializePageObjects();
        page.waitForURL("**/", new Page.WaitForURLOptions().setTimeout(TestConfig.getElementTimeout()));
    }

}