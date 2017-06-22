package setup;

import java.util.ResourceBundle;

public class AppProperties {
    public static String getPricingProperty(String propertyName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pricing");
        return resourceBundle.getString(propertyName);
    }
}
