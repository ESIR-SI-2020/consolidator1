package fr.esir.jxc.models.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.esir.jxc.models.events.Event;
import fr.esir.jxc.models.events.EventAbstract;
import fr.esir.jxc.models.events.EventUnsafe;
import fr.esir.jxc.models.router.EventRouter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MessageListener {
    private CountDownLatch eventModelLatch = new CountDownLatch(1);

    public CountDownLatch getEventModelLatch() {
        return this.eventModelLatch;
    }

    @KafkaListener(topics = "${eventModel.topic.name}", containerFactory = "eventModelUnsafekafkaListenerContainerFactory")
    public void listenWithHeaders(
            @Payload EventAbstract message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws IOException {

        if(message instanceof Event){
            Event eventMessage=(Event)message;
            EventRouter.route((new Event(eventMessage.getEventName(),eventMessage.getBody())));
            this.eventModelLatch.countDown();
            return;
        }
        if(message instanceof EventUnsafe) {
            EventUnsafe eventMessage = (EventUnsafe) message;
            ObjectMapper mapper = new ObjectMapper();
            JsonNode bodyNode = mapper.readTree(eventMessage.getBody());
            if (!bodyNode.isObject()) {
                return;
            } else {
                EventRouter.route(new Event(eventMessage.getEventName(), (ObjectNode) bodyNode));
                this.eventModelLatch.countDown();
                return;
            }

        }
        else{
            return;
        }
    }



}


