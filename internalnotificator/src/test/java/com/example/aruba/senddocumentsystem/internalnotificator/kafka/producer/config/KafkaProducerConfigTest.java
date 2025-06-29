package com.example.aruba.senddocumentsystem.internalnotificator.kafka.producer.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerConfigTest {

    @InjectMocks
    private KafkaProducerConfig consumerConfig;

    @Test
    public void whenInvokeKafkaListenerContainerFactory_ShouldNotThrowException(){
        updateField(consumerConfig, "bootstrapAddress", "address");

        assertDoesNotThrow(() -> consumerConfig.producerFactory());
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
