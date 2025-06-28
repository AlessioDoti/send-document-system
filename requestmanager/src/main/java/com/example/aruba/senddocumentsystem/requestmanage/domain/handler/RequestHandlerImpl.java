package com.example.aruba.senddocumentsystem.requestmanage.domain.handler;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanage.domain.exception.MessageDeliveryException;
import com.example.aruba.senddocumentsystem.requestmanage.domain.port.messagebroker.MessageProducer;
import com.example.aruba.senddocumentsystem.requestmanage.domain.port.nosql.RequestNoSqlService;
import com.example.aruba.senddocumentsystem.requestmanage.domain.port.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.requestmanage.domain.service.RequestService;
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
    private RequestService requestService;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private RequestNoSqlService requestNoSqlService;

    @Override
    @Transactional
    public RequestDTO insertRequest(RequestDTO dto) {
        try{
            MDC.put("username", dto.getUsername());
            MDC.put("uuid", dto.getTraceParent());

            log.info("Inserting Request");
            var returnDTO = requestService.insertRequest(dto);
            log.info("Receiversuccessfully inserted, all listeners will be notified");

            messageProducer.produceMessage(returnDTO);
            log.info("Insert Message sent Successfully");
            return returnDTO;
        } catch (MessageDeliveryException e){
            log.error("Cannot send insert tracking event, it will be persisted");
            requestNoSqlService.persistEvent(dto, e.getMessage());
            log.info("Insert Event Persisted Successfully");
            return dto;
        } finally {
            MDC.clear();
        }
    }
}
