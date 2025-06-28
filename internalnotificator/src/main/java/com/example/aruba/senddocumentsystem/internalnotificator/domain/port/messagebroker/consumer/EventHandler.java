package com.example.aruba.senddocumentsystem.internalnotificator.domain.port.messagebroker.consumer;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;

public interface EventHandler {

    void handleEvent(NotificationDTO dto);

}
