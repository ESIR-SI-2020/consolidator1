import src.main.java.EventConsumer;

public class EventConsumerTest {
    public static void main(String[] args) {
        String broker="localhost:9092";
        String topicName="TutorialTopic";
        String groupId="4";

        while(true){
            EventConsumer.consume(broker,groupId,topicName);
        }
    }
}
