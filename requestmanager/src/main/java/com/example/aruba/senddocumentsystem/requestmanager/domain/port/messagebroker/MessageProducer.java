package com.example.aruba.senddocumentsystem.requestmanager.domain.port.messagebroker;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;

public interface MessageProducer {

    void produceMessage(RequestDTO dto);
}
