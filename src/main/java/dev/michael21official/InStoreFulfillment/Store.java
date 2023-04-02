package dev.michael21official.InStoreFulfillment;

import java.util.Arrays;

public class Store {
    private String[] pickers;
    private String pickingStartTime;
    private String pickingEndTime;

    public String[] getPickers() {
        return pickers;
    }

    public void setPickers(String[] pickers) {
        this.pickers = pickers;
    }

    public String getPickingStartTime() {
        return pickingStartTime;
    }

    public void setPickingStartTime(String pickingStartTime) {
        this.pickingStartTime = pickingStartTime;
    }

    public String getPickingEndTime() {
        return pickingEndTime;
    }

    public void setPickingEndTime(String pickingEndTime) {
        this.pickingEndTime = pickingEndTime;
    }

    @Override
    public String toString() {
        return "Store{" +
                "pickers=" + Arrays.toString(pickers) +
                ", pickingStartTime='" + pickingStartTime + '\'' +
                ", pickingEndTime='" + pickingEndTime + '\'' +
                '}';
    }
}
