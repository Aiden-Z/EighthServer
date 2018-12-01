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
