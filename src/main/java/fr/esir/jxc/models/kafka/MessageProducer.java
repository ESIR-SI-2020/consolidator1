package fr.esir.jxc.models.kafka;


import fr.esir.jxc.models.events.Event;
import fr.esir.jxc.models.events.EventAbstract;
import fr.esir.jxc.models.events.EventUnsafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class MessageProducer {

    @Value(value = "${eventModel.topic.name}")
    private String eventModelTopicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Event> eventkafkaTemplate;

    @Autowired
    private KafkaTemplate<String, EventUnsafe> eventUnsafekafkaTemplate;

    public void sendEventModelMessage(Event msg) {
        eventkafkaTemplate.send(eventModelTopicName, msg);
    }


    public void sendEventUnsafeModelMessage(EventUnsafe msg) {
        eventUnsafekafkaTemplate.send(eventModelTopicName,msg);
    }
}