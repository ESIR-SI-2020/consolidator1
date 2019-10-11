import src.main.java.kafka.EventConsumer;

public class EventConsumerTest {
    public static void main(String[] args) {
        String broker="localhost:9092";
        String topicName="TestTopic";
        String groupId="4";

        while(true){
            EventConsumer.consume(broker,groupId,topicName);
        }
    }
}
