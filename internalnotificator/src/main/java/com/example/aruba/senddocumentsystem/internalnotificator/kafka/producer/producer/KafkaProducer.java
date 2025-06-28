package com.example.aruba.senddocumentsystem.internalnotificator.kafka.producer.producer;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.exception.MessageDeliveryException;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.messagebroker.producer.MessageProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class KafkaProducer implements MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void produceMessage(NotificationDTO dto) {
        try{
            var header = buildHeader(dto);
            var message = buildMessage(dto);
            sendMessage(topic, header, message);
        }catch (Exception e){
            throw new MessageDeliveryException(e.getMessage());
        }
    }

    @SneakyThrows
    private void sendMessage(String topic, String header, String message){
        kafkaTemplate.send(topic, header, message).get();
    }

    @SneakyThrows
    private String buildHeader(NotificationDTO dto){
        var header = new HashMap<String, String>();
        header.put("traceParent", dto.getTraceParent());

        return objectMapper.writeValueAsString(header);
    }

    @SneakyThrows
    private String buildMessage(NotificationDTO dto){
        return objectMapper.writeValueAsString(dto);
    }
}
