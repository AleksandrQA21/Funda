package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Centralized test data management
 * Handles test credentials, error messages, and other test data
 */
public class TestDataManager {
    private static final Logger logger = LoggerFactory.getLogger(TestDataManager.class);

    /**
     * User credentials for different test scenarios
     */
    public static class UserCredentials {

        /**
         * Get valid email for Client's login tests
         * Priority: Environment variable -> Properties file
         *
         * @return Valid email address
         */
        public static String getClientValidEmail() {
            String email = System.getenv("TEST_USER_EMAIL");
            if (email != null && !email.trim().isEmpty()) {
                logger.debug("Using email from environment variable");
                return email;
            }

            email = ConfigReader.getClientTestUserEmail();
            if (email != null && !email.trim().isEmpty()) {
                logger.debug("Using email from properties file");
                return email;
            }

            logger.warn("No valid email found in environment or properties");
            throw new RuntimeException("Valid email not configured. Set TEST_USER_EMAIL environment variable or test.client.email property");
        }

        /**
         * Get valid password for Client's login tests
         * Priority: Environment variable -> Properties file
         *
         * @return Valid password
         */
        public static String getClientValidPassword() {
            String password = System.getenv("TEST_USER_PASSWORD");
            if (password != null && !password.trim().isEmpty()) {
                logger.debug("Using password from environment variable");
                return password;
            }

            password = ConfigReader.getClientTestUserPassword();
            if (password != null && !password.trim().isEmpty()) {
                logger.debug("Using password from properties file");
                return password;
            }

            logger.warn("No valid password found in environment or properties");
            throw new RuntimeException("Valid password not configured. Set TEST_USER_PASSWORD environment variable or test.client.password property");
        }

        /**
         * Get valid email for Admin's login tests
         * Priority: Environment variable -> Properties file
         *
         * @return Valid email address
         */
        public static String getAdminValidEmail() {
            String email = System.getenv("TEST_USER_EMAIL");
            if (email != null && !email.trim().isEmpty()) {
                logger.debug("Using email from environment variable");
                return email;
            }

            email = ConfigReader.getAdminTestUserEmail();
            if (email != null && !email.trim().isEmpty()) {
                logger.debug("Using email from properties file");
                return email;
            }

            logger.warn("No valid email found in environment or properties");
            throw new RuntimeException("Valid email not configured. Set TEST_USER_EMAIL environment variable or test.client.email property");
        }


        /**
         * Get valid password for Admin's login tests
         * Priority: Environment variable -> Properties file
         *
         * @return Valid password
         */
        public static String getAdminValidPassword() {
            String password = System.getenv("TEST_USER_PASSWORD");
            if (password != null && !password.trim().isEmpty()) {
                logger.debug("Using password from environment variable");
                return password;
            }

            password = ConfigReader.getAdminTestUserPassword();
            if (password != null && !password.trim().isEmpty()) {
                logger.debug("Using password from properties file");
                return password;
            }

            logger.warn("No valid password found in environment or properties");
            throw new RuntimeException("Valid password not configured. Set TEST_USER_PASSWORD environment variable or test.client.password property");
        }

        /**
         * Get invalid email for negative test scenarios
         *
         * @return Invalid email address
         */
        public static String getInvalidEmail() {
            return "invalid@example.com";
        }

        /**
         * Get invalid password for negative test scenarios
         *
         * @return Invalid password
         */
        public static String getInvalidPassword() {
            return "wrongpass123";
        }

        /**
         * Get short/weak password for validation tests
         *
         * @return Short password
         */
        public static String getShortPassword() {
            return "1Q2";
        }

        /**
         * Get empty email (for validation)
         *
         * @return Empty string
         */
        public static String getEmptyEmail() {
            return "";
        }

        /**
         * Get empty password (for validation)
         *
         * @return Empty string
         */
        public static String getEmptyPassword() {
            return "";
        }

