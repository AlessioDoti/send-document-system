package com.example.aruba.senddocumentsystem.requestmanage.kafka.producer;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanage.domain.exception.MessageDeliveryException;
import com.example.aruba.senddocumentsystem.requestmanage.domain.ports.messagebroker.MessageProducer;
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
    public void produceMessage(RequestDTO dto) {
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
    private String buildHeader(RequestDTO dto){
        var header = new HashMap<String, String>();
        header.put("uuid", dto.getUuid());
        header.put("username", dto.getUsername());

        return objectMapper.writeValueAsString(header);
    }

    @SneakyThrows
    private String buildMessage(RequestDTO dto){
        return objectMapper.writeValueAsString(dto);
    }
}
