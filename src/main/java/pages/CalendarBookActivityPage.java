package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for Calendar page
 */
public class CalendarBookActivityPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CalendarBookActivityPage.class);

    private static final String CALENDAR_LEFT_ARROW = ".vc-arrow.is-left";
    private static final String CALENDAR_RIGHT_ARROW = ".vc-arrow.is-right";
    private static final String LIST_BOOKED_DAYS = "//div[@class='p-3 cart_item d-flex']";

    // Локатор только дни текущего месяца и доступные (не disabled)
    private final Locator currentMonthDays= page.locator(
            "div.vc-day:not(.from-prev-month):not(.from-next-month) span.vc-day-content.vc-focusable:not([aria-disabled='true'])"
    );

    public CalendarBookActivityPage(Page page) {
        super(page);
    }


        // Только дни текущего месяца и доступные (не disabled)
//        currentMonthDays = page.locator(
//                "div.vc-day:not(.from-prev-month):not(.from-next-month) span.vc-day-content.vc-focusable:not([aria-disabled='true'])"
//        );
//


    // Метод выбора первых N доступных дней
    public void selectFirstNDays(int numberOfDays) {
        logger.info("Selecting first {} days", numberOfDays);
        for (int i = 0; i < numberOfDays; i++) {
            currentMonthDays.nth(i).click();
            page.waitForTimeout(500); // Пауза для наглядности, опционально
        }
    }
}
