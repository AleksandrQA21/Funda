package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminBookingsListPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(AdminBookingsListPage.class);

    private static final String BOOKINGS_LIST_TITLE = "//h1[text()='Booking List']";
    private static final String BOOKINGS_SEARCH_INPUT = "//input[@placeholder='search by Name...']";
    private static final String BOOKINGS_SELECT_PERIOD_INPUT = "//input[@placeholder='Select a period']";
    private static final String BOOKINGS_RESET_BUTTON = "//button[text()='Reset']";
    private static final String BOOKINGS_FILTER_BUTTON = "ki-outline ki-filter fs-2";
    private static final String BOOKINGS_EXPORT_ALL_BUTTON = "//button[contains(@class, 'btn-light-primary') and normalize-space(.)='Export All']";
    private static final String BOOKINGS_EXPORT_BUTTON = "//button[contains(@class, 'btn-light-primary') and normalize-space(.)='Export']";


    public AdminBookingsListPage(Page page) {
        super(page);
    }
}
