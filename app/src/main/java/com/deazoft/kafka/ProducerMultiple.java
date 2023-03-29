package com.deazoft.kafka;

import com.deazoft.kafka.entities.Client;
import com.deazoft.kafka.entities.Loan;
import com.deazoft.kafka.entities.User;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

public class ProducerMultiple {

    public static Properties getProperties()
    {
        return getPropertiesWithStrategy(io.confluent.kafka.serializers.subject.TopicRecordNameStrategy.class);
    }

    public static Properties getPropertiesWithStrategy(Class strategy)
    {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("acks","1");
        properties.put("retries","3");

        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", KafkaJsonSchemaSerializer.class);

        properties.put("schema.registry.url","http://127.0.0.1:8081");
        properties.put("auto.register.schemas",false);
        properties.put("value.subject.name.strategy",strategy);
        return properties;
    }

    public static <T> void sendObject(String topicName, T objectToSend, String key) {

        // Set up Kafka Producer properties
        Properties props = getProperties();

        // Create Kafka Producer instance
        KafkaProducer<String, T> producer = new KafkaProducer<>(props);

        // Create ProducerRecord with topic name, null key, and object to send as value
        ProducerRecord<String, T> record = new ProducerRecord<>(topicName, key, objectToSend);

        // Send the record using the Kafka Producer instance
        producer.send(record,new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println(recordMetadata);
                } else {
                    e.printStackTrace();
                }
            }
        });

        producer.flush();
        // Close the Kafka Producer instance
        producer.close();
    }

    public static void main(String[] args) {

        String topic = "multiple";

        var user = new User("bryan-3", "asdasd", "asdasdasd","asdasd");
        var client = new Client("Jose David", "Escobar Ardila");

        sendObject(topic, user, "user-1");
        sendObject(topic, client, "client-1");
        sendObject(topic, new Loan(UUID.randomUUID().toString()),"loan-1");


    }
}
