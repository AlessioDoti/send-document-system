package com.example.aruba.senddocumentsystem.deliverytracker.domain.port.persistence;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;

public interface DeliveryPersistenceService {

    void persistDelivery(DeliveryDTO dto);
    DeliveryDTO findByUsernameAndTraceParent(String username, String traceParent);
}
