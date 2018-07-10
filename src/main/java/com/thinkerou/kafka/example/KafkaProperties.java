package com.thinkerou.kafka.example;

/**
 * KafkaProperiter
 */
public class KafkaProperties {

    private KafkaProperties() {}

    public static final String KAFKA_HOST = "localhost";
    public static final int KAFKA_PORT = 9092;
    public static final String TOPIC = "kafka-example-demo";
    public static final String GROUP_ID = "kafka-example-demo-test";
    public static final String KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String VALUE_DESERIALIZER = "com.thinkerou.kafka.example.CallbackDeserializer";
}
