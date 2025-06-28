package com.example.aruba.senddocumentsystem.receivermanager.domain.ports.rest.impl;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.MessageDeliveryException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.messagebroker.MessageProducer;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.nosql.ReceiverNoSqlService;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.receivermanager.domain.service.ReceiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestHandlerImpl implements RequestHandler {

    @Autowired
    private ReceiverService receiverService;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ReceiverNoSqlService receiverNoSqlService;

    @Transactional
    @Override
    public ReceiverDTO insertReceiver(ReceiverDTO dto) {
        try{
            MDC.put("username", dto.getUsername());
            MDC.put("receiver", dto.getReceiverFiscalCode());

            log.info("Inserting Receiver: {}", dto.getReceiverFiscalCode());
            var returnDTO = receiverService.insertReceiver(dto);
            log.info("Receiver: {} successfully inserted, all listeners will be notified", dto.getReceiverFiscalCode());

            messageProducer.produceMessage(returnDTO);
            log.info("Insert Message sent Successfully");

            return returnDTO;
        } catch (MessageDeliveryException e) {
            log.error("Cannot send insert tracking event, it will be persisted");
            receiverNoSqlService.persistEvent(dto);
            log.info("Insert Event Persisted Successfully");

            return dto;
        }finally {
            MDC.clear();
        }
    }

    @Transactional
    @Override
    public ReceiverDTO updateReceiver(ReceiverDTO dto) {
        try{
            MDC.put("receiver", dto.getReceiverFiscalCode());

            log.info("Updating receiver: {}", dto.getReceiverFiscalCode());
            var returnDTO = receiverService.updateReceiver(dto);
            log.info("Receiver: {} successfully updated, all listeners will be notified", dto.getReceiverFiscalCode());

            messageProducer.produceMessage(returnDTO);
            log.info("Update Message sent Successfully");

            return returnDTO;
        } catch (MessageDeliveryException e) {
            log.error("Cannot send update tracking event, it will be persisted");
            receiverNoSqlService.persistEvent(dto);
            log.info("Update Event Persisted Successfully");

            return dto;
        }finally {
            MDC.clear();
        }
    }

    @Override
    public Page<ReceiverDTO> getUserReceivers(String username, Pageable pageable) {
        log.info("Getting Receivers for user: {}", username);
        return receiverService.findUserReceivers(username, pageable);
    }

    @Override
    public List<ReceiverDTO> getReceiversFromCodes(List<String> codes, String user) {
        log.info("Getting Receivers from codes: {}", codes);
        return receiverService.findReceiversFromCodes(codes, user);
    }
}
