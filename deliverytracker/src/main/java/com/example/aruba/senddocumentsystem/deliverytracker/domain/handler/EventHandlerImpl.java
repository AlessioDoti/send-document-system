package com.example.aruba.senddocumentsystem.deliverytracker.domain.handler;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.messagebroker.EventHandler;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.nosql.DeliveryNoSqlService;
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
public class EventHandlerImpl implements EventHandler {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private DeliveryNoSqlService noSqlService;

    @Override
    @Transactional
    public void handleEvent(DeliveryDTO dto) {
        try{
            MDC.put("username", dto.getUsername());
            MDC.put("traceParent", dto.getTraceParent());

            log.info("Inserting Delivery: {}", dto.getTraceParent());
            deliveryService.persistDelivery(dto);
            log.info("Delivery: {} successfully inserted", dto.getTraceParent());

        } catch (Exception e) {
            log.error("Error occurred while processing the event event {}\n it will be persisted", dto.getTraceParent());
            noSqlService.persistEvent(dto, e.getMessage());
            log.info("Insert Event Persisted Successfully");
            throw new RuntimeException(e);
        }finally {
            MDC.clear();
        }

    }

}
