package com.example.aruba.senddocumentsystem.deliverytracker.domain.port.rest;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;

public interface RequestHandler {

    DeliveryDTO findByUsernameAndTraceParent(String username, String traceParent);

}
