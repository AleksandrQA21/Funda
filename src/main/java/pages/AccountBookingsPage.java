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
    private static final String INVOICE_CREDIT_VIEW_BUTTON = "a.btn.btn-sm.btn-ellipse.btn-default";
    private static final String BOOKING_REF_LABELS = "table#bookingsIndexBookingsTable label.my-auto.no-break";
    private static final String BOOKING_COST = "//h6[contains(text(),'Booking Cost')]/following-sibling::small";
    private static final String STATUS_BADGE = "td span.badge";
    private static final String COST = "//table[@id='viewDatesBookedTable']//tbody/tr/td[7]";
    private static final String DATES_BOOKED_TAB = "#dates-booked-tab";
    private static final String PAYMENTS_MADE_TAB = "#payments-made-tab";
    private static final String EXPECTED_PAYMENTS_TAB = "#expected-payments-tab";
    private static final String INVOICE_CREDIT_NOTE_TAB = "#invoices-credit-notes-tab";
    private static final String CARD_INSTALMENT_CELLS = "#viewPaymentsTable>tbody>tr>td";
    private static final String EXPECTED_PAYMENTS_TEXT = "//div[@id='expected-payments']//div[@class='card-body']//h6";
    private static final String INVOICE_TOTAL_AMOUNT = "tr.gross-total-row>td:nth-child(2)";


    public AccountBookingsPage(Page page) {
        super(page);
    }

    public void clickFutureInstalmentsViewButton() {
        logger.info("Clicking Future Instalments View button");
        clickElement(FUTURE_INSTALMENTS_VIEW_BUTTON);
    }

    public void clickInvoiceCreditViewButton() {
        logger.info("Clicking Invoice Credit View button");
        clickElement(INVOICE_CREDIT_VIEW_BUTTON);
    }

    public void clickDatesBookedTab() {
        logger.info("Clicking Future Instalments View button");
        clickElement(DATES_BOOKED_TAB);
    }

    public void clickExpectedPaymentsTab() {
        logger.info("Clicking Future Instalments View button");
        clickElement(EXPECTED_PAYMENTS_TAB);
    }

    public void clickPaymentsMadeTab() {
        logger.info("Clicking Future Instalments View button");
        clickElement(PAYMENTS_MADE_TAB);
    }

    public void clickInvoiceCreditNoteTab() {
        logger.info("Clicking Future Instalments View button");
        clickElement(INVOICE_CREDIT_NOTE_TAB);
    }

    public void getExpectedPaymentsText() {
        logger.info("Getting expected payments element");
        getElement(EXPECTED_PAYMENTS_TEXT);
    }

    public void getInvoiceTotalAmount() {
        logger.info("Getting invoice total amount element");
        getElement(INVOICE_TOTAL_AMOUNT);
    }


    public List<String> getAllBookingReferences() {
        logger.debug("Fetching all booking reference numbers from the bookings table");
        return getElement(BOOKING_REF_LABELS)
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

    public  Locator getBookingCost() {
        logger.debug("Getting booking cost");
        return getElement(BOOKING_COST);
    }

    public  Locator getStatusBadge() {
        logger.debug("Getting status badge");
        return getElement(STATUS_BADGE);
    }

    public  Locator getCost() {
        logger.debug("Getting cost");
        return getElement(COST);
    }

    public List<String> getCardInstalmentCells() {
        logger.debug("Getting card instalment cells");
        return getElement(CARD_INSTALMENT_CELLS)
                .allTextContents()
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /** using in test
    List<String> actualCells = paymentsPage.getCardInstalmentCells();
    List<String> expected = List.of("Card Instalment", "22/07/2025", "£20.00", "£20.00", "23/07/2025");

    Assert.assertEquals(actualCells, expected);
    */



}
