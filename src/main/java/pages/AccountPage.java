package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(AccountPage.class);

    private static final String ACCOUNT_PERSONAL_INFORMATION_BUTTON = "//h6[text()='Personal Information']";
    private static final String ACCOUNT_CHILDREN_BUTTON = "//h6[text()='Children']";
    private static final String ACCOUNT_WALLET_BUTTON = "//h6[text()='Wallet']";
    private static final String ACCOUNT_YOUR_BOOKINGS_BUTTON = "//h6[text()='Your Bookings']";
    private static final String ACCOUNT_PINS_BUTTON = "//h6[text()='Pins']";
    private static final String ACCOUNT_LOGIN_AND_SECURITY_BUTTON = "//h6[text()='Login and Security']";
    private static final String ACCOUNT_SUBSCRIPTION_BUTTON = "//h6[text()='Subscription']";
    private static final String ACCOUNT_YOUR_ADDRESSES_BUTTON = "//h6[text()='Your Addresses']";
    private static final String ACCOUNT_CONTACT_US_BUTTON = "//h6[text()='Contact Us']";

    public AccountPage(Page page) {
        super(page);
    }
    public void clickPersonalInformationButton() {
        logger.info("Clicking Personal Information button");
        clickElement(ACCOUNT_PERSONAL_INFORMATION_BUTTON);
    }

    public void clickChildrenButton() {
        logger.info("Clicking Children button");
        clickElement(ACCOUNT_CHILDREN_BUTTON);
    }

    public void clickWalletButton() {
        logger.info("Clicking Wallet button");
        clickElement(ACCOUNT_WALLET_BUTTON);
    }

    public void clickYourBookingsButton() {
        logger.info("Clicking Your Bookings button");
        clickElement(ACCOUNT_YOUR_BOOKINGS_BUTTON);
    }

    public void clickPinsButton() {
        logger.info("Clicking Pins button");
        clickElement(ACCOUNT_PINS_BUTTON);
    }

    public void clickLoginAndSecurityButton() {
        logger.info("Clicking Login and Security button");
        clickElement(ACCOUNT_LOGIN_AND_SECURITY_BUTTON);
    }

    public void clickSubscriptionButton() {
        logger.info("Clicking Subscription button");
        clickElement(ACCOUNT_SUBSCRIPTION_BUTTON);
    }

    public void clickYourAddressesButton() {
        logger.info("Clicking Your Addresses button");
        clickElement(ACCOUNT_YOUR_ADDRESSES_BUTTON);
    }

    public void clickContactUsButton() {
        logger.info("Clicking Contact Us button");
        clickElement(ACCOUNT_CONTACT_US_BUTTON);
    }
}
