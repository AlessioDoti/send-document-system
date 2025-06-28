package com.example.aruba.senddocumentsystem.deliverytracker.domain.service;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.persistence.DeliveryPersistenceService;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliveryService extends ValidatingService<DeliveryDTO> {

    @Autowired
    private final DeliveryPersistenceService persistenceService;

    public DeliveryService(Validator validator, DeliveryPersistenceService persistenceService) {
        super(validator);
        this.persistenceService = persistenceService;
    }

    public DeliveryDTO persistDelivery(DeliveryDTO dto){
        validate(dto);
        return persistenceService.persistDelivery(dto);
    }

    public DeliveryDTO findByUsernameAndTraceParent(String username, String traceParent){
        return persistenceService.findByUsernameAndTraceParent(username, traceParent);
    }
}
