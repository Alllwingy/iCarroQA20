package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {

    static Properties properties = new Properties();

    public static String getProperty(String parameter) {

        if (properties.toString().equals("{}"))
            initiateProperty();

        return properties.getProperty(parameter);
    }

    private static void initiateProperty() {

        String target = System.getProperty("target", "config");
        String path = String.format("src/test/resources/%s.properties", target);

        try (FileReader fileReader = new FileReader(path)) {

            properties.load(fileReader);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}