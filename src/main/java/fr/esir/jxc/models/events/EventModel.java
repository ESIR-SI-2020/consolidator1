package fr.esir.jxc.models.events;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class EventModel {
    private String nameEvent;
    private ObjectNode body;

    public EventModel(){

    }

    public EventModel(String nameEvent, ObjectNode body){
        this.nameEvent=nameEvent;
        this.body=body;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public ObjectNode getBody() {
        return body;
    }

    public void setName(ObjectNode body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return nameEvent + ", " + body + "!";
    }
}
