package com.thinkerou.kafka.example;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        CallbackConsumer consumerThread = new CallbackConsumer(KafkaProperties.TOPIC);
        consumerThread.start();
    }
}
