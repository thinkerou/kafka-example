package com.thinkerou.kafka.example;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import com.thinkerou.proto.helloworld.Callback;

/**
 * CallbackDeserializer
 */
public class CallbackDeserializer implements Deserializer<Callback> {

    private static final Logger logger = LoggerFactory.getLogger(CallbackDeserializer.class);

    @Override
    public Callback deserialize(final String topic, byte[] data) {
        try {
            return Callback.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            logger.error("callback received un-parse message...");
            return null;
            //throw new RuntimeException("Received un-parse message " + e.getMessage(), e);
        }
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
}
