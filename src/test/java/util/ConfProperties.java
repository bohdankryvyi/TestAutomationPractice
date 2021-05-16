package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The ConfProperties class provides a functionality to get properties from conf.properties file
 */
public class ConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            //set a way to the config file
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //exception on no file etc
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Method for setting an appropriate property based on key in the conf.properties file
     * @param key from properties file
     * @return property
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
