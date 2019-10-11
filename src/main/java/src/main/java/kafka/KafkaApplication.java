package src.main.java.kafka;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import src.main.java.EventModel;
import src.main.java.router.EventRouter;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);

        producer.sendEventModelMessage(new EventModel("eventName","body of event"));
        listener.eventModelLatch.await(10, TimeUnit.SECONDS);
        context.close();
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageProducer {

        @Value(value = "${eventModel.topic.name}")
        private String eventModelTopicName;

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        private KafkaTemplate<String, EventModel> EventModelkafkaTemplate;

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

        public void sendEventModelMessage(EventModel msg) {
            EventModelkafkaTemplate.send(eventModelTopicName, msg);

        }


    }

    public static class MessageListener {
        private CountDownLatch eventModelLatch = new CountDownLatch(1);

        @KafkaListener(topics ="${eventModel.topic.name}",containerFactory = "EventModelkafkaListenerContainerFactory")
        public void listenWithHeaders(
                @Payload EventModel message,
                @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
            JSONObject messageJson= new JSONObject(message);

            if(messageJson.has("nameEvent") && messageJson.has("body") && messageJson.get("body") instanceof JSONObject){
                EventRouter.eventRouter(messageJson.getString("nameEvent"),(JSONObject)messageJson.get("body"));
            }
            this.eventModelLatch.countDown();
        }


    }
}