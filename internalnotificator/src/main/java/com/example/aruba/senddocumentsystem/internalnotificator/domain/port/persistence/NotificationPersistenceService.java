package com.example.aruba.senddocumentsystem.internalnotificator.domain.port.persistence;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;

public interface NotificationPersistenceService {

    void persistNotification(NotificationDTO dto);
}
