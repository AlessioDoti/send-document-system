package com.example.aruba.senddocumentsystem.deliverytracker.domain.handler;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestHandlerImpl implements RequestHandler {

    @Autowired
    private final DeliveryService service;

    @Override
    @Transactional
    public DeliveryDTO findByUsernameAndTraceParent(String username, String traceParent) {
        try{
            MDC.put("username", username);
            MDC.put("traceParent", traceParent);

            log.info("Getting Delivery {} status ", traceParent);
            return service.findByUsernameAndTraceParent(username, traceParent);
        } finally {
            MDC.clear();
        }

    }
}
