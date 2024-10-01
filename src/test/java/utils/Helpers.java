package utils;

import com.google.gson.JsonObject;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Helpers {
    public static Properties properties = new Properties();
    public static Properties getConfigData() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/capabilitiesData.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static JSONObject locators(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        String locatorsFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName + ".json";
        return (JSONObject) parser.parse(new FileReader(locatorsFilePath));
    }
}
