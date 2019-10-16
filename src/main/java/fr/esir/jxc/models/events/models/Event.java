package fr.esir.jxc.models.events.models;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class Event extends EventAbstract {
    private String eventName;
    private ObjectNode body;

    public Event() {
    }

    public Event(String eventName, ObjectNode body) {
        this.eventName = eventName;
        this.body = body;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public ObjectNode getBody() {
        return body;
    }

    public void setBody(ObjectNode body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "eventName='" + eventName + '\'' +
                ", body=" + body +
                '}';
    }
}
