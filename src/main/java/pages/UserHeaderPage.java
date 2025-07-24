package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserHeaderPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(UserHeaderPage.class);

    private static final String HEADER_ADMIN_PANEL_BUTTON = "//a[text()='Admin Panel']";
    private static final String HEADER_BOOK_NOW_BUTTON = "//a[text()='Book Now']";
    private static final String HEADER_PINS_BUTTON = "//a[text()='Pins']";
    private static final String HEADER_ACCOUNT_BUTTON = "//a[text()='Account']";
    private static final String HEADER_WALLET_BUTTON = "//a[text()='Wallet']";
    private static final String HEADER_HELP_BUTTON = "//a[text()='Help']";
    private static final String HEADER_LOGOUT_BUTTON = "//a[text()='Logout']";

    public UserHeaderPage(Page page) {
        super(page);
    }

    public Locator getHeaderAccountButtonLocator () {
        logger.debug("Getting Remember Me checkbox locator");
        return getElement(HEADER_ACCOUNT_BUTTON);
    }


    public void clickAccountButton() {
        logger.info("Clicking Account button");
        clickElement(HEADER_ACCOUNT_BUTTON);
    }
    
    public void clickWalletButton() {
        logger.info("Clicking Wallet button");
        clickElement(HEADER_WALLET_BUTTON);
    }
    
    public void clickHelpButton() {
        logger.info("Clicking Help button");
        clickElement(HEADER_HELP_BUTTON);
    }
    
    public void clickLogoutButton() {
        logger.info("Clicking Logout button");
        clickElement(HEADER_LOGOUT_BUTTON);
    }
    
    public void clickAdminPanelButton() {
        logger.info("Clicking Admin Panel button");
        clickElement(HEADER_ADMIN_PANEL_BUTTON);
    }
    
    public void clickBookNowButton() {
        logger.info("Clicking Book Now button");
        clickElement(HEADER_BOOK_NOW_BUTTON);
    }
    
    public void clickPinsButton() {
        logger.info("Clicking Pins button");
        clickElement(HEADER_PINS_BUTTON);
    }
}