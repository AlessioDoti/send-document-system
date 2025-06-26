package com.example.aruba.senddocumentsystem.receivermanager.domain.ports.rest.impl;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.receivermanager.domain.service.ReceiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestHandlerImpl implements RequestHandler {

    @Autowired
    ReceiverService receiverService;

    @Transactional
    @Override
    public ReceiverDTO insertReceiver(ReceiverDTO dto) {
        //todo use MDC
        //todo stampa esito
        log.info("Inserting Receiver: {}", dto.getReceiverFiscalCode());
        return receiverService.insertReceiver(dto);
        //todo kafka produce
    }

    @Transactional
    @Override
    public ReceiverDTO updateReceiver(ReceiverDTO dto) {
        //todo use MDC
        //todo stampa esito
        log.info("Updating receiver: {}", dto.getReceiverFiscalCode());
        return receiverService.updateReceiver(dto);
        //todo kafka
    }

    @Override
    public Page<ReceiverDTO> getUserReceivers(String username, Pageable pageable) {
        log.info("Getting Receivers for user: {}", username);
        return receiverService.findUserReceivers(username, pageable);
    }
}