        /**
         * Validate that required credentials are configured
         *
         * @throws RuntimeException if credentials are missing
         */
        public static void validateClientCredentials() {
            try {
                getClientValidEmail();
                getClientValidPassword();
                logger.info("Test credentials validation successful");
            } catch (RuntimeException e) {
                logger.error("Test credentials validation failed: {}", e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Expected error messages for validation
     */
    public static class ErrorMessages {

        // Email validation errors
        public static final String INVALID_EMAIL = "The selected email is invalid.";
        public static final String REQUIRED_EMAIL = "The email field is required.";

        // Password validation errors  
        public static final String PASSWORD_MISMATCH = "Password does not match";
        public static final String REQUIRED_PASSWORD = "The password field is required.";

        // General authentication errors
        public static final String AUTHENTICATION_FAILED = "Authentication failed";
        public static final String ACCOUNT_LOCKED = "Account has been locked";
        public static final String TOO_MANY_ATTEMPTS = "Too many login attempts";

        /**
         * Get error message by type
         *
         * @param errorType Type of error
         * @return Error message string
         */
        public static String getErrorMessage(ErrorType errorType) {
            switch (errorType) {
                case INVALID_EMAIL:
                    return INVALID_EMAIL;
                case REQUIRED_EMAIL:
                    return REQUIRED_EMAIL;
                case PASSWORD_MISMATCH:
                    return PASSWORD_MISMATCH;
                case REQUIRED_PASSWORD:
                    return REQUIRED_PASSWORD;
                case AUTHENTICATION_FAILED:
                    return AUTHENTICATION_FAILED;
                case ACCOUNT_LOCKED:
                    return ACCOUNT_LOCKED;
                case TOO_MANY_ATTEMPTS:
                    return TOO_MANY_ATTEMPTS;
                default:
                    throw new IllegalArgumentException("Unknown error type: " + errorType);
            }
        }
    }

    /**
     * Enum for different error types
     */
    public enum ErrorType {
        INVALID_EMAIL,
        REQUIRED_EMAIL,
        PASSWORD_MISMATCH,
        REQUIRED_PASSWORD,
        AUTHENTICATION_FAILED,
        ACCOUNT_LOCKED,
        TOO_MANY_ATTEMPTS
    }

    /**
     * Test URLs for different environments
     */
    public static class URLs {

        /**
         * Get base URL based on environment
         *
         * @return Base URL for current environment
         */
        public static String getBaseUrl() {
            String environment = getEnvironment();
            String baseUrl;

            switch (environment.toLowerCase()) {
                case "prod":
                case "production":
                    baseUrl = "https://fundaactive.com";
                    break;
                case "dev":
                case "development":
                default:
                    baseUrl = ConfigReader.getBaseUrl();
                    break;
            }

            logger.debug("Base URL for environment '{}': {}", environment, baseUrl);
            return baseUrl;
        }

        /**
         * Get Client's login page URL
         *
         * @return  Client's login page URL
         */
        public static String getClientLoginUrl() {
            return getBaseUrl() + "/login";
        }

        /**
         * Get Admin's login page URL
         *
         * @return Admin's login page URL
         */
        public static String getAdminLoginUrl() {
            return getBaseUrl() + "/admin/login";
        }

        /**
         * Get homepage URL after successful login
         *
         * @return Full homepage URL
         */
        public static String getHomepageUrl() {
            return getBaseUrl() + "/activity/list";
        }

        /**
         * Get forgot password URL
         *
         * @return Full forgot password URL
         */
        public static String getForgotPasswordUrl() {
            return getBaseUrl() + "/forgot-password";
        }

        /**
         * Get register page URL
         *
         * @return Full register page URL
         */
        public static String getRegisterUrl() {
            return getBaseUrl() + "/register";
        }
    }

    /**
     * Browser and test configuration data
     */
    public static class TestConfiguration {

        /**
         * Get current test environment
         *
         * @return Environment name
         */
        public static String getEnvironment() {
            return System.getProperty("test.environment",
                    System.getenv("TEST_ENVIRONMENT") != null ?
                            System.getenv("TEST_ENVIRONMENT") : "dev");
        }

        /**
         * Get browser type for testing
         *
         * @return Browser type name
         */
        public static String getBrowserType() {
            return System.getProperty("browser.type",
                    System.getenv("BROWSER_TYPE") != null ?
                            System.getenv("BROWSER_TYPE") : "chromium");
        }

        /**
         * Check if running in headless mode
         *
         * @return true if headless mode
         */
        public static boolean isHeadless() {
            String headless = System.getProperty("browser.headless",
                    System.getenv("BROWSER_HEADLESS"));

            if (headless != null) {
                return Boolean.parseBoolean(headless);
            }

            return ConfigReader.isBrowserHeadless();
        }

        /**
         * Get element timeout in milliseconds
         *
         * @return Timeout value
         */
        public static int getElementTimeout() {
            String timeout = System.getProperty("element.timeout",
                    System.getenv("ELEMENT_TIMEOUT"));

            if (timeout != null) {
                try {
                    return Integer.parseInt(timeout);
                } catch (NumberFormatException e) {
                    logger.warn("Invalid element timeout value '{}', using default", timeout);
                }
            }

            return ConfigReader.getBrowserTimeout();
        }
    }

    /**
     * Get current test environment
     *
     * @return Environment name (dev, staging, prod)
     */
    private static String getEnvironment() {
        return TestConfiguration.getEnvironment();
    }

    /**
     * Initialize and validate all test data
     * Should be called at test suite start
     */
    public static void initialize() {
        logger.info("Initializing TestDataManager");
        logger.info("Environment: {}", TestConfiguration.getEnvironment());
        logger.info("Browser: {}", TestConfiguration.getBrowserType());
        logger.info("Headless: {}", TestConfiguration.isHeadless());
        logger.info("Base URL: {}", URLs.getBaseUrl());

        // Validate required credentials
        UserCredentials.validateClientCredentials();

        logger.info("TestDataManager initialized successfully");
    }
}
