package fr.esir.jxc.models.events;

public class EventModelUnsafe {
    private String nameEvent;
    private String body;

    public EventModelUnsafe(){

    }

    public EventModelUnsafe(String nameEvent, String body) {

        this.nameEvent = nameEvent;

        this.body = body;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
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
                "nameEvent='" + nameEvent + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
