package fr.esir.jxc.models.kafka;


import fr.esir.jxc.models.events.EventModelUnsafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class MessageProducer {

    @Value(value = "${eventModel.topic.name}")
    private String eventModelTopicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, EventModelUnsafe> EventModelUnsafekafkaTemplate;

    public void sendMessage(final String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("eventTopic", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendEventModelMessage(EventModelUnsafe msg) {
        EventModelUnsafekafkaTemplate.send(eventModelTopicName, msg);

    }


}