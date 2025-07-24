package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import config.TestDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for client's login page
 */
public class ClientLoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ClientLoginPage.class);
    
    // URLs
    private static final String CLIENT_LOGIN_URL = "https://dev-nd.fundaactive.com/login";
    private static final String ADMIN_LOGIN_URL = "https://dev-nd.fundaactive.com/admin/login";

    // Selectors
    private static final String EMAIL_INPUT = "input[id='email']";
    private static final String PASSWORD_INPUT = "input[id='password']";
    private static final String SIGN_IN_BUTTON = "button[id='#login']";
    private static final String REMEMBER_ME_CHECKBOX = "input[type='checkbox']";
    private static final String FORGOT_PASSWORD_LINK = "a[href*='forgot-password']";
    private static final String CREATE_ACCOUNT_LINK = "a[href*='register']";
    private static final String GOOGLE_ACCOUNT_LINK = "a[href*='google']";
    private static final String HELP_CENTER_LINK = "a[href*='help.funda.group']";
    private static final String SHOW_HIDE_PASSWORD_BUTTON = "i[id='show_password_eye']";
    private static final String PASSWORD_ERROR = "//span[text()='Password does not match']";
    private static final String EMPTY_PASSWORD_ERROR = "//span[text()='The password field is required.']";
    private static final String LOADING_INDICATOR = "//p[text()='Loading...']";
    private static final String EMAIL_ERROR = "//span[text()='The selected email is invalid.']";
    private static final String EMPTY_EMAIL_ERROR = "//span[text()='The email field is required.']";

    public ClientLoginPage(Page page) {
        super(page);
    }

    // Navigation methods
    public void navigateToClientLoginPage() {
        logger.info("Navigating to client login page");
        navigateToUrl(CLIENT_LOGIN_URL);
    }

    // Input methods
    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        fillElement(EMAIL_INPUT, email);
    }

    public void enterPassword(String password) {
        logger.info("Entering password (masked for security)");
        fillElementSecure(PASSWORD_INPUT, password);
    }

    // Click methods
    public void clickSignInButton() {
        logger.info("Clicking Sign In button");
        clickElement(SIGN_IN_BUTTON);
    }

    public void clickRememberMeCheckbox() {
        logger.info("Clicking Remember Me checkbox");
        clickElement(REMEMBER_ME_CHECKBOX);
    }

    public void clickForgotPasswordLink() {
        logger.info("Clicking Forgot Password link");
        clickElement(FORGOT_PASSWORD_LINK);
    }

    public void clickCreateAccountLink() {
        logger.info("Clicking Create Account link");
        clickElement(CREATE_ACCOUNT_LINK);
    }

    public void clickGoogleLoginButton() {
        logger.info("Clicking Google Login button");
        clickElement(GOOGLE_ACCOUNT_LINK);
    }

    public void clickHelpCenterLink() {
        logger.info("Clicking Help Center link");
        clickElement(HELP_CENTER_LINK);
    }

    public void clickShowHidePasswordButton() {
        logger.info("Clicking Show/Hide Password button");
        clickElement(SHOW_HIDE_PASSWORD_BUTTON);
    }

    // Visibility check methods
    public boolean isPasswordErrorPresent() {
        logger.debug("Checking if password error is present");
        return isElementVisible(PASSWORD_ERROR);
    }

    public boolean isLoadingIndicatorPresent() {
        logger.debug("Checking if loading indicator is present");
        return isElementVisible(LOADING_INDICATOR);
    }

    public boolean isEmailErrorPresent() {
        logger.debug("Checking if email error is present");
        return isElementVisible(EMAIL_ERROR);
    }

    public boolean isPasswordErrorMessageDisplayed() {
        logger.debug("Checking if password error message is displayed");
        return isElementVisible(PASSWORD_ERROR);
    }

    public boolean isEmptyPasswordErrorMessageDisplayed() {
        logger.debug("Checking if empty password error message is displayed");
        return isElementVisible(EMPTY_PASSWORD_ERROR);
    }

    public boolean isLoadingIndicatorVisible() {
        logger.debug("Checking if loading indicator is visible");
        return isElementVisible(LOADING_INDICATOR);
    }

    public boolean isEmailErrorMessageDisplayed() {
        logger.debug("Checking if email error message is displayed");
        return isElementVisible(EMAIL_ERROR);
    }

    public boolean isEmptyEmailErrorMessageDisplayed() {
        logger.debug("Checking if empty email error message is displayed");
        return isElementVisible(EMPTY_EMAIL_ERROR);
    }

    // Locator getter methods
    public Locator getEmailInput() {
        logger.debug("Getting email input locator");
        return getElement(EMAIL_INPUT);
    }

    public Locator getPasswordInput() {
        logger.debug("Getting password input locator");
        return getElement(PASSWORD_INPUT);
    }

    public Locator getSignInButton() {
        logger.debug("Getting Sign In button locator");
        return getElement(SIGN_IN_BUTTON);
    }

    public Locator getRememberMeCheckbox() {
        logger.debug("Getting Remember Me checkbox locator");
        return getElement(REMEMBER_ME_CHECKBOX);
    }

    public Locator getForgotPasswordLink() {
        logger.debug("Getting Forgot Password link locator");
        return getElement(FORGOT_PASSWORD_LINK);
    }

    public Locator getCreateAccountLink() {
        logger.debug("Getting Create Account link locator");
        return getElement(CREATE_ACCOUNT_LINK);
    }

    public Locator getGoogleLoginButton() {
        logger.debug("Getting Google Login button locator");
        return getElement(GOOGLE_ACCOUNT_LINK);
    }

    public Locator getHelpCenterLink() {
        logger.debug("Getting Help Center link locator");
        return getElement(HELP_CENTER_LINK);
    }

    public Locator getShowHidePasswordButton() {
        logger.debug("Getting Show/Hide Password button locator");
        return getElement(SHOW_HIDE_PASSWORD_BUTTON);
    }

    public Locator getPasswordError() {
        logger.debug("Getting password error locator");
        return getElement(PASSWORD_ERROR);
    }

    public Locator getLoadingIndicator() {
        logger.debug("Getting loading indicator locator");
        return getElement(LOADING_INDICATOR);
    }

    public Locator getEmailError() {
        logger.debug("Getting email error locator");
        return getElement(EMAIL_ERROR);
    }

    // Text getter methods
    public String getPasswordErrorText() {
        logger.debug("Getting password error text");
        waitForElementToBeVisible(PASSWORD_ERROR);
        return getElementText(PASSWORD_ERROR);
    }

    public String getEmptyPasswordErrorText() {
        logger.debug("Getting empty password error text");
        waitForElementToBeVisible(EMPTY_PASSWORD_ERROR);
        return getElementText(EMPTY_PASSWORD_ERROR);
    }

    public String getEmailErrorText() {
        logger.debug("Getting email error text");
        waitForElementToBeVisible(EMAIL_ERROR);
        return getElementText(EMAIL_ERROR);
    }

    public String getEmptyEmailErrorText() {
        logger.debug("Getting empty email error text");
        waitForElementToBeVisible(EMPTY_EMAIL_ERROR);
        return getElementText(EMPTY_EMAIL_ERROR);
    }

    // Wait methods
    public void waitForPasswordErrorMessage() {
        logger.debug("Waiting for password error message to appear");
        waitForElementToBeVisible(PASSWORD_ERROR);
    }

    public void waitForEmptyPasswordErrorMessage() {
        logger.debug("Waiting for empty password error message to appear");
        waitForElementToBeVisible(EMPTY_PASSWORD_ERROR);
    }

    public void waitForEmailErrorMessage() {
        logger.debug("Waiting for email error message to appear");
        waitForElementToBeVisible(EMAIL_ERROR);
    }

    public void waitForEmptyEmailErrorMessage() {
        logger.debug("Waiting for empty email error message to appear");
        waitForElementToBeVisible(EMPTY_EMAIL_ERROR);
    }

    // Complex action methods
    public void login(String email, String password) {
        logger.info("Performing login with email: {}", email);
        try {
            enterEmail(email);
            enterPassword(password);
            clickSignInButton();
            logger.info("Login process completed");
        } catch (Exception e) {
            logger.error("Login failed for email: {}", email, e);
            throw new RuntimeException("Login process failed", e);
        }
    }

    public void enterValidCredentials() {
        logger.info("Performing entering valid client credentials");
        try {
            String validEmail = TestDataManager.UserCredentials.getClientValidEmail();
            String validPassword = TestDataManager.UserCredentials.getClientValidPassword();
            enterEmail(validEmail);
            enterPassword(validPassword);
            logger.info("Enter valid credentials completed");
        } catch (Exception e) {
            logger.error("Enter valid credentials failed", e);
            throw new RuntimeException("Enter valid credentials failed", e);
        }
    }


    public void loginWithRememberMe(String email, String password) {
        logger.info("Performing login with Remember Me for email: {}", email);
        try {
            enterEmail(email);
            enterPassword(password);
            clickRememberMeCheckbox();
            clickSignInButton();
            logger.info("Login with Remember Me process completed");
        } catch (Exception e) {
            logger.error("Login with Remember Me failed for email: {}", email, e);
            throw new RuntimeException("Login with Remember Me failed", e);
        }
    }

    public void enterValidPassword() {
        logger.info("Entering valid client password");
        String validPassword = TestDataManager.UserCredentials.getClientValidPassword();
        enterPassword(validPassword);
    }

    public void enterInvalidPassword() {
        logger.info("Entering invalid client password");
        String invalidPassword = TestDataManager.UserCredentials.getInvalidPassword();
        enterPassword(invalidPassword);
    }

    public void enterValidEmail() {
        logger.info("Entering valid client email");
        String validEmail = TestDataManager.UserCredentials.getClientValidEmail();
        enterEmail(validEmail);
    }
    public void enterInvalidEmail() {
        logger.info("Entering invalid client email");
        String invalidEmail = TestDataManager.UserCredentials.getInvalidEmail();
        enterEmail(invalidEmail);
    }

}