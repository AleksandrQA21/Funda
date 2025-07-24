package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration reader for main package classes
 * Reads properties from test resources
 */
public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE = "config/properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static boolean isBrowserHeadless() {
        return Boolean.parseBoolean(properties.getProperty("browser.headless", "false"));
    }

    public static int getBrowserTimeout() {
        return Integer.parseInt(properties.getProperty("browser.timeout", "10000"));
    }

    public static String getClientTestUserEmail() {
        return properties.getProperty("test.client.email");
    }

    public static String getClientTestUserPassword() {
        return properties.getProperty("test.client.password");
    }

    public static String getAdminTestUserEmail() {
        return properties.getProperty("test.admin.email");
    }

    public static String getAdminTestUserPassword() {
        return properties.getProperty("test.admin.password");
    }
    
    public static String getBrowserType() {
        return properties.getProperty("browser.type", "chromium");
    }
    
    public static int getElementTimeout() {
        String timeout = properties.getProperty("element.timeout");
        return timeout != null ? Integer.parseInt(timeout) : getBrowserTimeout();
    }
    
    public static int getRetryCount() {
        String retry = properties.getProperty("retry.count");
        return retry != null ? Integer.parseInt(retry) : 3;
    }
    
    public static String getEnvironment() {
        return properties.getProperty("test.environment", "dev");
    }
}
