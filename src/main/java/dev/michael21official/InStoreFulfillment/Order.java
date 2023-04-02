package dev.michael21official.InStoreFulfillment;

import java.math.BigDecimal;

public class Order {
    private String orderId;
    private BigDecimal orderValue;
    private String pickingTime;
    private String completeBy;

    public Order(String orderId, BigDecimal orderValue, String pickingTime, String completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public String getPickingTime() {
        return pickingTime;
    }

    public String getCompleteBy() {
        return completeBy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderValue=" + orderValue +
                ", pickingTime='" + pickingTime + '\'' +
                ", completeBy='" + completeBy + '\'' +
                '}';
    }
}
