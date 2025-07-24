package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for handling screenshots in tests
 */
public class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    
    /**
     * Take screenshot and attach to Allure report
     * @param page Playwright page instance
     * @param name Screenshot name for the report
     */
    public static void takeScreenshot(Page page, String name) {
        if (page == null) {
            logger.warn("Cannot take screenshot - page is null");
            return;
        }
        
        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setType(com.microsoft.playwright.options.ScreenshotType.PNG));
                
            Allure.addAttachment(name, "image/png", 
                new ByteArrayInputStream(screenshot), "png");
                
            logger.debug("Screenshot taken and attached to report: {}", name);
            
        } catch (Exception e) {
            logger.error("Failed to take screenshot '{}': {}", name, e.getMessage());
        }
    }
    
    /**
     * Take screenshot on test failure
     * @param page Playwright page instance
     */
    public static void takeScreenshotOnFailure(Page page) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String screenshotName = "Test Failure - " + timestamp;
        takeScreenshot(page, screenshotName);
    }
    
    /**
     * Take screenshot of specific element
     * @param page Playwright page instance
     * @param selector CSS selector of the element
     * @param name Screenshot name
     */
    public static void takeElementScreenshot(Page page, String selector, String name) {
        if (page == null) {
            logger.warn("Cannot take element screenshot - page is null");
            return;
        }
        
        try {
            byte[] screenshot = page.locator(selector).screenshot(
                new com.microsoft.playwright.Locator.ScreenshotOptions()
                    .setType(com.microsoft.playwright.options.ScreenshotType.PNG));
                    
            Allure.addAttachment(name + " (Element)", "image/png",
                new ByteArrayInputStream(screenshot), "png");
                
            logger.debug("Element screenshot taken: {} for selector: {}", name, selector);
            
        } catch (Exception e) {
            logger.error("Failed to take element screenshot '{}' for selector '{}': {}", 
                name, selector, e.getMessage());
        }
    }
    
    /**
     * Take screenshot with timestamp
     * @param page Playwright page instance
     * @param baseName Base name for the screenshot
     */
    public static void takeTimestampedScreenshot(Page page, String baseName) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String screenshotName = baseName + " - " + timestamp;
        takeScreenshot(page, screenshotName);
    }
}
