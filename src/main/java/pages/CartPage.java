package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    private static final String LIST_BOOKED_DAYS = "//div[@class='p-3 cart_item d-flex']";
    private static final String TOTAL_PRICE = "#cart_items > div.sidebar_item > div.ml-auto.mr-2 > span.black.font-weight-bold";
    private static final String TOTAL_BOOKING = "#cart_items span#total_booking";
    private static final String TOTAL_PAY_NOW = "#cart_items span#total_pay_now";
    private static final String CREDIT_DEBIT_RADIO_BUTTON = "//input[@value='card' and @type='radio']";
    private static final String ACTUAL_CARD_BUTTON = "(//input[@class='radio-custom'])[1]";
    private static final String READ_AGREE_CHECKBOX = "(//span[@class='checkmark'])[1]";
    private static final String SUBMIT_PAYMENT_BUTTON = "//button[text()='Submit Payment']";
    private static final String CONFIRM_PAYMENT_CONTINUE_BUTTON = "button.swal2-confirm.swal2-styled";
    private static final String CONFIRM_PAYMENT_BACK_BUTTON = "button.swal2-cancel.swal2-styled";
    private static final String SET_PAYMENT_DEFAULT_CHECKBOX = "label.checkbox-container.p-0.m-0.mr-2";



    public CartPage(Page page) {
        super(page);
    }


}
