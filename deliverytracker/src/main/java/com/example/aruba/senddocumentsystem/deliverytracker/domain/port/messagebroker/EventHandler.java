package com.example.aruba.senddocumentsystem.deliverytracker.domain.port.messagebroker;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;

public interface EventHandler {

    DeliveryDTO handleEvent(DeliveryDTO dto);

}
