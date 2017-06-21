package setup;

import java.util.ResourceBundle;

public class AppProperties {
    public static String getPricingProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pricing");
        return resourceBundle.getString(propertyName);
    }

    public static String getApplicationProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        return resourceBundle.getString(propertyName);
    }

    public static String getEnv() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        return resourceBundle.getString("env");
    }
}
