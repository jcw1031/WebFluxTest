package com.woopaca.webfluxtest;

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
        change = false;
        int newEventIndex = events.size() - 1;
        return events.get(newEventIndex);
    }
}
