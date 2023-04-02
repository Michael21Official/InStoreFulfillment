package dev.michael21official.InStoreFulfillment;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonConverter {
    private static final Gson gson = new Gson();

    public JsonConverter() throws IOException {
    }

    public static Order[] readOrders(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Order[] orders = gson.fromJson(reader, Order[].class);
        reader.close();
        return orders;
    }

    public static Store readStore(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Store store = gson.fromJson(reader, Store.class);
        reader.close();
        return store;
    }
}