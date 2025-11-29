package org.cowary.arttrackerback.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperUtil {

    private Properties properties = null;

    public ProperUtil(String fileName) {
        Properties prop = new Properties();
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("WEB-INF/config/" + fileName);
            prop.load(stream);
            properties = prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProp(String name) {
        return properties.getProperty(name);
    }
}
