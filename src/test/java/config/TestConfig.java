package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static Properties properties;
    private static final String CONFIG_FILE = "config/properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = TestConfig.class.getClassLoader()
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
        return Boolean.parseBoolean(properties.getProperty("browser.headless"));
    }

    public static int getBrowserTimeout() {
        return Integer.parseInt(properties.getProperty("browser.timeout"));
    }

    public static String getTestUserEmail() {
        return properties.getProperty("test.client.email");
    }

    public static String getTestUserPassword() {
        return properties.getProperty("test.client.password");
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
    
    /**
     * Validate that all required properties are present
     * @throws RuntimeException if required properties are missing
     */
    public static void validateConfiguration() {
        String[] requiredProperties = {
            "base.url", "test.client.email", "test.client.password"
        };
        
        StringBuilder missingProperties = new StringBuilder();
        for (String property : requiredProperties) {
            String value = properties.getProperty(property);
            if (value == null || value.trim().isEmpty()) {
                if (missingProperties.length() > 0) {
                    missingProperties.append(", ");
                }
                missingProperties.append(property);
            }
        }
        
        if (missingProperties.length() > 0) {
            throw new RuntimeException("Required properties are missing: " + missingProperties.toString());
        }
    }
}

