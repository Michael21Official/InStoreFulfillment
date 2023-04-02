package dev.michael21official.InStoreFulfillment;

public class Order {
    private String orderId;
    private String orderValue;
    private String pickingTime;
    private String completeBy;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(String pickingTime) {
        this.pickingTime = pickingTime;
    }

    public String getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(String completeBy) {
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderValue='" + orderValue + '\'' +
                ", pickingTime='" + pickingTime + '\'' +
                ", completeBy='" + completeBy + '\'' +
                '}';
    }
}