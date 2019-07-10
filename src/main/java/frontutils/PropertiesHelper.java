package frontutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties propertiesProperties = new Properties();

    public static Properties getProperties() {
        FileInputStream fileProperties;
        try {
        	String path;
        	path = "src/main/resources/properties/config.properties";
            fileProperties = new FileInputStream(path);
            propertiesProperties.setProperty("path", System.getProperty("user.home"));
            InputStreamReader readerProperties = new InputStreamReader(fileProperties, "UTF-8");
            propertiesProperties.load(readerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesProperties;
    }
}
