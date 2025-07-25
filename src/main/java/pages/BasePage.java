package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import config.BrowserManager;
import config.TestDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {
    protected final Page page;
    protected static final int DEFAULT_TIMEOUT = 5000;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

public BasePage(Page page) {
    if (page == null || page.isClosed()) {
        throw new IllegalStateException("No valid page available. Make sure createNewPage() is called in @Before.");
    }
    this.page = page;
}

    public void waitForElement(String selector) {
        logger.debug("Waiting for element: {}", selector);
        try {
            page.locator(selector).waitFor();
            logger.debug("Element found: {}", selector);
        } catch (Exception e) {
            logger.error("Failed to wait for element: {}", selector, e);
            throw new RuntimeException("Element not found: " + selector, e);
        }
    }

    public void waitForElement(String selector, int timeout) {
        logger.debug("Waiting for element: {} with timeout: {}ms", selector, timeout);
        try {
            page.locator(selector).waitFor(new Locator.WaitForOptions().setTimeout(timeout));
            logger.debug("Element found: {}", selector);
        } catch (Exception e) {
            logger.error("Failed to wait for element: {} within {}ms", selector, timeout, e);
            throw new RuntimeException("Element not found within timeout: " + selector, e);
        }
    }

    protected boolean isElementVisible(String selector) {
        int timeoutMillis = TestDataManager.TestConfiguration.getElementTimeout();
        logger.debug("Checking visibility of element: {}", selector);
        try {
            Locator locator = page.locator(selector);
            locator.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMillis));
            boolean isVisible =
                    locator.isVisible(new Locator.IsVisibleOptions().setTimeout(timeoutMillis));
            logger.debug("Element {} visibility: {}", selector, isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.error("Failed to check visibility of element: {}", selector, e);
            return false;
        }
    }

    protected Locator getElement(String selector) {
        logger.debug("Getting element: {}", selector);
        try {
            return page.locator(selector);
        } catch (Exception e) {
            logger.error("Failed to get element: {}", selector, e);
            throw new RuntimeException("Unable to get element: " + selector, e);
        }
    }

    protected void clickElement(String selector) {
        logger.debug("Clicking element: {}", selector);
        try {
            waitForElement(selector);
            page.locator(selector).click();
            logger.debug("Successfully clicked element: {}", selector);
        } catch (Exception e) {
            logger.error("Failed to click element: {}", selector, e);
            throw new RuntimeException("Unable to click element: " + selector, e);
        }
    }

    protected void fillElement(String selector, String text) {
        logger.debug("Filling element: {} with text: {}", selector, text);
        try {
            waitForElement(selector);
            page.locator(selector).fill(text);
            logger.debug("Successfully filled element: {}", selector);
        } catch (Exception e) {
            logger.error("Failed to fill element: {}", selector, e);
            throw new RuntimeException("Unable to fill element: " + selector, e);
        }
    }

    protected void fillElementSecure(String selector, String text) {
        logger.debug("Filling element: {} with secure text (masked)", selector);
        try {
            waitForElement(selector);
            page.locator(selector).fill(text);
            logger.debug("Successfully filled element with secure text: {}", selector);
        } catch (Exception e) {
            logger.error("Failed to fill element with secure text: {}", selector, e);
            throw new RuntimeException("Unable to fill element with secure text: " + selector, e);
        }
    }

    protected String getElementText(String selector) {
        logger.debug("Getting text from element: {}", selector);
        try {
            waitForElement(selector);
            String text = page.locator(selector).textContent();
            String result = text != null ? text.trim() : "";
            logger.debug("Element {} text: '{}'", selector, result);
            return result;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", selector, e);
            throw new RuntimeException("Unable to get text from element: " + selector, e);
        }
    }

    protected void waitForElementToBeVisible(String selector) {
        logger.debug("Waiting for element to be visible: {}", selector);
        try {
            page.locator(selector).waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(DEFAULT_TIMEOUT));
            logger.debug("Element is now visible: {}", selector);
        } catch (Exception e) {
            logger.error("Element did not become visible: {}", selector, e);
            throw new RuntimeException("Element not visible: " + selector, e);
        }
    }

    protected void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        try {
            page.navigate(url);
            logger.debug("Successfully navigated to: {}", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw new RuntimeException("Unable to navigate to URL: " + url, e);
        }
    }

    public void waitForPageToLoad() {
        logger.debug("Waiting for page to load");
        try {
            page.waitForLoadState();
            logger.debug("Page loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to wait for page load", e);
            throw new RuntimeException("Page load timeout", e);
        }
    }
}