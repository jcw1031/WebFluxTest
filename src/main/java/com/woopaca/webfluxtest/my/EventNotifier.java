package com.woopaca.webfluxtest.my;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventNotifier {

    private List<String> events = new ArrayList<>();

    private boolean change = false;

    public void add(String data) {
        events.add(data);
        change = true;
    }

    public boolean isChange() {
        return change;
    }

    public String getNewEvent() {
        System.out.println("EventNotifier.getNewEvent");
        int newEventIndex = events.size() - 1;
        return events.get(newEventIndex);
    }

    public void setChange(boolean change) {
        this.change = change;
    }
}
