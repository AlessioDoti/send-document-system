package com.example.aruba.senddocumentsystem.internalnotificator.domain.port.messagebroker.producer;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;

public interface MessageProducer {

    void produceMessage(NotificationDTO receiverDTO);
}
