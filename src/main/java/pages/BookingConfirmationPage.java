package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingConfirmationPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(BookingConfirmationPage.class);

    private static final String BOOKING_REFERENCE = "//p[contains(text(),'booking reference')]";
    private static final String BOOK_NOW_BUTTON = "a.btn.btn-theme.btn-ellipse.nonAjaxLink";

    public BookingConfirmationPage(Page page) {
        super(page);
    }

    public String getBookingReferenceNumber() {
        logger.info("Getting Booking Reference number");
        String fullText = getElementText(BOOKING_REFERENCE);
        return fullText.replace("Your booking reference: ", "").trim();
    }

    public void clickBookNow() {
        logger.info("Clicking Booking Now button");
        page.locator(BOOK_NOW_BUTTON).click();
    }
}