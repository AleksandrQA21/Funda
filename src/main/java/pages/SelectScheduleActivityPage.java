package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectScheduleActivityPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(SelectScheduleActivityPage.class);

    private static final String FIRST_SCHEDULE_CHECKBOX = "(//span[@class='checkmark'])[1]";
    private static final String SELECTED_SCHEDULE_PRICE = "(//div[@data-title='Price'])[1]";
    private static final String CONTINUE_BUTTON = "(//button[text()='Continue'])[1]";
    private static final String SELECT_CHILDREN_CHECKBOX = "(//span[@class='checkmark'])[1]";


    public SelectScheduleActivityPage(Page page) {
        super(page);
    }
}
