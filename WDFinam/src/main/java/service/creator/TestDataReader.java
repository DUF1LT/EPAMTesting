package service.creator;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("finam");

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
