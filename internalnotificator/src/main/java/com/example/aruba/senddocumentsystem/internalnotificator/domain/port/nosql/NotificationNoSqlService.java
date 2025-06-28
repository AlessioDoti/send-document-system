package com.example.aruba.senddocumentsystem.internalnotificator.domain.port.nosql;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;

public interface NotificationNoSqlService {

    void persistEvent(NotificationDTO dto, String error);
}
