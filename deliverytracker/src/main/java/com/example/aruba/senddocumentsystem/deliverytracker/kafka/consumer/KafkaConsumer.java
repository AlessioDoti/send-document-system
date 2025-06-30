package com.example.aruba.senddocumentsystem.deliverytracker.kafka.consumer;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.messagebroker.EventHandler;
import com.example.aruba.senddocumentsystem.deliverytracker.kafka.factory.DeliveryDTOFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    @Autowired
    private final DeliveryDTOFactory factory;

    @Autowired
    private final EventHandler handler;

    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "kafka.consumer.group-id")
    public void consume(@Header(KafkaHeaders.RECEIVED_KEY) String key, String value) {
        log.info("Received message - Key: {}, Value: {}", key, value);
        var dto = factory.getDeliveryDTO(value);
        handler.handleEvent(dto);
    }

}
