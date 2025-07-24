package config;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe singleton browser manager for Playwright automation
 * Manages browser lifecycle with proper resource cleanup
 */
public final class BrowserManager {
    private static final Logger log = LoggerFactory.getLogger(BrowserManager.class);

    // Singleton components
    private static volatile Playwright playwright;
    private static volatile Browser browser;
    private static final ReentrantLock initLock = new ReentrantLock();

    // Thread-local resources for parallel execution
    private static final ThreadLocal<BrowserContext> contexts = new ThreadLocal<>();
    private static final ThreadLocal<Page> pages = new ThreadLocal<>();


    private BrowserManager() {
        // Private constructor for singleton
    }

    /**
     * Gets current page for thread without creating new one
     * Returns null if no page exists for current thread
     */
    public static Page getCurrentPage() {
        Page page = pages.get();
        if (page == null || page.isClosed()) {
            log.warn("Page is null or closed. Returning null.");
            return null;
        }
        return page;
    }

    /**
     * Creates new page for current thread, closing previous if exists
     */
    public static Page createNewPage() {
        closeCurrentThreadResources();
        initializeBrowserIfNeeded();
        return createPageForCurrentThread();
    }

    /**
     * Closes page and context for current thread
     */
    public static void closePage() {
        closeCurrentThreadResources();
    }

    /**
     * Checks if current thread has valid page
     */
    public static boolean hasValidPage() {
        Page page = pages.get();
        return page != null && !page.isClosed();
    }

    /**
     * Checks if browser is initialized and connected
     */
    public static boolean isBrowserInitialized() {
        return playwright != null && browser != null && browser.isConnected();
    }

    /**
     * Gets browser info for logging/reporting
     */
    public static String getBrowserInfo() {
        return String.format("Browser: %s, Headless: %s",
                getBrowserTypeName(), ConfigReader.isBrowserHeadless());
    }

    /**
     * Shuts down all browser resources
     * Should be called once at test suite end
     */
    public static void shutdown() {
        log.info("Shutting down browser manager");
        
        // Close resources for current thread
        closeCurrentThreadResources();
        
        if (browser != null) {
            safeClose("browser", browser::close);
            browser = null;
        }
        
        if (playwright != null) {
            safeClose("playwright", playwright::close);
            playwright = null;
        }
        
        log.info("Browser manager shutdown completed");
    }

    // Private helper methods

    private static void initializeBrowserIfNeeded() {
        if (!isBrowserInitialized()) {
            initLock.lock();
            try {
                if (!isBrowserInitialized()) {
                    initializeBrowser();
                }
            } finally {
                initLock.unlock();
            }
        }
    }

    public static void initializeBrowser() {
        log.info("Initializing browser: {}", getBrowserTypeName());

        playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.isBrowserHeadless())
                .setTimeout(ConfigReader.getBrowserTimeout())
                .setSlowMo(100);

        browser = getBrowserType().launch(options);

        log.info("Browser initialized successfully: {}", getBrowserInfo());
    }

    private static Page createPageForCurrentThread() {
        try {
            BrowserContext context = browser.newContext(getContextOptions());
            Page page = context.newPage();
            page.setDefaultTimeout(ConfigReader.getBrowserTimeout());

            contexts.set(context);
            pages.set(page);

            log.debug("New page created for thread: {}", Thread.currentThread().getId());
            return page;

        } catch (Exception e) {
            log.error("Failed to create page for thread {}: {}",
                    Thread.currentThread().getId(), e.getMessage());
            throw new RuntimeException("Failed to create browser page", e);
        }
    }

    private static Browser.NewContextOptions getContextOptions() {
        return new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setLocale("en-US")
                .setTimezoneId("UTC")
                .setIgnoreHTTPSErrors(true);
    }

    private static void closeCurrentThreadResources() {
        BrowserContext context = contexts.get();

        if (context != null) {
            log.debug("Closing context for thread: {}", Thread.currentThread().getId());
            safeClose("context", context::close);
        }

        contexts.remove();
        pages.remove();
    }

    private static BrowserType getBrowserType() {
        if (playwright == null) {
            throw new IllegalStateException("Playwright not initialized");
        }

        String browserName = System.getProperty("browser.type", "chromium").toLowerCase();

        switch (browserName) {
            case "firefox": return playwright.firefox();
            case "webkit":
            case "safari": return playwright.webkit();
            case "chrome":
            case "chromium":
            default: return playwright.chromium();
        }
    }

    private static String getBrowserTypeName() {
        String browserName = System.getProperty("browser.type", "chromium").toLowerCase();

        switch (browserName) {
            case "firefox": return "Firefox";
            case "webkit":
            case "safari": return "WebKit";
            case "chrome": return "Chrome";
            case "chromium":
            default: return "Chromium";
        }
    }

    private static void safeClose(String resourceName, Runnable closeAction) {
        try {
            closeAction.run();
            log.debug("{} closed successfully", resourceName);
        } catch (Exception e) {
            log.warn("Error closing {}: {}", resourceName, e.getMessage());
        }
    }
}