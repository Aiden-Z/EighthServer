package com.eight.server.Scheduler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Scheduler extends Thread{
    private List<ScheduledItem> checkList;
    private Scheduler(){
        checkList = Collections.synchronizedList(new LinkedList<>());
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            long time = System.currentTimeMillis();
            int i = 0;
            if (checkList.size() == 0 || time - checkList.get(i).getCleanUpTime() < 0) {
                yield();
            } else {
                i++;
                while (checkList.size() > i && time - checkList.get(i).getCleanUpTime() >= 0) {
                    i++;
                }
                for (int j = 0; j < i; j++) {
                    ScheduledItem scheduledItem = checkList.get(0);
                    scheduledItem.trigger();
                    checkList.remove(0);
                }
            }
        }
    }

    private static class SchedulerHandler{
        private static Scheduler INSTANCE = new Scheduler();
    }

    public static Scheduler getInstance() {
        return SchedulerHandler.INSTANCE;
    }

    public void PushItem(ScheduledItem item) {
        checkList.add(item);
    }
}
