package dev.michael21official.InStoreFulfillment;

import java.util.Arrays;

public class Store {
    private String[] pickers;
    private String pickingStartTime;
    private String pickingEndTime;

    public Store(String[] pickers, String pickingStartTime, String pickingEndTime) {
        this.pickers = pickers;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }

    public String[] getPickers() {
        return pickers;
    }

    public String getPickingStartTime() {
        return pickingStartTime;
    }

    public String getPickingEndTime() {
        return pickingEndTime;
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
