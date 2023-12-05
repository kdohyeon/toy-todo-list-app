package org.example.event;

public interface Event {
    boolean support(EventType type);

    void print();
}
