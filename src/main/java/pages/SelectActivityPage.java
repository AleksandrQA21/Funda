package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectActivityPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(SelectActivityPage.class);

    private static final String AUTOTEST_ACTIVITY_BUTTON = "//h6[contains(text(),'Autotest')]";
    private static final String BOOK_NOW_BUTTON = "//span[text()='Book Now']";

    public SelectActivityPage(Page page) {
        super(page);
    }
}
