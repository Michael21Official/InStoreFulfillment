package dev.michael21official.InStoreFulfillment;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonConverter {
    private static final Gson gson = new Gson();

    private JsonConverter() {}

    public static Order[] readOrders(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return gson.fromJson(reader, Order[].class);
        }
    }

    public static Store readStore(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return gson.fromJson(reader, Store.class);
        }
    }
}
