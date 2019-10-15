package fr.esir.jxc.models.kafka;

import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.esir.jxc.models.events.Event;
import fr.esir.jxc.models.events.EventUnsafe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode body = (ObjectNode) mapper.readTree("{\"name\": \"john doe\", \"age\": 22, \"city\": \" chicago\"}");
        producer.sendEventModelMessage(new Event("eventName", body));
        //producer.sendEventUnsafeModelMessage(new EventUnsafe("eventName", "eee"));
        listener.getEventModelLatch().await(10, TimeUnit.SECONDS);
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



}