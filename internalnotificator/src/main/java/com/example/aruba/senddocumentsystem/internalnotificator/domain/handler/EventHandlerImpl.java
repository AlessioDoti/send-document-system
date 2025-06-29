package com.example.aruba.senddocumentsystem.internalnotificator.domain.handler;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.exception.SkipMessageException;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.messagebroker.consumer.EventHandler;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.messagebroker.producer.MessageProducer;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.port.nosql.NotificationNoSqlService;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandlerImpl implements EventHandler {

    @Autowired
    private final NotificationService service;

    @Autowired
    private final MessageProducer producer;

    @Autowired
    private final NotificationNoSqlService noSqlService;

    @Override
    public void handleEvent(NotificationDTO dto) {
        try{
            MDC.put("traceParent", dto.getTraceParent());

            log.info("Inserting Notification for delivery: {}", dto.getTraceParent());
            service.persistNotification(dto);
            log.info("Notification Inserted Successfully");

            log.info("Sending Notification");
            producer.produceMessage(dto);
            log.info("Notification Sent");
        } catch (SkipMessageException ex){
            log.info("No message will be sent to listeners");
        } catch (Exception e) {
            log.error("Error occurred while processing the event event {}\n it will be persisted", dto.getTraceParent());
            noSqlService.persistEvent(dto, e.getMessage());
            log.info("Insert Event Persisted Successfully");
            throw new RuntimeException(e);
        } finally {
            MDC.clear();
        }
    }
}
