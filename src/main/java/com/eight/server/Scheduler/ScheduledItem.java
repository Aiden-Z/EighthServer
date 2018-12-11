package com.eight.server.Scheduler;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

public class ScheduledItem {
    private long cleanUpTime;
    private BooleanSupplier cleanUpcallback;
    public ScheduledItem(long cleanUpTime, BooleanSupplier cleanUpCallback){
        this.cleanUpTime = cleanUpTime;
        this.cleanUpcallback = cleanUpCallback;
    }
    public long getCleanUpTime() {
        return cleanUpTime;
    }
    public boolean trigger() {
        return cleanUpcallback.getAsBoolean();
    }
}
