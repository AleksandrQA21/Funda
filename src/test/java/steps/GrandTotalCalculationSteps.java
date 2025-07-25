package steps;

import com.microsoft.playwright.Page;
import config.BrowserManager;
import config.TestConfig;
import config.TestDataManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ClientLoginPage;
import pages.AdminDashboardPage;
import pages.UserHeaderPage;

import static utils.ScreenshotUtils.takeScreenshot;

/**
 * Step definitions for Grand Total calculation feature
 */
@Epic("GrandTotalCalculation")
@Feature("GrandTotalCalculationFeature")
public class GrandTotalCalculationSteps {

    private Page page;
    private ClientLoginPage clientLoginPage;
    private UserHeaderPage userHeaderPage;

    // Page lifecycle managed by TestHooks no need to use @Before\After annotation in steps class.

    /**
     * Initialize page objects using current page from BrowserManager
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

    @Given("I am logged in as Admin on the Admin Dashboard")
    @Step("Loggin as Admin to the Admin Dashboard")
    public void iAmLoggedAsAdminOnTheAdminDashboard() {
        initializePageObjects();
        String adminLoginUrl = TestDataManager.URLs.getAdminLoginUrl();
        page.navigate(adminLoginUrl);
        clientLoginPage.waitForPageToLoad();
        String validAdminEmail = TestDataManager.UserCredentials.getAdminValidEmail();
        String validAdminPassword = TestDataManager.UserCredentials.getAdminValidPassword();
        clientLoginPage.login(validAdminEmail,validAdminPassword);
        //Wait Admin Dashboard page loaded
        page.waitForURL("**/admin", new Page.WaitForURLOptions().setTimeout(TestDataManager.TestConfiguration.getElementTimeout()));
    }
}
