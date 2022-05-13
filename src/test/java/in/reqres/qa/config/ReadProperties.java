package in.reqres.qa.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static in.reqres.qa.util.General.CONFIG_FILE;

/*
Use Singleton pattern for reading value from Properties
 */
public class ReadProperties {
    private static ReadProperties instance = null;
    private Properties properties = null;

    private ReadProperties() {
        properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ReadProperties getInstance() {
        if (instance == null) {
            instance = new ReadProperties();
        }
        return instance;
    }

    public String getPropertyValue(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }

    public String getUrl() {
        return getPropertyValue("app.url");
    }

    public static void main(String[] args) {
        System.out.println(ReadProperties.getInstance().getUrl());
    }
}
