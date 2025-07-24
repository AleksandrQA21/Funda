package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for Admin dashboard page
 */
public class AdminDashboardPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(AdminDashboardPage.class);
    // URLs
    private static final String ADMIN_DASHBOARD_URL = "https://dev-nd.fundaactive.com/admin";
    // Selectors
    private static final String ADMIN_DASHBOARD_TITLE = "//h1[text()='Dashboard']";
    private static final String CUSTOMERS_BUTTON = "//span[text()='Customers']";
    private static final String PARENTS_BUTTON = "//span[text()=' Parents']";
    private static final String PARENT_SEARCH = "input[placeholder='Search Parent']";
    private static final String PARENT_ACTIONS_BUTTON = "//button[contains(text(),'Actions')]";
    private static final String PARENT_VIEW_BUTTON = "(//div[@class='menu-item px-3'])[1]//a[text()='View']";
    private static final String PARENT_CONNECT_AS_BUTTON = "(//div[@class='menu-item px-3'])[2]//a[text()='Connect As']";
    private static final String PARENT_DELETE_BUTTON = "(//div[@class='menu-item px-3'])[3]//a[text()='Delete']";




    public AdminDashboardPage(Page page) {
        super(page);
    }
}
