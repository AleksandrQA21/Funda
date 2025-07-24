package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AccountBookingsPage extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(AccountBookingsPage.class);

    private static final String FUTURE_INSTALMENTS_VIEW_BUTTON = "//a[contains(text(),'View')]";
    private static final String BOOKING_REF_LABELS = "table#bookingsIndexBookingsTable label.my-auto.no-break";


    public AccountBookingsPage(Page page) {
        super(page);
    }

    public void clickFutureInstalmentsViewButton() {
        logger.info("Clicking Future Instalments View button");
        page.locator(FUTURE_INSTALMENTS_VIEW_BUTTON).click();
    }

    public List<String> getAllBookingReferences() {
        logger.debug("Fetching all booking reference numbers from the bookings table");
        return page.locator(BOOKING_REF_LABELS)
                .allTextContents()
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public Locator getViewButtonByReference(String referenceNumber) {
        logger.debug("Locating View button for booking reference: {}", referenceNumber);

        Locator label = page.locator("xpath=//label[normalize-space(text())='" + referenceNumber + "']");
        if (!label.isVisible()) {
            throw new RuntimeException("Booking reference not found or not visible: " + referenceNumber);
        }

        return label.locator("xpath=ancestor::tr//a[contains(@class,'btn-ellipse')]");
    }

    public void clickViewButtonByReference(String referenceNumber) {
        logger.info("Clicking View button for booking reference: {}", referenceNumber);
        getViewButtonByReference(referenceNumber).click();
    }

    public void validateAndClickViewButton(String referenceNumber) {
        List<String> refs = getAllBookingReferences();
        logger.debug("Available references: {}", refs);

        if (!refs.contains(referenceNumber)) {
            throw new AssertionError("Expected booking reference '" + referenceNumber + "' not found in the table.");
        }

        clickViewButtonByReference(referenceNumber);
    }
}
