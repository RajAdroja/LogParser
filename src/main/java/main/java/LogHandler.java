package main.java;
public interface LogHandler {
    void setNext(LogHandler nextHandler);
    void handle(String logEntry);
}