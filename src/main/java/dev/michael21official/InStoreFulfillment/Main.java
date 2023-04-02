package dev.michael21official.InStoreFulfillment;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String ordersFilePath = "D:\\Praca\\JAVA\\Ocado internship program\\self-test-data\\advanced-allocation\\orders.json";
        String storeFilePath = "D:\\Praca\\JAVA\\Ocado internship program\\self-test-data\\advanced-allocation\\store.json";
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
                        bestPicker = picker;
                        bestTime = time;
                    }
                }

                if (bestPicker != null) {
                    System.out.println("Assigning order " + order.getOrderId() + " to picker " + bestPicker);
                    int pickingTimeMinutes = Integer.parseInt(order.getPickingTime().substring(2, order.getPickingTime().length() - 1));
                    pickerTimeMap.put(bestPicker, bestTime.plusMinutes(pickingTimeMinutes));
                } else {
                    System.out.println("Skipping order " + order.getOrderId() + " due to lack of available pickers");
                }
            }

            // Print picking schedule for each picker
            for (Map.Entry<String, LocalTime> entry : pickerTimeMap.entrySet()) {
                String picker = entry.getKey();
                LocalTime time = entry.getValue();
                System.out.println("Picker " + picker + " finished picking at " + time.format(DateTimeFormatter.ofPattern("HH:mm")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}