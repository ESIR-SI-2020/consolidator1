package fr.esir.jxc.models.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.esir.jxc.models.events.EventModel;
import fr.esir.jxc.models.events.EventModelUnsafe;
import fr.esir.jxc.models.router.EventRouter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;

public class MessageListener {
    private CountDownLatch eventModelLatch = new CountDownLatch(1);

    public CountDownLatch getEventModelLatch() {
        return this.eventModelLatch;
    }

    @KafkaListener(topics = "${eventModel.topic.name}", containerFactory = "EventModelUnsafekafkaListenerContainerFactory")
    public void listenWithHeaders(
            @Payload EventModelUnsafe message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode bodyNode = mapper.readTree(message.getBody());

        if(!bodyNode.isObject()) {
            // ignore not correctly formatted events
            return;
        } else {
            EventRouter.route(new EventModel(message.getNameEvent(),(ObjectNode) bodyNode));
        }
        this.eventModelLatch.countDown();
    }


}
