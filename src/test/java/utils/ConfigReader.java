package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String read(String path, String key) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        Properties prop= new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static String read(String key) throws IOException {
        return read(Constants.CONFIG_FILE_PATH, key);
    }
}
