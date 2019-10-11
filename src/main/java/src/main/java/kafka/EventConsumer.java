package src.main.java.kafka;



import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

import static org.apache.kafka.common.requests.FetchMetadata.log;

public class EventConsumer {
    public static int consume(String brokers, String groupId, String topicName) {
        // Create a consumer
        KafkaConsumer<String, String> consumer;
        // Configure the consumer
        Properties properties = new Properties();
        // Point it to the brokers
        properties.setProperty("bootstrap.servers", brokers);
        // Set the consumer group (all consumers must belong to a group).
        properties.setProperty("group.id", groupId);
        // Set how to serialize key/value pairs
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // When a group is first created, it has no offset stored to start reading from. This tells it to start
        // with the earliest record in the stream.
        properties.setProperty("auto.offset.reset", "earliest");

        // specify the protocol for Domain Joined clusters
        //properties.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");

        consumer = new KafkaConsumer<String, String>(properties);

        // Subscribe to the 'test' topic
        consumer.subscribe(Arrays.asList(topicName));

        // Loop until ctrl + c
        int count = 0;
        try {
            while (true) {
                // Poll for records
                ConsumerRecords<String, String> records = consumer.poll(100);
                // Did we get any?
                if (records.count() == 0) {
                    // timeout/nothing to read
                } else {
                    // Yes, loop over records
                    for (ConsumerRecord<String, String> record : records) {
                        // Display record and count
                        count += 1;

                        System.out.println(count + ": " + record.value());
                    }
                    consumer.commitAsync();
                }
            }

        } catch (Exception e) {
            log.error("Unexpected error", e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }

        }
        return 1;

    }
}