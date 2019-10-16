package fr.esir.jxc.models.events.models;


public class EventUnsafe extends EventAbstract {
    private String eventName;
    private String body;

    public EventUnsafe(){

    }

    public EventUnsafe(String eventName, String body) {

        this.eventName = eventName;

        this.body = body;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EventModelUnsafe{" +
                "nameEvent='" + eventName + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
