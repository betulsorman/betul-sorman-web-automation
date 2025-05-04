package utils;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties dosyası okunamadı.");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
