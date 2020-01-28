package fr.esir.jxc.models.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.esir.jxc.models.Json;
import fr.esir.jxc.models.events.models.Event;
import fr.esir.jxc.models.events.models.EventAbstract;
import fr.esir.jxc.models.events.models.EventUnsafe;
import fr.esir.jxc.models.router.EventRouter;
import fr.esir.jxc.models.user.UserEventsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Service
public class MessageListener {
    private CountDownLatch eventModelLatch = new CountDownLatch(1);

    public CountDownLatch getEventModelLatch() {
        return this.eventModelLatch;
    }

    private final UserEventsHandler userEventsHandler;

    public MessageListener(@Autowired UserEventsHandler userEventsHandler) {
        this.userEventsHandler = userEventsHandler;
    }

    @KafkaListener(topics = "${eventModel.topic.name}", containerFactory = "eventModelUnsafekafkaListenerContainerFactory")
    public void listenWithHeaders(
            @Payload @Autowired EventAbstract message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {

        EventRouter eventRouter =new EventRouter(userEventsHandler);
        System.out.println("in");
        if(message instanceof Event){
            Event eventMessage=(Event)message;
            eventRouter.route((new Event(eventMessage.getEventName(), eventMessage.getBody())));
            this.eventModelLatch.countDown();
            return;
        }
        if(message instanceof EventUnsafe) {
            EventUnsafe eventMessage = (EventUnsafe) message;
            JsonNode bodyNode = Json.APP_OBJECT_MAPPER.readTree(eventMessage.getBody());
            if (!bodyNode.isObject()) {
                return;
            } else {
                eventRouter.route(new Event(eventMessage.getEventName(), (ObjectNode) bodyNode));
                this.eventModelLatch.countDown();
                return;
            }

        }
        else{
            return;
        }
    }



}


