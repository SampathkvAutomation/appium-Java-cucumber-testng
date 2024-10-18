package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static Properties properties = new Properties();
    TestUtils testUtils = new TestUtils();

    public Properties getProperties() throws IOException {
        InputStream is = null;
        String propsFileName = "config.properties";

        if(properties.isEmpty()){
            try {
                testUtils.logger().info("Loading config properties...");
                is = getClass().getClassLoader().getResourceAsStream(propsFileName);
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                testUtils.logger().fatal("Failed to load config properties :(" + e.toString());
                throw e;
            } finally {
                if(is != null){
                    is.close();
                }
            }
        }
        return properties;
    }
}
