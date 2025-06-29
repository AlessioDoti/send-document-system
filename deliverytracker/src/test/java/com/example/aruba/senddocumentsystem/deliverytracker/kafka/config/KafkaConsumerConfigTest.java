package com.example.aruba.senddocumentsystem.deliverytracker.kafka.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerConfigTest {

    private Long backOffInterval;

    private Long maxAttempts;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private ConsumerFactory<String, String> consumerFactory;

    @InjectMocks
    private KafkaConsumerConfig consumerConfig;

    @Test
    public void whenInvokeKafkaListenerContainerFactory_ShouldNotThrowException(){
        updateField(consumerConfig, "backOffInterval", 1L);
        updateField(consumerConfig, "maxAttempts", 1L);

        assertDoesNotThrow(() -> consumerConfig.kafkaListenerContainerFactory(consumerFactory));
    }

    private void updateField(Object target, String fieldName, Object val){
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, val);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
