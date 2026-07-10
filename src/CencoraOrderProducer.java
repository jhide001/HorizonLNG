import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class CencoraOrderProducer {

    public static void main(String[] args) {

        // Step 1 — Configure the producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        // Step 2 — Create the producer
        KafkaProducer<String, String> producer =
                new KafkaProducer<>(props);

        // Step 3 — Create and send the message
        String topic = "orders.order.created";
        String key = "ORDER-12345";
        String value = "{\"orderId\":\"12345\"," +
                "\"product\":\"Amoxicillin\"," +
                "\"quantity\":500," +
                "\"status\":\"CREATED\"}";

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, key, value);

        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("[Cencora] Order published:" +
                        " Topic=" + metadata.topic() +
                        " Partition=" + metadata.partition() +
                        " Offset=" + metadata.offset());
            } else {
                System.out.println("[ERROR] " + exception.getMessage());
            }
        });

        // Step 4 — Flush and close
        producer.flush();
        producer.close();
        System.out.println("[Cencora] Producer closed successfully.");
    }
}