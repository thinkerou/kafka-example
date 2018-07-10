package com.thinkerou.kafka.example;


import java.util.Map;

import com.thinkerou.proto.helloworld.Callback;

import org.apache.kafka.common.serialization.Serializer;

public class CallbackSerializer implements Serializer<Callback> {

    @Override
    public byte[] serialize(final String topic, final Callback data) {
        return data.toByteArray();
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
}
