package com.example.aruba.senddocumentsystem.deliverytracker.domain.port.nosql;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;

public interface DeliveryNoSqlService {

    void persistEvent(DeliveryDTO dto, String error);
}
