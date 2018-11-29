package controller;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SettingInitializer {
    public static final File settingsFile = new File("settings.json");

    public static JSONObject settings = new JSONObject();

    public static final String KEY_COUNT_FROM_ALL = "countFromAll";
    public static final String KEY_CHOOSE_FROM_1ST_ROUND = "chooseHall2MemberFrom1stRound";

    public static void initialize () {
        if (!settingsFile.exists()) {
            /* the following are default values */
            settings.put(KEY_COUNT_FROM_ALL, false);
            settings.put(KEY_CHOOSE_FROM_1ST_ROUND, true);

            try (FileWriter writer = new FileWriter(settingsFile)) {
                writer.write(settings.toString());
                writer.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
        /* if the JSON file exists read it's content and initialize the settings */
        String JSONFileContent = "{}";
        try {
            JSONFileContent = new Scanner(new File("settings.json"))
                    .useDelimiter("\\Z")
                    .next()
                    .replaceAll("\n", "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        settings = new JSONObject(JSONFileContent);
    }
}
