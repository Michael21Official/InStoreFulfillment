package dev.michael21official.InStoreFulfillment;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.time.Duration;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        String ordersFilePath = "D:\\Praca\\JAVA\\InStoreFulfillment\\src\\main\\resources\\self-test-data\\advanced-allocation\\orders.json";
        String storeFilePath = "D:\\Praca\\JAVA\\InStoreFulfillment\\src\\main\\resources\\self-test-data\\advanced-allocation\\store.json";
        try {
            Order[] orders = JsonConverter.readOrders(ordersFilePath);
            Store store = JsonConverter.readStore(storeFilePath);
            LocalTime startTime = LocalTime.parse(store.getPickingStartTime());

            System.out.println("Orders:");
            for (Order order : orders) {
                System.out.println(order);
            }

            System.out.println("\nStore:");
            System.out.println(store);

            // Sort orders by completeBy time in ascending order
            Arrays.sort(orders, Comparator.comparing(Order::getCompleteBy));

            // Initialize map of pickers and their available time
            Map<String, LocalTime> pickerTimeMap = new HashMap<>();
            for (String picker : store.getPickers()) {
                pickerTimeMap.put(picker, LocalTime.parse(store.getPickingStartTime()));
            }

            // Assign orders to pickers based on available time
            for (Order order : orders) {
                LocalTime completeByTime = LocalTime.parse(order.getCompleteBy(), DateTimeFormatter.ofPattern("HH:mm"));

                String bestPicker = null;
                LocalTime bestTime = null;

                for (Map.Entry<String, LocalTime> entry : pickerTimeMap.entrySet()) {
                    String picker = entry.getKey();
                    LocalTime time = entry.getValue();
                    long delay = Instant.now().toEpochMilli() - startTime.atDate(LocalDate.now()).toInstant(ZoneOffset.UTC).toEpochMilli();
                    Thread.sleep(delay);

                    if (time.isBefore(completeByTime) && (bestTime == null || time.isBefore(bestTime))) {
                        bestTime = time;
                        bestPicker = picker;
                    }
                }




                if (bestPicker == null) {
                    System.out.println("Order " + order.getOrderId() + " could not be assigned to a picker in time.");
                } else {
                    System.out.println("Order " + order.getOrderId() + " assigned to picker " + bestPicker + " to be completed by " + order.getCompleteBy());
                    pickerTimeMap.put(bestPicker, bestTime.plus(Duration.parse(order.getPickingTime())));
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }
}
