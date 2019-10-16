package fr.esir.jxc.models.kafka;

import fr.esir.jxc.models.events.models.EventAbstract;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;


    public DefaultKafkaConsumerFactory<String, EventAbstract> eventModelUnsafeconsumerFactory() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "eventmodel");
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);

        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        final JsonDeserializer<EventAbstract> jsonDeserializer= new JsonDeserializer<>(EventAbstract.class);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<String, EventAbstract>(props, new StringDeserializer(),jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventAbstract>
    eventModelUnsafekafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EventAbstract> factory
                = new ConcurrentKafkaListenerContainerFactory<String, EventAbstract>();
        factory.setConsumerFactory(eventModelUnsafeconsumerFactory());
        return factory;
    }



}