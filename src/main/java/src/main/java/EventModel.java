package src.main.java;

public class EventModel {
    private String nameEvent;
    private String body;

    public EventModel(){

    }

    public EventModel(String nameEvent,String body){
        this.nameEvent=nameEvent;
        this.body=body;
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

    public void setName(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return nameEvent + ", " + body + "!";
    }
}
