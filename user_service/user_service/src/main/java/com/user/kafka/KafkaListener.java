package com.user.kafka;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "consumerTopic",groupId = "GRP1")
    void listener(String data){
        System.out.println( "Listener Received Message-> " + data + ":)");
    }
}
