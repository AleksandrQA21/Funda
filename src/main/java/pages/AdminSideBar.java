package pages;

import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminSideBar extends BasePage{
    private static final Logger logger = LoggerFactory.getLogger(AdminSideBar.class);

    private static final String ORGANIZATIONS_TAB = "//span[contains(text(),'Organizations')]";
    private static final String ORGANIZATION_LIST_TAB = "//a[contains(@href, '/admin/organization/list')]//span[@class='menu-title' and " +
            "normalize-space(.)" +
            "='List']";
    private static final String ORGANIZATION_CREATE_TAB = "//a[contains(@href, '/admin/organization')]//span[@class='menu-title' and " +
            "normalize-space(text" +
            "())='Create']";
    private static final String DASHBOARD_TAB = "//a[contains(@href, '/admin')]//span[@class='menu-title' and normalize-space(text())='Dashboard']";
    private static final String CUSTOMERS_TAB = "//span[@class='menu-title' and normalize-space(text())='Customers']";
    private static final String PARENTS_TAB = "//a[contains(@href, '/admin/adult/list')]//span[@class='menu-title' and normalize-space(text())='Parents']";
    private static final String CHILDREN_TAB = "//a[contains(@href, '/admin/participant/list')]//span[@class='menu-title' and normalize-space(text())='Children']";
    private static final String PARENT_GROUPS_TAB = "//a[contains(@href, '/admin/group/')]//span[@class='menu-title' and normalize-space(text())" +
            "='Parent Groups']";
    private static final String ACTIVITIES_TAB = "//span[@class='menu-title' and normalize-space(text())='Activities']";
    private static final String ACTIVITIES_LIST_TAB = "//a[contains(@href, '/admin/activity/list')]//span[@class='menu-title' and normalize-space(text())='List']";
    private static final String ACTIVITIES_CREATE_TAB = "//a[contains(@href, '/admin/activity')]//span[@class='menu-title' and normalize-space(text())='Create']";
    private static final String ACTIVITIES_CATEGORIES_TAB = "//a[contains(@href, '/admin/category')]//span[@class='menu-title' and normalize-space(text())='Categories']";
    private static final String LOCATIONS_TAB = "//span[@class='menu-title' and normalize-space(text())='Locations']";
    private static final String REGISTER_TAB = "//span[@class='menu-title' and normalize-space(text())='Register']";
    private static final String BOOKINGS_TAB = "//span[contains(@class, 'menu-link')]//span[@class='menu-title' and normalize-space(text())='Bookings']";
    private static final String BOOKINGS_LIST_TAB = "//a[contains(@href, '/admin/booking/list')]//span[@class='menu-title' and normalize-space(text" +
            "())='Bookings']";
    private static final String CCGPS_BOOKINGS_TAB = "//a[contains(@href, '/admin/booking/ccgps')]//span[@class='menu-title' and normalize-space(text())='CCGPS Bookings']";
    private static final String CANCELLED_BOOKINGS_TAB = "//a[contains(@href, '/admin/booking/cancelled')]//span[@class='menu-title' and " +
            "normalize-space(text())='Cancelled Bookings']";
    private static final String BOOKING_S_BULK_CANCELLATIONS_TAB = "//a[contains(@href, '/admin/booking/bulk_cancel')]//span[@class='menu-title' and normalize-space(text())='Bulk Cancellations']";
    private static final String FINANCE_TAB = "//span[@class='menu-title' and normalize-space(text())='Finance']";
    private static final String FINANCE_RECONCILIATION__TAB = "//a[contains(@href, '/admin/voucher/list')]//span[@class='menu-title' and normalize-space(text())='Reconciliation']";
    private static final String FINANCE_WALLET_PAYMENTS_TAB = "//a[contains(@href, '/admin/walletVoucher/list')]//span[@class='menu-title' and normalize-space(text())='Wallet Payments']";
    private static final String FINANCE_CREDIT_BALANCES_TAB = "//a[contains(@href, '/admin/reports/credits-list')]//span[@class='menu-title' and normalize-space(text())='Credit Balances']";
    private static final String FINANCE_TRANSACTIONS_TAB = "//a[contains(@href, '/admin/reports/transactions-list')]//span[@class='menu-title' and normalize-space(text())='Transactions']";
    private static final String FINANCE_PAYMENT_TYPES_TAB = "//a[contains(@href, '/admin/paymentsTypes/list')]//span[@class='menu-title' and normalize-space(text())='Payment Types']";
    private static final String MARKETING_TAB = "//span[@class='menu-title' and normalize-space(text())='Marketing']";
    private static final String MARKETING_EMAIL_TEMPLATES_TAB = "//a[contains(@href, '/admin/email/list')]//span[@class='menu-title' and normalize-space(text())='Email Templates']";
    private static final String MARKETING_EMAILS_LIST_TAB = "//a[contains(@href, '/admin/email/emails-list')]//span[@class='menu-title' and normalize-space(text())='Emails list']";
    private static final String MARKETING_SEND_EMAIL_TAB = "//a[contains(@href, '/admin/email/select-template')]//span[@class='menu-title' and normalize-space(text())='Send Email']";
    private static final String MARKETING_FORMS_TAB = "//a[contains(@href, '/admin/forms/list')]//span[@class='menu-title' and normalize-space(text())='Forms']";
    private static final String MARKETING_PARENT_SEGMENTS_TAB = "//a[contains(@href, '/admin/email/segments/list')]//span[@class='menu-title' and normalize-space(text())='Parent Segments']";
    private static final String PROMO_TAB = "//span[@class='menu-title' and normalize-space(text())='Promo']";
    private static final String PROMO_CODES_TAB = "//a[contains(@href, '/admin/promo/list')]//span[@class='menu-title' and normalize-space(text())='Codes']";
    private static final String PROMO_USED_CODES_TAB = "//a[contains(@href, '/admin/promo/used-list')]//span[@class='menu-title' and normalize-space(text())='Used Codes']";
    private static final String PROMO_BULK_CODES_TAB = "//a[contains(@href, '/admin/promo/bulk/list')]//span[@class='menu-title' and normalize-space(text())='Bulk Codes']";
    private static final String REPORTS_TAB = "//span[@class='menu-title' and normalize-space(text())='Reports']";
    private static final String REPORTS_ATTENDANCE_REPORT_TAB = "//a[contains(@href, '/admin/reports/attendance-list')]//span[@class='menu-title' and normalize-space(text())='Attendance Report']";
    private static final String REPORTS_ATTENDANCE_REPORT_MONTHLY_TAB = "//a[contains(@href, '/admin/reports/attendance-list-monthly')]//span[@class='menu-title' and normalize-space(text())='Attendance Report Monthly']";
    private static final String REPORTS_ANSWERS_REPORT_TAB = "//a[contains(@href, '/admin/reports/answers')]//span[@class='menu-title' and normalize-space(text())='Answers Report']";
    private static final String EXPORT_TAB = "//span[@class='menu-title' and normalize-space(text())='Export']";
    private static final String SUBSCRIPTIONS_TAB = "//span[@class='menu-title' and normalize-space(text())='Subscriptions']";
    private static final String SUBSCRIPTIONS_LIST_TAB = "//a[contains(@href, '/admin/subscriptions/list')]//span[@class='menu-title' and normalize-space(text())='Subscriptions List']";
    private static final String SUBSCRIPTIONS_REDEEM_CODES_TAB = "//a[contains(@href, '/admin/redeem')]//span[@class='menu-title' and " +
            "normalize-space" +
            "(text())='Redeem codes']";



    public AdminSideBar(Page page) {
        super(page);
    }

    // Organizations section
    public void clickOrganizationsTab() {
        logger.info("Clicking Organizations tab");
        clickElement(ORGANIZATIONS_TAB);
    }

    public void clickOrganizationListTab() {
        logger.info("Clicking Organization List tab");
        clickElement(ORGANIZATION_LIST_TAB);
    }

    public void clickOrganizationCreateTab() {
        logger.info("Clicking Organization Create tab");
        clickElement(ORGANIZATION_CREATE_TAB);
    }

    // Dashboard
    public void clickDashboardTab() {
        logger.info("Clicking Dashboard tab");
        clickElement(DASHBOARD_TAB);
    }

    // Customers section
    public void clickCustomersTab() {
        logger.info("Clicking Customers tab");
        clickElement(CUSTOMERS_TAB);
    }

    public void clickParentsTab() {
        logger.info("Clicking Parents tab");
        clickElement(PARENTS_TAB);
    }

    public void clickChildrenTab() {
        logger.info("Clicking Children tab");
        clickElement(CHILDREN_TAB);
    }

    public void clickParentGroupsTab() {
        logger.info("Clicking Parent Groups tab");
        clickElement(PARENT_GROUPS_TAB);
    }

    // Activities section
    public void clickActivitiesTab() {
        logger.info("Clicking Activities tab");
        clickElement(ACTIVITIES_TAB);
    }

    public void clickActivitiesListTab() {
        logger.info("Clicking Activities List tab");
        clickElement(ACTIVITIES_LIST_TAB);
    }

    public void clickActivitiesCreateTab() {
        logger.info("Clicking Activities Create tab");
        clickElement(ACTIVITIES_CREATE_TAB);
    }

    public void clickActivitiesCategoriesTab() {
        logger.info("Clicking Activities Categories tab");
        clickElement(ACTIVITIES_CATEGORIES_TAB);
    }

    // Locations and Register
    public void clickLocationsTab() {
        logger.info("Clicking Locations tab");
        clickElement(LOCATIONS_TAB);
    }

    public void clickRegisterTab() {
        logger.info("Clicking Register tab");
        clickElement(REGISTER_TAB);
    }

    // Bookings section
    public void clickBookingsTab() {
        logger.info("Clicking Bookings tab");
        clickElement(BOOKINGS_TAB);
    }

    public void clickBookingsListTab() {
        logger.info("Clicking Bookings List tab");
        clickElement(BOOKINGS_LIST_TAB);
    }

    public void clickCcgpsBookingsTab() {
        logger.info("Clicking CCGPS Bookings tab");
        clickElement(CCGPS_BOOKINGS_TAB);
    }

    public void clickCancelledBookingsTab() {
        logger.info("Clicking Cancelled Bookings tab");
        clickElement(CANCELLED_BOOKINGS_TAB);
    }

    public void clickBulkCancellationsTab() {
        logger.info("Clicking Bulk Cancellations tab");
        clickElement(BOOKING_S_BULK_CANCELLATIONS_TAB);
    }

    // Finance section
    public void clickFinanceTab() {
        logger.info("Clicking Finance tab");
        clickElement(FINANCE_TAB);
    }

    public void clickFinanceReconciliationTab() {
        logger.info("Clicking Finance Reconciliation tab");
        clickElement(FINANCE_RECONCILIATION__TAB);
    }

    public void clickFinanceWalletPaymentsTab() {
        logger.info("Clicking Finance Wallet Payments tab");
        clickElement(FINANCE_WALLET_PAYMENTS_TAB);
    }

    public void clickFinanceCreditBalancesTab() {
        logger.info("Clicking Finance Credit Balances tab");
        clickElement(FINANCE_CREDIT_BALANCES_TAB);
    }

    public void clickFinanceTransactionsTab() {
        logger.info("Clicking Finance Transactions tab");
        clickElement(FINANCE_TRANSACTIONS_TAB);
    }

    public void clickFinancePaymentTypesTab() {
        logger.info("Clicking Finance Payment Types tab");
        clickElement(FINANCE_PAYMENT_TYPES_TAB);
    }

    // Marketing section
    public void clickMarketingTab() {
        logger.info("Clicking Marketing tab");
        clickElement(MARKETING_TAB);
    }

    public void clickMarketingEmailTemplatesTab() {
        logger.info("Clicking Marketing Email Templates tab");
        clickElement(MARKETING_EMAIL_TEMPLATES_TAB);
    }

    public void clickMarketingEmailsListTab() {
        logger.info("Clicking Marketing Emails List tab");
        clickElement(MARKETING_EMAILS_LIST_TAB);
    }

    public void clickMarketingSendEmailTab() {
        logger.info("Clicking Marketing Send Email tab");
        clickElement(MARKETING_SEND_EMAIL_TAB);
    }

    public void clickMarketingFormsTab() {
        logger.info("Clicking Marketing Forms tab");
        clickElement(MARKETING_FORMS_TAB);
    }

    public void clickMarketingParentSegmentsTab() {
        logger.info("Clicking Marketing Parent Segments tab");
        clickElement(MARKETING_PARENT_SEGMENTS_TAB);
    }

    // Promo section
    public void clickPromoTab() {
        logger.info("Clicking Promo tab");
        clickElement(PROMO_TAB);
    }

    public void clickPromoCodesTab() {
        logger.info("Clicking Promo Codes tab");
        clickElement(PROMO_CODES_TAB);
    }

    public void clickPromoUsedCodesTab() {
        logger.info("Clicking Promo Used Codes tab");
        clickElement(PROMO_USED_CODES_TAB);
    }

    public void clickPromoBulkCodesTab() {
        logger.info("Clicking Promo Bulk Codes tab");
        clickElement(PROMO_BULK_CODES_TAB);
    }

    // Reports section
    public void clickReportsTab() {
        logger.info("Clicking Reports tab");
        clickElement(REPORTS_TAB);
    }

    public void clickReportsAttendanceReportTab() {
        logger.info("Clicking Reports Attendance Report tab");
        clickElement(REPORTS_ATTENDANCE_REPORT_TAB);
    }

    public void clickReportsAttendanceReportMonthlyTab() {
        logger.info("Clicking Reports Attendance Report Monthly tab");
        clickElement(REPORTS_ATTENDANCE_REPORT_MONTHLY_TAB);
    }

    public void clickReportsAnswersReportTab() {
        logger.info("Clicking Reports Answers Report tab");
        clickElement(REPORTS_ANSWERS_REPORT_TAB);
    }

    // Export
    public void clickExportTab() {
        logger.info("Clicking Export tab");
        clickElement(EXPORT_TAB);
    }

    // Subscriptions section
    public void clickSubscriptionsTab() {
        logger.info("Clicking Subscriptions tab");
        clickElement(SUBSCRIPTIONS_TAB);
    }

    public void clickSubscriptionsListTab() {
        logger.info("Clicking Subscriptions List tab");
        clickElement(SUBSCRIPTIONS_LIST_TAB);
    }

    public void clickSubscriptionsRedeemCodesTab() {
        logger.info("Clicking Subscriptions Redeem Codes tab");
        clickElement(SUBSCRIPTIONS_REDEEM_CODES_TAB);
    }

}
