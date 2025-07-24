package hooks;

import com.microsoft.playwright.Page;
import config.BrowserManager;
import config.TestConfig;
import config.TestDataManager;
import io.cucumber.java.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static utils.ScreenshotUtils.takeScreenshotOnFailure;

/**
 * Global test hooks for test suite initialization and cleanup
 */
public class TestHooks {
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
    
    /**
     * Executed once before all scenarios
     * Initializes test configuration and validates setup
     */
    @BeforeAll
    public static void globalSetUp() {
        logger.info("=== Starting Test Suite ===");
        
        try {
            // Validate test configuration
            logger.info("Validating test configuration...");
            TestConfig.validateConfiguration();
            logger.info("Test configuration validation successful");
            
            // Initialize test data manager
            logger.info("Initializing test data manager...");
            TestDataManager.initialize();
            logger.info("Test data manager initialized successfully");
            
            // Initialize browser (will be reused across scenarios)
            logger.info("Initializing browser...");
            BrowserManager.initializeBrowser();
            logger.info("Browser initialization completed: {}", BrowserManager.getBrowserInfo());
            logger.info("=== Test Suite Setup Completed Successfully ===");
            
        } catch (Exception e) {
            logger.error("Failed to initialize test suite: {}", e.getMessage(), e);
            throw new RuntimeException("Test suite initialization failed", e);
        }
    }

    /**
     * Setup before each scenario
     * Creates fresh page for scenario isolation
     */
    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        logger.info("▶ Starting scenario: {}", scenario.getName());
        
        try {
            // Create fresh page for each scenario to ensure isolation
            BrowserManager.createNewPage();
            logger.debug("New page created successfully for scenario: {}", scenario.getName());
            
        } catch (Exception e) {
            logger.error("Failed to create new page for scenario '{}': {}", 
                        scenario.getName(), e.getMessage(), e);
            throw new RuntimeException("Scenario setup failed: " + e.getMessage(), e);
        }
    }

    /**
     * Cleanup after each scenario
     * Takes screenshot on failure and cleans up resources
     */
    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        try {
            // Take screenshot on failure
            if (scenario.isFailed()) {
                takeFailureScreenshot(scenario);
            }
            
            // Clean up page resources for current thread
            logger.debug("Cleaning up resources for scenario: {}", scenario.getName());
            BrowserManager.closePage();
            
        } catch (Exception e) {
            logger.error("Error during scenario cleanup for '{}': {}", 
                        scenario.getName(), e.getMessage(), e);
        }
        
        logger.info("▶ Completed scenario: {} - Status: {}", 
                   scenario.getName(), scenario.getStatus());
    }

    /**
     * Takes screenshot when scenario fails
     * Fixed method signature to match ScreenshotUtils
     */
    private void takeFailureScreenshot(Scenario scenario) {
        try {
            Page currentPage = BrowserManager.getCurrentPage();
            if (currentPage != null && !currentPage.isClosed()) {
                logger.warn("Test scenario '{}' failed - taking screenshot", scenario.getName());
                // Используем правильную сигнатуру метода - передаем Page, не Scenario
                takeScreenshotOnFailure(currentPage);
            } else {
                logger.warn("Cannot take screenshot for failed scenario '{}' - page is null or closed", 
                           scenario.getName());
            }
        } catch (Exception e) {
            logger.error("Failed to capture failure screenshot for scenario '{}': {}",
                        scenario.getName(), e.getMessage(), e);
        }
    }

    /**
     * Executed once after all scenarios
     * Performs cleanup of global resources
     */
    @AfterAll
    public static void globalTearDown() {
        logger.info("=== Test Suite Cleanup Started ===");
        try {
            performBrowserCleanup();
            logger.info("=== Test Suite Cleanup Completed ===");
        } catch (Exception e) {
            logger.error("Error during test suite cleanup: {}", e.getMessage(), e);
        }
    }

    /**
     * Safely cleanup browser resources
     */
    private static void performBrowserCleanup() {
        logger.info("Closing browser and Playwright resources...");
        try {
            BrowserManager.shutdown();
            logger.info("Browser cleanup completed successfully");
        } catch (Exception e) {
            logger.error("Error during browser cleanup: {}", e.getMessage(), e);
            throw new RuntimeException("Browser cleanup failed", e);
        }
    }
}