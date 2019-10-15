package fr.esir.jxc.models.router;

import fr.esir.jxc.models.events.Event;

public class EventRouter {


    public static void route(Event eventModel) {
        System.out.println(eventModel);
    }

}
