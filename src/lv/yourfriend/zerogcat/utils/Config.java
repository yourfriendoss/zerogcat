package lv.yourfriend.zerogcat.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Config {
    public static String token;
    public static String prefix = "please-";
    public static LMDBWrapper db;

    public static Integer maxAmountOfChannels = 3;
    public static Integer maxAmountOfQuotes = 3;

    public static Gson gson = new Gson();

    public static void parse(File file) {
        try {
            try (Scanner myReader = new Scanner(file)) {
                String data = "";
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine();
                }

                JsonObject o = gson.fromJson(data, JsonObject.class);
                JsonElement tokenElement = o.get("token");
                JsonElement maxAmountOfChannelsElement = o.get("maxAmountOfChannels");
                JsonElement maxAmountOfQuotesElement = o.get("maxAmountOfQuotes");
                JsonElement prefixElement = o.get("prefix");

                if (maxAmountOfChannelsElement != null) {
                    maxAmountOfChannels = maxAmountOfChannelsElement.getAsInt();
                }

                if (maxAmountOfQuotesElement != null) {
                    maxAmountOfQuotes = maxAmountOfQuotesElement.getAsInt();
                }

                if (prefixElement != null) {
                    prefix = prefixElement.getAsString();
                }

                if (tokenElement == null) {
                    System.out.println("Missing token!");
                    System.exit(-1);
                } else {
                    token = tokenElement.getAsString();
                }

                db = new LMDBWrapper(Paths.get("database"), "database");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
