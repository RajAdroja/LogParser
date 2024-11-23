package main.java;

import java.util.*;

public class ApplicationLogHandler implements LogHandler {
    private LogHandler next;
    private Map<String, Integer> severityCount = new HashMap<>();

    @Override
    public void setNext(LogHandler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public void handle(String logEntry) {
        if (logEntry.contains("level=")) {
            String level = logEntry.split("level=")[1].split(" ")[0];
            severityCount.put(level, severityCount.getOrDefault(level, 0) + 1);
        } else if (next != null) {
            next.handle(logEntry);
        }
    }

    public Map<String, Integer> getSeverityCounts() {
        return severityCount;
    }
}