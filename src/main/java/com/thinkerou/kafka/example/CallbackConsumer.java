package com.thinkerou.kafka.example;

import java.util.Collections;
import java.util.Properties;

import com.thinkerou.proto.helloworld.Callback;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.utils.ShutdownableThread;


/**
 * CallbackConsumer
 */
public class CallbackConsumer extends ShutdownableThread {

    private static final Logger logger = LoggerFactory.getLogger(CallbackConsumer.class);

    private final KafkaConsumer<String, Callback> consumer;
    private final String topic;

    public CallbackConsumer(String topic) {
        super("kafkaWxampleConsumer", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_HOST + ":" + KafkaProperties.KAFKA_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.GROUP_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaProperties.KEY_DESERIALIZER);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProperties.VALUE_DESERIALIZER);

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<String, Callback> records = consumer.poll(1000);
        for (ConsumerRecord<String, Callback> record : records) {
            logger.info("Received message: (" + record.key() + ", " + record.value() + ")");
            Callback event = record.value();
            if (event == null) return;

            switch (event.getEventTypeCase()) {
                case ONE_EVENT:
                    logger.warn("one event: {}", event.getOneEvent());
                    break;
                case TWO_EVENT:
                    logger.warn("one event: {}", event.getTwoEvent());
                    break;
                default:
                    logger.warn("Handle unknown event: {}", event.getEventTypeCase().getNumber());
            }
        }
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}
